package com.example.test_task_weather_forecast.utils

import com.example.test_task_weather_forecast.data.database.WeatherEntity
import com.example.test_task_weather_forecast.data.model.Weather
import com.example.test_task_weather_forecast.data.model.WeatherForecast
import com.example.test_task_weather_forecast.data.model.WeatherForecastHourly
import com.example.test_task_weather_forecast.data.model.WeatherHourly
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
            cityName = city.name,
            lat = city.coord.lat,
            lon = city.coord.lon,
            list = list,
        )
    }

    fun WeatherResponse.asDomainModel(): WeatherModel {
        return WeatherModel(
            cityName = city?.name ?: "",
            lat = city?.coord?.lat ?: 0.0,
            lon = city?.coord?.lon ?: 0.0,
            list = list?.map {
                            it.asDomainModel()
            } ?: listOf(),
        )
    }

    fun WeatherForecast.asDomainModel(): WeatherForecastHourly {
        return WeatherForecastHourly(
            dtTxt = dtTxt ?: "",
            temp = main?.temp ?: 0.0,
            weather = weather?.map {
                                   it.asDomainModel()
            } ?: listOf(),
        )
    }

    fun Weather.asDomainModel(): WeatherHourly {
        return WeatherHourly(
            description = description ?: "",
            icon = icon ?: ""
        )
    }
}
