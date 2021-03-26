package com.dsorcelli.newfeaturesproject.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dsorcelli.newfeaturesproject.models.CityMeteo
import com.dsorcelli.newfeaturesproject.repository.CityMeteoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CityMeteoDetailsVM() : ViewModel() {

    val cityMeteoLive: LiveData<CityMeteo?> get() =  cityMeteoLiveMut
    private val cityMeteoLiveMut = MutableLiveData<CityMeteo?>()

    fun registerForMeteoUpdates(cityName: String) =
        CityMeteoRepository.getCityMeteoByNameLiveData(cityName)

    fun fetchMeteo(cityName: String) = viewModelScope.launch(Dispatchers.IO) {
        val cityMeteo: CityMeteo? = CityMeteoRepository.fetchMeteo(cityName)
        cityMeteoLiveMut.postValue(cityMeteo)
    }

}

