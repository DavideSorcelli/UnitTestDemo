package com.dsorcelli.newfeaturesproject.utils

class SimpleService(private val database: SimpleDatabase) {

    fun query(query: String): Boolean {
        return database.isAvailable()
    }

    override fun toString(): String {
        return "Using database with id: ${database.getUniqueId()}"
    }

}