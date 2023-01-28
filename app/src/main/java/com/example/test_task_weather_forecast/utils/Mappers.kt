package com.example.test_task_weather_forecast.utils

import com.example.test_task_weather_forecast.data.database.WeatherEntity
import com.example.test_task_weather_forecast.data.model.City
import com.example.test_task_weather_forecast.data.model.WeatherForecast
import com.example.test_task_weather_forecast.data.model.WeatherResponse


fun WeatherEntity.asDomainModel(): WeatherResponse {
    return WeatherResponse(
            key = key,
            city = city,
            cnt = cnt,
            cod = cod,
            list = list,
            message = message
        )
    }

fun WeatherResponse.asDatabaseModel(): WeatherEntity {
    return WeatherEntity(
        key = key,
        city = city,
        cnt = cnt,
        cod = cod,
        list = list,
        message = message
        )
    }



/*key = it.key,
clouds = it.clouds,
dt = it.dt,
dtTxt = it.dtTxt,
main = it.main,
pop = it.pop,
sys = it.sys,
visibility = it.visibility,
weather = it.weather,
wind = it.wind*/
