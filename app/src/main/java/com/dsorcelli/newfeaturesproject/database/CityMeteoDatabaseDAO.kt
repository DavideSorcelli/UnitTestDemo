package com.dsorcelli.newfeaturesproject.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.dsorcelli.newfeaturesproject.models.CityMeteo
import java.time.LocalDateTime
import java.util.concurrent.locks.Condition

@Dao
interface CityMeteoDatabaseDAO {
//DAO: Data Access Object -> interfaccia per operare sul DB che rappresenta

    @Insert
    suspend fun insert(cityMeteo: CityMeteo)

    @Query("UPDATE products SET weather_icon= :icon, condition= :condition , temperature= :temp, wind= :wind, clouds= :clouds, last_update= :lastUpdate  WHERE name =:name")
    suspend fun updateWeatherInfos(name: String, icon: String, condition: String, temp: String, wind: String, clouds: String, lastUpdate: kotlin.Long)

    @Query("SELECT * FROM products WHERE id= :id")
    suspend fun getById(id: Int): CityMeteo

    @Query("SELECT * FROM products WHERE id= :id")
    fun getByIdAsLiveData(id: Int): LiveData<CityMeteo>

    @Query("SELECT * FROM products")
    suspend fun getAll() : List<CityMeteo>

    @Query("SELECT * FROM products")
    fun getAllAsLiveData() : LiveData<List<CityMeteo>>

    @Query("DELETE FROM products")
    fun clear()

}