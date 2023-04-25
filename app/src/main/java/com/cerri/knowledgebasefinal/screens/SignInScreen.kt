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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.cerri.knowledgebasefinal.ApplicationViewModel
import com.cerri.knowledgebasefinal.components.Header
import io.github.jan.supabase.gotrue.gotrue
import io.github.jan.supabase.gotrue.providers.builtin.Email
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SignInScreen(
    navController: NavController,
    applicationViewModel: ApplicationViewModel
) {


    var emailVal by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue("", TextRange(0, 7)))
    }

    var passwordVal by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue("", TextRange(0, 7)))
    }

    val scaffoldState = rememberScaffoldState()

    Scaffold(topBar = { Header("Sign In", navController) }, scaffoldState = scaffoldState) {

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
            Text("Welcome back!", fontSize = 32.sp)
            Text("Please sign in to your account below.")
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
                value = emailVal,
                onValueChange = { emailVal = it },
                label = { Text("Email") }
            )

            OutlinedTextField(
                value = passwordVal,
                onValueChange = { passwordVal = it },
                label = { Text("Password") },
                visualTransformation = PasswordVisualTransformation()
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
                            runCatching {
                                // Attempt to add user metadata to database
                                applicationViewModel.client.gotrue.loginWith(Email) {
                                    email = emailVal.text
                                    password = passwordVal.text
                                }
                            }.onFailure {
                                scaffoldState.snackbarHostState.showSnackbar("Unable to login, incorrect email or password?")
                            }
                                .onSuccess {
                                    navController.navigate("Account_Screen")
                                }
                        }
                    }

                }) {
                    Text("Sign In")
                }


            }

            Divider(modifier = Modifier.padding(24.dp))

            Text("Need an account?", fontSize = 28.sp)

            TextButton(
                onClick = {
                    navController.navigate("Sign_Up_Screen")
                }, modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 96.dp, end = 96.dp)
            ) {
                Text("Create Account")
            }

        }
    }
}