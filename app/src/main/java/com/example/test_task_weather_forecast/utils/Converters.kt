package com.example.test_task_weather_forecast.utils

import androidx.room.TypeConverter
import com.example.test_task_weather_forecast.data.model.*
import com.google.gson.Gson

class ConverterClouds {
    @TypeConverter
    fun cloudsToJson(value: Clouds?) =
        Gson().toJson(value)

    @TypeConverter
    fun jsonToClouds(value: String) =
        Gson().fromJson(value, Clouds::class.java)
}

class ConverterMain {
    @TypeConverter
    fun mainToJson(value: Main?) =
        Gson().toJson(value)

    @TypeConverter
    fun jsonToMain(value: String) =
        Gson().fromJson(value, Main::class.java)
}

class ConverterSys {
    @TypeConverter
    fun sysToJson(value: Sys?) =
        Gson().toJson(value)

    @TypeConverter
    fun jsonToSys(value: String) =
        Gson().fromJson(value, Sys::class.java)
}

class ConverterWeather {
    @TypeConverter
    fun weatherToJson(value: Weather?) =
        Gson().toJson(value)

    @TypeConverter
    fun jsonToWeather(value: String) =
        Gson().fromJson(value, Weather::class.java)
}

class ConverterWeatherList {
    @TypeConverter
    fun weatherListToJson(value: List<Weather>?) =
        Gson().toJson(value)

    @TypeConverter
    fun jsonToWeatherList(value: String) =
        Gson().fromJson(value, Array<Weather>::class.java).toList()
}

class ConverterWind {
    @TypeConverter
    fun windToJson(value: Wind?) =
        Gson().toJson(value)

    @TypeConverter
    fun jsonToWind(value: String) =
        Gson().fromJson(value, Wind::class.java)
}

class ConverterCity {
    @TypeConverter
    fun cityToJson(value: City?) =
        Gson().toJson(value)

    @TypeConverter
    fun jsonToCity(value: String) =
        Gson().fromJson(value, City::class.java)
}

class ConverterWeatherForecastList {
    @TypeConverter
    fun weatherForecastListToJson(value: List<WeatherForecast>?) =
        Gson().toJson(value)

    @TypeConverter
    fun jsonToWeatherForecastList(value: String) =
        Gson().fromJson(value, Array<WeatherForecast>::class.java).toList()
}

