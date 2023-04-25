package com.cerri.knowledgebasefinal.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
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
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.cerri.knowledgebasefinal.ApplicationViewModel
import com.cerri.knowledgebasefinal.components.Header
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun NewDocumentScreen(
    navController: NavController,
    applicationViewModel: ApplicationViewModel
) {

    var titleVal by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue("", TextRange(0, 7)))
    }

    var descriptionVal by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue("", TextRange(0, 7)))
    }

    Scaffold(topBar = { Header("New Document", navController) }) {

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
            Text("Please enter the details of your new document below.")
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
                    navController.navigate("Documents_Screen")
                }) {
                    Text("Back")
                }

                Button(onClick = {
                    val scope = MainScope()

                    scope.launch {
                        coroutineScope {
                            applicationViewModel.createDocument(
                                title = titleVal.text,
                                description = descriptionVal.text
                            )
                            navController.navigate("Documents_Screen")
                        }
                    }

                }) {
                    Text("New Document")
                }
            }


        }
    }
}
