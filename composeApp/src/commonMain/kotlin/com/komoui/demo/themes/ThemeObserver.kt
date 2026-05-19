package com.komoui.demo.themes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import com.komoui.themes.DarkStyles
import com.komoui.themes.LightStyles
import com.komoui.themes.KomoStyles
import com.komoui.demo.themes.styles.AmberMinimal
import com.komoui.demo.themes.styles.AmberMinimalDark
import com.komoui.demo.themes.styles.BoltTech
import com.komoui.demo.themes.styles.BoltTechDark
import com.komoui.demo.themes.styles.Bubblegum
import com.komoui.demo.themes.styles.BubblegumDark
import com.komoui.demo.themes.styles.Caffeine
import com.komoui.demo.themes.styles.CaffeineDark
import com.komoui.demo.themes.styles.Catppuccin
import com.komoui.demo.themes.styles.CatppuccinDark
import com.komoui.demo.themes.styles.Claude
import com.komoui.demo.themes.styles.ClaudeDark
import com.komoui.demo.themes.styles.ModernMinimal
import com.komoui.demo.themes.styles.ModernMinimalDark
import com.komoui.demo.themes.styles.NeoBrutalism
import com.komoui.demo.themes.styles.NeoBrutalismDark
import com.komoui.demo.themes.styles.OceanBreeze
import com.komoui.demo.themes.styles.OceanBreezeDark
import com.komoui.demo.themes.styles.PastelDreams
import com.komoui.demo.themes.styles.PastelDreamsDark
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.withContext

object StylesSelection {
    val styles = listOf(
        "Default",
        "Amber Minimal",
        "Bolt Tech",
        "Bubblegum",
        "Catppuccin",
        "Caffeine",
        "Claude",
        "Modern Minimal",
        "NeoBrutalism",
        "Ocean Breeze",
        "Pastel Dreams",
    )
}

@Composable
fun <T> ThemeObserver(
    flow: Flow<T>,
    key1: Any? = null,
    key2: Any? = null,
    onEvent: (T) -> Unit
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(lifecycleOwner.lifecycle, key1, key2, flow) {
        lifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            withContext(Dispatchers.Main.immediate) {
                flow.collect(onEvent)
            }
        }
    }
}

object ThemeProvider {
    private val _events = Channel<ThemeEvent>()
    val events = _events.receiveAsFlow()

    suspend fun sendEvent(themes: ThemeEvent) {
        _events.send(themes)
    }
}

internal fun isDarkTheme(theme: String, systemTheme: Boolean): Boolean {
    return when (theme) {
        "light" -> false
        "dark" -> true
        else -> systemTheme
    }
}

internal fun getStyles(styles: String = "Default"): Pair<KomoStyles, KomoStyles> {
    return when (styles) {
        "Bubblegum" -> Bubblegum to BubblegumDark
        "Amber Minimal" -> AmberMinimal to AmberMinimalDark
        "Bolt Tech" -> BoltTech to BoltTechDark
        "Catppuccin" -> Catppuccin to CatppuccinDark
        "Caffeine" -> Caffeine to CaffeineDark
        "Claude" -> Claude to ClaudeDark
        "Modern Minimal" -> ModernMinimal to ModernMinimalDark
        "NeoBrutalism" -> NeoBrutalism to NeoBrutalismDark
        "Ocean Breeze" -> OceanBreeze to OceanBreezeDark
        "Pastel Dreams" -> PastelDreams to PastelDreamsDark
        else -> LightStyles to DarkStyles
    }
}