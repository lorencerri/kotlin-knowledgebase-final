package com.cerri.knowledgebasefinal.presentation.documents_screen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun NewDocumentScreen(
    navController: NavController,
    documentsViewModel: DocumentsViewModel = viewModel()
) {
    val getDocuments = documentsViewModel.state.value
    Log.d("DataScreen", "getData: $getDocuments")

    Scaffold(topBar = { Header("Add Document") }) {
        DocumentList(documents = getDocuments)
    }
}