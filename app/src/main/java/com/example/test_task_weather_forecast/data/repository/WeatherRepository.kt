package com.example.test_task_weather_forecast.data.repository

import android.util.Log
import com.example.test_task_weather_forecast.data.database.WeatherLocalDataSource
import com.example.test_task_weather_forecast.data.model.Weather
import com.example.test_task_weather_forecast.data.model.WeatherResponse
import com.example.test_task_weather_forecast.data.network.ApiResult
import com.example.test_task_weather_forecast.data.network.WetherRemoteDataSource
import com.example.test_task_weather_forecast.utils.Mappers.asDatabaseModel
import com.example.test_task_weather_forecast.utils.Mappers.asDomainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

const val TAG = "WeatherRepository"

class WeatherRepository @Inject constructor(
    private val network: WetherRemoteDataSource,
    private val database: WeatherLocalDataSource
) {

    val weatherForecast: Flow<WeatherResponse> =
        database.getAllWeather()
            .map {it.asDomainModel() }

    suspend fun refreshWeather(cityName: String) {
        withContext(Dispatchers.IO) {

            when (val response = network.getWeatherForecast(cityName)) {
                is ApiResult.Success -> {
                    val weather = response.data
                    database.insertAll(weather.asDatabaseModel())}
                is ApiResult.Error -> Log.e(TAG, "${response.code} ${response.message}")
                is ApiResult.Exception -> Log.e(TAG, "${response.e.cause} ${response.e.message}")
            }
        }
    }
}