package dr.shadcn.kmp.themes

import com.shadcn.ui.themes.KomoStyles

sealed class ThemeEvent {
    data class Styles(val key: String, val styles: Pair<KomoStyles, KomoStyles>): ThemeEvent()
    data class Theme(val theme: String): ThemeEvent()
}