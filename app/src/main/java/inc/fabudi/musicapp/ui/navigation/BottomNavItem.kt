package inc.fabudi.musicapp.ui.navigation

import inc.fabudi.musicapp.R

sealed class BottomNavItem(var title: String, var icon: Int, var screenRoute: String) {
    data object Explore: BottomNavItem("EXPLORE", R.drawable.baseline_music_note_24, "explore")
    data object Trending: BottomNavItem("TRENDING", R.drawable.baseline_local_fire_department_24, "trending")
    data object Search: BottomNavItem("SEARCH", R.drawable.baseline_search_24, "search")
    data object Library: BottomNavItem("LIBRARY", R.drawable.baseline_library_music_24, "library")
    data object Settings: BottomNavItem("SETTINGS", R.drawable.baseline_settings_24, "settings")
}