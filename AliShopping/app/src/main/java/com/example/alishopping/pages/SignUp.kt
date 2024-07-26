package com.example.alishopping.pages

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.alishopping.Navigate
import com.example.alishopping.R
import com.example.alishopping.shopViewModel.ShopViewModel


@Composable
fun SignUp(viewModel: ShopViewModel, navController: NavController) {

    val email by viewModel.email.observeAsState("")
    val password by viewModel.password.observeAsState("")
    val address by viewModel.address.observeAsState("")
    val tel by viewModel.tel.observeAsState("")
    val allProduct by viewModel.allProduct.observeAsState(listOf())
    val context = LocalContext.current

    BackHandler {
        navController.popBackStack(Navigate.SignUp, true)

    }

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
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    item {
                        OutlinedTextField(
                            value = email,
                            onValueChange = { viewModel._email.value = it },
                            singleLine = true,
                            textStyle = TextStyle(color = Color.DarkGray),
                            keyboardOptions = KeyboardOptions.Default.copy(
                                keyboardType = KeyboardType.Email
                            ),
                            label = {
                                Text(
                                    text = stringResource(id = R.string.signup_email_label),
                                    color = Color.Gray
                                )

                            },

                            modifier = Modifier.fillMaxWidth(),

                            )
                    }
                    item {
                        OutlinedTextField(
                            value = password,
                            onValueChange = { viewModel._password.value = it },
                            label = {
                                Text(
                                    text = stringResource(id = R.string.signup_password_label),
                                    color = Color.Gray
                                )

                            },
                            textStyle = TextStyle(color = Color.DarkGray),
                            singleLine = true,
                            keyboardOptions = KeyboardOptions.Default.copy(
                                keyboardType = KeyboardType.Password
                            ),

                            modifier = Modifier.fillMaxWidth(),
                            visualTransformation = PasswordVisualTransformation()
                        )
                    }
                    item {
                        OutlinedTextField(
                            value = tel,
                            onValueChange = { viewModel._tel.value = it },
                            textStyle = TextStyle(color = Color.DarkGray),
                            label = {
                                Text(
                                    text = stringResource(id = R.string.signup_tel_label),
                                    color = Color.Gray
                                )

                            },
                            singleLine = true,
                            keyboardOptions = KeyboardOptions.Default.copy(
                                keyboardType = KeyboardType.Phone
                            ),

                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                    item {
                        OutlinedTextField(
                            value = address,
                            textStyle = TextStyle(color = Color.DarkGray),
                            onValueChange = { viewModel._address.value = it },
                            label = {
                                Text(
                                    text = stringResource(id = R.string.signup_address_label),
                                    color = Color.Gray
                                )

                            },
                            singleLine = true,
                            keyboardOptions = KeyboardOptions.Default.copy(
                                keyboardType = KeyboardType.Text
                            ),

                            modifier = Modifier.fillMaxWidth()
                        )
                    }


                }
                Button(

                    onClick = {


                        viewModel.register(navController = navController, context = context)


                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.DarkGray,
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(text = stringResource(id = R.string.signup_register), color = Color.White)
                }
                Button(
                    onClick = { viewModel.backToLogin(navController) },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Gray,
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    contentPadding = PaddingValues(16.dp),

                    ) {
                    Text(text = stringResource(id = R.string.signup_back), color = Color.White)
                    Spacer(modifier = Modifier.width(10.dp))

                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "",
                        tint = Color.White
                    )
                }

            }


        }


    }

}






