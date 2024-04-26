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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import inc.fabudi.musicapp.R
import inc.fabudi.musicapp.Utils.toUrlWithUserAgent
import inc.fabudi.musicapp.ui.theme.MusicAppTheme

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun PlaylistCardWithDesc(
    title: String,
    author: String,
    artworkUrl: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Column(modifier = Modifier
        .wrapContentSize()
        .background(MaterialTheme.colorScheme.background)
        .padding(8.dp)
        .clickable { onClick() }) {
        GlideImage(
            model = artworkUrl.toUrlWithUserAgent(),
            contentDescription = "",
            modifier = modifier
                .height(128.dp)
                .aspectRatio(1 / 1f)
                .clip(RoundedCornerShape(15))
        )
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            modifier = Modifier
                .padding(top = 8.dp)
                .width(128.dp),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = author,
            color = Color.Gray,
            fontSize = 14.sp,
            modifier = Modifier.width(128.dp),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Preview
@Composable
fun PlaylistCardWithDescPreview() {
    PlaylistCardWithDesc(
        modifier = Modifier.height(256.dp),
        onClick = {},
        title = "",
        artworkUrl = "",
        author = ""
    )
}


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun PlaylistCardWithName(
    title: String,
    artworkUrl: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .background(MaterialTheme.colorScheme.background)
            .padding(8.dp)
            .clickable { onClick() }, horizontalAlignment = Alignment.Start
    ) {
        GlideImage(
            model = artworkUrl.toUrlWithUserAgent(),
            modifier = Modifier
                .clip(RoundedCornerShape(15))
                .height(96.dp)
                .aspectRatio(1 / 1f),
            contentDescription = "Playlist Artwork"
        )
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            modifier = Modifier
                .width(96.dp)
                .padding(top = 8.dp),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Preview
@Composable
fun PlaylistCardWithNamePreview() {
    PlaylistCardWithName(onClick = {}, title = "", artworkUrl = "")
}

@Composable
fun PlaylistCardWithNameFourImages(onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .width(128.dp)
            .height(IntrinsicSize.Min)
            .wrapContentHeight()
            .background(MaterialTheme.colorScheme.background)
            .padding(8.dp)
            .clickable { onClick() },
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
            Row(
                modifier = Modifier
                    .wrapContentSize()
                    .weight(1f)
            ) {
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
            Row(
                modifier = Modifier
                    .wrapContentSize()
                    .weight(1f)
            ) {
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
    PlaylistCardWithNameFourImages(onClick = {})
}


@Composable
fun PlaylistCardWithNameInside(modifier: Modifier = Modifier, onClick: () -> Unit) {
    Box(modifier = modifier
        .background(MaterialTheme.colorScheme.background)
        .padding(8.dp)
        .height(IntrinsicSize.Min)
        .aspectRatio(1 / 1f)
        .clip(RoundedCornerShape(15))
        .clickable { onClick() }) {
        Image(
            painter = painterResource(R.drawable.ic_launcher_background),
            contentDescription = "",
            modifier = Modifier.fillMaxSize()
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
                .padding(start = 16.dp, bottom = 8.dp)
        )
    }
}

@Preview
@Composable
fun PlaylistCardWithNameInsidePreview() {
    PlaylistCardWithNameInside(modifier = Modifier.height(256.dp), onClick = {})
}


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun TrendingTrackCard(
    onClick: () -> Unit,
    artworkUrl: String,
    place: Int,
    title: String,
    author: String,
    playsCount: Int
) {
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(8.dp)
            .clickable { onClick() },
        verticalAlignment = Alignment.Top
    ) {
        GlideImage(
            model = artworkUrl.toUrlWithUserAgent(),
            modifier = Modifier
                .clip(RoundedCornerShape(20))
                .height(96.dp)
                .aspectRatio(1 / 1f), contentDescription = "Track Artwork"
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp)
                .height(96.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "#$place",
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
            Text(text = title, fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Text(text = author, color = Color.Gray, fontSize = 14.sp)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(R.drawable.baseline_play_circle_24),
                    contentDescription = "",
                    modifier = Modifier
                        .height(14.dp)
                        .padding(end = 4.dp),
                    tint = Color.DarkGray
                )
                Text(text = "$playsCount Plays", fontSize = 14.sp, color = Color.DarkGray)
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
        TrendingTrackCard(onClick = {}, "", 0, "", "", 0)
    }
}

@Composable
fun ImageTrackCard(onClick: () -> Unit) {
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(8.dp)
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically
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
        ImageTrackCard(onClick = {})
    }
}


@Composable
fun AlbumTrackCard(title: String, authors: String, place: Int, onClick: () -> Unit) {
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .height(IntrinsicSize.Min)
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(8.dp)
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "%02d".format(place),
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
            Text(text = title, fontWeight = FontWeight.Bold)
            Text(text = authors, color = Color.Gray)
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
        AlbumTrackCard("", "", 0, onClick = {})
    }
}