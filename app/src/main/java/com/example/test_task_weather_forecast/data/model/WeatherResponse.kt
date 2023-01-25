package com.example.test_task_weather_forecast.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WeatherResponse(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<WeatherForecast>,
    val message: Int
)


@JsonClass(generateAdapter = true)
data class City(
    val coord: Coord,
    val country: String,
    val id: Int,
    val name: String,
    val population: Int,
    val sunrise: Int,
    val sunset: Int,
    val timezone: Int
)

@JsonClass(generateAdapter = true)
data class WeatherForecast(
    val clouds: Clouds,
    val dt: Int,
    val dt_txt: String,
    val main: Main,
    val pop: Int,
    val sys: Sys,
    val visibility: Int,
    val weather: List<Weather>,
    val wind: Wind
)

@JsonClass(generateAdapter = true)
data class Coord(
    val lat: Double,
    val lon: Double
)

@JsonClass(generateAdapter = true)
data class Clouds(
    val all: Int
)

@JsonClass(generateAdapter = true)
data class Main(
    val feels_like: Double,
    val grnd_level: Int,
    val humidity: Int,
    val pressure: Int,
    val sea_level: Int,
    val temp: Double,
    val temp_kf: Double,
    val temp_max: Double,
    val temp_min: Double
)

@JsonClass(generateAdapter = true)
data class Sys(
    val pod: String
)

@JsonClass(generateAdapter = true)
data class Weather(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)

@JsonClass(generateAdapter = true)
data class Wind(
    val deg: Int,
    val gust: Double,
    val speed: Double
)