package inc.fabudi.musicapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import inc.fabudi.musicapp.R
import inc.fabudi.musicapp.viewmodel.MusicViewModel

@Composable
fun PlayBar(trackCoverImage: Int) {
    Row(
        modifier = Modifier
            .height(64.dp)
            .background(Color(red = 235, green = 235, blue = 235, alpha = 255))
            .padding(8.dp), horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
    viewModel: MusicViewModel
            contentDescription = stringResource(R.string.artwork_image),
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            verticalAlignment = Alignment.CenterVertically
        ) {
            var imageAverageColor by remember { mutableStateOf(Color(0xff000000)) }
            val context = LocalContext.current.resources
            LaunchedEffect(trackCoverImage) {
                //TODO("Image shadow color depends on Image color")
            }
            Image(
                painter = painterResource(trackCoverImage),
                contentDescription = "",
                modifier = Modifier
                    .height(48.dp)
                    .aspectRatio(1 / 1f)
                    .shadow(
                        elevation = 3.dp,
                        shape = RoundedCornerShape(10.dp),
                        clip = true,
                        ambientColor = imageAverageColor,
                        spotColor = imageAverageColor
                    )
            )
            Column(modifier = Modifier.padding(start = 8.dp)) {
                Text(text = "Title", fontWeight = FontWeight.Bold, fontSize = 17.sp)
                Text(text = "Author", fontWeight = FontWeight.Normal, fontSize = 14.sp)
            }
        }
        var isPlaying by remember { mutableStateOf(false) }
        IconButton(
            onClick = { isPlaying = !isPlaying },
            modifier = Modifier.wrapContentSize(),
            enabled = true
        ) {
            Icon(
                painter = painterResource(if (isPlaying) R.drawable.baseline_pause_24 else R.drawable.baseline_play_arrow_24),
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
    PlayBar(trackCoverImage = R.drawable.ic_launcher_background)
}