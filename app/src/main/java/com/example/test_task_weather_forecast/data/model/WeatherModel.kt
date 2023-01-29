package com.example.test_task_weather_forecast.data.model

import com.squareup.moshi.Json

data class WeatherModel(
    val cityName: String = "",
    val lat: Double = 0.0,
    val lon: Double = 0.0,
    val list: List<WeatherForecast> = listOf(),
)

data class WeatherForecast(
    val dtTxt: String? = null,
    val main: Main? = null,
    val weather: List<Weather>? = null,
)

data class Main(
    val temp: Double? = null,
)

data class Weather(
    val description: String? = null,
    val icon: String? = null,
)