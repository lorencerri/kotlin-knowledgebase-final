package com.cerri.knowledgebasefinal.components

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun ActionButton(navController: NavController) {
    FloatingActionButton(
        backgroundColor = MaterialTheme.colors.primary,
        onClick = {
            navController.navigate("New_Document_Screen")
        })
    {
        Icon(Icons.Filled.Add, "")
    }
}