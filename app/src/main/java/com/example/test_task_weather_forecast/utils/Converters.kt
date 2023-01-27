package com.example.test_task_weather_forecast.utils

import androidx.room.TypeConverter
import com.example.test_task_weather_forecast.data.model.Clouds
import com.example.test_task_weather_forecast.data.model.Main
import com.example.test_task_weather_forecast.data.model.Sys
import com.google.gson.Gson


class ConverterClouds {
    @TypeConverter
    fun cloudsToJson(value: Clouds?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToClouds(value: String) = Gson().fromJson(value, Clouds::class.java)
}

class ConverterMain {
    @TypeConverter
    fun mainToJson(value: Main?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToMain(value: String) = Gson().fromJson(value, Main::class.java)
}

class ConverterSys {
    @TypeConverter
    fun sysToJson(value: Sys?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToSys(value: String) = Gson().fromJson(value, Sys::class.java)
}

class ConverterSys {
    @TypeConverter
    fun sysToJson(value: Sys?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToSys(value: String) = Gson().fromJson(value, Sys::class.java)
}

class ConverterWind {
    @TypeConverter
    fun sysToJson(value: Sys?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToSys(value: String) = Gson().fromJson(value, Sys::class.java)
}

