package com.cerri.knowledgebasefinal.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TextComponent(title: String, text: String, clickable: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        onClick = clickable,
        border = BorderStroke(1.dp, MaterialTheme.colors.primary)
    ) {
        Column(modifier = Modifier.padding(15.dp)) {
            Text(text = title, fontWeight = FontWeight.Bold)
            Text(text = text)
        }
    }
}