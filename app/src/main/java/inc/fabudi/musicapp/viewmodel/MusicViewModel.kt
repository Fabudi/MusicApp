package inc.fabudi.musicapp.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
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
import javax.inject.Inject


@HiltViewModel
class MusicViewModel @Inject constructor(private val repository: MusicRepository) : ViewModel() {

    private var _trendingTracks: MutableStateFlow<List<Track>> = MutableStateFlow(emptyList())
    private var _categories: MutableStateFlow<List<Category>> = MutableStateFlow(emptyList())
    private var _exploreItems: MutableStateFlow<List<ExploreItem>> = MutableStateFlow(emptyList())
    private var _playlist: MutableLiveData<Playlist> = MutableLiveData<Playlist>()
    var player = Player()
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
    val playlist
        get() = _playlist.value

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

    class Player {

        init {
            Log.d("MusicViewModel.Player", "Init: Some init things")
        }

        var playlist: MutableLiveData<Playlist> = MutableLiveData<Playlist>()
        var isPlaying = mutableStateOf(false)
        var isShuffled = mutableStateOf(false)
        var repeatState = mutableStateOf(RepeatState.NONE)
        var currentlyPlaying = MutableLiveData<Track>()
        var currentPosition = MutableLiveData<Int>()
        enum class RepeatState {
            NONE, SINGLE, ALL
        }

    }

}