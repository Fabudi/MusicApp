package inc.fabudi.musicapp.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import inc.fabudi.musicapp.R
import inc.fabudi.musicapp.ui.components.FBottomAppBar
import inc.fabudi.musicapp.ui.components.PlayBar
import inc.fabudi.musicapp.ui.navigation.NavigationGraph
import inc.fabudi.musicapp.viewmodel.MusicViewModel
import kotlinx.coroutines.launch

@Composable
fun MainScreen(viewModel: MusicViewModel) {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                currentlyPlayingState.value?.let {
                    PlayBar(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp), onClick = {
                            openBottomSheet = true
                            scope.launch { modalSheetState.show() }
                        },
                        viewModel = viewModel
                    )
                }
                FBottomAppBar(navController = navController, viewModel = viewModel)
            }
        }
    ) {
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(it)) {
            NavigationGraph(navController = navController)
        }
    }
}