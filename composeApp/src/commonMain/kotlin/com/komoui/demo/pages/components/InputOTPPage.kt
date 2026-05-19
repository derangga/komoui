package com.komoui.demo.pages.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.komoui.components.InputOTP
import com.komoui.themes.styles
import com.komoui.demo.components.ContentPageWithTitle
import com.komoui.demo.components.Layout

@Composable
fun InputOTPPage() {
    Layout {
        Text(
            "Input OTP",
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.SemiBold)
        )

        Text(
            text = "Accessible one-time password component with copy paste functionality.",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(4.dp))

        ContentPageWithTitle("1. Basic 6-digit") {
            var code by remember { mutableStateOf("") }
            InputOTP(
                value = code,
                onValueChange = { code = it },
                length = 6,
            )
        }

        ContentPageWithTitle("2. With separator") {
            var code by remember { mutableStateOf("") }
            InputOTP(
                value = code,
                onValueChange = { code = it },
                length = 6,
                separatorIndices = setOf(2),
            )
        }

        ContentPageWithTitle("3. Four digits") {
            var code by remember { mutableStateOf("") }
            InputOTP(
                value = code,
                onValueChange = { code = it },
                length = 4,
            )
        }

        ContentPageWithTitle("4. Controlled value") {
            var code by remember { mutableStateOf("123") }
            Column {
                InputOTP(
                    value = code,
                    onValueChange = { code = it },
                    length = 6,
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = if (code.isEmpty()) "Please enter the code." else "You entered: $code",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.styles.mutedForeground
                )
            }
        }

        ContentPageWithTitle("5. Alphanumeric") {
            var code by remember { mutableStateOf("") }
            InputOTP(
                value = code,
                onValueChange = { code = it.uppercase() },
                length = 6,
                keyboardType = KeyboardType.Text,
            )
        }

        ContentPageWithTitle("6. Disabled") {
            InputOTP(
                value = "12",
                onValueChange = {},
                length = 6,
                enabled = false,
            )
        }

        ContentPageWithTitle("7. Error state") {
            var code by remember { mutableStateOf("123") }
            Column {
                InputOTP(
                    value = code,
                    onValueChange = { code = it },
                    length = 6,
                    isError = true,
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Invalid code. Please try again.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.styles.destructive
                )
            }
        }
    }
}
