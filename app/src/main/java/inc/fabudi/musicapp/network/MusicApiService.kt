package inc.fabudi.musicapp.network

import inc.fabudi.musicapp.model.Category
import inc.fabudi.musicapp.model.Explore
import inc.fabudi.musicapp.model.Favourites
import inc.fabudi.musicapp.model.Playlist
import inc.fabudi.musicapp.model.Playlists
import inc.fabudi.musicapp.model.Track
import inc.fabudi.musicapp.model.Trending
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface MusicApiService {

    @GET("favourites.php")
    fun getMyFavourites(): Observable<Favourites>

    @GET("trending.php")
    fun getTrending(@Query("genreId") genreId: Int): Observable<Trending>

    @GET("genres.php")
    fun getCategories(): Observable<List<Category>>

    @POST("track.php")
    fun setFavourite(
        @Query("trackId") trackId: Int,
        @Query("setFavourite") isFavourite: Boolean
    ): Boolean

    @GET("track.php")
    fun getTrack(@Query("trackId") trackId: Int): Observable<Track>

    @GET("recentlyPlayed.php")
    fun getRecentlyPlayed(@Query("userId") userId: Int): Observable<Playlists>

    @GET("recommended.php")
    fun getRecommended(@Query("userId") userId: Int): Observable<Playlists>

    @GET("explore.php")
    fun getExplore(@Query("userId") userId: Int): Observable<Explore>

    @GET("playlist.php")
    fun getPlaylist(@Query("id") albumId: Int): Observable<Playlist>

}