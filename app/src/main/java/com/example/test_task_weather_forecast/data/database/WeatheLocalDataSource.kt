package com.example.test_task_weather_forecast.data.database

import com.example.test_task_weather_forecast.data.model.WeatherForecast
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class WeatherLocalDataSource @Inject constructor(
    private val database: AppDatabase
) {
    fun getAllWeather(): Flow<WeatherEntity> =
        database.weatherDao().getAllWeather()

    suspend fun insertAll(weatherForecast: WeatherEntity) =
        database.weatherDao().insertAll(weatherForecast)

    suspend fun deleteAll() =
        database.weatherDao().deleteAll()
}