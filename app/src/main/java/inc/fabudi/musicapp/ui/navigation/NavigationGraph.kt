package inc.fabudi.musicapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import inc.fabudi.musicapp.ui.screens.Album
import inc.fabudi.musicapp.ui.screens.ExploreScreen
import inc.fabudi.musicapp.ui.screens.LibraryScreen
import inc.fabudi.musicapp.ui.screens.Player
import inc.fabudi.musicapp.ui.screens.SearchScreen
import inc.fabudi.musicapp.ui.screens.SettingsScreen
import inc.fabudi.musicapp.ui.screens.TrendingScreen

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController, startDestination = BottomNavItem.Explore.screenRoute) {
        composable(route = BottomNavItem.Explore.screenRoute) {
            ExploreScreen(navController)
        }
        composable(BottomNavItem.Trending.screenRoute) {
            TrendingScreen(navController, hiltViewModel())
        }
        composable(BottomNavItem.Search.screenRoute) {
            SearchScreen(navController)
        }
        composable(BottomNavItem.Library.screenRoute) {
            LibraryScreen(navController)
        }
        composable(BottomNavItem.Settings.screenRoute) {
            SettingsScreen(navController)
        }
        composable("Player") {
            Player(navController)
        }
        composable(
            "Artist/{artistId}",
            arguments = listOf(navArgument("artistId") { type = NavType.StringType })
        ) {
            Player(navController)
        }
        composable("Album/{albumId}",
            arguments = listOf(navArgument("albumId") { type = NavType.StringType })
        ) {
                backStackEntry ->
            Album(navController, backStackEntry.arguments?.getString("albumId"))
        }
    }
}