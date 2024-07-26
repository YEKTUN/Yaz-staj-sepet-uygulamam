package com.example.alishopping.navigate

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.alishopping.Navigate
import com.example.alishopping.pages.HomePage
import com.example.alishopping.pages.LoginMenu
import com.example.alishopping.pages.LoginStarted
import com.example.alishopping.pages.SignUp
import com.example.alishopping.shopViewModel.ShopViewModel

@Composable
fun AppPageNavigation(viewModel: ShopViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Navigate.LoginStarted, builder = {
        composable(Navigate.LoginStarted) {
            LoginStarted(navController)
        }
        composable(Navigate.LoginMenu) {
            LoginMenu(navController, viewModel)
        }
        composable(Navigate.SignUp) {
            SignUp(viewModel, navController)
        }
        composable(Navigate.HomePage) {
            HomePage(navController, viewModel)
        }


    })

}