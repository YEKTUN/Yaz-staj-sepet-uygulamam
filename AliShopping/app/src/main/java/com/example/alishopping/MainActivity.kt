package com.example.alishopping


import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider
import com.example.alishopping.database.Database
import com.example.alishopping.database.ShopViewModelFactory
import com.example.alishopping.navigate.AppPageNavigation
import com.example.alishopping.repository.AuthenticationRepository
import com.example.alishopping.repository.ProductRepository
import com.example.alishopping.repository.ReceiptDetailRepository
import com.example.alishopping.repository.ReceiptRepository
import com.example.alishopping.shopViewModel.ShopViewModel
import com.example.alishopping.ui.theme.AliShoppingTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val database = Database.getDatabase(this)
        val repositoryAuth = AuthenticationRepository(database.authDao())
        val repositoryReceipt = ReceiptRepository(database.receiptDao())
        val repositoryReceiptDetail = ReceiptDetailRepository(database.receiptDetailDao())
        val repositoryProduct = ProductRepository(database.productDao())
        val viewModelFactory = ShopViewModelFactory(
            repositoryAuth,
            repositoryReceipt,
            repositoryReceiptDetail,
            repositoryProduct
        )

        val appViewModel = ViewModelProvider(this, viewModelFactory)[ShopViewModel::class.java]


        enableEdgeToEdge()
        setContent {
            AliShoppingTheme {
                AppPageNavigation(appViewModel)
            }
        }
    }
}

