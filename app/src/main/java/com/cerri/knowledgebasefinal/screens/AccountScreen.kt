package com.cerri.knowledgebasefinal.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.cerri.knowledgebasefinal.ApplicationViewModel
import com.cerri.knowledgebasefinal.User
import com.cerri.knowledgebasefinal.components.Header
import com.cerri.knowledgebasefinal.components.LoadingIndicator
import io.github.jan.supabase.gotrue.gotrue
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AccountScreen(
    navController: NavController,
    applicationViewModel: ApplicationViewModel,
) {

    var usernameVal by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue("", TextRange(0, 7)))
    }

    var userVal by remember { mutableStateOf<User?>(null) }

    LaunchedEffect("getUser") {
        val user = applicationViewModel.getUserOrNull()
        if (user == null) {
            navController.navigate("Sign_In_Screen")
        } else {
            userVal = user
            usernameVal = TextFieldValue(user.username, TextRange(0, user.username.length))
        }
    }

    if (userVal == null) LoadingIndicator()
    else {
        Scaffold(topBar = { Header("Account", navController, displayAccountButton = false) }) {
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
                Text(
                    modifier = Modifier.padding(start = 32.dp, end = 32.dp),
                    text = "Hello, ${userVal?.username}!",
                    fontSize = 32.sp
                )
                Text(
                    modifier = Modifier.padding(start = 32.dp, end = 32.dp),
                    text = "You can change your personal information below."
                )
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
                    value = usernameVal,
                    onValueChange = { usernameVal = it },
                    label = { Text("Username") }
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
                                applicationViewModel.updateUsername(usernameVal.text)
                                navController.navigate("Account_Screen")
                            }
                        }

                    }) {
                        Text("Update")
                    }
                }

                Divider(modifier = Modifier.padding(24.dp))
                Button(modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 96.dp, end = 96.dp),
                    onClick = {
                        val scope = MainScope()

                        scope.launch {
                            coroutineScope {
                                applicationViewModel.client.gotrue.invalidateSession()
                                navController.navigate("Sign_In_Screen")
                            }
                        }

                    }) {
                    Text("Logout")
                }

            }
        }
    }
}