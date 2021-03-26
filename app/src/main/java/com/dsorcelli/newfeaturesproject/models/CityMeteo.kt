package com.dsorcelli.newfeaturesproject.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dsorcelli.newfeaturesproject.R
import java.time.LocalDateTime
import java.util.*
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
    val weatherCondition : String? = null,
    @ColumnInfo(name = "temperature")
    val weatherTemp : String? = null,
    @ColumnInfo(name = "wind")
    val weatherWind : String? = null,
    @ColumnInfo(name = "clouds")
    val weatherCloudsPerc : String? = null,
    @ColumnInfo(name = "weather_icon")
    val weatherIcon : String? = null, //id da appendere alla richiesta all'api
    @ColumnInfo(name = "last_update")
    val lastUpdate : Long = 0L //id da appendere alla richiesta all'api

)
