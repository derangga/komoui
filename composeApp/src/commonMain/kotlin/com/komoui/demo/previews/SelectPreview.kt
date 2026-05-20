package com.komoui.demo.previews

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.komoui.components.Select

private val fruits = listOf("Apple", "Banana", "Blueberry", "Grapes", "Pineapple")

@Preview
@Composable
internal fun SelectPreview() {
    PreviewSurface {
        Text("Empty")
        var selected by remember { mutableStateOf<String?>(null) }
        Select(
            options = fruits,
            selectedOption = selected,
            onOptionSelected = { selected = it },
            placeholder = "Select a fruit",
        )

        Text("Selected")
        var chosen by remember { mutableStateOf<String?>("Banana") }
        Select(
            options = fruits,
            selectedOption = chosen,
            onOptionSelected = { chosen = it },
        )

        Text("Disabled")
        Select(
            options = fruits,
            selectedOption = "Apple",
            onOptionSelected = {},
            enabled = false,
        )
    }
}
