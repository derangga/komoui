package com.komoui.demo.previews

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.komoui.components.ComboBox

private val frameworks = listOf(
    "Next.js",
    "SvelteKit",
    "Nuxt.js",
    "Remix",
    "Astro",
)

@Preview
@Composable
internal fun ComboboxPreview() {
    PreviewSurface {
        Text("Empty")
        var selected by remember { mutableStateOf<String?>(null) }
        ComboBox(
            options = frameworks,
            selectedOption = selected,
            onOptionSelected = { selected = it },
            placeholder = "Select framework…",
        )

        Text("Selected")
        var chosen by remember { mutableStateOf<String?>("Next.js") }
        ComboBox(
            options = frameworks,
            selectedOption = chosen,
            onOptionSelected = { chosen = it },
        )

        Text("Disabled")
        ComboBox(
            options = frameworks,
            selectedOption = "Remix",
            onOptionSelected = {},
            enabled = false,
        )
    }
}
