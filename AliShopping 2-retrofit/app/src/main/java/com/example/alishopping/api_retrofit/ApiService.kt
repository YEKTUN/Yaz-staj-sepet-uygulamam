package com.example.alishopping.api_retrofit

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @GET("products")
    fun getProducts(): Call<ProductBackResponse>

    @POST("receiptSet")
    fun createReceipt(@Body receiptRequest: ReceiptT): Call<ReceiptT>

    @GET("receipt")
    fun getReceipt(): Call<ReceiptResponseRes>

    @POST("receiptDetailSet")
    fun createReceiptDetail(@Body receiptDetailRequest: ReceiptDetailT): Call<ReceiptDetailT>

    @POST("register")
    fun createAuth(@Body authRequest: AuthT): Call<AuthT>


    @POST("login")
    fun login(@Body authRequest: AuthT): Call<AuthTRes>

    @POST("searchUsername")
    fun searchUsername(@Body authRequest: AuthT): Call<AuthTRes>


}