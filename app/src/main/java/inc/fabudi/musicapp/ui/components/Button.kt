package inc.fabudi.musicapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp

@Composable
fun ButtonWithPayload(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    icon: Painter,
    iconContentDescription: String = "",
    text: String,
    payload: @Composable () -> Unit = {}
) {
    Row(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .height(64.dp)
            .shadow(
                elevation = 2.dp,
                shape = RoundedCornerShape(15)
            )
            .background(MaterialTheme.colorScheme.background)
            .clickable { onClick() }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = icon,
            contentDescription = iconContentDescription,
            modifier = Modifier.padding(end = 16.dp)
        )
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )
        payload()
    }
}

@Composable
fun ButtonWithIcon(
    modifier: Modifier = Modifier,
    icon: Painter,
    iconContentDescription: String = "",
    onClick: () -> Unit = {},
) {
    Row(
        modifier = modifier
            .wrapContentSize()
            .shadow(
                elevation = 2.dp,
                shape = RoundedCornerShape(15)
            )
            .background(MaterialTheme.colorScheme.background)
            .height(46.dp)
            .aspectRatio(1 / 1f)
            .clickable {
                onClick()
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = icon,
            contentDescription = iconContentDescription,
        )
    }
}