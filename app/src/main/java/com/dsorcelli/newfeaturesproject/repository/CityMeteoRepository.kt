package com.dsorcelli.newfeaturesproject.repository

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.dsorcelli.newfeaturesproject.database.Database
import com.dsorcelli.newfeaturesproject.models.CityMeteo
import com.dsorcelli.newfeaturesproject.network.WeatherApi
import java.net.UnknownHostException
import java.util.*


// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
object CityMeteoRepository {


    // TODO: move to service, use const (or move to gradle BuildConfigFields)
    private val METEO_APP_ID = "b28f193c6e8448d7fe9dda464d06b20b"

    //La classe Repository si occupa di fare da interfaccia tra la ViewModel e il data layer
    //In pratica fornisce dei metodi per prendere i dati dalla classe database invocandone i metodi primitivi
    //o per prendere i dati da api tramite chiamate ad endpoint: è un'astrazione del data layer vero e proprio.

    //Una funzione suspended può essere chiamata solo da un' altra funzione supsended.
    //In questo caso la getAll viene chiamata nell'ambito di una coroutine in ProductsListVM
    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.


    // DAO
    private val mProductDao = Database.instance.citiesDao()

    suspend fun insert(cityMeteo: CityMeteo) = mProductDao.insert(cityMeteo)

    suspend fun getAll() = mProductDao.getAll()

    fun getAllAsLiveData() = mProductDao.getAllAsLiveData()

    suspend fun getProdById(productId: Int) = mProductDao.getById(productId)

    fun getProdByIdAsLiveData(productId: Int) = mProductDao.getByIdAsLiveData(productId)

    //calls openweatherpi through retrofit
    //if successful update the db element with the weather infos -> fragment observer will update the view
    //If failing -> use DB  cached infos if available
    @RequiresApi(Build.VERSION_CODES.O) // TODO: ??
    suspend fun fetchMeteo(lastUpdate: Long, cityName: String) {
        try {
            if (Date().time.minus(1000 * 60) > lastUpdate) {
                Log.d("Repository", "Request to the API")
                val apiResponse = WeatherApi.retrofitService.getCityWeather(cityName, METEO_APP_ID, "metric")
                //Update della città nel DB con le info relative al meteo -> observer del vm si occupa di aggiornarlo tramite la chiamata precedente a getByIdAslIveData e l'observer
                Log.d("Repository", "Response code ${apiResponse.code()}")
                if (apiResponse.isSuccessful) {
                    val result = apiResponse.body()!!
                    mProductDao.updateWeatherInfos(
                        cityName,
                        result.weather[0].icon,
                        result.weather[0].main,
                        result.main.temp.toString(),
                        result.wind.speed.toString(),
                        result.clouds.all.toString(),
                        Date().time
                    )
                }
            }
            else
            {
                // TODO: il meccanismo di cache va messo nell'else, se la richiesta fallisce controlli quanto è vecchia la cache, se è recente rispondi con quella
                Log.d("Repository", "Retrieving from cache")
            }

        }
        catch(e:UnknownHostException)
        {
            //Does nothing, simply continues to show data cached from the database
            Log.e("CityMeteoDetailsVM", "error fetching meteo")
            Log.e("CityMeteoDetailsVM", e.toString())
        }
        catch (e: Exception) {
            //Does nothing, simply continues to show data cached from the database
            Log.e("CityMeteoDetailsVM", "error fetching meteo")
            Log.e("CityMeteoDetailsVM", e.toString())
        }
    }


}