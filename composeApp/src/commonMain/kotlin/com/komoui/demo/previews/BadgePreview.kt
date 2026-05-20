package com.komoui.demo.previews

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.komoui.components.Badge
import com.komoui.components.BadgeVariant

@Preview
@Composable
internal fun BadgePreview() {
    PreviewSurface {
        Text("Variants")
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Badge { Text("Default") }
            Badge(variant = BadgeVariant.Secondary) { Text("Secondary") }
            Badge(variant = BadgeVariant.Destructive) { Text("Destructive") }
            Badge(variant = BadgeVariant.Outline) { Text("Outline") }
        }

        Text("Dot Badges (no content)")
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Badge()
            Badge(variant = BadgeVariant.Secondary)
            Badge(variant = BadgeVariant.Destructive)
            Badge(variant = BadgeVariant.Outline)
        }
    }
}
