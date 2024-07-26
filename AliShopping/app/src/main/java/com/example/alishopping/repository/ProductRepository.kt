package com.example.alishopping.repository

import com.example.alishopping.dao.ProductDao
import com.example.alishopping.entities.Product

class ProductRepository(private val productDao: ProductDao) {
    suspend fun insertProduct(product: List<Product>) {
        return productDao.insertProduct(product)
    }


    suspend fun getAllProduct(): List<Product> {
        return productDao.getAllProduct()
    }


}