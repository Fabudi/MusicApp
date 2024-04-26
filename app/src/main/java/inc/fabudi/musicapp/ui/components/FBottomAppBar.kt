package inc.fabudi.musicapp.ui.components

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import inc.fabudi.musicapp.ui.navigation.BottomNavItem

@Composable
fun FBottomAppBar(navController: NavController) {
    val screens = listOf(
        BottomNavItem.Explore,
        BottomNavItem.Trending,
        BottomNavItem.Search,
        BottomNavItem.Library,
        BottomNavItem.Profile
    )
    BottomNavigation(
        modifier = Modifier,
        backgroundColor = MaterialTheme.colorScheme.background
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        screens.forEach { screen ->
            BottomNavigationItem(icon = {
                Icon(
                    painterResource(id = screen.icon),
                    contentDescription = screen.title
                )
            },
                label = {
                    Text(
                        text = screen.title, fontSize = 9.sp, fontWeight = FontWeight.Bold
                    )
                },
                selectedContentColor = MaterialTheme.colorScheme.primary,
                unselectedContentColor = Color.Gray,
                alwaysShowLabel = true,
                selected = currentRoute?.lowercase()?.contains(screen.screenRoute.lowercase())
                    ?: false,
                onClick = {
                    navController.navigate(screen.screenRoute) {
                        navController.graph.startDestinationRoute?.let { screenRoute ->
                            popUpTo(screenRoute) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                })
        }
    }
}

@Preview
@Composable
fun FBottomAppBarPreview() {
    FBottomAppBar(rememberNavController())
}