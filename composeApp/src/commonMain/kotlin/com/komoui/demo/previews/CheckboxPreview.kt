package com.komoui.demo.previews

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.komoui.components.Checkbox

@Preview
@Composable
internal fun CheckboxPreview() {
    PreviewSurface {
        Text("States")
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Checkbox(checked = true, onCheckedChange = {})
            Text("Checked")
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Checkbox(checked = false, onCheckedChange = {})
            Text("Unchecked")
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Checkbox(checked = true, onCheckedChange = {}, enabled = false)
            Text("Disabled checked")
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Checkbox(checked = false, onCheckedChange = {}, enabled = false)
            Text("Disabled unchecked")
        }
    }
}
