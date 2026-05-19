package com.komoui.demo.pages.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.komoui.components.Alert
import com.komoui.components.AlertDefaults
import com.komoui.components.AlertVariant
import com.komoui.themes.styles
import com.komoui.demo.components.ContentPageWithTitle
import com.komoui.demo.components.Layout

@Composable
fun AlertPage() {
    Layout {
        Text(
            "Alert",
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.SemiBold)
        )
        Text(
            text = "Displays a callout for user attention.",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))

        ContentPageWithTitle("1. Default alert") {
            Alert(
                title = { Text("Heads up!") },
                description = { Text("You can add components to your app using the cli.") }
            )
        }

        ContentPageWithTitle("2. Destructive alert") {
            Alert(
                variant = AlertVariant.Destructive,
                title = { Text("Error") },
                description = { Text("Your session has expired. Please log in again.") }
            )
        }

        ContentPageWithTitle("3. Alert with leading content") {
            Alert(
                icon = {
                    // Placeholder for an actual icon, e.g., from Material Icons Extended
                    // For demonstration, a simple text icon
                    Text("💡", fontSize = 24.sp)
                },
                title = { Text("Tip!") },
                description = { Text("This is a helpful tip for using the application effectively.") }
            )
        }

        ContentPageWithTitle("4. Custom Alert") {
            Alert(
                icon = {
                    Icon(Icons.Default.Info, contentDescription = "Info", tint = MaterialTheme.styles.primaryForeground)
                },
                title = { Text("Information") },
                description = { Text("This is a helpful information that you need to know") },
                colors = AlertDefaults.colors().copy(
                    backgroundColor = MaterialTheme.styles.chart2.copy(alpha = 0.6f),
                    borderColors = MaterialTheme.styles.chart3.copy(alpha = 0.6f),
                    titleColor = MaterialTheme.styles.primaryForeground,
                    descriptionColor = MaterialTheme.styles.primaryForeground
                )
            )
        }
    }
}