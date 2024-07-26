package com.example.alishopping.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "authentication")
data class Authentication(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var email: String,
    val password: String,
    val tel: String,
    val address: String
)
