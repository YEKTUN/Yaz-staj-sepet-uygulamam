package com.example.alishopping.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
import com.example.alishopping.entities.ReceiptDetail

@Dao
interface ReceiptDetailDao {
    @Insert
    suspend fun insertReceiptDetail(receiptDetail: ReceiptDetail)

    @Delete
    suspend fun deleteReceiptDetail(receiptDetail: ReceiptDetail)

    @Update
    suspend fun updateReceiptDetail(receiptDetail: ReceiptDetail)


}