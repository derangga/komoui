package com.komoui.kmp

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime

expect fun LocalDateTime.format(format: String): String

expect fun LocalDate.format(format: String): String