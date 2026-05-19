package com.komoui.demo.themes

import com.komoui.themes.KomoStyles

sealed class ThemeEvent {
    data class Styles(val key: String, val styles: Pair<KomoStyles, KomoStyles>): ThemeEvent()
    data class Theme(val theme: String): ThemeEvent()
}