package com.dsorcelli.newfeaturesproject.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dsorcelli.newfeaturesproject.utils.Storage

class SettingsVM : ViewModel() {

    val measureUnit : Storage.TempMeasureUnit
        get() = measureUnitMut //posso anche direttamente mettere Storage.measureUnit
    private var measureUnitMut = Storage.TempMeasureUnit.DEF

    init {
        measureUnitMut = Storage.measureUnit
    }

    fun saveMeasureUnit(measureUnit: Storage.TempMeasureUnit)
    {
        Storage.saveMeasureUnit(measureUnit)
    }

}