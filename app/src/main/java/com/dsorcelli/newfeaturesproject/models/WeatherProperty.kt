package com.dsorcelli.newfeaturesproject.models



//Moji assegna automaticamente all'oggetto deserializzato i campi contrassegnati nel json dal nome della proprietà
data class WeatherProperty (
    val weather : List<WeatherType>,
    val main: MainWeatherInfos
        )

data class  WeatherType(
    val id: Double,
    val main: String,
    val description:String,
    val icon: String
)

data class MainWeatherInfos(
    val temp: Double,
    val feels_like: Double,
    val temp_min : Double,
    val temp_max : Double,
    val pressure : Double,
    val humidity : Double
)