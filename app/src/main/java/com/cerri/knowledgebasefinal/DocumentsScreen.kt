package com.cerri.knowledgebasefinal

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
@Preview(showBackground = true)
fun DocumentsScreen(
    documentsViewModel: DocumentsViewModel = viewModel()
) {
    val getDocuments = documentsViewModel.state.value
    Log.d("DataScreen", "getData: $getDocuments")

    Scaffold(
        topBar = { Header() },
        floatingActionButton = { ActionButton() }
    ) {
        DocumentList(documents = getDocuments)
    }

}

@Composable
fun ActionButton() {
    FloatingActionButton(onClick = { /*TODO*/ }) {
        Text(text = "+", fontSize = 24.sp, color = Color.White)
    }
}

@Composable
fun Header() {
    TopAppBar {
        Text("Documents", Modifier.padding(8.dp), fontSize = 16.sp)
    }
}

@Composable
fun DocumentList(documents: List<Document>) {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ) {
        if (documents.isEmpty()) {
            CircularProgressIndicator()
        }

        documents.forEach {
            Text(text = "${it.title} - ${it.description}")
        }
    }
}