package com.komoui.demo.previews

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.komoui.components.Input
import com.komoui.components.InputVariant

@Preview
@Composable
internal fun InputPreview() {
    PreviewSurface {
        Text("Outlined (default)")
        var outlined by remember { mutableStateOf("") }
        Input(
            value = outlined,
            onValueChange = { outlined = it },
            placeholder = "Email",
        )

        Text("With value")
        var filled by remember { mutableStateOf("hello@example.com") }
        Input(value = filled, onValueChange = { filled = it })

        Text("Underlined")
        var underlined by remember { mutableStateOf("") }
        Input(
            value = underlined,
            onValueChange = { underlined = it },
            placeholder = "Username",
            variant = InputVariant.Underlined,
        )

        Text("With leading icon")
        var search by remember { mutableStateOf("") }
        Input(
            value = search,
            onValueChange = { search = it },
            placeholder = "Search…",
            leadingIcon = { Icon(Icons.Filled.Search, contentDescription = null) },
        )

        Text("Error")
        var errored by remember { mutableStateOf("invalid") }
        Input(
            value = errored,
            onValueChange = { errored = it },
            isError = true,
            supportingText = { Text("Please enter a valid value.") },
        )

        Text("Disabled")
        Input(value = "can't edit", onValueChange = {}, enabled = false)
    }
}
