package com.dsorcelli.newfeaturesproject.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dsorcelli.newfeaturesproject.models.CityMeteo
import com.dsorcelli.newfeaturesproject.models.WeatherProperty
import com.dsorcelli.newfeaturesproject.network.WeatherAPIService
import com.dsorcelli.newfeaturesproject.network.WeatherApi
import com.dsorcelli.newfeaturesproject.repository.CityMeteoRepository
import kotlinx.coroutines.launch

class CityMeteoDetailsVM() : ViewModel() {


    //Info on the city (DB)
    val cityMeteo: LiveData<CityMeteo>
        get() = cityMeteoMut
    private val cityMeteoMut = MutableLiveData<CityMeteo>()

    //weatherApi response body element
    val weatherResp: LiveData<WeatherProperty>
        get() = weatherRespMut
    private val weatherRespMut = MutableLiveData<WeatherProperty>()


    fun retrieveProduct(productId: Int) = viewModelScope.launch {
        CityMeteoRepository.getProdById(productId).let {
            cityMeteoMut.postValue(it)
        }
    }

    fun fetchMeteo(cityName: String) = viewModelScope.launch {
           weatherRespMut.postValue(CityMeteoRepository.fetchMeteo(cityName))
        }
    }

