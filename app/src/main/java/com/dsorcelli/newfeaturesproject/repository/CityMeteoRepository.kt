package com.dsorcelli.newfeaturesproject.repository

import android.util.Log
import com.dsorcelli.newfeaturesproject.future.Database
import com.dsorcelli.newfeaturesproject.models.CityMeteo
import com.dsorcelli.newfeaturesproject.models.WeatherProperty
import com.dsorcelli.newfeaturesproject.network.WeatherApi


// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
object CityMeteoRepository {

    //La classe Repository si occupa di fare da interfaccia tra la ViewModel e il data layer
    //In pratica fornisce dei metodi per prendere i dati dalla classe database invocandone i metodi primitivi
    //o per prendere i dati da api tramite chiamate ad endpoint: è un'astrazione del data layer vero e proprio.

    //Una funzione suspended può essere chiamata solo da un' altra funzione supsended.
    //In questo caso la getAll viene chiamata nell'ambito di una coroutine in ProductsListVM
    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.


    // DAO
    private val mProductDao = Database.instance.productDao()

    suspend fun insert(cityMeteo: CityMeteo) = mProductDao.insert(cityMeteo)

    suspend fun getAll() = mProductDao.getAll()

    fun getAllAsLiveData() = mProductDao.getAllAsLiveData()


    suspend fun getProdById(productId: Int) = mProductDao.getById(productId)

    //calls openweatherpi through retrofit
    suspend fun fetchMeteo(cityName : String) : WeatherProperty? {
        var result : WeatherProperty?
        try {
            result =  WeatherApi.retrofitService.getCityWeather(
                cityName,
                "b28f193c6e8448d7fe9dda464d06b20b"
            )
        } catch (e: Exception) {
            Log.e("CityMeteoDetailsVM", "error fetching meteo")
            Log.e("CityMeteoDetailsVM", e.message!!)
            result = null
        }
        return result
    }

}