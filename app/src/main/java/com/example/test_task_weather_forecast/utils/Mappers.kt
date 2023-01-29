package com.example.test_task_weather_forecast.utils

import com.example.test_task_weather_forecast.data.database.WeatherEntity
import com.example.test_task_weather_forecast.data.model.Weather
import com.example.test_task_weather_forecast.data.model.WeatherForecast
import com.example.test_task_weather_forecast.data.model.WeatherForecastHourly
import com.example.test_task_weather_forecast.data.model.WeatherHourly
import com.example.test_task_weather_forecast.data.model.WeatherModel
import com.example.test_task_weather_forecast.data.model.WeatherResponse

object  Mappers {
    fun WeatherEntity.asDomainModel(): WeatherModel {
        return WeatherModel(
            cityName = cityName,
            lat = lat,
            lon = lon,
            list = list
        )
    }

    fun WeatherResponse.asDatabaseModel(): WeatherEntity {
        return WeatherEntity(
            cityName = city?.name ?: "",
            lat = city?.coord?.lat ?: 0.0,
            lon = city?.coord?.lon ?: 0.0,
            list = list?.map {
                            it.asDatabaseModel()
            } ?: listOf(),
        )
    }
    fun WeatherForecast.asDatabaseModel(): WeatherForecastHourly {
        return WeatherForecastHourly(
            dtTxt = dtTxt ?: "",
            temp = main?.temp ?: 0.0,
            weather = weather?.map {
                                   it.asDatabaseModel()
            } ?: listOf(),
        )
    }
    fun Weather.asDatabaseModel(): WeatherHourly {
        return WeatherHourly(
            description = description ?: "",
            icon = icon ?: ""
        )
    }
}
