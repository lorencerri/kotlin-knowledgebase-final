package com.cerri.knowledgebasefinal.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Header(title: String) {
    TopAppBar {
        Text(title, Modifier.padding(8.dp), fontSize = 16.sp)
    }
}