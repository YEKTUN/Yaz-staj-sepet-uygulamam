package com.example.alishopping.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "products")
data class Product(
    @PrimaryKey(autoGenerate = true)
    val productId: Int = 0,

    val productName: String,

    val productImage: String,

    val productPrice: String
)

