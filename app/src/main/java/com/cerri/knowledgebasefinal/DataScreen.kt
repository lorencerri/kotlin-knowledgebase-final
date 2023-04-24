package com.cerri.knowledgebasefinal

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
@Preview(showBackground = true)
fun DataScreen(
    dataViewModel: DataViewModel = viewModel()
) {
    val getData = dataViewModel.state.value
    Log.d("DataScreen", "getData: $getData")

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ) {
        getData.forEach {
            Text(text = "${it.title} - ${it.description}")
        }
    }
}