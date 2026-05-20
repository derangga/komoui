package com.komoui.demo.previews

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Inbox
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.komoui.components.Button
import com.komoui.components.Empty
import com.komoui.components.EmptyContent
import com.komoui.components.EmptyDescription
import com.komoui.components.EmptyHeader
import com.komoui.components.EmptyMedia
import com.komoui.components.EmptyMediaVariant
import com.komoui.components.EmptyTitle

@Preview
@Composable
internal fun EmptyPreview() {
    PreviewSurface {
        Empty {
            EmptyHeader {
                EmptyMedia(variant = EmptyMediaVariant.Icon) {
                    Icon(Icons.Filled.Inbox, contentDescription = null)
                }
                EmptyTitle { Text("No messages") }
                EmptyDescription { Text("You don't have any messages yet. Start a conversation to see them here.") }
            }
            EmptyContent {
                Button(onClick = {}) { Text("Start a chat") }
            }
        }
    }
}
