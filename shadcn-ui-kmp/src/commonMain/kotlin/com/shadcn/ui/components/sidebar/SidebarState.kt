package com.shadcn.ui.components.sidebar

/**
 * Defines how the sidebar behaves when collapsed.
 */
enum class SidebarCollapsible {
    /** Slides off-screen when collapsed (default behavior). */
    Offcanvas,
    /** Collapses to an icon-only rail when collapsed. */
    Icon,
    /** Always visible, cannot be collapsed. */
    None
}

/**
 * Holds the current state of the sidebar.
 *
 * @param isOpen Whether the sidebar is currently open.
 * @param isMobile Whether the current screen is in mobile mode.
 * @param collapsible The collapsible behavior of the sidebar.
 * @param toggleSidebar Callback to toggle the sidebar open/closed.
 * @param closeSidebar Callback to close the sidebar.
 */
data class SidebarState(
    val isOpen: Boolean,
    val isMobile: Boolean,
    val collapsible: SidebarCollapsible = SidebarCollapsible.Offcanvas,
    val toggleSidebar: () -> Unit,
    val closeSidebar: () -> Unit
) {
    /**
     * Whether the sidebar is in collapsed icon mode (icon-only rail).
     * True when: sidebar is closed, not mobile, and collapsible is [SidebarCollapsible.Icon].
     */
    val isCollapsedIcon: Boolean
        get() = !isOpen && !isMobile && collapsible == SidebarCollapsible.Icon
}
