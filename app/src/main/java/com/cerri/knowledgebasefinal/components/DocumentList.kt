package com.cerri.knowledgebasefinal.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.cerri.knowledgebasefinal.Document

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