package com.cerri.knowledgebasefinal.components

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.cerri.knowledgebasefinal.Document

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DocumentList(documents: List<Document>, navController: NavController) {

    if (documents.isEmpty()) return LoadingIndicator()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(
            space = 16.dp,
            alignment = Alignment.CenterVertically
        )
    ) {
        items(documents.size, key = { it }) {
            Row(Modifier.animateItemPlacement()) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            Log.d("DocumentList", "Document clicked: ${documents[it].id}")
                            navController.navigate("Document_Screen?documentId=${documents[it].id}")
                        },
                    border = BorderStroke(1.dp, MaterialTheme.colors.primary)
                ) {
                    Column(modifier = Modifier.padding(15.dp)) {
                        Text(text = documents[it].title, fontWeight = FontWeight.Bold)
                        Text(text = documents[it].description)
                    }
                }
            }
        }
    }
}