package com.komoui.demo.previews

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.komoui.components.Spinner
import com.komoui.components.SpinnerVariant

@Preview
@Composable
internal fun SpinnerPreview() {
    PreviewSurface {
        Text("Variants (default size)")
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Spinner(variant = SpinnerVariant.Circular)
            Spinner(variant = SpinnerVariant.Bounce)
            Spinner(variant = SpinnerVariant.Moon)
            Spinner(variant = SpinnerVariant.Pulse)
        }

        Text("Sizes")
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Spinner(size = 24.dp)
            Spinner(size = 40.dp)
            Spinner(size = 56.dp)
        }
    }
}
