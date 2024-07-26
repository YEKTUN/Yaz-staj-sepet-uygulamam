package com.example.alishopping.repository

import com.example.alishopping.dao.ReceiptDetailDao
import com.example.alishopping.entities.ReceiptDetail

class ReceiptDetailRepository(private val receiptDetailDao: ReceiptDetailDao) {
    suspend fun insertReceiptDetail(receiptDetail: ReceiptDetail) {
        return receiptDetailDao.insertReceiptDetail(receiptDetail)
    }


}