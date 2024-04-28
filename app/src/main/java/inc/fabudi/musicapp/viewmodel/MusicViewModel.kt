package inc.fabudi.musicapp.viewmodel

import android.app.Notification
import android.app.PendingIntent
import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.annotation.OptIn
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.media3.common.AudioAttributes
import androidx.media3.common.C
import androidx.media3.common.MediaItem
import androidx.media3.common.MediaMetadata
import androidx.media3.common.PlaybackException
import androidx.media3.common.util.UnstableApi
import androidx.media3.datasource.DefaultHttpDataSource
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.MediaSource
import androidx.media3.exoplayer.source.ProgressiveMediaSource
import androidx.media3.session.MediaSession
import androidx.media3.ui.PlayerNotificationManager
import dagger.hilt.android.lifecycle.HiltViewModel
import inc.fabudi.musicapp.NotificationManager
import inc.fabudi.musicapp.Utils
import inc.fabudi.musicapp.Utils.toCommaString
import inc.fabudi.musicapp.model.Category
import inc.fabudi.musicapp.model.Explore
import inc.fabudi.musicapp.model.ExploreItem
import inc.fabudi.musicapp.model.Playlist
import inc.fabudi.musicapp.model.Track
import inc.fabudi.musicapp.model.Trending
import inc.fabudi.musicapp.repository.MusicRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject


