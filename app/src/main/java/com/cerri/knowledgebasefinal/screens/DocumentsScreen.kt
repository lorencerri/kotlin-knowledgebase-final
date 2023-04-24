package com.cerri.knowledgebasefinal.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.cerri.knowledgebasefinal.SupabaseViewModel
import com.cerri.knowledgebasefinal.components.ActionButton
import com.cerri.knowledgebasefinal.components.DocumentList
import com.cerri.knowledgebasefinal.components.Header

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DocumentsScreen(
    navController: NavController,
    supabaseViewModel: SupabaseViewModel = viewModel()
) {
    val getDocuments = supabaseViewModel.documents.value
    Log.d("DataScreen", "getData: $getDocuments")

    Scaffold(
        topBar = { Header("Documents") },
        floatingActionButton = { ActionButton(navController) }
    ) {
        DocumentList(documents = getDocuments)
    }

}
