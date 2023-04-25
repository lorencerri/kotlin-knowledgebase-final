package com.cerri.knowledgebasefinal.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun Header(title: String, navController: NavController) {
    TopAppBar(
        title = { Text(title) },
        actions = {
            IconButton(onClick = {
                navController.navigate("Account_Screen")
            }) {
                Icon(Icons.Filled.Person, null)
            }
        }
    )
}