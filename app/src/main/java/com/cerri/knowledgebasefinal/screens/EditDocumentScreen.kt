package com.cerri.knowledgebasefinal.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.cerri.knowledgebasefinal.ApplicationViewModel
import com.cerri.knowledgebasefinal.Document
import com.cerri.knowledgebasefinal.components.Header
import com.cerri.knowledgebasefinal.components.LoadingIndicator
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun EditDocumentScreen(
    navController: NavHostController,
    applicationViewModel: ApplicationViewModel,
    documentId: String
) {
    var document by remember { mutableStateOf<Document?>(null) }

    var titleVal by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue(""))
    }

    var descriptionVal by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue(""))
    }

    LaunchedEffect("getUser") {
        val user = applicationViewModel.getUserOrNull()
        if (user != null) {
            document = applicationViewModel.getDocument(documentId)
            if (document == null) navController.navigate("Documents_Screen")
            titleVal = TextFieldValue(document?.title ?: "")
            descriptionVal = TextFieldValue(document?.description ?: "")
        }
    }


    if (document == null) LoadingIndicator()
    else {
        Scaffold(topBar = { Header("Edit Document", navController, true) }) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(
                    space = 16.dp,
                    alignment = Alignment.CenterVertically
                ),
            ) {
                Text("Hello!", fontSize = 32.sp)
                Text("Please edit the details of your document below.")
            }


            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(
                    space = 16.dp,
                    alignment = Alignment.CenterVertically
                ),
            ) {


                OutlinedTextField(
                    value = titleVal,
                    onValueChange = { titleVal = it },
                    label = { Text("Title") }
                )

                OutlinedTextField(
                    value = descriptionVal,
                    onValueChange = { descriptionVal = it },
                    label = { Text("Description") }
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    TextButton(onClick = {
                        val scope = MainScope()

                        scope.launch {
                            coroutineScope {
                                applicationViewModel.deleteDocument(documentId)
                                navController.popBackStack()
                            }
                        }
                    }) {
                        Text("Delete", color = androidx.compose.ui.graphics.Color.Red)
                    }

                    Button(onClick = {
                        val scope = MainScope()

                        scope.launch {
                            coroutineScope {
                                applicationViewModel.editDocument(
                                    documentId = documentId,
                                    title = titleVal.text,
                                    description = descriptionVal.text
                                )
                                navController.popBackStack()
                            }
                        }

                    }) {
                        Text("Update")
                    }
                }


            }
        }
    }
}