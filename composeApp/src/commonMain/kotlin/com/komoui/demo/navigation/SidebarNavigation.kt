package com.komoui.demo.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.komoui.demo.SidebarRoute
import com.komoui.demo.pages.sidebar.DashboardPage
import com.komoui.demo.pages.sidebar.ProjectPage
import com.komoui.demo.pages.sidebar.TaskPage

@Composable
fun SidebarNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = SidebarRoute.Dashboard.path,
    ) {
        composable(SidebarRoute.Dashboard.path) {
            DashboardPage(navController)
        }
        composable(SidebarRoute.Project.path) {
            ProjectPage(navController)
        }
        composable(SidebarRoute.Task.path) {
            TaskPage(navController)
        }
    }
}
