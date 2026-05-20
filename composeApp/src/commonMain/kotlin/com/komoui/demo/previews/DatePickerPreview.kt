package com.komoui.demo.previews

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.komoui.components.DatePicker
import kotlinx.datetime.LocalDate

@Preview
@Composable
internal fun DatePickerPreview() {
    PreviewSurface {
        Text("Empty")
        var empty by remember { mutableStateOf<LocalDate?>(null) }
        DatePicker(
            selectedDate = empty,
            onDateSelected = { empty = it },
            placeholder = "Pick a date",
        )

        Text("Selected")
        var picked by remember { mutableStateOf<LocalDate?>(LocalDate(2026, 5, 20)) }
        DatePicker(
            selectedDate = picked,
            onDateSelected = { picked = it },
        )
    }
}
