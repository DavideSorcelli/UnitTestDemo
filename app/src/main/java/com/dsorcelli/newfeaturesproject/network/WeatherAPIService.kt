package com.dsorcelli.newfeaturesproject.network

import androidx.lifecycle.LiveData
import com.dsorcelli.newfeaturesproject.models.WeatherProperty
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

class WeatherAPIService {
    //
    private val BASE_URL = "https://android-kotlin-fun-mars-server.appspot.com"


    //use a Retrofit builder to create a Retrofit object.
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()
    //Converter factory: converter tells Retrofit what to do with the data it gets back from the web service. In this case, you want Retrofit to fetch a JSON response from the web service, and return it as a String. Retrofit has a ScalarsConverter
    //but we use the MoshiConverter imported in gradle

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory()) //for moshi annotations to work
        .build()


    //Retrofit ha come funzione quella di wrappare le chiamate ad un'API in un'interfaccia, ovvero questa.
    //Ogni endpoint sarà wrappabto con un metodo richiamabile.
    //NB: al posto del metodo Call (retrofit) stiamo usando una function suspended in modo da poterla richiamare tramite coroutine
    interface WeatherAPIService {
        @GET("data/2.5/weather?")
        suspend fun getCityWeather(@Query("city name") city: String, @Query("APPID") app_id: String): List<WeatherProperty>
    }

}