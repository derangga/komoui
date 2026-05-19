package com.shadcn.ui.themes

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

object DarkStyles : KomoStyles {
    override val background: Color = Color(0xFF0A0A0A)
    override val foreground: Color = Color(0xFFFAFAFA)
    override val card: Color = Color(0xFF171717)
    override val cardForeground: Color = Color(0xFFFAFAFA)
    override val popover: Color = Color(0xFF262626)
    override val popoverForeground: Color = Color(0xFFFAFAFA)
    override val primary: Color = Color(0xFFE5E5E5)
    override val primaryForeground: Color = Color(0xFF171717)
    override val secondary: Color = Color(0xFF262626)
    override val secondaryForeground: Color = Color(0xFFFAFAFA)
    override val muted: Color = Color(0xFF262626)
    override val mutedForeground: Color = Color(0xFFA1A1A1)
    override val accent: Color = Color(0xFF404040)
    override val accentForeground: Color = Color(0xFFFAFAFA)
    override val destructive: Color = Color(0xFFFF6467)
    override val destructiveForeground: Color = Color(0xFFFAFAFA)
    override val border: Color = Color.White.copy(alpha = 0.10f)
    override val input: Color = Color.White.copy(alpha = 0.15f)
    override val ring: Color = Color(0xFF737373)

    override val chart1: Color = Color(0xFF2563EB)
    override val chart2: Color = Color(0xFF34D399)
    override val chart3: Color = Color(0xFFF4A261)
    override val chart4: Color = Color(0xFFA78BFA)
    override val chart5: Color = Color(0xFFF43F5E)

    override val sidebar: Color = Color(0xFF171717)
    override val sidebarForeground: Color = Color(0xFFFAFAFA)
    override val sidebarPrimary: Color = Color(0xFF1447E6) // Updated
    override val sidebarPrimaryForeground: Color = Color(0xFFFAFAFA)
    override val sidebarAccent: Color = Color(0xFF262626)
    override val sidebarAccentForeground: Color = Color(0xFFFAFAFA)
    override val sidebarBorder: Color = Color.White.copy(alpha = 0.10f)
    override val sidebarRing: Color = Color(0xFF525252)
    override val snackbar: Color = Color(0xFF262626)

    @Composable
    override fun shadow2xs(): List<BoxShadow> = listOf(
        BoxShadow(
            offsetX = 0.dp,
            offsetY = 1.dp,
            blurRadius = 3.dp,
            spread = 0.dp,
            color = MaterialTheme.styles.border
        )
    )
    @Composable
    override fun shadowXs(): List<BoxShadow> = listOf(
        BoxShadow(
            offsetX = 0.dp,
            offsetY = 1.dp,
            blurRadius = 3.dp,
            spread = 0.dp,
            color = MaterialTheme.styles.border
        )
    )
    @Composable
    override fun shadowSm(): List<BoxShadow> = listOf(
        BoxShadow(
            offsetX = 0.dp,
            offsetY = 1.dp,
            blurRadius = 3.dp,
            spread = 0.dp,
            color = MaterialTheme.styles.border
        ),
        BoxShadow(
            offsetX = 0.dp,
            offsetY = 1.dp,
            blurRadius = 2.dp,
            spread = -1.dp,
            color = MaterialTheme.styles.border
        )
    )
    @Composable
    override fun shadow(): List<BoxShadow> = listOf(
        BoxShadow(
            offsetX = 0.dp,
            offsetY = 1.dp,
            blurRadius = 3.dp,
            spread = 0.dp,
            color = MaterialTheme.styles.border
        ),
        BoxShadow(
            offsetX = 0.dp,
            offsetY = 1.dp,
            blurRadius = 2.dp,
            spread = -1.dp,
            color = MaterialTheme.styles.border
        )
    )
    @Composable
    override fun shadowMd(): List<BoxShadow> = listOf(
        BoxShadow(
            offsetX = 0.dp,
            offsetY = 1.dp,
            blurRadius = 3.dp,
            spread = 0.dp,
            color = MaterialTheme.styles.border
        ),
        BoxShadow(
            offsetX = 0.dp,
            offsetY = 2.dp,
            blurRadius = 4.dp,
            spread = -1.dp,
            color = MaterialTheme.styles.border
        )
    )
    @Composable
    override fun shadowLg(): List<BoxShadow> = listOf(
        BoxShadow(
            offsetX = 0.dp,
            offsetY = 1.dp,
            blurRadius = 3.dp,
            spread = 0.dp,
            color = MaterialTheme.styles.border
        ),
        BoxShadow(
            offsetX = 0.dp,
            offsetY = 4.dp,
            blurRadius = 6.dp,
            spread = -1.dp,
            color = MaterialTheme.styles.border
        )
    )
    @Composable
    override fun shadowXl(): List<BoxShadow> = listOf(
        BoxShadow(
            offsetX = 0.dp,
            offsetY = 1.dp,
            blurRadius = 3.dp,
            spread = 0.dp,
            color = MaterialTheme.styles.border
        ),
        BoxShadow(
            offsetX = 0.dp,
            offsetY = 8.dp,
            blurRadius = 10.dp,
            spread = -1.dp,
            color = MaterialTheme.styles.border
        )
    )
    @Composable
    override fun shadow2xl(): List<BoxShadow> = listOf(
        BoxShadow(
            offsetX = 0.dp,
            offsetY = 1.dp,
            blurRadius = 3.dp,
            spread = 0.dp,
            color = MaterialTheme.styles.border
        )
    )
}