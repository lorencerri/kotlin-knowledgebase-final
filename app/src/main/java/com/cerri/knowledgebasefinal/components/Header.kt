package com.cerri.knowledgebasefinal.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun Header(
    title: String,
    navController: NavController,
    displayAccountButton: Boolean = true,
    showBack: Boolean = false
) {

    val navigationIcon: @Composable (() -> Unit)? = if (showBack) {
        {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.Filled.ArrowBack, null)
            }
        }
    } else null

    TopAppBar(
        title = { Text(title) },
        navigationIcon = navigationIcon,
        actions = {
            if (displayAccountButton) {
                IconButton(onClick = {
                    navController.navigate("Account_Screen")
                }) {
                    Icon(Icons.Filled.Person, null)
                }
            }
        }
    )

}