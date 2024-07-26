package com.example.alishopping.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.alishopping.entities.Authentication

@Dao
interface AuthDao {
    @Insert
    suspend fun insert(auth: Authentication)

    @Delete
    suspend fun delete(auth: Authentication)

    @Update
    suspend fun update(auth: Authentication)

    @Query("SELECT*FROM authentication")
    suspend fun getAll(): List<Authentication>

    @Query("SELECT*FROM authentication WHERE email = :email AND password = :password")
    suspend fun search(email: String, password: String): Authentication?

    @Query("SELECT*FROM authentication WHERE email = :email ")
    suspend fun search(email: String): Authentication?


}