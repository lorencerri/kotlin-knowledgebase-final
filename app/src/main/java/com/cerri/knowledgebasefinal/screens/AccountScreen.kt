package com.cerri.knowledgebasefinal.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.cerri.knowledgebasefinal.ApplicationViewModel
import com.cerri.knowledgebasefinal.components.Header

@Composable
fun SignUpScreen(
    navController: NavController,
    applicationViewModel: ApplicationViewModel
) {
    Header(title = "Account", navController = navController)
}