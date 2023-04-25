package com.cerri.knowledgebasefinal.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cerri.knowledgebasefinal.ApplicationViewModel
import com.cerri.knowledgebasefinal.screens.*

@Composable
fun NavigationGraph(
    navController: NavHostController = rememberNavController(),
    applicationViewModel: ApplicationViewModel = ApplicationViewModel(),
) {
    NavHost(
        navController = navController,
        startDestination = Screens.DocumentsScreen.route
    ) {
        composable(route = Screens.DocumentsScreen.route) {
            DocumentsScreen(navController, applicationViewModel)
        }
        composable(route = Screens.NewDocumentScreen.route) {
            NewDocumentScreen(navController, applicationViewModel)
        }
        composable(route = Screens.AccountScreen.route) {
            AccountScreen(navController, applicationViewModel)
        }
        composable(route = Screens.SignUpScreen.route) {
            SignUpScreen(navController, applicationViewModel)
        }
        composable(route = Screens.SignInScreen.route) {
            SignInScreen(navController, applicationViewModel)
        }
    }
}