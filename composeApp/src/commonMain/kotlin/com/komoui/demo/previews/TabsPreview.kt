package com.komoui.demo.previews

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.komoui.components.Tabs

@Preview
@Composable
internal fun TabsPreview() {
    PreviewSurface {
        Tabs(
            selectedTabIndex = 0,
            onTabSelected = {},
            tabs = listOf("Account", "Password", "Notifications"),
        ) { index ->
            when (index) {
                0 -> Text("Make changes to your account here.")
                1 -> Text("Change your password here.")
                2 -> Text("Manage notification preferences.")
            }
        }
    }
}
