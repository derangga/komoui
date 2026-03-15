package com.shadcn.ui.components.sidebar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.shadcn.ui.components.Button
import com.shadcn.ui.components.ButtonSize
import com.shadcn.ui.components.ButtonVariant
import com.shadcn.ui.components.Skeleton
import com.shadcn.ui.themes.radius
import com.shadcn.ui.themes.styles

// ---------------------------------------------------------------------------
// Internal helper: Mobile sidebar overlay (extracted from SidebarInset/Layout)
// ---------------------------------------------------------------------------

/**
 * Internal helper that renders a mobile sidebar overlay with backdrop and slide animation.
 *
 * @param isOpen Whether the sidebar is currently open.
 * @param onDismiss Callback when the backdrop is tapped.
 * @param modifier Modifier for the root container.
 * @param sidebarContent The sidebar content to display in the overlay.
 */
@Composable
internal fun MobileSidebarOverlay(
    isOpen: Boolean,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    sidebarContent: @Composable () -> Unit
) {
    // Backdrop when sidebar is open
    if (isOpen) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.5f))
                .clickable { onDismiss() }
                .zIndex(1f)
        )
    }

    // Animated sidebar overlay
    AnimatedVisibility(
        visible = isOpen,
        enter = slideInHorizontally(
            initialOffsetX = { -it },
            animationSpec = tween(300)
        ),
        exit = slideOutHorizontally(
            targetOffsetX = { -it },
            animationSpec = tween(300)
        ),
        modifier = Modifier.zIndex(2f)
    ) {
        sidebarContent()
    }
}

// ---------------------------------------------------------------------------
// Core sidebar components
// ---------------------------------------------------------------------------

private val ICON_RAIL_WIDTH = 48.dp

/**
 * Main sidebar container.
 *
 * @param modifier Modifier to apply to the sidebar.
 * @param sidebarWidth Width of the sidebar on desktop.
 * @param mobileWidth Width of the sidebar on mobile.
 * @param content The sidebar content.
 */
@Composable
fun Sidebar(
    modifier: Modifier = Modifier,
    sidebarWidth: Dp = 256.dp,
    mobileWidth: Dp = 288.dp,
    content: @Composable ColumnScope.() -> Unit
) {
    val sidebarState = LocalSidebarState.current

    val targetWidth = when {
        sidebarState.isMobile -> mobileWidth
        sidebarState.isCollapsedIcon -> ICON_RAIL_WIDTH
        else -> sidebarWidth
    }
    val animatedWidth by animateDpAsState(
        targetValue = targetWidth,
        animationSpec = tween(200)
    )

    Column(
        modifier = modifier
            .fillMaxHeight()
            .width(animatedWidth)
            .background(
                color = MaterialTheme.styles.sidebar,
                shape = if (sidebarState.isMobile) {
                    RoundedCornerShape(topEnd = 8.dp, bottomEnd = 8.dp)
                } else {
                    RoundedCornerShape(0.dp)
                }
            ),
        horizontalAlignment = if (sidebarState.isCollapsedIcon) Alignment.CenterHorizontally else Alignment.Start
    ) {
        content()
    }
}

/**
 * Sidebar trigger button (hamburger menu).
 *
 * @param modifier Modifier to apply to the trigger button.
 * @param content Custom content for the trigger. Defaults to a hamburger menu icon.
 */
@Composable
fun SidebarTrigger(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit = {
        Icon(
            Icons.Default.Menu,
            contentDescription = "Toggle sidebar",
            tint = MaterialTheme.styles.sidebarForeground
        )
    }
) {
    val sidebarState = LocalSidebarState.current

    Button(
        onClick = sidebarState.toggleSidebar,
        modifier = modifier,
        size = ButtonSize.Icon,
        variant = ButtonVariant.Ghost
    ) {
        content()
    }
}

/**
 * Main content wrapper that adapts to sidebar.
 * Handles mobile overlay and desktop inset layout.
 *
 * @param modifier Modifier to apply to the content area.
 * @param sidebarContent The sidebar content to display.
 * @param content The main content.
 */
@Composable
fun SidebarInset(
    modifier: Modifier = Modifier,
    sidebarContent: @Composable () -> Unit,
    content: @Composable () -> Unit
) {
    val sidebarState = LocalSidebarState.current

    if (sidebarState.isMobile) {
        // Mobile: Sidebar overlays the content with a backdrop
        Box(modifier = modifier.fillMaxSize()) {
            content()

            MobileSidebarOverlay(
                isOpen = sidebarState.isOpen,
                onDismiss = { sidebarState.closeSidebar() }
            ) {
                Sidebar {
                    sidebarContent()
                }
            }
        }
    } else {
        // Desktop: Adjust content area based on sidebar presence
        Box(
            modifier = modifier
                .fillMaxSize()
                .then(if (sidebarState.isOpen) Modifier.padding(start = 16.dp) else Modifier)
        ) {
            content()
        }
    }
}

