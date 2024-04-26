package inc.fabudi.musicapp.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.MarqueeAnimationMode
import androidx.compose.foundation.MarqueeSpacing
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
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
import inc.fabudi.musicapp.Utils.toUrlWithUserAgent
import inc.fabudi.musicapp.viewmodel.MusicViewModel

@OptIn(ExperimentalFoundationApi::class, ExperimentalGlideComposeApi::class)
@Composable
fun PlayBar(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    viewModel: MusicViewModel
) {
    Row(modifier = modifier
        .padding(horizontal = 16.dp)
        .height(64.dp)
        .shadow(
            elevation = 2.dp, shape = RoundedCornerShape(15)
        )
        .background(MaterialTheme.colorScheme.secondaryContainer)
        .clickable { onClick() }
        .padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
        GlideImage(
            model = viewModel.player.currentlyPlaying.value!!.artworkUrl.toUrlWithUserAgent(),
            contentDescription = stringResource(R.string.artwork_image),
            modifier = Modifier
                .padding(end = 16.dp)
                .clip(RoundedCornerShape(15))
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            Text(
                text = viewModel.player.currentlyPlaying.value!!.title,
                fontWeight = FontWeight.Bold,
                fontSize = 17.sp,
                modifier = Modifier.basicMarquee(
                    animationMode = MarqueeAnimationMode.Immediately,
                    spacing = MarqueeSpacing(32.dp)
                ),
                overflow = TextOverflow.Clip,
                maxLines = 1
            )
            Text(
                text = viewModel.player.currentlyPlaying.value!!.authors.toCommaString(),
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                modifier = Modifier.basicMarquee(
                    animationMode = MarqueeAnimationMode.Immediately,
                    spacing = MarqueeSpacing(32.dp)
                ),
                overflow = TextOverflow.Clip,
                maxLines = 1
            )
        }
        IconButton(
            onClick = { viewModel.player.isPlaying.value = !viewModel.player.isPlaying.value },
            modifier = Modifier.wrapContentSize(),
            enabled = true
        ) {
            Icon(
                painter = painterResource(if (viewModel.player.isPlaying.value) R.drawable.baseline_pause_24 else R.drawable.baseline_play_arrow_24),
                contentDescription = "",
                modifier = Modifier
                    .height(36.dp)
                    .aspectRatio(1 / 1f)
            )
        }
    }
}


@Preview
@Composable
fun PlayBarPreview() {
    PlayBar(onClick = {}, viewModel = hiltViewModel())
}