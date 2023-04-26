package com.cerri.knowledgebasefinal.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun EditTextComponent(
    title: String,
    text: String,
    onTextChange: (title: String) -> Unit,
    onDelete: () -> Unit,
    onTitleChange: (title: String) -> Unit
) {

    var titleVal by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue(title))
    }

    var textVal by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue(text))
    }

    var isDeleted by rememberSaveable { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth(),
        border = BorderStroke(1.dp, MaterialTheme.colors.primary)
    ) {
        Column(modifier = Modifier.padding(top = 15.dp, start = 15.dp, end = 15.dp)) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = titleVal,
                onValueChange = {
                    titleVal = it
                    onTitleChange(titleVal.text)
                },
                label = { Text("Title") }
            )


            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = textVal,
                onValueChange = { textVal = it },
                label = {
                    Text("Content")
                    onTextChange(textVal.text)
                }
            )

            TextButton(enabled = !isDeleted,
                modifier = Modifier.fillMaxWidth(), onClick = {
                    onDelete()
                    isDeleted = true
                }) {
                Text(if (isDeleted) "Deleted!" else "Delete", color = Color.Red)
            }
        }
    }
}
