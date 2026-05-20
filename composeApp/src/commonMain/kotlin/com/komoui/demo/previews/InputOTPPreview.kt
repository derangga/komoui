package com.komoui.demo.previews

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.komoui.components.InputOTP

@Preview
@Composable
internal fun InputOTPPreview() {
    PreviewSurface {
        Text("Empty (6 slots)")
        var empty by remember { mutableStateOf("") }
        InputOTP(value = empty, onValueChange = { empty = it }, length = 6)

        Text("Partially filled")
        var partial by remember { mutableStateOf("123") }
        InputOTP(value = partial, onValueChange = { partial = it }, length = 6)

        Text("With separator after slot 2")
        var grouped by remember { mutableStateOf("12") }
        InputOTP(
            value = grouped,
            onValueChange = { grouped = it },
            length = 6,
            separatorIndices = setOf(2),
        )

        Text("Error state")
        var errored by remember { mutableStateOf("999") }
        InputOTP(
            value = errored,
            onValueChange = { errored = it },
            length = 6,
            isError = true,
        )
    }
}
