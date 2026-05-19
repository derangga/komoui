package com.komoui.demo

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform