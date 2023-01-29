package com.example.test_task_weather_forecast.data.model

import com.squareup.moshi.Json

data class WeatherModel(
    val cityName: String,
    val lat: Double,
    val lon: Double,
    val list: List<WeatherForecastHourly>,
)

data class WeatherForecastHourly(
    val dtTxt: String,
    val temp: Double,
    val weather: List<WeatherHourly>,
)

data class WeatherHourly(
    val description: String,
    val icon: String,
)