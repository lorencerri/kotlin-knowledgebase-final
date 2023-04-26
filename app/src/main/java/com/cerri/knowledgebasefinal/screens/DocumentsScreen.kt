package com.cerri.knowledgebasefinal.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.cerri.knowledgebasefinal.ApplicationViewModel
import com.cerri.knowledgebasefinal.components.ActionButton
import com.cerri.knowledgebasefinal.components.DocumentList
import com.cerri.knowledgebasefinal.components.Header

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DocumentsScreen(
    navController: NavController,
    applicationViewModel: ApplicationViewModel
) {
    val getDocuments = applicationViewModel.documents.value
    var isLoggedIn by remember { mutableStateOf(false) }

    LaunchedEffect("getUser") {
        applicationViewModel.getDocuments()
        val user = applicationViewModel.getUserOrNull()
        if (user != null) isLoggedIn = true
    }

    Scaffold(
        topBar = { Header("Documents", navController) },
        floatingActionButton = { if (isLoggedIn) ActionButton(navController) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(
                space = 16.dp,
            ),
        ) {
            Text(
                modifier = Modifier.padding(start = 32.dp, end = 32.dp),
                text = "Techgenix",
                fontSize = 32.sp
            )
            Text(
                text = "Welcome to our knowledge base, please select a document below.",
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(start = 32.dp, end = 32.dp)
            )
            Divider(modifier = Modifier.padding(24.dp))
            DocumentList(documents = getDocuments, navController = navController)
        }


    }

}
