package inc.fabudi.musicapp.model

data class Track(
    val id: Int,
    val artworkUrl: String,
    val duration: Int,
    val releaseDay: Int,
    val releaseMonth: Int,
    val releaseYear: Int,
    val streamUrl: String,
    val title: String,
    val authors: List<User>,
    val userFavorite: Boolean,
    val playCount: Int
)