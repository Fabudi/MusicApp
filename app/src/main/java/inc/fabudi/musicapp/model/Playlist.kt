package inc.fabudi.musicapp.model

data class Playlist(
    val id: Int,
    val artworkUrl: String,
    val title: String,
    val trackCount: Int,
    val tracks: List<Track>,
    val user: User,
    val userId: Int
)