/**
 * Complete sidebar layout wrapper.
 * Provides a full sidebar + content layout with header, content, and footer slots.
 *
 * @param modifier Modifier to apply to the layout.
 * @param sidebarHeader Optional header content for the sidebar.
 * @param sidebarContent Optional main content for the sidebar.
 * @param sidebarFooter Optional footer content for the sidebar.
 * @param content The main content area.
 */
@Composable
fun SidebarLayout(
    modifier: Modifier = Modifier,
    sidebarHeader: @Composable (() -> Unit)? = null,
    sidebarContent: @Composable (() -> Unit)? = null,
    sidebarFooter: @Composable (() -> Unit)? = null,
    content: @Composable () -> Unit
) {
    val sidebarState = LocalSidebarState.current

    if (sidebarState.isMobile) {
        // Mobile layout: Overlay
        Box(modifier = modifier.fillMaxSize()) {
            content()

            MobileSidebarOverlay(
                isOpen = sidebarState.isOpen,
                onDismiss = { sidebarState.closeSidebar() }
            ) {
                Sidebar {
                    Spacer(modifier = Modifier.height(24.dp))
                    sidebarHeader?.invoke()
                    Box(modifier = Modifier.weight(1f)) {
                        sidebarContent?.invoke()
                    }
                    sidebarFooter?.invoke()
                    Spacer(modifier = Modifier.height(24.dp))
                }
            }
        }
    } else {
        // Desktop layout: Side by side
        Row(modifier = modifier.fillMaxSize()) {
            when (sidebarState.collapsible) {
                SidebarCollapsible.Icon -> {
                    // Icon mode: always render, width animates between full and rail
                    Sidebar {
                        sidebarHeader?.invoke()
                        Box(modifier = Modifier.weight(1f)) {
                            sidebarContent?.invoke()
                        }
                        sidebarFooter?.invoke()
                    }
                }
                SidebarCollapsible.None -> {
                    // None: always show full sidebar, ignore toggle
                    Sidebar {
                        sidebarHeader?.invoke()
                        Box(modifier = Modifier.weight(1f)) {
                            sidebarContent?.invoke()
                        }
                        sidebarFooter?.invoke()
                    }
                }
                SidebarCollapsible.Offcanvas -> {
                    // Offcanvas: slide in/out
                    AnimatedVisibility(
                        visible = sidebarState.isOpen,
                    ) {
                        Sidebar {
                            sidebarHeader?.invoke()
                            Box(modifier = Modifier.weight(1f)) {
                                sidebarContent?.invoke()
                            }
                            sidebarFooter?.invoke()
                        }
                    }
                }
            }

            // Main content - takes remaining space
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
            ) {
                content()
            }
        }
    }
}

// ---------------------------------------------------------------------------
// Sidebar structural components
// ---------------------------------------------------------------------------

/**
 * Scrollable content area of the sidebar.
 *
 * @param modifier Modifier to apply.
 * @param content The sidebar content.
 */
@Composable
fun SidebarContent(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    val sidebarState = LocalSidebarState.current
    val horizontalPadding = if (sidebarState.isCollapsedIcon) 4.dp else 16.dp

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = horizontalPadding)
            .verticalScroll(rememberScrollState())
    ) {
        content()
    }
}

/**
 * Sidebar header section.
 *
 * @param modifier Modifier to apply.
 * @param content The header content.
 */
@Composable
fun SidebarHeader(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    val sidebarState = LocalSidebarState.current
    val padding = if (sidebarState.isCollapsedIcon) {
        Modifier.padding(top = 24.dp, start = 4.dp, end = 4.dp)
    } else {
        Modifier.padding(top = 24.dp, start = 8.dp, end = 8.dp)
    }

    Column(
        modifier = modifier
            .defaultMinSize(minHeight = 64.dp)
            .fillMaxWidth()
            .then(padding),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = if (sidebarState.isCollapsedIcon) Alignment.CenterHorizontally else Alignment.Start
    ) {
        content()
    }
}

/**
 * Sidebar footer section.
 *
 * @param modifier Modifier to apply.
 * @param content The footer content.
 */
@Composable
fun SidebarFooter(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    val sidebarState = LocalSidebarState.current
    val padding = if (sidebarState.isCollapsedIcon) {
        Modifier.padding(vertical = 8.dp, horizontal = 4.dp)
    } else {
        Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .then(padding),
        contentAlignment = if (sidebarState.isCollapsedIcon) Alignment.Center else Alignment.TopStart
    ) {
        content()
    }
}

