package inc.fabudi.musicapp.ui.components

import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.math.sin

@Composable
fun LoadingStub() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyRow(
            modifier = Modifier.wrapContentSize(),
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            items(3) {
                Spacer(
                    modifier = Modifier
                        .loadingVertical(30.dp, it * 100)
                        .size(20.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.primary)
                )
            }
        }

    }
}

@Composable
fun Modifier.loadingVertical(dx: Dp, shift: Int): Modifier {
    val transition = rememberInfiniteTransition(label = "loadingVertical")
    val translateAnimation by transition.animateFloat(
        initialValue = 0f,
        targetValue = dx.value,
        animationSpec = infiniteRepeatable(
            tween(durationMillis = 1000 - shift, easing = Easing(::sin)),
            RepeatMode.Reverse
        ),
        label = "loadingVertical",
    )
    return this.offset(0.dp, translateAnimation.dp)
}

@Preview
@Composable
fun LoadingStubPreview() {
    LoadingStub()
}