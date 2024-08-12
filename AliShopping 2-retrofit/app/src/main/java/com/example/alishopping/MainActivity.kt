package com.example.alishopping


import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider
import com.example.alishopping.navigate.AppPageNavigation
import com.example.alishopping.shopViewModel.ShopViewModel
import com.example.alishopping.ui.theme.AliShoppingTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val appViewModel = ViewModelProvider(this)[ShopViewModel::class.java]


        enableEdgeToEdge()
        setContent {
            AliShoppingTheme {
                AppPageNavigation(appViewModel)
            }
        }
    }
}

