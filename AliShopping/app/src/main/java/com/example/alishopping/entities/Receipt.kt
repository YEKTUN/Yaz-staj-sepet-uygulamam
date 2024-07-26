package com.example.alishopping.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "receipt")
data class Receipt(
    @PrimaryKey(autoGenerate = true)
    val receiptNumber: Int,
    val receiptDate: String,
    val receiptTotal: Int
)
