package com.example.alishopping.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.alishopping.entities.Product

@Dao
interface ProductDao {
    @Insert
    suspend fun insertProduct(product: List<Product>)


    @Delete
    suspend fun deleteProduct(product: Product)

    @Update
    suspend fun updateProduct(product: Product)

    @Query("SELECT * FROM products")
    suspend fun getAllProduct(): List<Product>

}