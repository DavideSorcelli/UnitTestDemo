package com.dsorcelli.newfeaturesproject

import android.app.Application
import android.content.Context
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule
class CityMeteoApplication : Application() {

    init
    {
        instance = this
    }

    companion object {

        lateinit var instance: CityMeteoApplication

        val context: Context
            get() = instance.applicationContext
    }


}