package com.dsorcelli.newfeaturesproject.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dsorcelli.newfeaturesproject.R
import java.time.LocalDateTime
import kotlin.random.Random
import kotlin.random.Random.Default.nextInt

@Entity(tableName = "products")
data class CityMeteo(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val cityId : Int,
    @ColumnInfo(name = "name")
    val cityName: String,
    val cityImg: Int? = -1, //risorsa R.drawable (rappresentato da intero)
    @ColumnInfo(name = "condition")
    val weatherCondition : String?,
    @ColumnInfo(name = "temperature")
    val weatherTemp : String?,
    @ColumnInfo(name = "wind")
    val weatherWind : String?,
    @ColumnInfo(name = "clouds")
    val weatherCloudsPerc : String?,
    @ColumnInfo(name = "weather_icon")
    val weatherIcon : String?, //id da appendere alla richiesta all'api
    @ColumnInfo(name = "last_update")
    val lastUpdate : Long //id da appendere alla richiesta all'api

)
