package inc.fabudi.musicapp.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import inc.fabudi.musicapp.R
import kotlin.math.pow


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CollapsingTopAppBar(
    modifier: Modifier = Modifier,
    titleText: String,
    smallTitleText: String,
    navigationIcon: @Composable () -> Unit,
    actions: @Composable RowScope.() -> Unit,
    maxHeight: Dp = 0.dp,
    pinnedHeight: Dp = 64.dp,
    @DrawableRes backgroundImageId: Int,
    scrollBehavior: TopAppBarScrollBehavior
) {
    val pinnedHeightPx: MutableState<Float>
    val maxHeightPx: MutableState<Float>
    val titleTextSize: TextUnit
    val appBarHeight: Dp
    val collapsedFactor = scrollBehavior.state.collapsedFraction
    val isCollapsed = collapsedFactor > 0.8f
    val gradientBrush = Brush.verticalGradient(colors = listOf(Color.Transparent, Color.Black))
    val titleSmallFontSize = MaterialTheme.typography.titleLarge.fontSize.value
    val titleLargeFontSize = MaterialTheme.typography.displayMedium.fontSize.value
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    LocalDensity.current.run {
        pinnedHeightPx = remember { mutableFloatStateOf(pinnedHeight.toPx()) }
        maxHeightPx =
            remember { mutableFloatStateOf((if (maxHeight == 0.dp) screenWidth else maxHeight).toPx()) }
        appBarHeight = lerp(maxHeightPx.value, pinnedHeightPx.value, collapsedFactor).toDp()
        titleTextSize = lerp(titleLargeFontSize, titleSmallFontSize, collapsedFactor).sp
    }
    LaunchedEffect(Unit) {
        scrollBehavior.state.heightOffset = pinnedHeightPx.value
        scrollBehavior.state.heightOffsetLimit = -maxHeightPx.value
    }
    Box(
        modifier = modifier
            .height(appBarHeight)
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .alpha(1f - collapsedFactor)
        ) {
            if (!isCollapsed) {
                Image(
                    painter = painterResource(backgroundImageId),
                    contentDescription = "Album cover image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(gradientBrush)
                )
            }
        }
        Row(
            modifier = Modifier
                .height(pinnedHeight)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { /*TODO*/ },
                modifier = Modifier.height(48.dp),
                content = navigationIcon
            )
        }
        Column(
            modifier = Modifier
                .padding(start = 16.dp)
                .height(IntrinsicSize.Min)
                .align(Alignment.BottomStart), verticalArrangement = Arrangement.Center
        ) {
            if (!isCollapsed) {
                Text(
                    text = smallTitleText,
                    color = Color.LightGray,
                    fontSize = 24.sp,
                    modifier = Modifier.alpha(1f - collapsedFactor)
                )
            }
            Text(
                text = titleText,
                color = if (!isCollapsed) Color.White else Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = titleTextSize,
                modifier = Modifier
                    .height(pinnedHeight)
                    .offset(x = (32 * collapsedFactor.pow(10)).dp)
                    .wrapContentHeight(align = Alignment.CenterVertically),
                overflow = TextOverflow.Ellipsis
            )
        }
        Row(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 16.dp)
                .offset(y = 32.dp),
            verticalAlignment = Alignment.CenterVertically,
            content = actions
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun ScrollableTopAppBarPreview() {
    CollapsingTopAppBar(
        modifier = Modifier.heightIn(min = 56.dp),
        titleText = "Album Name",
        smallTitleText = "Album",
        navigationIcon = {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "",
                modifier = Modifier,
                tint = MaterialTheme.colorScheme.onBackground
            )
        },
        actions = {
            IconButton(onClick = {}, content = {
                Icon(
                    painter = painterResource(R.drawable.baseline_play_arrow_24),
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.background,
                    modifier = Modifier
                        .height(64.dp)
                        .padding(8.dp)
                        .aspectRatio(1 / 1f)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.primary)
                )
            })
        },
        backgroundImageId = R.drawable.ic_launcher_background,
        scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    )
}