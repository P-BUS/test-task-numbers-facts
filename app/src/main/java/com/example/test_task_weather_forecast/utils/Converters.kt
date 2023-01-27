package com.example.test_task_weather_forecast.utils

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.test_task_weather_forecast.data.model.*
import com.google.gson.Gson

@ProvidedTypeConverter
class ConverterClouds {
    @TypeConverter
    fun cloudsToJson(value: Clouds?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToClouds(value: String) = Gson().fromJson(value, Clouds::class.java)
}

@ProvidedTypeConverter
class ConverterMain {
    @TypeConverter
    fun mainToJson(value: Main?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToMain(value: String) = Gson().fromJson(value, Main::class.java)
}

@ProvidedTypeConverter
class ConverterSys {
    @TypeConverter
    fun sysToJson(value: Sys?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToSys(value: String) = Gson().fromJson(value, Sys::class.java)
}

@ProvidedTypeConverter
class ConverterWeather {
    @TypeConverter
    fun weatherToJson(value: Weather?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToWeather(value: String) = Gson().fromJson(value, Weather::class.java)
}

@ProvidedTypeConverter
class ConverterWeatherList {
    @TypeConverter
    fun weatherListToJson(value: List<Weather>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToWeatherList(value: String) =
        Gson().fromJson(value, Array<Weather>::class.java).toList()
}

@ProvidedTypeConverter
class ConverterWind {
    @TypeConverter
    fun windToJson(value: Wind?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToWind(value: String) = Gson().fromJson(value, Wind::class.java)
}

