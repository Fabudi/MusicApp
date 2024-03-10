package inc.fabudi.musicapp.ui.components

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import inc.fabudi.musicapp.R
import inc.fabudi.musicapp.ui.theme.MusicAppTheme

enum class TrackCardType {
    IMAGE, TRENDING, ALBUM, PLAYLIST, PLAYLIST_AUTHOR, PLAYLIST_TITLE_INSIDE, PLAYLIST_TITLE
}

@Composable
fun PlaylistCardWithDesc() {
    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .padding(8.dp)
    ) {
        Image(
            painter = painterResource(R.drawable.ic_launcher_background),
            contentDescription = "",
            modifier = Modifier.clip(RoundedCornerShape(15))
        )
        Text(
            text = "Title",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            modifier = Modifier.padding(top = 8.dp)
        )
        Text(text = "Author", color = Color.Gray, fontSize = 14.sp)
    }
}

@Preview
@Composable
fun PlaylistCardWithDescPreview() {
    PlaylistCardWithDesc()
}


@Composable
fun PlaylistCardWithName() {
    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.ic_launcher_background),
            contentDescription = "",
            modifier = Modifier.clip(RoundedCornerShape(15))
        )
        Text(
            text = "Title",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

@Preview
@Composable
fun PlaylistCardWithNamePreview() {
    PlaylistCardWithName()
}

@Composable
fun PlaylistCardWithNameFourImages() {
    Column(
        modifier = Modifier
            .width(128.dp)
            .height(IntrinsicSize.Min)
            .wrapContentHeight()
            .background(MaterialTheme.colorScheme.background)
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(15))
                .width(IntrinsicSize.Min)
                .height(IntrinsicSize.Min)
                .weight(1f)
        ) {
            Row(modifier = Modifier
                .wrapContentSize()
                .weight(1f)) {
                Image(
                    painter = painterResource(R.drawable.ic_launcher_background),
                    contentDescription = "",
                    modifier = Modifier
                        .wrapContentSize()
                        .weight(1f)
                )
                Image(
                    painter = painterResource(R.drawable.ic_launcher_foreground),
                    contentDescription = "",
                    modifier = Modifier
                        .wrapContentSize()
                        .weight(1f)
                )
            }
            Row(modifier = Modifier
                .wrapContentSize()
                .weight(1f)) {
                Image(
                    painter = painterResource(R.drawable.ic_launcher_foreground),
                    contentDescription = "",
                    modifier = Modifier
                        .wrapContentSize()
                        .weight(1f)
                )
                Image(
                    painter = painterResource(R.drawable.ic_launcher_background),
                    contentDescription = "",
                    modifier = Modifier
                        .wrapContentSize()
                        .weight(1f)
                )
            }
        }
        Text(
            text = "Title",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            modifier = Modifier
                .padding(top = 8.dp)
                .wrapContentSize()
        )
    }
}

@Preview
@Composable
fun PlaylistCardWithNameFourImagesPreview() {
    PlaylistCardWithNameFourImages()
}


@Composable
fun PlaylistCardWithNameInside() {
    Box(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .padding(8.dp)
            .height(IntrinsicSize.Min)
            .width(IntrinsicSize.Min)
            .clip(RoundedCornerShape(15))
    ) {
        Image(
            painter = painterResource(R.drawable.ic_launcher_background),
            contentDescription = "",
            modifier = Modifier
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent, Color.Black
                        )
                    )
                )
        )
        Text(
            text = "Title",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = Color.White,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(start = 8.dp, bottom = 8.dp)
        )
    }
}

@Preview
@Composable
fun PlaylistCardWithNameInsidePreview() {
    PlaylistCardWithNameInside()
}


@Composable
fun TrendingTrackCard() {
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(8.dp)
            .clickable {
                Toast
                    .makeText(
                        context,
                        "TODO(): Play selected track/Open selected album/playlist",
                        Toast.LENGTH_SHORT
                    )
                    .show()
            }, verticalAlignment = Alignment.Top
    ) {
        Image(
            painter = painterResource(R.drawable.ic_launcher_background),
            modifier = Modifier
                .clip(RoundedCornerShape(20))
                .height(96.dp)
                .aspectRatio(1 / 1f),
            contentDescription = ""
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp)
                .height(96.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "#1",
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                modifier = Modifier
                    .clip(
                        RoundedCornerShape(50)
                    )
                    .background(MaterialTheme.colorScheme.primary)
                    .padding(start = 12.dp, end = 12.dp, top = 4.dp, bottom = 4.dp),
                color = Color.White
            )
            Text(text = "Title", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Text(text = "Author", color = Color.Gray, fontSize = 14.sp)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(R.drawable.baseline_play_circle_24),
                    contentDescription = "",
                    modifier = Modifier
                        .height(14.dp)
                        .padding(end = 4.dp),
                    tint = Color.DarkGray
                )
                Text(text = "2.5M Plays", fontSize = 14.sp, color = Color.DarkGray)
            }
        }
        IconButton(onClick = {
            Toast.makeText(
                context, "TODO(): Show BottomSheet with different options", Toast.LENGTH_SHORT
            ).show()
        }, content = {
            Icon(
                painter = painterResource(R.drawable.baseline_more_vert_24),
                contentDescription = "",
                modifier = Modifier
                    .height(24.dp)
                    .aspectRatio(1 / 1f)
            )
        })
    }
}

@Preview
@Composable
fun TrendingTrackCardPreview() {
    MusicAppTheme {
        TrendingTrackCard()
    }
}

@Composable
fun ImageTrackCard() {
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(8.dp)
            .clickable {
                Toast
                    .makeText(context, "TODO(): Play selected track", Toast.LENGTH_SHORT)
                    .show()
            }, verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(R.drawable.ic_launcher_background),
            modifier = Modifier
                .clip(RoundedCornerShape(20))
                .height(48.dp)
                .aspectRatio(1 / 1f),
            contentDescription = ""
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(8.dp)
        ) {
            Text(text = "Title", fontWeight = FontWeight.Bold)
            Text(text = "Author", color = Color.Gray)
        }
        IconButton(onClick = {
            Toast.makeText(
                context, "TODO(): Show BottomSheet with different options", Toast.LENGTH_SHORT
            ).show()
        }, content = {
            Icon(
                painter = painterResource(R.drawable.baseline_more_vert_24),
                contentDescription = "",
                modifier = Modifier
                    .height(24.dp)
                    .aspectRatio(1 / 1f)
            )
        })
    }
}

@Preview
@Composable
fun ImageTrackCardPreview() {
    MusicAppTheme {
        ImageTrackCard()
    }
}


@Composable
fun AlbumTrackCard() {
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .height(IntrinsicSize.Min)
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(8.dp)
            .clickable {
                Toast
                    .makeText(context, "TODO(): Play selected track", Toast.LENGTH_SHORT)
                    .show()
            }, verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "03",
            modifier = Modifier
                .fillMaxHeight()
                .padding(top = 8.dp),
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(8.dp)
        ) {
            Text(text = "Title", fontWeight = FontWeight.Bold)
            Text(text = "Author", color = Color.Gray)
        }
        IconButton(onClick = {
            Toast.makeText(
                context, "TODO(): Show BottomSheet with different options", Toast.LENGTH_SHORT
            ).show()
        }, content = {
            Icon(
                painter = painterResource(R.drawable.baseline_more_vert_24),
                contentDescription = "",
                modifier = Modifier
                    .height(24.dp)
                    .aspectRatio(1 / 1f)
            )
        })
    }
}

@Preview
@Composable
fun AlbumTrackCardPreview() {
    MusicAppTheme {
        AlbumTrackCard()
    }
}