package dr.shadcn.kmp.pages.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.UnfoldMore
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.shadcn.ui.components.Collapsible
import com.shadcn.ui.themes.radius
import com.shadcn.ui.themes.styles
import dr.shadcn.kmp.components.ContentPageWithTitle
import dr.shadcn.kmp.components.Layout

@Composable
fun CollapsiblePage() {
    Layout {
        Text(
            "Collapsible",
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.SemiBold)
        )
        Text(
            text = "An interactive component which expands and collapses a single panel of content.",
            style = MaterialTheme.typography.titleMedium
        )

        ContentPageWithTitle {
            BasicCollapsibleDemo()
        }

        ContentPageWithTitle {
            ShowMoreCollapsibleDemo()
        }

        ContentPageWithTitle {
            DisabledCollapsibleDemo()
        }
    }
}

@Composable
private fun BasicCollapsibleDemo() {
    var open by remember { mutableStateOf(false) }
    val styles = MaterialTheme.styles
    val radius = MaterialTheme.radius

    Collapsible(
        open = open,
        onOpenChange = { open = it },
        modifier = Modifier.fillMaxWidth()
    ) {
        CollapsibleTrigger(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(radius.md))
                .background(styles.secondary)
                .padding(horizontal = 16.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = if (open) "Hide details" else "Show details",
                color = styles.foreground,
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium),
                modifier = Modifier.align(Alignment.CenterVertically)
            )
            Icon(Icons.Default.UnfoldMore, contentDescription = "")
        }
        CollapsibleContent(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
                .clip(RoundedCornerShape(radius.md))
                .border(1.dp, styles.border, RoundedCornerShape(radius.md))
                .padding(16.dp)
        ) {
            Text(
                text = "@derangga starred 3 repositories.",
                color = styles.foreground,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
private fun ShowMoreCollapsibleDemo() {
    var open by remember { mutableStateOf(false) }
    val styles = MaterialTheme.styles
    val radius = MaterialTheme.radius

    val rows = listOf(
        "@radix-ui/primitives",
        "@radix-ui/colors",
        "@stitches/react",
        "@floating-ui/react",
        "@tanstack/react-query"
    )

    Collapsible(
        open = open,
        onOpenChange = { open = it },
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(
                text = rows.first(),
                color = styles.foreground,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(radius.md))
                    .border(1.dp, styles.border, RoundedCornerShape(radius.md))
                    .padding(12.dp)
            )

            CollapsibleContent(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    rows.drop(1).forEach { item ->
                        Text(
                            text = item,
                            color = styles.foreground,
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(radius.md))
                                .border(1.dp, styles.border, RoundedCornerShape(radius.md))
                                .padding(12.dp)
                        )
                    }
                }
            }

            CollapsibleTrigger(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(radius.md))
                    .background(styles.secondary)
                    .padding(horizontal = 16.dp, vertical = 10.dp)
            ) {
                Text(
                    text = if (open) "Show less" else "Show ${rows.size - 1} more",
                    color = styles.foreground,
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium),
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            }
        }
    }
}

@Composable
private fun DisabledCollapsibleDemo() {
    val open = false
    val styles = MaterialTheme.styles
    val radius = MaterialTheme.radius

    Collapsible(
        open = open,
        onOpenChange = {},
        enabled = false,
        modifier = Modifier.fillMaxWidth()
    ) {
        CollapsibleTrigger(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(radius.md))
                .background(styles.muted)
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            Text(
                text = "Disabled trigger",
                color = styles.mutedForeground,
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium),
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
        CollapsibleContent(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        ) {
            Text(
                text = "You should not see this.",
                color = styles.foreground,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
