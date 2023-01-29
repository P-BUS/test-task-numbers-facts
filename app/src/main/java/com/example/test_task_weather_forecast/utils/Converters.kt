package com.example.test_task_weather_forecast.utils

import androidx.room.TypeConverter
import com.example.test_task_weather_forecast.data.model.*
import com.google.gson.Gson

class ConverterWeatherHourly {
    @TypeConverter
    fun weatherToJson(value: WeatherHourly?) =
        Gson().toJson(value)

    @TypeConverter
    fun jsonToWeatherHourly(value: String) =
        Gson().fromJson(value, WeatherHourly::class.java)
}

class ConverterWeatherList {
    @TypeConverter
    fun weatherHourlyListToJson(value: List<WeatherHourly>?) =
        Gson().toJson(value)

    @TypeConverter
    fun jsonToWeatherHourlyList(value: String) =
        Gson().fromJson(value, Array<WeatherHourly>::class.java).toList()
}

class ConverterWeatherForecastList {
    @TypeConverter
    fun weatherForecastHourlyListToJson(value: List<WeatherForecastHourly>?) =
        Gson().toJson(value)

    @TypeConverter
    fun jsonToWeatherForecastHourlyList(value: String) =
        Gson().fromJson(value, Array<WeatherForecastHourly>::class.java).toList()
}

