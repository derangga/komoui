package dr.shadcn.kmp.pages.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.unit.dp
import com.shadcn.ui.components.Button
import com.shadcn.ui.components.ButtonVariant
import com.shadcn.ui.components.Empty
import com.shadcn.ui.components.EmptyContent
import com.shadcn.ui.components.EmptyDescription
import com.shadcn.ui.components.EmptyHeader
import com.shadcn.ui.components.EmptyMedia
import com.shadcn.ui.components.EmptyMediaVariant
import com.shadcn.ui.components.EmptyTitle
import com.shadcn.ui.themes.radius
import com.shadcn.ui.themes.styles
import dr.shadcn.kmp.components.ContentPageWithTitle
import dr.shadcn.kmp.components.Layout

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
                    EmptyDescription { Text("You're all caught up. New messages will appear here.") }
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
                    EmptyDescription { Text("Try adjusting your search or filters to find what you're looking for.") }
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
                )
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
