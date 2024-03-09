package inc.fabudi.musicapp.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import inc.fabudi.musicapp.ui.components.FBottomAppBar
import inc.fabudi.musicapp.ui.navigation.NavigationGraph

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { FBottomAppBar(navController = navController) }
    ) {
        Box(modifier = Modifier.fillMaxSize().padding(it)){
            NavigationGraph(navController = navController)
        }
    }
}