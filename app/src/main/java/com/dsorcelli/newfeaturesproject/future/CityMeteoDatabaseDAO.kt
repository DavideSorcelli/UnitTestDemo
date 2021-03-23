package com.dsorcelli.newfeaturesproject.future

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.dsorcelli.newfeaturesproject.models.CityMeteo

@Dao
interface CityMeteoDatabaseDAO {
//DAO: Data Access Object -> interfaccia per operare sul DB che rappresenta

    @Insert
    suspend fun insert(cityMeteo: CityMeteo)

    @Update
    fun update(cityMeteo: CityMeteo)

    @Query("SELECT * FROM products WHERE id= :id")
    suspend fun getById(id: Int): CityMeteo

    @Query("SELECT * FROM products")
    suspend fun getAll() : List<CityMeteo>

    @Query("SELECT * FROM products")
    fun getAllAsLiveData() : LiveData<List<CityMeteo>>

    @Query("DELETE FROM products")
    fun clear()

}