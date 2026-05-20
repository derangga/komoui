package com.komoui.demo.previews

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.komoui.components.Slider

@Preview
@Composable
internal fun SliderPreview() {
    PreviewSurface {
        Text("Continuous")
        var value by remember { mutableStateOf(0.5f) }
        Slider(value = value, onValueChange = { value = it })

        Text("Stepped (5 steps)")
        var stepped by remember { mutableStateOf(0.4f) }
        Slider(value = stepped, onValueChange = { stepped = it }, steps = 5)

        Text("Disabled")
        Slider(value = 0.25f, onValueChange = {}, enabled = false)
    }
}
