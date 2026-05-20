package com.komoui.demo.previews

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.komoui.components.LayoutOrientation
import com.komoui.components.RadioButtonWithLabel
import com.komoui.components.RadioGroup

@Preview
@Composable
internal fun RadioButtonPreview() {
    PreviewSurface {
        Text("Vertical")
        RadioGroup(
            selectedValue = "comfortable",
            onValueChange = {},
            orientation = LayoutOrientation.Vertical,
        ) {
            RadioButtonWithLabel(
                value = "default",
                label = "Default",
                selectedValue = "comfortable",
                onValueChange = {},
            )
            RadioButtonWithLabel(
                value = "comfortable",
                label = "Comfortable",
                selectedValue = "comfortable",
                onValueChange = {},
            )
            RadioButtonWithLabel(
                value = "compact",
                label = "Compact",
                selectedValue = "comfortable",
                onValueChange = {},
            )
        }

        Text("Horizontal")
        RadioGroup(
            selectedValue = "yes",
            onValueChange = {},
            orientation = LayoutOrientation.Horizontal,
        ) {
            RadioButtonWithLabel(
                value = "yes",
                label = "Yes",
                selectedValue = "yes",
                onValueChange = {},
            )
            RadioButtonWithLabel(
                value = "no",
                label = "No",
                selectedValue = "yes",
                onValueChange = {},
            )
        }
    }
}
