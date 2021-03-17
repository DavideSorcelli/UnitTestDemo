package com.dsorcelli.newfeaturesproject

import android.app.Application
import android.content.Context
class ProductApplication : Application() {

    init
    {
        instance = this
    }

    companion object {

        lateinit var instance: ProductApplication

        val context: Context
            get() = instance.applicationContext
    }


}