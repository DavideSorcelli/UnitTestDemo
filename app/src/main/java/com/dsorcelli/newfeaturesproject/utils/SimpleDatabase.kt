package com.dsorcelli.newfeaturesproject.utils

import com.dsorcelli.newfeaturesproject.models.Product
import kotlinx.coroutines.delay

object SimpleDatabase {

    private const val UUID = 42

    suspend fun getAllProducts(): List<Product> {
        delay((1000L..2500).random())
        val productsList = mutableListOf<Product>()
        repeat(5) {
            productsList.add(Product.randomProduct())
        }
        return productsList
    }

    fun isAvailable(): Boolean {
        return false
    }

    fun getUniqueId(): Int {
        return UUID
    }

}