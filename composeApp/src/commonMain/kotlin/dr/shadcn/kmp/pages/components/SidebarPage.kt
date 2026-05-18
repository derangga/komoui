package dr.shadcn.kmp.pages.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.shadcn.ui.components.Avatar
import com.shadcn.ui.components.Button
import com.shadcn.ui.components.ButtonSize
import com.shadcn.ui.components.sidebar.Sidebar
import com.shadcn.ui.components.sidebar.SidebarCollapsible
import com.shadcn.ui.components.sidebar.SidebarContent
import com.shadcn.ui.components.sidebar.SidebarFooter
import com.shadcn.ui.components.sidebar.SidebarGroup
import com.shadcn.ui.components.sidebar.SidebarGroupContent
import com.shadcn.ui.components.sidebar.SidebarGroupLabel
import com.shadcn.ui.components.sidebar.SidebarHeader
import com.shadcn.ui.components.sidebar.SidebarInset
import com.shadcn.ui.components.sidebar.SidebarMenu
import com.shadcn.ui.components.sidebar.SidebarMenuBadge
import com.shadcn.ui.components.sidebar.SidebarMenuButton
import com.shadcn.ui.components.sidebar.SidebarMenuButtonSize
import com.shadcn.ui.components.sidebar.SidebarMenuItem
import com.shadcn.ui.components.sidebar.SidebarMenuSub
import com.shadcn.ui.components.sidebar.SidebarMenuSubButton
import com.shadcn.ui.components.sidebar.SidebarProvider
import com.shadcn.ui.components.sidebar.SidebarRail
import com.shadcn.ui.components.sidebar.SidebarSeparator
import com.shadcn.ui.components.sidebar.SidebarTrigger
import com.shadcn.ui.components.sidebar.SidebarVariant
import com.shadcn.ui.themes.styles
import dr.shadcn.kmp.Content
import dr.shadcn.kmp.SidebarRoute
import dr.shadcn.kmp.navigation.SidebarNavigation

@Composable
fun SidebarLayoutPage() {
    var selectedItem by remember { mutableStateOf("Dashboard") }
    val sidebarNav = rememberNavController()

    SidebarProvider(
        defaultOpen = true,
        variant = SidebarVariant.Sidebar,
        collapsible = SidebarCollapsible.Icon,
    ) {
        Sidebar {
            SidebarHeader(
                title = "My App",
                icon = {
                    Button(size = ButtonSize.Icon, onClick = {}) {
                        Icon(
                            Icons.Default.Star,
                            contentDescription = "logo",
                            tint = MaterialTheme.styles.sidebarPrimaryForeground,
                            modifier = Modifier.size(24.dp),
                        )
                    }
                },
            )
            SidebarContent {
                AppSidebarMenu(sidebarNav, selectedItem) { selectedItem = it }
            }
            SidebarFooter {
                NavUser(name = "shadcn", email = "m@example.com")
            }
        }
        SidebarInset {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.styles.background)
                    .padding(16.dp),
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    SidebarTrigger()
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = selectedItem,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.styles.foreground,
                    )
                }
                Spacer(modifier = Modifier.height(24.dp))
                SidebarNavigation(sidebarNav)
            }
        }
    }
}

@Composable
private fun AppSidebarMenu(
    sidebarNav: NavHostController,
    selectedMenu: String,
    onMenuClick: (String) -> Unit,
) {
    val menus = listOf(
        Content("Dashboard", SidebarRoute.Dashboard.path),
        Content("Projects", SidebarRoute.Project.path),
        Content("Tasks", SidebarRoute.Task.path),
    )

    SidebarGroup {
        SidebarGroupLabel("Navigation")
        SidebarGroupContent {
            SidebarMenu {
                menus.forEach { item ->
                    SidebarMenuItem {
                        SidebarMenuButton(
                            onClick = {
                                onMenuClick(item.title)
                                sidebarNav.navigate(item.route)
                            },
                            isActive = selectedMenu == item.title,
                            tooltip = item.title,
                            icon = {
                                Icon(
                                    imageVector = when (item.title) {
                                        "Dashboard" -> Icons.Default.Home
                                        "Projects" -> Icons.Default.Star
                                        else -> Icons.AutoMirrored.Filled.List
                                    },
                                    contentDescription = item.title,
                                    modifier = Modifier.size(20.dp),
                                    tint = MaterialTheme.styles.sidebarForeground,
                                )
                            },
                        ) {
                            Text(
                                text = item.title,
                                color = MaterialTheme.styles.sidebarForeground,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Medium,
                                modifier = Modifier.weight(1f),
                            )
                            if (item.title == "Tasks") {
                                SidebarMenuBadge {
                                    Text(
                                        text = "3",
                                        fontSize = 12.sp,
                                        color = MaterialTheme.styles.sidebarForeground.copy(alpha = 0.7f),
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    SidebarSeparator()

    SidebarGroup {
        SidebarGroupLabel("Settings")
        SidebarGroupContent {
            SidebarMenu {
                SidebarMenuButton(
                    text = "General",
                    onClick = { },
                    icon = {
                        Icon(
                            Icons.Default.Settings,
                            contentDescription = "Settings",
                            modifier = Modifier.size(20.dp),
                            tint = MaterialTheme.styles.sidebarForeground,
                        )
                    },
                )
                SidebarMenuSub {
                    SidebarMenuSubButton(onClick = { }) {
                        Text(
                            text = "Profile",
                            fontSize = 13.sp,
                            color = MaterialTheme.styles.sidebarForeground,
                        )
                    }
                    SidebarMenuSubButton(onClick = { }) {
                        Text(
                            text = "Notifications",
                            fontSize = 13.sp,
                            color = MaterialTheme.styles.sidebarForeground,
                        )
                    }
                }
                SidebarMenuButton(
                    text = "Calendar",
                    onClick = { },
                    icon = {
                        Icon(
                            Icons.Default.DateRange,
                            contentDescription = "Calendar",
                            modifier = Modifier.size(20.dp),
                            tint = MaterialTheme.styles.sidebarForeground,
                        )
                    },
                )
            }
        }
    }
}

/**
 * NavUser footer — mirrors shadcn's `NavUser` from dashboard-01/app-sidebar.tsx.
 *
 * Renders an avatar + name/email + chevron inside a Large [SidebarMenuButton]. Since
 * the menu button collapses to icon-only in icon mode, the footer naturally reduces to
 * just the avatar when the sidebar is collapsed.
 */
@Composable
private fun NavUser(name: String, email: String) {
    SidebarMenu {
        SidebarMenuItem {
            SidebarMenuButton(
                onClick = { },
                size = SidebarMenuButtonSize.Large,
                tooltip = name,
                icon = {
                    Avatar(
                        model = "https://avatars.githubusercontent.com/u/124599?v=4",
                        size = 32.dp,
                        fallbackText = name.take(2).uppercase(),
                    )
                },
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = name,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.styles.sidebarForeground,
                        maxLines = 1,
                    )
                    Text(
                        text = email,
                        fontSize = 12.sp,
                        color = MaterialTheme.styles.mutedForeground,
                        maxLines = 1,
                    )
                }
                Icon(
                    imageVector = Icons.Default.UnfoldMore,
                    contentDescription = "Open user menu",
                    modifier = Modifier.size(16.dp),
                    tint = MaterialTheme.styles.sidebarForeground,
                )
            }
        }
    }
}
