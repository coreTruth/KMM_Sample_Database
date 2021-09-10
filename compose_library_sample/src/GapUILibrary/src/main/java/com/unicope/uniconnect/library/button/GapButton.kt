package com.unicope.uniconnect.library.button

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun GapButton(text: String, icon: ImageVector, customInterface: GapButtonInterface, modifier: Modifier) {
    Button(
        onClick = { customInterface.onButtonClick()},
        modifier = modifier
    ) {
        Row {
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier.padding(end = 4.dp)
            )
            Text(text)
        }
    }
}