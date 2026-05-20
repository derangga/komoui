package com.komoui.demo.previews

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.komoui.components.Collapsible

@Preview
@Composable
internal fun CollapsiblePreview() {
    PreviewSurface {
        Text("Open")
        Collapsible(open = true, onOpenChange = {}) {
            CollapsibleTrigger { Text("Show details") }
            CollapsibleContent {
                Text("This region is revealed when the collapsible is open.")
                Text("It can contain any composable content stacked in a Column.")
            }
        }

        Text("Closed")
        Collapsible(open = false, onOpenChange = {}) {
            CollapsibleTrigger { Text("Show details") }
            CollapsibleContent {
                Text("Hidden content — not visible until expanded.")
            }
        }
    }
}
