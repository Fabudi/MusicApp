package inc.fabudi.musicapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import inc.fabudi.musicapp.ui.screens.ExploreScreen
import inc.fabudi.musicapp.ui.screens.LibraryScreen
import inc.fabudi.musicapp.ui.screens.SearchScreen
import inc.fabudi.musicapp.ui.screens.SettingsScreen
import inc.fabudi.musicapp.ui.screens.TrendingScreen

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController, startDestination = BottomNavItem.Explore.screenRoute) {
        composable(route = BottomNavItem.Explore.screenRoute) {
            ExploreScreen()
        }
        composable(BottomNavItem.Trending.screenRoute) {
            TrendingScreen()
        }
        composable(BottomNavItem.Search.screenRoute) {
            SearchScreen()
        }
        composable(BottomNavItem.Library.screenRoute) {
            LibraryScreen()
        }
        composable(BottomNavItem.Settings.screenRoute) {
            SettingsScreen()
        }
    }
}