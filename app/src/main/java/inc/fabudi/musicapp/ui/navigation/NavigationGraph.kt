package inc.fabudi.musicapp.ui.navigation

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
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
import inc.fabudi.musicapp.ui.screens.ProfileScreen
import inc.fabudi.musicapp.ui.screens.SearchScreen
import inc.fabudi.musicapp.ui.screens.TrendingScreen

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(
        navController,
        startDestination = BottomNavItem.Explore.screenRoute,
        enterTransition = { fadeIn() },
        popEnterTransition = { fadeIn() },
        exitTransition = { fadeOut() },
        popExitTransition = { fadeOut() }
    ) {
        composable(route = BottomNavItem.Explore.screenRoute) {
            ExploreScreen(navController, hiltViewModel())
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
        composable(BottomNavItem.Profile.screenRoute) {
            ProfileScreen(navController)
        }
        composable("Player") {
            Player(navController)
        }
        composable(
            "Artist/{artistId}",
            arguments = listOf(navArgument("artistId") { type = NavType.IntType })
        ) {
            Player(navController)
        }
        composable(
            "Explore/Album/{albumId}",
            arguments = listOf(navArgument("albumId") { type = NavType.IntType })
        ) { backStackEntry ->
            Album(navController, backStackEntry.arguments?.getInt("albumId") ?: 0, hiltViewModel())
        }
        composable(
            "Library/Album/{albumId}",
            arguments = listOf(navArgument("albumId") { type = NavType.IntType })
        ) { backStackEntry ->
            Album(navController, backStackEntry.arguments?.getInt("albumId") ?: 0, hiltViewModel())
        }
        composable(
            "Search/Album/{albumId}",
            arguments = listOf(navArgument("albumId") { type = NavType.IntType })
        ) { backStackEntry ->
            Album(navController, backStackEntry.arguments?.getInt("albumId") ?: 0, hiltViewModel())
        }
    }
}