package com.cerri.knowledgebasefinal.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.cerri.knowledgebasefinal.ApplicationViewModel
import com.cerri.knowledgebasefinal.screens.AccountScreen
import com.cerri.knowledgebasefinal.screens.DocumentScreen
import com.cerri.knowledgebasefinal.screens.DocumentsScreen
import com.cerri.knowledgebasefinal.screens.NewDocumentScreen
import com.cerri.knowledgebasefinal.screens.SignInScreen
import com.cerri.knowledgebasefinal.screens.SignUpScreen

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
        composable(
            route = Screens.DocumentScreen.route, arguments = listOf(
                navArgument("documentId") { defaultValue = "" },
            )
        ) {
            Log.d("NavigationGraph", it.arguments.toString())
            DocumentScreen(
                navController,
                applicationViewModel,
                documentId = it.arguments?.getString("documentId") ?: "0"
            )
        }
    }
}