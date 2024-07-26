package com.example.alishopping.pages

import android.annotation.SuppressLint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.alishopping.Navigate
import com.example.alishopping.R
import com.example.alishopping.shopViewModel.ShopViewModel


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "RestrictedApi")
@Composable
fun LoginMenu(
    navController: NavController,
    viewModel: ShopViewModel,


    ) {
    val emailLogin by viewModel.emailLogin.observeAsState("")
    val passwordLogin by viewModel.passwordLogin.observeAsState("")



    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.main_menu_background)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier
                .size(400.dp, 700.dp)
                .padding(16.dp),
            colors = CardDefaults.elevatedCardColors(
                containerColor = colorResource(id = R.color.main_menu_background_card),
            )
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ali),
                    contentDescription = "",
                    modifier = Modifier.size(130.dp)
                )
                OutlinedTextField(
                    value = emailLogin,
                    onValueChange = { viewModel.setEmailLogin(it) },
                    textStyle = TextStyle(color = Color.DarkGray),


                    label = {
                        Text(
                            text = stringResource(id = R.string.email_login_label),
                            color = Color.Gray
                        )
                    },

                    singleLine = true,
                    modifier = Modifier.width(300.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    value = passwordLogin,
                    onValueChange = { viewModel.setPasswordLogin(it) },
                    visualTransformation = PasswordVisualTransformation(),
                    singleLine = true,
                    textStyle = TextStyle(color = Color.DarkGray),

                    label = {
                        Text(
                            text = stringResource(id = R.string.password_login_label),
                            color = Color.Gray
                        )
                    },
                    modifier = Modifier.width(300.dp)
                )
                Row(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {

                    Button(
                        onClick = {


                            viewModel.accessHomePage(
                                emailLogin,
                                passwordLogin,
                                navController = navController,
                                context = navController.context
                            )
                            navController.popBackStack()


                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray)
                    ) {
                        Text(
                            text = stringResource(id = R.string.login_menu_login),
                            color = Color.White
                        )
                    }
                    TextButton(onClick = {
                        navController.navigate(Navigate.SignUp)
                    }) {
                        Text(
                            text = stringResource(id = R.string.login_menu_register_go),
                            color = Color.Gray
                        )
                    }

                }
                Spacer(modifier = Modifier.height(32.dp))

                LineWithText()



                Surface(color = colorResource(id = R.color.white)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.facebook),
                            contentDescription = "",
                            modifier = Modifier
                                .size(50.dp)

                                .clickable(onClick = {

                                }),


                            )
                        Image(
                            painter = painterResource(id = R.drawable.google),
                            contentDescription = "",
                            modifier = Modifier
                                .size(50.dp)
                                .clickable(onClick = {

                                }),


                            )
                        Image(
                            painter = painterResource(id = R.drawable.x),
                            contentDescription = "",
                            modifier = Modifier
                                .size(50.dp)

                                .clickable(onClick = {

                                }),


                            )
                    }

                }


            }

        }


    }
}


@Composable
fun LineWithText() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .height(50.dp)
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val canvasWidth = size.width
            val canvasHeight = size.height

            drawLine(
                color = Color.Black,
                start = Offset(0f, canvasHeight / 2),
                end = Offset((canvasWidth - 110.dp.toPx()) / 2, canvasHeight / 2),
                strokeWidth = 2f
            )

            drawLine(
                color = Color.Black,
                start = Offset((canvasWidth + 110.dp.toPx()) / 2, canvasHeight / 2),
                end = Offset(canvasWidth, canvasHeight / 2),
                strokeWidth = 2f
            )
        }

        Text(
            text = stringResource(id = R.string.or_sign_up_with),
            style = TextStyle(
                color = Color.Gray,
                fontSize = 16.sp
            ),
            modifier = Modifier

                .padding(horizontal = 8.dp)
        )
    }
}



