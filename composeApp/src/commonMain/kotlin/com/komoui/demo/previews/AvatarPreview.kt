package com.komoui.demo.previews

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.komoui.components.Avatar

@Preview
@Composable
internal fun AvatarPreview() {
    PreviewSurface {
        Text("Sizes")
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Avatar(model = null, fallbackText = "AB", size = 32.dp)
            Avatar(model = null, fallbackText = "CD", size = 40.dp)
            Avatar(model = null, fallbackText = "EF", size = 56.dp)
            Avatar(model = null, fallbackText = "GH", size = 72.dp)
        }

        Text("Fallback initials")
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Avatar(model = null, fallbackText = "JD")
            Avatar(model = null, fallbackText = "RM")
            Avatar(model = null, fallbackText = "KP")
        }
    }
}
