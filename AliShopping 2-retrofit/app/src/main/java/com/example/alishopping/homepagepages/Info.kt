package com.example.alishopping.homepagepages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.EditOff
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.alishopping.R
import com.example.alishopping.shopViewModel.ShopViewModel

@Composable
fun Info(viewModel: ShopViewModel) {
    LaunchedEffect(key1 = true) {
        viewModel.getInformation()
    }
    val infoEmail = viewModel.infoEmail.observeAsState("Empty")
    val infoAddress = viewModel.infoUsername.observeAsState("Empty")
    val infoTel = viewModel.infoTel.observeAsState("Empty")
    var isEmailEnabled by remember {
        mutableStateOf(false)
    }
    var isTelEnabled by remember {
        mutableStateOf(false)
    }
    var isAddressEnabled by remember {
        mutableStateOf(false)
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(800.dp)
            .offset(y = 70.dp)
            .background(color = colorResource(id = R.color.main_menu_background)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier
                .size(400.dp, 620.dp)
                .offset(y = (-50).dp)
                .padding(16.dp),
            colors = CardDefaults.elevatedCardColors(
                containerColor = colorResource(id = R.color.main_menu_background_card),
            )
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column {
                    Text(
                        text = stringResource(id = R.string.info_title),
                        color = Color.Black,
                        fontSize = 40.sp
                    )

                }
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Text(
                            text = stringResource(id = R.string.info_title_email),
                            color = Color.DarkGray,
                            modifier = Modifier
                                .weight(1f)
                                .offset(x = 15.dp)
                        )
                        OutlinedTextField(
                            value = infoEmail.value,
                            onValueChange = { viewModel.setEmailInfo(it) },
                            textStyle = TextStyle(color = Color.DarkGray),
                            singleLine = true,
                            modifier = Modifier
                                .weight(2f),
                            enabled = isEmailEnabled,

                            )
                        if (isEmailEnabled) {
                            IconButton(onClick = {
                                isEmailEnabled = false
                            }) {
                                Icon(
                                    imageVector = Icons.Default.EditOff,
                                    contentDescription = "",
                                    tint = Color.Black
                                )
                            }
                        } else {
                            IconButton(onClick = {
                                isEmailEnabled = true
                            }) {

                                Icon(
                                    imageVector = Icons.Default.Edit,
                                    contentDescription = "",
                                    tint = Color.Black

                                )
                            }
                        }


                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Text(
                            text = stringResource(id = R.string.info_title_tel),
                            color = Color.DarkGray,
                            modifier = Modifier
                                .weight(1f)
                                .offset(x = 15.dp)
                        )
                        OutlinedTextField(
                            value = infoTel.value,
                            onValueChange = { viewModel.setTelInfo(it) },
                            textStyle = TextStyle(color = Color.DarkGray),
                            singleLine = true,
                            modifier = Modifier
                                .weight(2f), enabled = isTelEnabled
                        )
                        if (isTelEnabled) {
                            IconButton(onClick = {
                                isTelEnabled = false
                            }) {
                                Icon(
                                    imageVector = Icons.Default.EditOff,
                                    contentDescription = "",
                                    tint = Color.Black
                                )
                            }
                        } else {
                            IconButton(onClick = {
                                isTelEnabled = true
                            }) {

                                Icon(
                                    imageVector = Icons.Default.Edit,
                                    contentDescription = "",
                                    tint = Color.Black

                                )
                            }
                        }
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Text(
                            text = stringResource(id = R.string.info_title_address),
                            color = Color.DarkGray,
                            modifier = Modifier
                                .weight(1f)
                                .offset(x = 10.dp)
                        )
                        OutlinedTextField(
                            value = infoAddress.value,
                            onValueChange = { viewModel.setUsernameInfo(it) },
                            textStyle = TextStyle(color = Color.DarkGray),
                            singleLine = true,
                            modifier = Modifier
                                .weight(2f), enabled = isAddressEnabled
                        )
                        if (isAddressEnabled) {
                            IconButton(onClick = {
                                isAddressEnabled = false
                            }) {
                                Icon(
                                    imageVector = Icons.Default.EditOff,
                                    contentDescription = "",
                                    tint = Color.Black
                                )
                            }
                        } else {
                            IconButton(onClick = {
                                isAddressEnabled = true
                            }) {

                                Icon(
                                    imageVector = Icons.Default.Edit,
                                    contentDescription = "",
                                    tint = Color.Black

                                )
                            }
                        }
                    }
                }

            }


        }


    }

}