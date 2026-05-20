package com.komoui.demo.previews

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.komoui.components.Calendar
import kotlinx.datetime.LocalDate

@Preview
@Composable
internal fun CalendarPreview() {
    PreviewSurface {
        var selected by remember { mutableStateOf(LocalDate(2026, 5, 20)) }
        Calendar(
            selectedDate = selected,
            onDateSelected = { selected = it },
        )
    }
}