/**
 * Groups related sidebar items together.
 *
 * @param modifier Modifier to apply.
 * @param content The group content.
 */
@Composable
fun SidebarGroup(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        content()
    }
}

/**
 * Label for a sidebar group. Hidden in collapsed icon mode.
 *
 * @param text The label text.
 * @param modifier Modifier to apply.
 */
@Composable
fun SidebarGroupLabel(
    text: String,
    modifier: Modifier = Modifier
) {
    val sidebarState = LocalSidebarState.current
    if (sidebarState.isCollapsedIcon) return

    Text(
        text = text,
        modifier = modifier.padding(horizontal = 12.dp, vertical = 4.dp),
        fontSize = 12.sp,
        fontWeight = FontWeight.Medium,
        color = MaterialTheme.styles.sidebarForeground.copy(alpha = 0.7f)
    )
}

/**
 * Label for a sidebar group.
 * @see SidebarGroupLabel
 */
@Deprecated("Use SidebarGroupLabel instead", ReplaceWith("SidebarGroupLabel(text, modifier)"))
@Composable
fun SidebarLabel(
    text: String,
    modifier: Modifier = Modifier
) {
    SidebarGroupLabel(text, modifier)
}

/**
 * Wrapper for sidebar group content.
 *
 * @param modifier Modifier to apply.
 * @param content The group content.
 */
@Composable
fun SidebarGroupContent(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        content()
    }
}

// ---------------------------------------------------------------------------
// Sidebar menu components
// ---------------------------------------------------------------------------

/**
 * Container for sidebar menu items.
 *
 * @param modifier Modifier to apply.
 * @param content The menu items.
 */
@Composable
fun SidebarMenu(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        content()
    }
}

/**
 * A clickable sidebar menu item container.
 *
 * @param onClick Callback when the item is clicked.
 * @param modifier Modifier to apply.
 * @param content The item content.
 */
@Composable
fun SidebarMenuItem(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(MaterialTheme.radius.md))
            .clickable { onClick() }
            .padding(horizontal = 12.dp, vertical = 8.dp)
    ) {
        content()
    }
}

/**
 * Slot-based sidebar menu button with icon and trailing content support.
 * In collapsed icon mode, only the icon is shown (centered).
 *
 * @param onClick Callback when the button is clicked.
 * @param modifier Modifier to apply.
 * @param isActive Whether this button is currently active/selected.
 * @param icon Optional leading icon composable.
 * @param content The button content (text and optional trailing elements).
 */
@Composable
fun SidebarMenuButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isActive: Boolean = false,
    icon: (@Composable () -> Unit)? = null,
    content: @Composable RowScope.() -> Unit
) {
    val sidebarState = LocalSidebarState.current
    val styles = MaterialTheme.styles
    val backgroundColor = if (isActive) styles.sidebarAccent else Color.Unspecified
    val contentColor = if (isActive) styles.sidebarAccentForeground else styles.sidebarForeground

    if (sidebarState.isCollapsedIcon) {
        // Icon mode: show only icon, centered
        Box(
            modifier = modifier
                .size(ICON_RAIL_WIDTH - 8.dp)
                .clip(RoundedCornerShape(MaterialTheme.radius.md))
                .background(backgroundColor)
                .clickable {
                    onClick()
                    if (sidebarState.isMobile) sidebarState.closeSidebar()
                },
            contentAlignment = Alignment.Center
        ) {
            if (icon != null) {
                icon()
            }
        }
    } else {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(MaterialTheme.radius.md))
                .background(backgroundColor)
                .clickable {
                    onClick()
                    if (sidebarState.isMobile) sidebarState.closeSidebar()
                }
                .padding(horizontal = 12.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (icon != null) {
                Box(modifier = Modifier.size(20.dp)) {
                    icon()
                }
                Spacer(modifier = Modifier.width(8.dp))
            }
            content()
        }
    }
}

/**
 * Convenience overload of [SidebarMenuButton] with a text label.
 *
 * @param text The button label text.
 * @param onClick Callback when the button is clicked.
 * @param modifier Modifier to apply.
 * @param isActive Whether this button is currently active/selected.
 * @param icon Optional leading icon composable.
 */
@Composable
fun SidebarMenuButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isActive: Boolean = false,
    icon: (@Composable () -> Unit)? = null
) {
    val styles = MaterialTheme.styles
    val textColor = if (isActive) styles.sidebarAccentForeground else styles.sidebarForeground

    SidebarMenuButton(
        onClick = onClick,
        modifier = modifier,
        isActive = isActive,
        icon = icon
    ) {
        Text(
            text = text,
            color = textColor,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.weight(1f)
        )
    }
}

