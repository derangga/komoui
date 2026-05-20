package com.komoui.demo.previews

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.komoui.components.Alert
import com.komoui.components.AlertVariant

@Preview
@Composable
internal fun AlertPreview() {
    PreviewSurface {
        Text("Default")
        Alert(
            icon = { Icon(Icons.Filled.Info, contentDescription = null) },
            title = { Text("Heads up!") },
            description = { Text("You can add components to your app using the cli.") },
        )

        Text("Destructive")
        Alert(
            variant = AlertVariant.Destructive,
            icon = { Icon(Icons.Filled.Warning, contentDescription = null) },
            title = { Text("Error") },
            description = { Text("Your session has expired. Please log in again.") },
        )

        Text("Without Icon")
        Alert(
            title = { Text("Note") },
            description = { Text("This alert has no icon at the start.") },
        )
    }
}
