package com.example.test_task_weather_forecast.data.model

import com.squareup.moshi.Json

data class WeatherModel(
    val cityName: String = "",
    val lat: Double = 0.0,
    val lon: Double = 0.0,
    val list: List<WeatherForecast> = listOf(),
)

data class WeatherForecast(
    val clouds: Clouds? = null,
    val dt: Int? = null,
    @Json(name = "dt_txt")
    val dtTxt: String? = null,
    val main: Main? = null,
    val pop: Int? = null,
    val sys: Sys? = null,
    val visibility: Int? = null,
    val weather: List<Weather>? = null,
    val wind: Wind? = null
)

data class Main(
    @Json(name = "feels_like")
    val feelsLike: Double? = null,
    @Json(name = "grnd_level")
    val grndLevel: Int? = null,
    val humidity: Int? = null,
    val pressure: Int? = null,
    @Json(name = "sea_level")
    val seaLevel: Int? = null,
    val temp: Double? = null,
    @Json(name = "temp_kf")
    val tempKf: Double? = null,
    @Json(name = "temp_max")
    val tempMax: Double? = null,
    @Json(name = "temp_min")
    val temp_min: Double? = null
)

data class Weather(
    val description: String? = null,
    val icon: String? = null,
    val id: Int? = null,
    val main: String? = null
)