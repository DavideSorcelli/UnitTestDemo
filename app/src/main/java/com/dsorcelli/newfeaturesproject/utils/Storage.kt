package com.dsorcelli.newfeaturesproject.utils

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.icu.util.Measure
import android.icu.util.MeasureUnit
import com.dsorcelli.newfeaturesproject.CityMeteoApplication

object Storage {

    enum class Keys(val value: String) {
        MEASURE_UNIT("MEASURE_UNIT"),
    }

    enum class TempMeasureUnit(val value: String)
    {
        EU("metric"),
        UK("standard"),
        DEF("standard")
    }


    val sharedPrefs: SharedPreferences by lazy {
        CityMeteoApplication.instance.getSharedPreferences(
            "myApp",
            Context.MODE_PRIVATE
        )
    }


    val measureUnit : TempMeasureUnit
        get() {
            var returnVal : TempMeasureUnit = TempMeasureUnit.DEF
            val mu = sharedPrefs.getString(Keys.MEASURE_UNIT.value, "standard")
            when(mu)
            {
                "metric" -> returnVal = TempMeasureUnit.EU
                "standard" -> returnVal = TempMeasureUnit.UK
                "standard" -> returnVal = TempMeasureUnit.DEF
            }
            return returnVal
        }


    fun saveMeasureUnit(chosenUnit : TempMeasureUnit) {
        with(sharedPrefs.edit()) {
            putString(Keys.MEASURE_UNIT.value, chosenUnit.value)
            apply()
        }
    }

}

