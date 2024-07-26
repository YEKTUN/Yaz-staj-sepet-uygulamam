package com.example.alishopping.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.alishopping.Navigate
import com.example.alishopping.R
import kotlinx.coroutines.delay

@Composable
fun LoginStarted(navController: NavController) {
    var isLoading by remember {
        mutableStateOf(true)

    }

    LaunchedEffect(Unit) {
        delay(2000)
        isLoading = false
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = colorResource(id = R.color.app_starting)
            ),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.login_started_name),
            color = colorResource(id = R.color.white),
            fontSize = 32.sp
        )
        if (isLoading) {
            CircularProgressIndicator()
        } else {
            navController.navigate(Navigate.LoginMenu)
            navController.popBackStack(Navigate.LoginStarted, true)
        }


    }

}


