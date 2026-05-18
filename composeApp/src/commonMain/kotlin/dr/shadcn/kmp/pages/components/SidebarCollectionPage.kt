package dr.shadcn.kmp.pages.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.shadcn.ui.components.Accordion
import com.shadcn.ui.components.AccordionItemData
import com.shadcn.ui.components.Button
import dr.shadcn.kmp.TopLevelRoute
import dr.shadcn.kmp.components.ContentPageWithTitle
import dr.shadcn.kmp.components.Layout

@Composable
fun SidebarCollectionPage(parentNav: NavHostController) {
    Layout {
        Text(
            "Sidebar",
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.SemiBold)
        )

        Text(
            text = "A composable, themeable and customizable sidebar component.",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(4.dp))

        Spacer(modifier = Modifier.height(20.dp))

        Text("1. Standard sidebar (variant = Sidebar)")
        Accordion(
            items =
                listOf(
                    AccordionItemData(
                        id = "item-1",
                        header = { Text("Details") },
                        content = {
                            Text("The standard sidebar mirrors shadcn/ui: place Sidebar and SidebarInset as siblings inside SidebarProvider. On desktop they render as a Row; on mobile (viewport < 768.dp) SidebarProvider hosts them inside a Material3 ModalNavigationDrawer. This demo uses collapsible = Icon so the sidebar can collapse to an icon rail with tooltips.")
                        }
                    )
                )
        )
        Spacer(modifier = Modifier.height(12.dp))
        ContentPageWithTitle {
            Button(
                onClick = { parentNav.navigate(TopLevelRoute.SidebarLayoutGraph.path) }
            ) { Text("Open standard sidebar demo") }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text("2. Inset sidebar (variant = Inset)")
        Accordion(
            items =
                listOf(
                    AccordionItemData(
                        id = "item-1",
                        header = { Text("Details") },
                        content = {
                            Text("The inset variant lets the sidebar background extend to the screen edge and wraps the main content (SidebarInset) in a rounded, shadowed card. SidebarInset reads the variant from LocalSidebarState and adjusts its margin/radius/elevation automatically.")
                        }
                    )
                )
        )
        Spacer(modifier = Modifier.height(12.dp))
        ContentPageWithTitle {
            Button(
                onClick = { parentNav.navigate(TopLevelRoute.SidebarInsetGraph.path) }
            ) { Text("Open inset sidebar demo") }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text("3. Offcanvas sidebar (collapsible = Offcanvas)")
        Accordion(
            items =
                listOf(
                    AccordionItemData(
                        id = "item-1",
                        header = { Text("Details") },
                        content = {
                            Text("With collapsible = Offcanvas, the sidebar hides entirely when collapsed — no icon rail. The Sidebar slot emits nothing and the main content (SidebarInset) reclaims the full width. Tap SidebarTrigger to bring the sidebar back.")
                        }
                    )
                )
        )
        Spacer(modifier = Modifier.height(12.dp))
        ContentPageWithTitle {
            Button(
                onClick = { parentNav.navigate(TopLevelRoute.SidebarOffcanvasGraph.path) }
            ) { Text("Open offcanvas sidebar demo") }
        }
    }
}