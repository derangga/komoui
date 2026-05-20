package com.komoui.demo.previews

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.komoui.components.Button
import com.komoui.components.ButtonVariant
import com.komoui.components.Card
import com.komoui.components.CardContent
import com.komoui.components.CardDescription
import com.komoui.components.CardFooter
import com.komoui.components.CardHeader
import com.komoui.components.CardTitle

@Preview
@Composable
internal fun CardPreview() {
    PreviewSurface {
        Text("Basic Card")
        Card {
            CardHeader {
                CardTitle { Text("Notifications") }
                CardDescription { Text("You have 3 unread messages.") }
            }
            CardContent {
                Text("Push notifications, emails, and in-app alerts are all enabled.")
            }
            Spacer(Modifier.height(16.dp))
        }

        Text("With Footer")
        Card {
            CardHeader {
                CardTitle { Text("Create project") }
                CardDescription { Text("Deploy your new project in one-click.") }
            }
            CardContent {
                Text("Choose a name and framework to get started.")
            }
            Spacer(Modifier.height(16.dp))
            CardFooter {
                Button(onClick = {}, variant = ButtonVariant.Outline) { Text("Cancel") }
                Spacer(Modifier.width(8.dp))
                Button(onClick = {}) { Text("Deploy") }
            }
        }
    }
}
