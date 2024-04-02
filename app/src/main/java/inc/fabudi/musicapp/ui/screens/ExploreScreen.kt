package inc.fabudi.musicapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import inc.fabudi.musicapp.ui.components.PlaylistCardWithDesc
import inc.fabudi.musicapp.ui.components.PlaylistCardWithName
import inc.fabudi.musicapp.ui.components.PlaylistCardWithNameFourImages
import inc.fabudi.musicapp.ui.theme.Typography

@Composable
fun ExploreScreen(navController: NavHostController) {
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
            item {
                Text(
                    text = "Recently played",
                    style = Typography.titleMedium,
                    modifier = Modifier
                        .padding(bottom = 8.dp, start = 16.dp)
                )
                LazyRow {
                    items(8) {
                        PlaylistCardWithName(onClick = { navController.navigate("Album/$it") })
                    }
                }
            }
            item {
                Text(
                    text = "Recommended for you",
                    style = Typography.titleMedium,
                    modifier = Modifier.padding(bottom = 8.dp, start = 16.dp)
                )
                LazyRow {
                    items(8) {
                        PlaylistCardWithDesc(
                            modifier = Modifier.height(152.dp),
                            onClick = { navController.navigate("Album/$it") })
                    }
                }
            }
            item {
                Text(
                    text = "New Releases",
                    style = Typography.titleMedium,
                    modifier = Modifier.padding(bottom = 8.dp, start = 16.dp)
                )
                LazyRow {
                    items(4) {
                        PlaylistCardWithDesc(
                            modifier = Modifier.height(152.dp),
                            onClick = { navController.navigate("Album/$it") })
                    }
                }
            }
            item {
                Text(
                    text = "Your top mixes",
                    style = Typography.titleMedium,
                    modifier = Modifier.padding(bottom = 8.dp, start = 16.dp)
                )
                LazyRow {
                    items(8) {
                        PlaylistCardWithNameFourImages(onClick = { navController.navigate("Album/$it") })
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun ExploreScreenPreview() {
    ExploreScreen(rememberNavController())
}