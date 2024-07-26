package com.example.alishopping.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "receiptDetail",
    foreignKeys = [ForeignKey(
        entity = Receipt::class,
        parentColumns = ["receiptNumber"],
        childColumns = ["receiptNumber"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class ReceiptDetail(
    @PrimaryKey(autoGenerate = true)
    val detailId: Int = 0,
    val productName: String,
    val quantity: Int,
    val price: Double,
    val receiptNumber: Int
)
