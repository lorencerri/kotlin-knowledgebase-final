package com.cerri.knowledgebasefinal.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.cerri.knowledgebasefinal.Document

@Composable
fun DocumentList(documents: List<Document>) {

    if (documents.isEmpty()) return LoadingIndicator()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        documents.forEach {
            Card(modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .clickable {}) {
                Column(modifier = Modifier.padding(15.dp)) {
                    Text(text = it.title, fontWeight = FontWeight.Bold)
                    Text(text = it.description)
                }
            }
        }
    }
}