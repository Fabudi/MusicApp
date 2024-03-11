package inc.fabudi.musicapp.ui.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import inc.fabudi.musicapp.R


@Composable
fun Player() {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {
            IconButton(onClick = {
                Toast.makeText(context, "Close the player screen", Toast.LENGTH_SHORT).show()
            }, content = {
                Icon(
                    painter = painterResource(R.drawable.baseline_keyboard_arrow_down_24),
                    contentDescription = "",
                    modifier = Modifier
                        .height(24.dp)
                        .aspectRatio(1 / 1f)
                )
            })
            Column(
                modifier = Modifier.weight(1f), horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "NOW PLAYING FROM",
                    color = Color.Gray,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "ALBUM",
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }
            IconButton(onClick = {
                Toast.makeText(context, "Open bottom sheet with more options", Toast.LENGTH_SHORT)
                    .show()
            }, content = {
                Icon(
                    painter = painterResource(R.drawable.baseline_more_horiz_24),
                    contentDescription = "",
                    modifier = Modifier
                        .height(24.dp)
                        .aspectRatio(1 / 1f)
                )
            })
        }
        Row(
            modifier = Modifier.padding(top = 32.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.ic_launcher_background),
                contentDescription = "",
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .aspectRatio(1 / 1f)
                    .shadow(elevation = 16.dp, clip = true, shape = RoundedCornerShape(10))
            )
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Track Name",
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                modifier = Modifier.padding(top = 16.dp)
            )
            Text(
                text = "Author and other",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color.Gray,
                modifier = Modifier.padding(top = 8.dp)
            )
            Column(modifier = Modifier.fillMaxWidth()) {
                Slider(value = .3f, onValueChange = {}, modifier = Modifier.fillMaxWidth())
                Row(
                    modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "1:00",
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                    Text(
                        text = "3:32",
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(end = 8.dp)
                    )
                }
            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                IconButton(onClick = {
                    Toast.makeText(context, "Rewind 5s", Toast.LENGTH_SHORT).show()
                }, content = {
                    Icon(
                        painter = painterResource(R.drawable.baseline_replay_5_24),
                        contentDescription = "",
                        modifier = Modifier
                            .height(24.dp)
                            .aspectRatio(1 / 1f)
                    )
                })
                IconButton(onClick = {
                    Toast.makeText(context, "Previous track", Toast.LENGTH_SHORT).show()
                }, content = {
                    Icon(
                        painter = painterResource(R.drawable.baseline_fast_rewind_24),
                        contentDescription = "",
                        modifier = Modifier
                            .height(48.dp)
                            .aspectRatio(1 / 1f)
                    )
                })
                var isPlaying by remember { mutableStateOf(false) }
                IconButton(onClick = {
                    isPlaying = !isPlaying
                    Toast.makeText(context, "Pause/Play", Toast.LENGTH_SHORT).show()
                }, content = {
                    Icon(
                        painter = painterResource(if (isPlaying) R.drawable.baseline_pause_circle_24 else R.drawable.baseline_play_circle_24),
                        contentDescription = "",
                        modifier = Modifier
                            .height(78.dp)
                            .aspectRatio(1 / 1f),
                        tint = MaterialTheme.colorScheme.primary
                    )
                })
                IconButton(onClick = {
                    Toast.makeText(context, "Next track", Toast.LENGTH_SHORT).show()
                }, content = {
                    Icon(
                        painter = painterResource(R.drawable.baseline_fast_forward_24),
                        contentDescription = "",
                        modifier = Modifier
                            .height(48.dp)
                            .aspectRatio(1 / 1f)
                    )
                })
                IconButton(onClick = {
                    Toast.makeText(context, "Forward 5s", Toast.LENGTH_SHORT).show()
                }, content = {
                    Icon(
                        painter = painterResource(R.drawable.baseline_forward_5_24),
                        contentDescription = "",
                        modifier = Modifier
                            .height(24.dp)
                            .aspectRatio(1 / 1f)
                    )
                })
            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth().padding(top = 16.dp, bottom = 16.dp)
            ) {
                var isFavourite by remember { mutableStateOf(false) }
                var isShuffling by remember { mutableStateOf(false) }
                var isRepeating by remember { mutableStateOf(false) }
                IconButton(onClick = {
                    isFavourite = !isFavourite
                    Toast.makeText(context, "Add to favourite", Toast.LENGTH_SHORT).show()
                }, content = {
                    Icon(
                        painter = painterResource(if (isFavourite) R.drawable.baseline_favorite_24 else R.drawable.baseline_favorite_border_24),
                        contentDescription = "",
                        modifier = Modifier
                            .height(24.dp)
                            .aspectRatio(1 / 1f),
                        tint = if (isFavourite) MaterialTheme.colorScheme.primary else Color.Gray
                    )
                })
                IconButton(onClick = {
                    isShuffling = !isShuffling
                    Toast.makeText(context, "Shuffle", Toast.LENGTH_SHORT).show()
                }, content = {
                    Icon(
                        painter = painterResource(R.drawable.baseline_shuffle_24),
                        contentDescription = "",
                        modifier = Modifier
                            .height(24.dp)
                            .aspectRatio(1 / 1f),
                        tint = if (isShuffling) MaterialTheme.colorScheme.primary else Color.Gray
                    )
                })
                IconButton(onClick = {
                    isRepeating = !isRepeating
                    Toast.makeText(context, "Repeat", Toast.LENGTH_SHORT).show()
                }, content = {
                    Icon(
                        painter = painterResource(if (isRepeating) R.drawable.baseline_repeat_one_24 else R.drawable.baseline_repeat_24),
                        contentDescription = "",
                        modifier = Modifier
                            .height(24.dp)
                            .aspectRatio(1 / 1f),
                        tint = if (isRepeating) MaterialTheme.colorScheme.primary else Color.Gray
                    )
                })
                IconButton(onClick = {
                    Toast.makeText(context, "Lyrics", Toast.LENGTH_SHORT).show()
                }, content = {
                    Icon(
                        painter = painterResource(R.drawable.baseline_queue_music_24),
                        contentDescription = "",
                        modifier = Modifier
                            .height(24.dp)
                            .aspectRatio(1 / 1f),
                        tint = Color.Gray
                    )
                })
            }
        }
    }
}


@Preview
@Composable
fun PlayerPreview() {
    Player()
}