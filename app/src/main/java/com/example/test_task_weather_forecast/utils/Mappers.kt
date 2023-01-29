package com.example.test_task_weather_forecast.utils

import com.example.test_task_weather_forecast.data.database.WeatherEntity
import com.example.test_task_weather_forecast.data.model.WeatherModel
import com.example.test_task_weather_forecast.data.model.WeatherResponse

object  Mappers {
    fun WeatherEntity.asDomainModel(): WeatherResponse {
        return WeatherResponse(
            city = city,
            cnt = cnt,
            cod = cod,
            list = list,
            message = message
        )
    }

    fun WeatherResponse.asDatabaseModel(): WeatherEntity {
        return WeatherEntity(
            city = city,
            cnt = cnt,
            cod = cod,
            list = list,
            message = message
        )
    }

    fun WeatherResponse.asWeatherModel(): WeatherModel {
        return WeatherModel(
            cityName = city?.name ?: "",
            lat = city?.coord?.lat ?: 0.0,
            lon = city?.coord?.lon ?: 0.0
        )
    }
}
