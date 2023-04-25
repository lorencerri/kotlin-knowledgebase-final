package com.cerri.knowledgebasefinal.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
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

    Scaffold(
        topBar = { Header("Documents", navController) },
        floatingActionButton = { ActionButton(navController) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(
                space = 16.dp,
                alignment = Alignment.CenterVertically
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
                modifier = Modifier.padding(bottom = 16.dp, start = 32.dp, end = 32.dp)
            )
            DocumentList(documents = getDocuments, navController = navController)
        }


    }

}
