package com.shadcn.ui.components.sidebar

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import com.shadcn.ui.kmp.getScreenWidth

private const val MOBILE_BREAKPOINT = 768

val LocalSidebarState = compositionLocalOf<SidebarState> {
    error("SidebarProvider not found")
}

/**
 * Provider for sidebar state management.
 *
 * @param defaultOpen Whether the sidebar starts open on desktop. Always starts closed on mobile.
 * @param collapsible The collapsible behavior of the sidebar.
 * @param content The content to be wrapped with sidebar state.
 */
@Composable
fun SidebarProvider(
    defaultOpen: Boolean = false,
    collapsible: SidebarCollapsible = SidebarCollapsible.Offcanvas,
    content: @Composable () -> Unit
) {

    val screenWidthDp = getScreenWidth()

    // Determine if we're in mobile mode
    val isMobile = screenWidthDp < MOBILE_BREAKPOINT.dp

    // Sidebar state - respects defaultOpen for both mobile and desktop
    var isOpen by rememberSaveable {
        mutableStateOf(if (isMobile) false else defaultOpen)
    }

    // Auto-close sidebar when switching to mobile mode, but preserve desktop state
    LaunchedEffect(isMobile) {
        if (isMobile) {
            isOpen = false
        }
    }

    val sidebarState = remember(isOpen, isMobile, collapsible) {
        SidebarState(
            isOpen = isOpen,
            isMobile = isMobile,
            collapsible = collapsible,
            toggleSidebar = {
                if (collapsible != SidebarCollapsible.None) {
                    isOpen = !isOpen
                }
            },
            closeSidebar = { isOpen = false }
        )
    }

    CompositionLocalProvider(
        LocalSidebarState provides sidebarState
    ) {
        content()
    }
}
