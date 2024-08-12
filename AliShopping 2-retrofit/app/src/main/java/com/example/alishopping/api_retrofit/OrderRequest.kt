package com.example.alishopping.api_retrofit


data class ReceiptT(

    val receiptNumber: Int = 0,
    val receiptDate: String,
    val receiptTotal: Int
)

data class ReceiptResponseRes(

    val receipts: List<ReceiptT>
)

data class ReceiptDetailT(
    val receiptNumber: Int,
    val productName: String,
    val quantity: Int,
    val total: Int,
    val price: Double


)

data class ReceiptDetailResponseRes(

    val receiptDetail: List<ReceiptDetailT>
)

data class AuthT(
    val username: String,
    val password: String,
    val email: String,
    val tel: String
)

data class AuthTRes(
    val auths: List<AuthT>
)

