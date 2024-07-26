package com.example.alishopping.database

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import androidx.room.RoomDatabase

import com.example.alishopping.dao.AuthDao
import com.example.alishopping.dao.ProductDao
import com.example.alishopping.dao.ReceiptDao
import com.example.alishopping.dao.ReceiptDetailDao
import com.example.alishopping.entities.Authentication
import com.example.alishopping.entities.Product
import com.example.alishopping.entities.Receipt
import com.example.alishopping.entities.ReceiptDetail
import com.example.alishopping.repository.AuthenticationRepository
import com.example.alishopping.repository.ProductRepository
import com.example.alishopping.repository.ReceiptDetailRepository
import com.example.alishopping.repository.ReceiptRepository
import com.example.alishopping.shopViewModel.ShopViewModel

@androidx.room.Database(
    entities = [Authentication::class, Receipt::class, ReceiptDetail::class, Product::class],
    version = 6
)
abstract class Database : RoomDatabase() {

    abstract fun authDao(): AuthDao
    abstract fun receiptDao(): ReceiptDao
    abstract fun receiptDetailDao(): ReceiptDetailDao
    abstract fun productDao(): ProductDao

    companion object {
        @Volatile
        private var INSTANCE: Database? = null

        fun getDatabase(context: Context): Database {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    Database::class.java,
                    "user_database"
                )
                    .fallbackToDestructiveMigration()

                    .build()
                INSTANCE = instance
                instance
            }
        }
    }


}


class ShopViewModelFactory(
    private val authRepository: AuthenticationRepository,
    private val receiptRepository: ReceiptRepository,
    private val receiptDetailRepository: ReceiptDetailRepository,
    private val productRepository: ProductRepository,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ShopViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ShopViewModel(
                authRepository,
                receiptRepository,
                receiptDetailRepository,
                productRepository
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
