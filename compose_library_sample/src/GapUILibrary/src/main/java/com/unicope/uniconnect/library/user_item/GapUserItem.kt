package com.unicope.uniconnect.library.user_item

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp

@Composable
fun GapUserItem(name: String, avatar: Painter) {
    Row(modifier = Modifier
        .padding(all = 8.dp)
        .fillMaxWidth()) {
        Image(
            painter = avatar,
            contentDescription = "Profile",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .border(1.5.dp, MaterialTheme.colors.primary, CircleShape)
        )
        Spacer(modifier = Modifier.width(8.dp))

        Column {
            var textColor = Color.Black;
            if (name.length > 5) {
                textColor = MaterialTheme.colors.secondary
            }

            Text(
                text = "$name",
                color = textColor,
                style = MaterialTheme.typography.subtitle2)
            Spacer(modifier = Modifier.height(4.dp))

            if(name.length > 5) {
                Text(
                    text = "Name length is longer than 5",
                    color = MaterialTheme.colors.secondary,
                    style = MaterialTheme.typography.caption)
            }
        }
    }
}