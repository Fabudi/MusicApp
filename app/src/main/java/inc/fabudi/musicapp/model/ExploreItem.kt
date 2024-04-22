package inc.fabudi.musicapp.model

data class ExploreItem(
    val title: String,
    val type: String,
    val playlists: List<Playlist>
)
