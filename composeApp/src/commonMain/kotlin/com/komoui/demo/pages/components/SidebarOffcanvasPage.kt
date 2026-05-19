package com.komoui.demo.pages.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
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
import androidx.navigation.compose.rememberNavController
import com.komoui.components.Button
import com.komoui.components.ButtonSize
import com.komoui.components.sidebar.Sidebar
import com.komoui.components.sidebar.SidebarCollapsible
import com.komoui.components.sidebar.SidebarContent
import com.komoui.components.sidebar.SidebarFooter
import com.komoui.components.sidebar.SidebarGroup
import com.komoui.components.sidebar.SidebarGroupContent
import com.komoui.components.sidebar.SidebarGroupLabel
import com.komoui.components.sidebar.SidebarHeader
import com.komoui.components.sidebar.SidebarInset
import com.komoui.components.sidebar.SidebarMenu
import com.komoui.components.sidebar.SidebarMenuButton
import com.komoui.components.sidebar.SidebarMenuItem
import com.komoui.components.sidebar.SidebarProvider
import com.komoui.components.sidebar.SidebarTrigger
import com.komoui.components.sidebar.SidebarVariant
import com.komoui.themes.radius
import com.komoui.themes.styles
import com.komoui.demo.Content
import com.komoui.demo.SidebarRoute
import com.komoui.demo.navigation.SidebarNavigation

/**
 * Demo: a sidebar that hides entirely when collapsed. Uses
 * `collapsible = SidebarCollapsible.Offcanvas` — when the user taps [SidebarTrigger], the
 * sidebar slot emits nothing and the inset reclaims the full width. Tap again to bring it
 * back. No icon rail is rendered.
 */
@Composable
fun SidebarOffcanvasPage() {
    val menus = listOf(
        Content("Dashboard", SidebarRoute.Dashboard.path),
        Content("Projects", SidebarRoute.Project.path),
        Content("Tasks", SidebarRoute.Task.path),
    )
    var selectedItem by remember { mutableStateOf("Dashboard") }
    val sidebarNav = rememberNavController()

    SidebarProvider(
        defaultOpen = true,
        variant = SidebarVariant.Sidebar,
        collapsible = SidebarCollapsible.Offcanvas,
    ) {
        Sidebar {
            SidebarHeader(
                title = "My App",
                icon = {
                    Box(
                        modifier = Modifier.size(28.dp).background(
                            MaterialTheme.styles.foreground,
                            RoundedCornerShape(MaterialTheme.radius.md),
                        ),
                        contentAlignment = Alignment.Center
                    ) {
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
                SidebarGroup {
                    SidebarGroupLabel("Navigation")
                    SidebarGroupContent {
                        SidebarMenu {
                            menus.forEach { item ->
                                SidebarMenuItem {
                                    SidebarMenuButton(
                                        onClick = {
                                            selectedItem = item.title
                                            sidebarNav.navigate(item.route)
                                        },
                                        isActive = selectedItem == item.title,
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
                                    }
                                }
                            }
                        }
                    }
                }
            }
            SidebarFooter(text = "© 2025 Shadcn Compose")
        }
        SidebarInset {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.styles.background)
                    .padding(horizontal = 8.dp),
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
