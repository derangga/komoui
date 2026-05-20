package com.komoui.demo.previews

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.komoui.components.Button
import com.komoui.components.ButtonSize
import com.komoui.components.ButtonVariant
import com.komoui.components.IconButton

@Preview
@Composable
internal fun ButtonPreview() {
    PreviewSurface {
        Text("Variants")
        Button(onClick = {}) { Text("Default") }
        Button(onClick = {}, variant = ButtonVariant.Destructive) { Text("Destructive") }
        Button(onClick = {}, variant = ButtonVariant.Outline) { Text("Outline") }
        Button(onClick = {}, variant = ButtonVariant.Secondary) { Text("Secondary") }
        Button(onClick = {}, variant = ButtonVariant.Ghost) { Text("Ghost") }
        Button(onClick = {}, variant = ButtonVariant.Link) { Text("Link") }

        Text("Sizes")
        Button(onClick = {}, size = ButtonSize.Lg) { Text("Large") }
        Button(onClick = {}, size = ButtonSize.Default) { Text("Default") }
        Button(onClick = {}, size = ButtonSize.Sm) { Text("Small") }
        Button(onClick = {}, size = ButtonSize.Xs) { Text("Extra Small") }

        Text("States")
        Button(onClick = {}, enabled = false) { Text("Disabled") }
        Button(onClick = {}, loading = true) { Text("Loading") }
        Button(onClick = {}, fullWidth = true) { Text("Full Width") }

        Text("Icon Buttons")
        IconButton(onClick = {}) {
            Icon(Icons.Filled.Favorite, contentDescription = null)
        }
        IconButton(onClick = {}, size = ButtonSize.IconSm, variant = ButtonVariant.Outline) {
            Icon(Icons.Filled.Favorite, contentDescription = null)
        }
    }
}
