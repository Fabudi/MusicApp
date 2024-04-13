package inc.fabudi.musicapp.repository

import inc.fabudi.musicapp.model.Trending
import inc.fabudi.musicapp.network.MusicApiService
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class MusicRepository @Inject constructor(private val apiService: MusicApiService) {
    fun getMyFavourites() = apiService.getMyFavourites()

    fun getTrending(): Observable<Trending> = apiService.getTrending()

    fun getCategories() = apiService.getCategories()

    fun setFavourite(id: Int, isFavourite: Boolean) = apiService.setFavourite(id, isFavourite)

    fun getTrack(id: Int) = apiService.getTrack(id)

    fun getRecentlyPlayed(id: Int) = apiService.getRecentlyPlayed(id)

    fun getRecommended(id: Int) = apiService.getRecommended(id)
}