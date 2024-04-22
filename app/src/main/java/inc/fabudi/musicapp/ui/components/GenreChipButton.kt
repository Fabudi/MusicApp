package inc.fabudi.musicapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun GenreChipButton(
    modifier: Modifier = Modifier,
    title: String,
    textColor: @Composable () -> Color,
    backgroundColor: @Composable () -> Color,
    onClick: () -> Unit,
) {
    Text(
        text = title,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp,
        color = textColor(),
        modifier = modifier
            .clip(
                RoundedCornerShape(20)
            )
            .clickable {
                onClick()
            }
            .background(backgroundColor())
            .padding(start = 24.dp, top = 16.dp, end = 24.dp, bottom = 16.dp),
    )
}