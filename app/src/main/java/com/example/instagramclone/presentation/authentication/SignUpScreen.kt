package com.example.instagramclone.presentation.authentication

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.instagramclone.R
import com.example.instagramclone.presentation.Toast
import com.example.instagramclone.utils.Response
import com.example.instagramclone.utils.Screens

@Composable
fun SignUpScreen(navController: NavHostController, viewModel: AuthenticationViewModel) {

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val userName = remember {
                mutableStateOf("")
            }
            val emailState = remember {
                mutableStateOf("")
            }
            val passwordState = remember {
                mutableStateOf("")
            }
            Image(
                painter = painterResource(id = R.drawable.instagram_logo),
                contentDescription = "SignUp Screen",
                modifier = Modifier
                    .width(250.dp)
                    .padding(top = 16.dp)
                    .padding(8.dp)
            )
            Text(
                text = "Sign In",
                modifier = Modifier.padding(10.dp),
                fontSize = 30.sp,
                fontFamily = FontFamily.SansSerif
            )
            OutlinedTextField(
                value = userName.value,
                onValueChange = {
                    userName.value = it
                },
                modifier = Modifier.padding(10.dp),
                label = {
                    Text(
                        text = "Enter User Name"
                    )
                }
            )
            OutlinedTextField(
                value = emailState.value,
                onValueChange = {
                    emailState.value = it
                },
                modifier = Modifier.padding(10.dp),
                label = {
                    Text(
                        text = "Enter Your Email"
                    )
                }
            )
            OutlinedTextField(
                value = passwordState.value,
                onValueChange = {
                    passwordState.value = it
                },
                modifier = Modifier.padding(10.dp),
                label = {
                    Text(
                        text = "Enter Your Password"
                    )
                },
                visualTransformation = PasswordVisualTransformation()
            )
            Button(
                onClick = {
                    viewModel.signUp(
                        email = emailState.value,
                        password = passwordState.value,
                        userName = userName.value
                    )
                }, modifier = Modifier.padding(8.dp)
            ) {
                Text(
                    text = " Sign Up"
                )
                when (val response = viewModel.signUpState.value) {
                    is Response.Loading -> {
                        CircularProgressIndicator(
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                    is Response.Success -> {
                        if (response.data) {
                            LaunchedEffect(key1 = true) {
                            navController.navigate(Screens.ProfileScreen.route) {
                                popUpTo(Screens.LoginScreen.route) {
                                    inclusive = true
                                  }
                              }
                           }
                        }
                        else {
                            Toast(message = "Sign Up Failed")
                            }
                        }
                        is Response.Error -> {
                            Toast(message = response.message)
                        }
                    }
                }
                Text(
                    text = "Already User? Sign In",
                    color = Color.Blue,
                    modifier = Modifier
                        .padding(8.dp)
                        .clickable {
                            navController.navigate(route = Screens.LoginScreen.route) {
                                launchSingleTop = true
                            }
                        }
                )
        }
    }
}