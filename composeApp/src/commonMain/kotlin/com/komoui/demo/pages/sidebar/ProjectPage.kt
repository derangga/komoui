package com.komoui.demo.pages.sidebar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun ProjectPage(nav: NavHostController) {
    Box(modifier = Modifier.padding(horizontal = 8.dp)) {
        Text(text = "Welcome to the Project!")
    }
}