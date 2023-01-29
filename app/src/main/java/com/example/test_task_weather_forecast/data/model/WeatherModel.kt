package com.example.test_task_weather_forecast.data.model

import com.squareup.moshi.Json

data class WeatherModel(
    val cityName: String = "",
    val lat: Double = 0.0,
    val lon: Double = 0.0,
    val forecastList: List<WeatherForecastModel> = listOf()
)

    data class WeatherForecastModel(
        @Json(name = "dt_txt")
        val dtTxt: String = "",
        val main: Temp = Temp(),
        val weather: List<Description>
    )

        data class Temp(
            val temp: Double = 0.0
        )

        data class Description(
            val description: String = "",
            val icon: String = ""
        )