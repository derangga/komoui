package com.komoui.demo.pages.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.komoui.components.Spinner
import com.komoui.components.SpinnerVariant
import com.komoui.themes.styles
import com.komoui.demo.components.ContentPageWithTitle
import com.komoui.demo.components.Layout

@Composable
fun SpinnerPage() {
    Layout {
        Text(
            "Spinner",
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.SemiBold)
        )

        Text(
            text = "An indeterminate loading indicator with four visual variants: circular, bounce, moon, and pulse.",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(4.dp))

        ContentPageWithTitle("1. Circular (default)") {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spinner()
            }
        }

        ContentPageWithTitle("2. Bounce") {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spinner(variant = SpinnerVariant.Bounce)
            }
        }

        ContentPageWithTitle("3. Moon") {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spinner(variant = SpinnerVariant.Moon)
            }
        }

        ContentPageWithTitle("4. Pulse") {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spinner(variant = SpinnerVariant.Pulse)
            }
        }

        ContentPageWithTitle("5. Custom size and color") {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterHorizontally),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spinner(size = 24.dp, color = MaterialTheme.styles.destructive)
                Spinner(
                    variant = SpinnerVariant.Moon,
                    size = 56.dp,
                    color = MaterialTheme.styles.destructive,
                    strokeWidth = 4.dp
                )
                Spinner(
                    variant = SpinnerVariant.Pulse,
                    size = 56.dp,
                    color = MaterialTheme.styles.destructive
                )
            }
        }
    }
}