@HiltViewModel
class MusicViewModel @Inject constructor(
    private val repository: MusicRepository, private val exoPlayer: ExoPlayer
) : ViewModel() {

    private var _trendingTracks: MutableStateFlow<List<Track>> = MutableStateFlow(emptyList())
    private var _categories: MutableStateFlow<List<Category>> = MutableStateFlow(emptyList())
    private var _exploreItems: MutableStateFlow<List<ExploreItem>> = MutableStateFlow(emptyList())
    private var _playlist: MutableLiveData<Playlist> = MutableLiveData<Playlist>()
    var player = Player(exoPlayer = exoPlayer)
    var selectedGenre: Int = 0
        set(value) {
            field = value
            if (value != 0) {
                _categories.value = _categories.value.toMutableList().apply {
                    val selectedGenre =
                        _categories.value.find { it.id == value } ?: _categories.value[0]
                    remove(selectedGenre)
                    add(0, selectedGenre)
                }
            } else {
                _categories.value = _categories.value.sortedBy { it.name }
            }

        }
    val trendingTracks
        get() = _trendingTracks
    val categories
        get() = _categories
    val exploreItems
        get() = _exploreItems
    val playlist: LiveData<Playlist> = _playlist

    fun getTrending() = repository.getTrending(selectedGenre)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe(this::handleTrending)

    fun getCategories() = repository.getCategories()
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe(this::handleCategories)

    fun getExplore(userId: Int) = repository.getExplore(userId = userId)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe({ handleExplore(it) }, { Log.e("Retrofit", it.message.toString()) })

    fun getPlaylist(playlistId: Int) = repository.getPlaylist(albumId = playlistId)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe({ handlePlaylist(it) }, { Log.e("Retrofit", it.message.toString()) })

    private fun handleTrending(trending: Trending) {
        _trendingTracks.value = trending.tracks
    }

    private fun handleCategories(categories: List<Category>) {
        _categories.value = categories.sortedBy { it.name }
    }

    private fun handleExplore(explore: Explore) {
        _exploreItems.value = explore.items
    }

    private fun handlePlaylist(playlist: Playlist) {
        _playlist.value = playlist
    }

    class Player(private val exoPlayer: ExoPlayer) {

        init {
            Log.d("MusicViewModel.Player", "Init: Some init things")
        }

        var playlist: MutableLiveData<Playlist> = MutableLiveData<Playlist>()
        private var _isPlaying = MutableStateFlow(false)
        val isPlaying: StateFlow<Boolean> = _isPlaying
        var isShuffled = mutableStateOf(false)
        var repeatState = mutableStateOf(RepeatState.NONE)
        var currentlyPlaying = MutableLiveData<Track>()
        var currentPosition = MutableLiveData((exoPlayer.currentPosition / 1000).toInt())
        private var isStarted = mutableStateOf(false)
        private lateinit var notificationManager: NotificationManager
        private lateinit var mediaSession: MediaSession

        @OptIn(UnstableApi::class)
        fun setup(context: Context) {
            if (isStarted.value) return
            isStarted.value = true
            val sessionActivityPendingIntent =
                context.packageManager?.getLaunchIntentForPackage(context.packageName)
                    ?.let { sessionIntent ->
                        PendingIntent.getActivity(
                            context,
                            SESSION_INTENT_REQUEST_CODE,
                            sessionIntent,
                            PendingIntent.FLAG_IMMUTABLE
                        )
                    }
            mediaSession = MediaSession.Builder(context, exoPlayer)
                .setSessionActivity(sessionActivityPendingIntent!!).build()
            notificationManager = NotificationManager(
                context, mediaSession.token, exoPlayer, PlayerNotificationListener()
            )
            notificationManager.showNotificationForPlayer(exoPlayer)
        }

        fun preparePlayer() {
            val audioAttributes = AudioAttributes.Builder().setUsage(C.USAGE_MEDIA)
                .setContentType(C.AUDIO_CONTENT_TYPE_MUSIC).build()
            exoPlayer.setAudioAttributes(audioAttributes, true)
            exoPlayer.repeatMode = repeatState.value.repeatState
            exoPlayer.addListener(playerListener)
        }

        @OptIn(UnstableApi::class)
        private fun setupPlaylist() {
            val audioItems: ArrayList<MediaSource> = arrayListOf()
            playlist.value!!.tracks.forEach {
                val mediaMetaData = MediaMetadata.Builder()
                    .setArtworkUri(
                        Uri.parse(it.artworkUrl)
                    )
                    .setTitle(it.title)
                    .setAlbumArtist(it.authors.toCommaString())
                    .build()
                val trackUri = Uri.parse(it.streamUrl)
                val mediaItem = MediaItem.Builder()
                    .setUri(trackUri)
                    .setMediaId(it.id.toString())
                    .setMediaMetadata(mediaMetaData)
                    .build()
                val dataSourceFactory =
                    DefaultHttpDataSource.Factory().setUserAgent(Utils.userAgent)
                val mediaSource =
                    ProgressiveMediaSource.Factory(dataSourceFactory).createMediaSource(mediaItem)
                audioItems.add(mediaSource)
            }
            exoPlayer.playWhenReady = false
            exoPlayer.setMediaSources(audioItems)
            exoPlayer.prepare()
        }


        fun playPlaylist(album: Playlist, index: Int = 0) {
            if (album.id == playlist.value?.id)
                return playSelectedTrack(index)
            playlist.value = album
            currentlyPlaying.postValue(playlist.value!!.tracks[index])
            resetCurrentPosition()
            setupPlaylist()
            play()
        }

        private fun playSelectedTrack(index: Int) {
            if (exoPlayer.currentMediaItemIndex == index) return
            exoPlayer.seekTo(index, 0)
            currentlyPlaying.postValue(playlist.value!!.tracks[index])
        }

        private fun resetCurrentPosition() = run { currentPosition.value = 0 }

        fun nextTrack() {
            val index = playlist.value!!.tracks.indexOf(currentlyPlaying.value)
            currentlyPlaying.value =
                playlist.value!!.tracks[(index + 1) % (playlist.value?.tracks?.size ?: return)]
            resetCurrentPosition()
            exoPlayer.seekToNext()
        }

        fun previousTrack() {
            val index = playlist.value!!.tracks.indexOf(currentlyPlaying.value)
            currentlyPlaying.value =
                playlist.value!!.tracks[if (index == 0) (playlist.value?.tracks?.size
                    ?: return) - 1 else index - 1]
            resetCurrentPosition()
            exoPlayer.seekToPrevious()
        }

        fun seekTo(position: Int) {
            exoPlayer.seekTo((position * 1000).toLong())
            currentPosition.value = position
        }

        @OptIn(UnstableApi::class)
        fun shuffle() {
            isShuffled.value = !isShuffled.value
            exoPlayer.shuffleModeEnabled = isShuffled.value
        }

        fun repeat() {
            repeatState.value = when (repeatState.value) {
                RepeatState.NONE -> RepeatState.ALL
                RepeatState.ALL -> RepeatState.SINGLE
                else -> RepeatState.NONE
            }
            exoPlayer.repeatMode = repeatState.value.repeatState
        }

        fun play() {
            if (isPlaying.value) exoPlayer.pause() else exoPlayer.play()
        }

        @UnstableApi
        private inner class PlayerNotificationListener :
            PlayerNotificationManager.NotificationListener {
            override fun onNotificationPosted(
                notificationId: Int, notification: Notification, ongoing: Boolean
            ) {

            }

            override fun onNotificationCancelled(notificationId: Int, dismissedByUser: Boolean) {

            }
        }


        private val playerListener = object : androidx.media3.common.Player.Listener {

            override fun onPlaybackStateChanged(playbackState: Int) {
                Log.d("MusicPlayer", "onPlaybackStateChanged: $playbackState")
                super.onPlaybackStateChanged(playbackState)
                syncPlayerFlows()
                when (playbackState) {
                    androidx.media3.common.Player.STATE_BUFFERING, androidx.media3.common.Player.STATE_READY -> {
                        notificationManager.showNotificationForPlayer(exoPlayer)
                    }

                    else -> {
                        notificationManager.hideNotification()
                    }
                }
            }

            override fun onMediaItemTransition(mediaItem: MediaItem?, reason: Int) {
                Log.d("MusicPlayer", "onMediaItemTransition: ${mediaItem?.mediaMetadata?.title}")
                super.onMediaItemTransition(mediaItem, reason)
                syncPlayerFlows()
            }

            override fun onIsPlayingChanged(isPlaying: Boolean) {
                Log.d("MusicPlayer", "onIsPlayingChanged: $isPlaying")
                super.onIsPlayingChanged(isPlaying)
                this@Player._isPlaying.value = isPlaying
            }

            override fun onPlayerError(error: PlaybackException) {
                super.onPlayerError(error)
                Log.e("MusicPlayer", "Error: ${error.message}")
            }
        }

        private fun syncPlayerFlows() {
            currentlyPlaying.postValue(
                playlist.value!!.tracks[exoPlayer.currentMediaItemIndex.coerceAtMost(
                    playlist.value?.tracks?.size ?: 0
                )]
            )
        }

        enum class RepeatState(val repeatState: Int) {
            NONE(repeatState = androidx.media3.common.Player.REPEAT_MODE_OFF), SINGLE(repeatState = androidx.media3.common.Player.REPEAT_MODE_ONE), ALL(
                repeatState = androidx.media3.common.Player.REPEAT_MODE_ALL
            )
        }

        fun onDestroy() {
            onClose()
            exoPlayer.release()
        }

        private fun onClose() {
            if (!isStarted.value) return
            isStarted.value = false
            mediaSession.run {
                release()
            }
            notificationManager.hideNotification()
            exoPlayer.removeListener(playerListener)
        }

        fun updatePosition() {
            currentPosition.value = (exoPlayer.currentPosition / 1000).toInt()
        }

        companion object {
            const val SESSION_INTENT_REQUEST_CODE = 0
        }

    }

}