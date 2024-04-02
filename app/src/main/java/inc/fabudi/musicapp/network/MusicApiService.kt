package inc.fabudi.musicapp.network

import inc.fabudi.musicapp.model.Favourites
import inc.fabudi.musicapp.model.Playlists
import inc.fabudi.musicapp.model.Track
import inc.fabudi.musicapp.model.Trending
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface MusicApiService {

    @GET("favourites.php")
    fun getMyFavourites(): Observable<Favourites>

    @GET("trending.php")
    fun getTrending(): Observable<Trending>

    @POST("track.php?trackId={trackId}&setFavourite={isFavourite}")
    fun setFavourite(@Path("trackId") id: Int, isFavourite: Boolean): Boolean

    @GET("track.php?trackId={trackId}")
    fun getTrack(@Path("trackId") id: Int): Observable<Track>

    @GET("recentlyPlayed.php?userId={userId}")
    fun getRecentlyPlayed(@Path("userId") id: Int): Observable<Playlists>

    @GET("recommended.php?userId={userId}")
    fun getRecommended(@Path("userId") id: Int): Observable<Playlists>
}