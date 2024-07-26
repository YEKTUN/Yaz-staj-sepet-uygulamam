package com.example.alishopping.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.alishopping.entities.Receipt

@Dao
interface ReceiptDao {
    @Insert
    suspend fun insertReceipt(receipt: Receipt)

    @Delete
    suspend fun deleteReceipt(receipt: Receipt)

    @Update
    suspend fun updateReceipt(receipt: Receipt)

    @Query("SELECT * FROM receipt")
    suspend fun getAllReceipt(): List<Receipt>
}
