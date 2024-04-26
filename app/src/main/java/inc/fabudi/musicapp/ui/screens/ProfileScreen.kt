package inc.fabudi.musicapp.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import inc.fabudi.musicapp.R
import inc.fabudi.musicapp.ui.components.ButtonWithIcon
import inc.fabudi.musicapp.ui.components.ButtonWithPayload
import inc.fabudi.musicapp.ui.components.UserCard
import inc.fabudi.musicapp.ui.theme.Typography
import inc.fabudi.musicapp.viewmodel.MusicViewModel

@Composable
fun ProfileScreen(navController: NavHostController, viewModel: MusicViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
            .padding(top = 16.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp)
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Profile",
                style = Typography.titleLarge
            )
            ButtonWithIcon(icon = painterResource(id = R.drawable.baseline_logout_24)) {
                Toast
                    .makeText(navController.context, "Log out", Toast.LENGTH_SHORT)
                    .show()
            }
        }
        UserCard(modifier = Modifier.padding(16.dp))
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                ButtonWithPayload(
                    onClick = {
                        Toast
                            .makeText(navController.context, "Open equalizer", Toast.LENGTH_SHORT)
                            .show()
                    },
                    icon = painterResource(id = R.drawable.baseline_equalizer_24),
                    text = "Equalizer"
                )
            }
            item {
                ButtonWithPayload(
                    icon = painterResource(id = R.drawable.baseline_signal_cellular_alt_24),
                    text = "Cellular Data"
                ) {
                    var cellularDataUsage by remember { mutableStateOf(false) }
                    Switch(
                        checked = cellularDataUsage, onCheckedChange = {
                            Toast.makeText(
                                navController.context,
                                "Cellular Data Usage: $it",
                                Toast.LENGTH_SHORT
                            ).show()
                            cellularDataUsage = it
                        },
                        modifier = Modifier
                    )
                }
            }
            item {
                ButtonWithPayload(
                    onClick = {
                        Toast
                            .makeText(
                                navController.context,
                                "Select desired Language",
                                Toast.LENGTH_SHORT
                            )
                            .show()
                    },
                    icon = painterResource(id = R.drawable.baseline_language_24),
                    text = "Select desired Language"
                ) {
                    Text(
                        text = "English",
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier
                    )
                }
            }
            item {
                ButtonWithPayload(
                    onClick = {
                        Toast
                            .makeText(navController.context, "Startup Screen", Toast.LENGTH_SHORT)
                            .show()
                    },
                    icon = painterResource(id = R.drawable.baseline_add_to_home_screen_24),
                    text = "Startup Screen"
                ) {
                    Text(
                        text = "Explore",
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier
                    )
                }
            }
            item {
                Spacer(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(1f)
                )
            }
            item {
                Text(
                    text = "v1.0.0",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.outline
                )
            }
        }

    }
}

@Preview
@Composable
fun ProfileScreenPreview() {
    ProfileScreen(navController = rememberNavController(), viewModel = hiltViewModel())
}
