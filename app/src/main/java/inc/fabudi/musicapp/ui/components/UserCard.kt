package inc.fabudi.musicapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import inc.fabudi.musicapp.R

@Composable
fun UserCard(
    modifier: Modifier = Modifier,
    nickname: String = "Nickname",
    followersCount: Int = 420,
    minutesListened: Int = 1337
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .shadow(
                elevation = 2.dp,
                shape = RoundedCornerShape(15)
            )
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "Avatar",
            modifier = Modifier.clip(shape = RoundedCornerShape(15))
        )
        Column(modifier = Modifier.padding(start = 16.dp)) {
            Text(
                text = nickname,
                style = MaterialTheme.typography.titleMedium
            )
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = followersCount.toString(),
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(text = "Followers")
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = minutesListened.toString(),
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(text = "Minutes Listened")
                }
            }
        }
    }
}

@Preview(device = "id:pixel_3a")
@Composable
private fun UserCardPreview() {
    UserCard()
}