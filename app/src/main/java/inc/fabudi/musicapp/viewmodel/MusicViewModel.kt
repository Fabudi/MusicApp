package inc.fabudi.musicapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import inc.fabudi.musicapp.model.Track
import inc.fabudi.musicapp.model.Trending
import inc.fabudi.musicapp.repository.MusicRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject


@HiltViewModel
class MusicViewModel @Inject constructor(private val repository: MusicRepository) : ViewModel() {

    private var _trendingTracks: MutableLiveData<List<Track>> = MutableLiveData(emptyList())
    val trendingTracks
        get() = _trendingTracks.value ?: emptyList()

    fun getTrending() = repository.getTrending()
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe(this::handleTrending)

    private fun handleTrending(trending: Trending) {
        _trendingTracks.value = trending.tracks
    }

}