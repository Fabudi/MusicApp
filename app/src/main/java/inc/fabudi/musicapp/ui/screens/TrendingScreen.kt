package inc.fabudi.musicapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import inc.fabudi.musicapp.Utils.toCommaString
import inc.fabudi.musicapp.ui.components.GenreChipButton
import inc.fabudi.musicapp.ui.components.TrendingTrackCard
import inc.fabudi.musicapp.ui.theme.Typography
import inc.fabudi.musicapp.viewmodel.MusicViewModel

@Composable
fun TrendingScreen(navController: NavHostController, viewmodel: MusicViewModel) {
    LaunchedEffect(Unit) {
        viewmodel.getCategories()
        viewmodel.getTrending()
    }
    val genresList = viewmodel.categories.collectAsState()
    val trendingTracksList = viewmodel.trendingTracks.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
            .padding(top = 16.dp)
    ) {
        Text(
            text = "Trending",
            style = Typography.titleLarge,
            modifier = Modifier.padding(start = 16.dp)
        )
        var selectedGenre by remember { mutableIntStateOf(viewmodel.selectedGenre) }
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(start = 16.dp),
            modifier = Modifier.padding(top = 16.dp)
        ) {
            item {
                val selected = selectedGenre == 0
                GenreChipButton(
                    title = "All",
                    textColor = {
                        if (selected)
                            MaterialTheme.colorScheme.onPrimary
                        else MaterialTheme.colorScheme.onBackground
                    },
                    backgroundColor = {
                        if (selected)
                            MaterialTheme.colorScheme.primary
                        else Color.LightGray
                    }) {
                    viewmodel.selectedGenre = 0
                    selectedGenre = 0
                    viewmodel.getTrending()
                }
            }
            itemsIndexed(genresList.value.map { it.name }.toList()) { id, item ->
                val selected = selectedGenre == genresList.value[id].id
                GenreChipButton(
                    title = item,
                    textColor = {
                        if (selected)
                            MaterialTheme.colorScheme.onPrimary
                        else MaterialTheme.colorScheme.onBackground
                    },
                    backgroundColor = {
                        if (selected)
                            MaterialTheme.colorScheme.primary
                        else Color.LightGray
                    }) {
                    viewmodel.selectedGenre =  genresList.value[id].id
                    selectedGenre = genresList.value[id].id
                    viewmodel.getTrending()
                }
            }
        }
        Text(
            text = "Top trending", style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            ), modifier = Modifier.padding(bottom = 8.dp, start = 16.dp, top = 16.dp)
        )
        LazyColumn(
            modifier = Modifier.padding(start = 16.dp, end = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            itemsIndexed(trendingTracksList.value) { index, track ->
                TrendingTrackCard(
                    onClick = {},
                    artworkUrl = track.artworkUrl,
                    title = track.title,
                    author = track.authors.toCommaString(),
                    playsCount = track.playCount,
                    place = index + 1
                )
            }
        }
    }
}

@Preview
@Composable
fun TrendingScreenPreview() {
    TrendingScreen(navController = rememberNavController(), viewmodel = hiltViewModel())
}