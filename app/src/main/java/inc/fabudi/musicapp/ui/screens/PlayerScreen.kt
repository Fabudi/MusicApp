package inc.fabudi.musicapp.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.MarqueeAnimationMode
import androidx.compose.foundation.MarqueeSpacing
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import inc.fabudi.musicapp.R
import inc.fabudi.musicapp.Utils.toCommaString
import inc.fabudi.musicapp.Utils.toMmSs
import inc.fabudi.musicapp.Utils.toUrlWithUserAgent
import inc.fabudi.musicapp.viewmodel.MusicViewModel
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds


@OptIn(ExperimentalFoundationApi::class, ExperimentalGlideComposeApi::class)
@Composable
fun PlayerScreen(viewModel: MusicViewModel = hiltViewModel()) {
    val currentlyPlayingState = viewModel.player.currentlyPlaying.observeAsState()
    LaunchedEffect(Unit) {
        while (true) {
            viewModel.player.updatePosition()
            delay(1.seconds / 2)
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Row(
            modifier = Modifier.padding(top = 32.dp)
        ) {
            GlideImage(
                model = currentlyPlayingState.value?.artworkUrl?.toUrlWithUserAgent() ?: "",
                contentDescription = stringResource(R.string.artwork_image),
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .aspectRatio(1 / 1f)
                    .shadow(
                        elevation = 16.dp, shape = RoundedCornerShape(15)
                    )
            )
        }
        Column(
            horizontalAlignment = Alignment.Start
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier
                        .wrapContentHeight()
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    Text(
                        text = currentlyPlayingState.value?.title ?: "",
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        modifier = Modifier.basicMarquee(
                            animationMode = MarqueeAnimationMode.Immediately,
                            spacing = MarqueeSpacing(32.dp)
                        ),
                        overflow = TextOverflow.Clip,
                        maxLines = 1
                    )
                    Text(
                        text = currentlyPlayingState.value?.authors?.toCommaString() ?: "",
                        fontWeight = FontWeight.Normal,
                        fontSize = 18.sp,
                        color = Color.Gray,
                        modifier = Modifier.basicMarquee(
                            animationMode = MarqueeAnimationMode.Immediately,
                            spacing = MarqueeSpacing(32.dp)
                        ),
                        overflow = TextOverflow.Clip,
                        maxLines = 1
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                var isFavourite by remember { mutableStateOf(false) }
                IconButton(onClick = {
                    isFavourite = !isFavourite
                }, content = {
                    Icon(
                        painter = painterResource(if (isFavourite) R.drawable.baseline_favorite_24 else R.drawable.baseline_favorite_border_24),
                        contentDescription = "",
                        modifier = Modifier
                            .height(32.dp)
                            .aspectRatio(1 / 1f),
                        tint = if (isFavourite) MaterialTheme.colorScheme.primary else Color.Gray
                    )
                }, modifier = Modifier.wrapContentSize())
            }
            Column(modifier = Modifier.fillMaxWidth()) {
                val currentPositionState = viewModel.player.currentPosition.observeAsState()
                Slider(
                    value = (currentPositionState.value?.toFloat() ?: 0f),
                    onValueChange = {
                        viewModel.player.seekTo(it.toInt())
                    },
                    modifier = Modifier.fillMaxWidth(),
                    valueRange = 0f..(currentlyPlayingState.value?.duration?.toFloat() ?: 1f)
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = (currentPositionState.value?.toFloat() ?: 0f).toMmSs(),
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                    Text(
                        text = (currentlyPlayingState.value?.duration?.toFloat() ?: 1f).toMmSs(),
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
                    viewModel.player.shuffle()
                }, content = {
                    Icon(
                        painter = painterResource(R.drawable.baseline_shuffle_24),
                        contentDescription = "",
                        modifier = Modifier
                            .height(24.dp)
                            .aspectRatio(1 / 1f),
                        tint = if (viewModel.player.isShuffled.value) MaterialTheme.colorScheme.primary else Color.Gray
                    )
                })
                IconButton(onClick = {
                    viewModel.player.previousTrack()
                }, content = {
                    Icon(
                        painter = painterResource(R.drawable.baseline_fast_rewind_24),
                        contentDescription = "",
                        modifier = Modifier
                            .height(48.dp)
                            .aspectRatio(1 / 1f)
                    )
                })
                IconButton(onClick = {
                    viewModel.player.play()
                }, content = {
                    val isPlaying = viewModel.player.isPlaying.collectAsState()
                    Icon(
                        painter = painterResource(if (isPlaying.value) R.drawable.baseline_pause_circle_24 else R.drawable.baseline_play_circle_24),
                        contentDescription = "",
                        modifier = Modifier
                            .height(78.dp)
                            .aspectRatio(1 / 1f),
                        tint = MaterialTheme.colorScheme.primary
                    )
                })
                IconButton(onClick = {
                    viewModel.player.nextTrack()
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
                    viewModel.player.repeat()
                }, content = {
                    Icon(
                        painter = painterResource(
                            if (viewModel.player.repeatState.value == MusicViewModel.Player.RepeatState.SINGLE) R.drawable.baseline_repeat_one_24
                            else R.drawable.baseline_repeat_24
                        ),
                        contentDescription = "",
                        modifier = Modifier
                            .height(24.dp)
                            .aspectRatio(1 / 1f),
                        tint = if (viewModel.player.repeatState.value != MusicViewModel.Player.RepeatState.NONE) MaterialTheme.colorScheme.primary
                        else Color.Gray
                    )
                })
            }
        }
        Spacer(modifier = Modifier.height(1.dp))
    }
}


@Preview
@Composable
fun PlayerScreenPreview() {
    PlayerScreen()
}