package inc.fabudi.musicapp.repository

import inc.fabudi.musicapp.model.Explore
import inc.fabudi.musicapp.model.Playlist
import inc.fabudi.musicapp.model.Trending
import inc.fabudi.musicapp.network.MusicApiService
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class MusicRepository @Inject constructor(private val apiService: MusicApiService) {
    fun getMyFavourites() = apiService.getMyFavourites()

    fun getTrending(): Observable<Trending> = apiService.getTrending()

    fun getCategories() = apiService.getCategories()

    fun getExplore(userId: Int): Observable<Explore> = apiService.getExplore(userId)

    fun getPlaylist(albumId: Int): Observable<Playlist> = apiService.getPlaylist(albumId)

    fun setFavourite(trackId: Int, isFavourite: Boolean) =
        apiService.setFavourite(trackId, isFavourite)

    fun getTrack(trackId: Int) = apiService.getTrack(trackId)

    fun getRecentlyPlayed(userId: Int) = apiService.getRecentlyPlayed(userId)

    fun getRecommended(userId: Int) = apiService.getRecommended(userId)
}