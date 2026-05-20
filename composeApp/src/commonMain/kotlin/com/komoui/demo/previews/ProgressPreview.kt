package com.komoui.demo.previews

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.komoui.components.Progress

@Preview
@Composable
internal fun ProgressPreview() {
    PreviewSurface {
        Text("0%")
        Progress(progress = 0f)

        Text("25%")
        Progress(progress = 0.25f)

        Text("50%")
        Progress(progress = 0.5f)

        Text("75%")
        Progress(progress = 0.75f)

        Text("100%")
        Progress(progress = 1f)
    }
}
