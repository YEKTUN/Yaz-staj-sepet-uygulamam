package com.example.alishopping.repository

import com.example.alishopping.dao.AuthDao
import com.example.alishopping.entities.Authentication

class AuthenticationRepository(private val authDao: AuthDao) {
    suspend fun insert(auth: Authentication) {
        return authDao.insert(auth)
    }

    suspend fun update(auth: Authentication) {
        return authDao.update(auth)
    }

    suspend fun delete(auth: Authentication) {
        return authDao.delete(auth)
    }

    suspend fun getAll(): List<Authentication> {
        return authDao.getAll()
    }

    suspend fun search(email: String, password: String): Authentication? {
        return authDao.search(email, password)
    }

    suspend fun search(email: String): Authentication? {
        return authDao.search(email)
    }

}