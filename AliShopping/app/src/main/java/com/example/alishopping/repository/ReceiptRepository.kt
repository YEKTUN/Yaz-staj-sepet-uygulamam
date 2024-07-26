package com.example.alishopping.repository


import com.example.alishopping.dao.ReceiptDao

import com.example.alishopping.entities.Receipt

class ReceiptRepository(private val receiptDao: ReceiptDao) {
    suspend fun insertReceipt(receipt: Receipt) {
        return receiptDao.insertReceipt(receipt)
    }

    suspend fun updateReceipt(receipt: Receipt) {
        return receiptDao.updateReceipt(receipt)
    }

    suspend fun deleteReceipt(receipt: Receipt) {
        return receiptDao.deleteReceipt(receipt)
    }

    suspend fun getAllReceipt(): List<Receipt> {
        return receiptDao.getAllReceipt()
    }


}
