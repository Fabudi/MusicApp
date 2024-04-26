package inc.fabudi.musicapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import inc.fabudi.musicapp.ui.components.PlaylistCardWithDesc
import inc.fabudi.musicapp.ui.components.PlaylistCardWithName
import inc.fabudi.musicapp.ui.theme.Typography
import inc.fabudi.musicapp.viewmodel.MusicViewModel

@Composable
fun ExploreScreen(navController: NavHostController, viewmodel: MusicViewModel) {
    LaunchedEffect(Unit) {
        viewmodel.getExplore(2) // TODO Replace with actual ID
    }
    val exploreItemsList = viewmodel.exploreItems.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
            .padding(top = 16.dp)
    ) {
        Text(
            text = "Explore",
            style = Typography.titleLarge,
            modifier = Modifier.padding(start = 16.dp)
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 16.dp)
        ) {

            items(exploreItemsList.value) { exploreItem ->
                Text(
                    text = exploreItem.title,
                    style = Typography.titleMedium,
                    modifier = Modifier
                        .padding(bottom = 8.dp, start = 16.dp)
                )
                LazyRow {
                    if (exploreItem.type == "Playlist")
                        items(exploreItem.playlists) { playlist ->
                            PlaylistCardWithName(
                                title = playlist.title,
                                artworkUrl = playlist.artworkUrl,
                                onClick = { navController.navigate("Explore/Album/${playlist.id}") })
                        }
                    if (exploreItem.type == "Single")
                        items(exploreItem.playlists) { playlist ->
                            PlaylistCardWithDesc(
                                title = playlist.title,
                                artworkUrl = playlist.artworkUrl,
                                author = playlist.authors.joinToString(", ") { it.nickname },
                                onClick = { navController.navigate("Explore/Album/${playlist.id}") })
                        }
                    if (exploreItem.type == "Album")
                        items(exploreItem.playlists) { playlist ->
                            PlaylistCardWithName(
                                title = playlist.title,
                                artworkUrl = playlist.artworkUrl,
                                onClick = { navController.navigate("Explore/Album/${playlist.id}") })
                        }
                }
            }
        }
    }
}

@Preview
@Composable
fun ExploreScreenPreview() {
    ExploreScreen(navController = rememberNavController(), viewmodel = hiltViewModel())
}