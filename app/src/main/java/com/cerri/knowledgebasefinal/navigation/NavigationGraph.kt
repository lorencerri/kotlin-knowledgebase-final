package com.cerri.knowledgebasefinal.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cerri.knowledgebasefinal.screens.DocumentsScreen
import com.cerri.knowledgebasefinal.screens.NewDocumentScreen

@Composable
fun NavigationGraph(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screens.DocumentsScreen.route
    ) {
        composable(route = Screens.DocumentsScreen.route) {
            DocumentsScreen(navController)
        }
        composable(route = Screens.NewDocumentScreen.route) {
            NewDocumentScreen(navController)
        }
    }
}