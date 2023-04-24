package com.cerri.knowledgebasefinal.components

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun ActionButton(navController: NavController) {
    FloatingActionButton(onClick = {
        navController.navigate("New_Document_Screen")
    }) {
        Text(text = "+", fontSize = 24.sp, color = Color.White)
    }
}