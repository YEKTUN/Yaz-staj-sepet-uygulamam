package com.example.alishopping.api_retrofit

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.awaitResponse

class ProductRepositoryBack {
    private val apiService: ApiService = RetrofitInstance.api
    suspend fun getProducts(): List<ProductBack> {
        return withContext(Dispatchers.IO) {
            val response = RetrofitInstance.api.getProducts().execute()
            if (response.isSuccessful) {
                response.body()?.products ?: emptyList()
            } else {
                emptyList()
            }
        }
    }

    suspend fun createReceipt(totalPrice: Int, formattedDate: String): ReceiptT {
        val receiptRequest = ReceiptT(
            receiptDate = formattedDate,
            receiptTotal = totalPrice,
        )
        val response = apiService.createReceipt(receiptRequest).awaitResponse()
        if (response.isSuccessful) {
            return response.body() ?: throw Exception("Failed to create receipt")
        } else {
            throw Exception("Failed to create receipt")
        }
    }


    suspend fun getReceipt(): List<ReceiptT> {

        return withContext(Dispatchers.IO) {
            val response = RetrofitInstance.api.getReceipt().execute()
            if (response.isSuccessful) {
                response.body()?.receipts ?: emptyList()
            } else {
                emptyList()
            }
        }
    }


    suspend fun createReceiptDetails(detail: ReceiptDetailT) {

        Log.d("PLACEHOLDER", "Creating receipt details: $detail")
        val response = apiService.createReceiptDetail(detail).awaitResponse()
        if (response.isSuccessful) {
            response.body() ?: throw Exception("Failed to create receipt")
        } else {
            throw Exception("Failed to create receipt")
        }
    }
}






