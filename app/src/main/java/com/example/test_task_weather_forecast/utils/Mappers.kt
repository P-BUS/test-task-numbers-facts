package com.example.test_task_weather_forecast.utils

import com.example.test_task_weather_forecast.data.database.WeatherEntity
import com.example.test_task_weather_forecast.data.model.WeatherForecast


fun List<WeatherEntity>.asDomainModel(): List<WeatherForecast> {
    return map {
        WeatherForecast(
            clouds = it.clouds,
            dt = it.dt,
            dtTxt = it.dtTxt,
            main = it.main,
            pop = it.pop,
            sys = it.sys,
            visibility = it.visibility,
            weather = it.weather,
            wind = it.wind
        )
    }
}

fun List<WeatherForecast>.asDatabaseModel(): List<WeatherEntity> {
    return map {
        WeatherEntity(
            clouds = it.clouds,
            dt = it.dt,
            dtTxt = it.dtTxt,
            main = it.main,
            pop = it.pop,
            sys = it.sys,
            visibility = it.visibility,
            weather = it.weather,
            wind = it.wind
        )
    }
}