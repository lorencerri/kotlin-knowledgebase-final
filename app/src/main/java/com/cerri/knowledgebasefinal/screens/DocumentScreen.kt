package com.cerri.knowledgebasefinal.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
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
import androidx.navigation.NavHostController
import com.cerri.knowledgebasefinal.ApplicationViewModel
import com.cerri.knowledgebasefinal.Document
import com.cerri.knowledgebasefinal.components.Header
import com.cerri.knowledgebasefinal.components.LoadingIndicator

@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
@Composable
fun DocumentScreen(
    navController: NavHostController,
    applicationViewModel: ApplicationViewModel,
    documentId: String
) {
    var document by remember { mutableStateOf<Document?>(null) }
    LaunchedEffect("getUser") {
        document = applicationViewModel.getDocument(documentId)
        if (document == null) navController.navigate("Documents_Screen")
        Log.d("DocumentScreen", "Document: $document")
    }

    if (document == null) LoadingIndicator()
    else {
        Scaffold(
            topBar = { Header("Document", navController, showBack = true) },
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
                    text = document!!.title,
                    fontSize = 32.sp
                )
                Text(
                    text = document!!.description,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = 16.dp, start = 32.dp, end = 32.dp)
                )

            }
        }
    }
}