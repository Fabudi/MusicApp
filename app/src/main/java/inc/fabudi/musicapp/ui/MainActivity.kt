package inc.fabudi.musicapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import inc.fabudi.musicapp.ui.screens.MainScreen
import inc.fabudi.musicapp.ui.theme.MusicAppTheme
import inc.fabudi.musicapp.viewmodel.MusicViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    val viewmodel: MusicViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MusicAppTheme {
                MainScreen(viewmodel)
            }
        }
    }
}