// ---------------------------------------------------------------------------
// New sidebar components
// ---------------------------------------------------------------------------

/**
 * A horizontal separator for the sidebar.
 * In collapsed icon mode, renders as a short centered line.
 *
 * @param modifier Modifier to apply.
 */
@Composable
fun SidebarSeparator(modifier: Modifier = Modifier) {
    val sidebarState = LocalSidebarState.current
    val horizontalPadding = if (sidebarState.isCollapsedIcon) 8.dp else 8.dp

    HorizontalDivider(
        modifier = modifier.padding(horizontal = horizontalPadding, vertical = 4.dp),
        thickness = 1.dp,
        color = MaterialTheme.styles.sidebarBorder
    )
}

/**
 * A badge for sidebar menu buttons. Hidden in collapsed icon mode.
 * Typically placed as trailing content inside [SidebarMenuButton].
 *
 * @param modifier Modifier to apply.
 * @param content The badge content (e.g., a count or status indicator).
 */
@Composable
fun SidebarMenuBadge(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    val sidebarState = LocalSidebarState.current
    if (sidebarState.isCollapsedIcon) return

    Box(
        modifier = modifier.padding(horizontal = 6.dp, vertical = 2.dp),
        contentAlignment = Alignment.Center
    ) {
        content()
    }
}

/**
 * Container for nested sub-menu items. Hidden in collapsed icon mode.
 * Renders with a left border and indentation.
 *
 * @param modifier Modifier to apply.
 * @param content The sub-menu items.
 */
@Composable
fun SidebarMenuSub(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    val sidebarState = LocalSidebarState.current
    if (sidebarState.isCollapsedIcon) return

    val borderColor = MaterialTheme.styles.sidebarBorder

    Column(
        modifier = modifier
            .padding(start = 24.dp)
            .drawBehind {
                drawLine(
                    color = borderColor,
                    start = Offset(0f, 0f),
                    end = Offset(0f, size.height),
                    strokeWidth = 1.dp.toPx()
                )
            }
            .padding(start = 8.dp)
    ) {
        content()
    }
}

/**
 * A button for sub-menu items. Smaller than [SidebarMenuButton].
 * Auto-closes the sidebar on mobile when clicked.
 *
 * @param onClick Callback when the button is clicked.
 * @param modifier Modifier to apply.
 * @param isActive Whether this button is currently active/selected.
 * @param content The button content.
 */
@Composable
fun SidebarMenuSubButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isActive: Boolean = false,
    content: @Composable RowScope.() -> Unit
) {
    val sidebarState = LocalSidebarState.current
    val styles = MaterialTheme.styles
    val backgroundColor = if (isActive) styles.sidebarAccent else Color.Unspecified

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(MaterialTheme.radius.md))
            .background(backgroundColor)
            .clickable {
                onClick()
                if (sidebarState.isMobile) sidebarState.closeSidebar()
            }
            .padding(horizontal = 8.dp, vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        content()
    }
}

/**
 * An action button for sidebar menu items (e.g., a more/options button).
 * Hidden in collapsed icon mode. Rendered as a ghost icon button at the trailing edge.
 *
 * @param onClick Callback when the action is clicked.
 * @param modifier Modifier to apply.
 * @param content The action content (typically an icon).
 */
@Composable
fun SidebarMenuAction(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    val sidebarState = LocalSidebarState.current
    if (sidebarState.isCollapsedIcon) return

    Button(
        onClick = onClick,
        modifier = modifier.size(28.dp),
        size = ButtonSize.Icon,
        variant = ButtonVariant.Ghost
    ) {
        content()
    }
}

/**
 * A skeleton loading placeholder that mimics a sidebar menu button.
 *
 * @param modifier Modifier to apply.
 * @param showIcon Whether to show an icon skeleton placeholder.
 */
@Composable
fun SidebarMenuSkeleton(
    modifier: Modifier = Modifier,
    showIcon: Boolean = true
) {
    val sidebarState = LocalSidebarState.current

    if (sidebarState.isCollapsedIcon) {
        // Icon mode: just show icon skeleton
        if (showIcon) {
            Skeleton(
                modifier = modifier
                    .size(20.dp),
                shape = RoundedCornerShape(MaterialTheme.radius.md)
            )
        }
    } else {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (showIcon) {
                Skeleton(
                    modifier = Modifier.size(20.dp),
                    shape = CircleShape
                )
                Spacer(modifier = Modifier.width(8.dp))
            }
            Skeleton(
                modifier = Modifier
                    .height(14.dp)
                    .fillMaxWidth()
            )
        }
    }
}
