package com.cerri.knowledgebasefinal.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.cerri.knowledgebasefinal.SupabaseViewModel
import com.cerri.knowledgebasefinal.components.Header
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun NewDocumentScreen(
    navController: NavController,
    supabaseViewModel: SupabaseViewModel = viewModel()
) {

    var titleVal by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue("", TextRange(0, 7)))
    }

    var descriptionVal by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue("", TextRange(0, 7)))
    }

    Scaffold(topBar = { Header("Add Document") }) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(
                space = 16.dp,
                alignment = Alignment.CenterVertically
            ),
        ) {

            TextField(
                value = titleVal,
                onValueChange = { titleVal = it },
                label = { Text("Title") }
            )

            TextField(
                value = descriptionVal,
                onValueChange = { descriptionVal = it },
                label = { Text("Description") }
            )

            Button(onClick = {
                val scope = MainScope()

                scope.launch {
                    coroutineScope {
                        supabaseViewModel.createDocument(
                            title = titleVal.text,
                            description = descriptionVal.text
                        )
                        navController.navigate("Documents_Screen")
                    }
                }

            }) {
                Text("Add Document")
            }
        }
    }
}
