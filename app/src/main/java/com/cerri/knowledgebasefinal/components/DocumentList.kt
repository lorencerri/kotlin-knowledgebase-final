package com.cerri.knowledgebasefinal.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
        )
    ) {
        items(documents.size, key = { it }) {
            Row(Modifier.animateItemPlacement()) {
                TextComponent(title = documents[it].title, text = documents[it].description) {
                    navController.navigate(
                        "Document_Screen?documentId=${documents[it].id}"
                    )
                }
            }
        }
    }
}