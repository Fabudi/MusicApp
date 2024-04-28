package inc.fabudi.musicapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import inc.fabudi.musicapp.R
import inc.fabudi.musicapp.Utils.toCommaString
import inc.fabudi.musicapp.ui.components.AlbumTrackCard
import inc.fabudi.musicapp.ui.components.CollapsingTopAppBar
import inc.fabudi.musicapp.viewmodel.MusicViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Album(navController: NavHostController, albumId: Int, viewModel: MusicViewModel) {
    LaunchedEffect(Unit) {
        viewModel.getPlaylist(albumId)
    }
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    if (viewModel.playlist.value != null)
        Scaffold(topBar = {
            CollapsingTopAppBar(
                modifier = Modifier.heightIn(min = 56.dp),
                titleText = viewModel.playlist.value?.title ?: "Placeholder",
                smallTitleText = viewModel.playlist.value?.type ?: "NULL",
                navigationIcon = {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "",
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                },
                navigationButtonOnClick = { navController.popBackStack() },
                actions = {
                    val playlistState = viewModel.playlist.observeAsState()
                    val isPlayingState = viewModel.player.isPlaying.collectAsState()
                    IconButton(onClick = {
                        if ((playlistState.value?.id == viewModel.player.playlist.value?.id))
                            viewModel.player.play()
                        else viewModel.player.playPlaylist(viewModel.playlist.value!!)
                    }, content = {
                        Icon(
                            painter = painterResource(
                                if (isPlayingState.value && (playlistState.value?.id == viewModel.player.playlist.value?.id))
                                    R.drawable.baseline_pause_24
                                else R.drawable.baseline_play_arrow_24
                            ),
                            contentDescription = "",
                            tint = MaterialTheme.colorScheme.background,
                            modifier = Modifier
                                .height(64.dp)
                                .aspectRatio(1 / 1f)
                                .clip(CircleShape)
                                .background(MaterialTheme.colorScheme.primary)
                                .padding(8.dp)
                        )
                    })
                },
                artworkUrl = viewModel.playlist.value?.artworkUrl ?: "",
                scrollBehavior = scrollBehavior
            )
        }, modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)) {
            LazyColumn(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize(),
                contentPadding = PaddingValues(top = 32.dp)
            ) {
                itemsIndexed(viewModel.playlist.value!!.tracks) { index, track ->
                    AlbumTrackCard(
                        title = track.title,
                        authors = track.authors.toCommaString(),
                        place = index + 1
                    ) {
                        viewModel.player.playPlaylist(viewModel.playlist.value!!, index)
                    }
                }
            }

        }
}

@Preview
@Composable
fun AlbumPreview() {
    Album(rememberNavController(), 1234, hiltViewModel())
}