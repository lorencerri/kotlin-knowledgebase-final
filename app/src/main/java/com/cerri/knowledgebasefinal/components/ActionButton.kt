package com.cerri.knowledgebasefinal.components

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun ActionButton(navController: NavController) {
    FloatingActionButton(
        backgroundColor = MaterialTheme.colors.primary,
        onClick = {
            navController.navigate("New_Document_Screen")
        })
    {
        Text(text = "+", fontSize = 24.sp, color = Color.White, textAlign = TextAlign.Center)
    }
}