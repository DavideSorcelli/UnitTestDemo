package com.dsorcelli.newfeaturesproject.repository

import com.dsorcelli.newfeaturesproject.utils.SimpleDatabase

object ProductRepository {

    suspend fun getAll() = SimpleDatabase.getAllProducts()

    suspend fun getAllUnderPrice10() =
        SimpleDatabase.getAllProducts().filter {
            it.price < 10.00
        }

}