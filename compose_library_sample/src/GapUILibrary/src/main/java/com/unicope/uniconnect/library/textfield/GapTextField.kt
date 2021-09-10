package com.unicope.uniconnect.library.textfield

import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier

@Composable
fun GapTextField(text: String, customInterface: GapTextFieldInterface, modifier: Modifier) {
    OutlinedTextField(
        value = text,
        onValueChange = { customInterface.onTextChange(it)},
        label = {
            Text("Name")
        },
        modifier = modifier
    )
}