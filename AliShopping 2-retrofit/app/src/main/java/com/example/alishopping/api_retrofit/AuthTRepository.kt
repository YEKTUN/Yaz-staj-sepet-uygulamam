package com.example.alishopping.api_retrofit

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AuthTRepository {
    private val authService: ApiService = RetrofitInstance.api


    suspend fun createAuth(auth: AuthT): AuthT {
        return withContext(Dispatchers.IO) {
            val response = authService.createAuth(auth).execute()
            if (response.isSuccessful) {
                response.body() ?: throw Exception("Failed to create auth")
            } else {
                throw Exception("Failed to create auth")
            }
        }
    }

    suspend fun login(auth: AuthT): AuthTRes {
        return withContext(Dispatchers.IO) {
            val response = authService.login(auth).execute()
            if (response.isSuccessful) {
                response.body() ?: throw Exception("Failed to create auth")
            } else {
                throw Exception("Failed to create auth")
            }
        }
    }

    suspend fun searchUsername(auth: AuthT): AuthTRes {
        return withContext(Dispatchers.IO) {
            val response = authService.searchUsername(auth).execute()
            if (response.isSuccessful) {
                response.body() ?: throw Exception("Failed to fetch auth")
            } else {
                throw Exception("Failed to fetch auth")
            }
        }
    }


}