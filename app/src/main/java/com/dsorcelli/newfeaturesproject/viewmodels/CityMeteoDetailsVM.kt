package com.dsorcelli.newfeaturesproject.viewmodels

import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dsorcelli.newfeaturesproject.models.CityMeteo
import com.dsorcelli.newfeaturesproject.repository.CityMeteoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Dispatcher

class CityMeteoDetailsVM() : ViewModel() {

    fun registerForMeteoUpdates(productId: Int) = CityMeteoRepository.getProdByIdAsLiveData(productId)

    @RequiresApi(Build.VERSION_CODES.O)
    fun fetchMeteo(lastUpdate:Long, cityName:String) = viewModelScope.launch(Dispatchers.IO) {
        CityMeteoRepository.fetchMeteo(lastUpdate,cityName)
    }
}

