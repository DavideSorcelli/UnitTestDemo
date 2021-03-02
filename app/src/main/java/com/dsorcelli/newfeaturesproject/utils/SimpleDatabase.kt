package com.dsorcelli.newfeaturesproject.utils

class SimpleDatabase {

    companion object {
        private const val UUID = 42
    }

    fun isAvailable(): Boolean {
        return false
    }

    fun getUniqueId(): Int {
        return UUID
    }

}