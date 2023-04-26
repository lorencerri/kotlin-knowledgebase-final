package com.cerri.knowledgebasefinal.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.MaterialTheme
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
import com.cerri.knowledgebasefinal.Component
import com.cerri.knowledgebasefinal.Document
import com.cerri.knowledgebasefinal.components.EditTextComponent
import com.cerri.knowledgebasefinal.components.Header
import com.cerri.knowledgebasefinal.components.LoadingIndicator
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
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

    var componentCount by remember { mutableStateOf(0) }

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
            componentCount = document?.components?.size ?: 0
        }
    }


    if (document == null) LoadingIndicator()
    else {
        Scaffold(floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = {
                    document!!.components.add(Component())
                    componentCount++
                    Log.d("Components", document!!.components.toString())
                },
                text = { Text("Add Component") },
                backgroundColor = MaterialTheme.colors.primary,
            )
        }, topBar = { Header("Edit Document", navController, true, true) }) {

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


                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 16.dp),
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
                                        description = descriptionVal.text,
                                        components = document!!.components
                                    )
                                    navController.popBackStack()
                                }
                            }

                        }) {
                            Text("Update")
                        }

                    }

                    Divider(modifier = Modifier.padding(24.dp))

                    LazyColumn(
                        modifier = Modifier
                            .padding(start = 16.dp, end = 16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(
                            space = 16.dp,
                            alignment = Alignment.CenterVertically
                        )
                    ) {
                        items(componentCount, key = { it }) {
                            Row(Modifier.animateItemPlacement()) {
                                EditTextComponent(
                                    title = document!!.components[it].title,
                                    text = document!!.components[it].content,
                                    onTitleChange = { title: String ->
                                        document!!.components[it].title = title
                                    },
                                    onTextChange = { text: String ->
                                        document!!.components[it].content = text
                                    },
                                    onDelete = {
                                        document!!.components[it].deleted = true
                                    }
                                )
                            }
                        }
                    }
                }

            }
        }
    }
}