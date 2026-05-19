package com.komoui.demo.pages.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.komoui.components.Button
import com.komoui.components.ButtonVariant
import com.komoui.components.Empty
import com.komoui.components.EmptyContent
import com.komoui.components.EmptyDescription
import com.komoui.components.EmptyHeader
import com.komoui.components.EmptyMedia
import com.komoui.components.EmptyMediaVariant
import com.komoui.components.EmptyTitle
import com.komoui.themes.radius
import com.komoui.themes.styles
import com.komoui.demo.components.ContentPageWithTitle
import com.komoui.demo.components.Layout

@Composable
fun EmptyPage() {
    Layout {
        Text(
            "Empty",
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.SemiBold)
        )

        Text(
            text = "Use the Empty component to display an empty state with optional media, title, description, and action.",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(4.dp))

        ContentPageWithTitle("1. Basic (Icon media)") {
            Empty {
                EmptyHeader {
                    EmptyMedia(variant = EmptyMediaVariant.Icon) {
                        Icon(Icons.Default.Email, contentDescription = null)
                    }
                    EmptyTitle { Text("No messages") }
                    EmptyDescription {
                        Text(
                            "You're all caught up. New messages will appear here.",
                            textAlign = TextAlign.Center
                        )
                    }
                }
                EmptyContent {
                    Button(onClick = { }) { Text("Compose") }
                }
            }
        }

        ContentPageWithTitle("2. Default media slot") {
            Empty {
                EmptyHeader {
                    EmptyMedia(variant = EmptyMediaVariant.Default) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = null,
                            modifier = Modifier.size(48.dp),
                            tint = MaterialTheme.styles.mutedForeground
                        )
                    }
                    EmptyTitle { Text("No results") }
                    EmptyDescription {
                        Text(
                            "Try adjusting your search or filters to find what you're looking for.",
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }

        ContentPageWithTitle("3. Title + description only") {
            Empty {
                EmptyHeader {
                    EmptyTitle { Text("Nothing here yet") }
                    EmptyDescription { Text("Items you create will show up in this list.") }
                }
            }
        }

        ContentPageWithTitle("4. With opt-in border") {
            Empty(
                modifier = Modifier.border(
                    width = 1.dp,
                    color = MaterialTheme.styles.border,
                    shape = RoundedCornerShape(MaterialTheme.radius.lg)
                ).padding(8.dp)
            ) {
                EmptyHeader {
                    EmptyMedia(variant = EmptyMediaVariant.Icon) {
                        Icon(Icons.Default.Search, contentDescription = null)
                    }
                    EmptyTitle { Text("No projects") }
                    EmptyDescription { Text("Create your first project to get started.") }
                }
                EmptyContent {
                    Button(onClick = { }, variant = ButtonVariant.Outline) { Text("New project") }
                }
            }
        }
    }
}
