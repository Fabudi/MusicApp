package inc.fabudi.musicapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import inc.fabudi.musicapp.ui.components.TrendingTrackCard
import inc.fabudi.musicapp.ui.theme.Typography
import inc.fabudi.musicapp.viewmodel.MusicViewModel

@Composable
fun TrendingScreen(navController: NavHostController, viewmodel: MusicViewModel) {
    LaunchedEffect(Unit) {
        viewmodel.getTrending()
        viewmodel.getCategories()
    }
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

        var selectedCategory by remember { mutableStateOf(viewmodel.categories[0]) }
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(start = 16.dp),
            modifier = Modifier.padding(top = 16.dp)
        ) {
            items(viewmodel.categories.size) {
                Text(
                    text = viewmodel.categories[it].name,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp,
                    color = if (selectedCategory == viewmodel.categories[it]) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier
                        .clip(
                            RoundedCornerShape(20)
                        )
                        .clickable {
                            selectedCategory = viewmodel.categories[it]
                        }
                        .background(if (selectedCategory == viewmodel.categories[it]) MaterialTheme.colorScheme.primary else Color.LightGray)
                        .padding(start = 24.dp, top = 16.dp, end = 24.dp, bottom = 16.dp),
                )
            }
        }
        Text(
            text = "Top trending", style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            ), modifier = Modifier.padding(bottom = 8.dp, start = 16.dp, top = 16.dp)
        )
        if (viewmodel.trendingTracks.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier.padding(start = 16.dp, end = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                itemsIndexed(viewmodel.trendingTracks) { index, track ->
                    TrendingTrackCard(
                        onClick = {},
                        artworkUrl = track.artworkUrl,
                        title = track.title,
                        author = track.authors.joinToString(", ") { it.nickname },
                        playsCount = track.playCount,
                        place = index+1
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun TrendingScreenPreview() {
    TrendingScreen(navController = rememberNavController(), viewmodel = hiltViewModel())
}