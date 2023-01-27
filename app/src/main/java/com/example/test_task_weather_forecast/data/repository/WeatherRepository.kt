package com.example.test_task_weather_forecast.data.repository

import android.util.Log
import com.example.test_task_weather_forecast.data.database.WeatherLocalDataSource
import com.example.test_task_weather_forecast.data.model.WeatherForecast
import com.example.test_task_weather_forecast.data.network.ApiResult
import com.example.test_task_weather_forecast.data.network.WetherRemoteDataSource
import com.example.test_task_weather_forecast.utils.asDatabaseModel
import com.example.test_task_weather_forecast.utils.asDomainModel
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

    val weatherForecast: Flow<List<WeatherForecast>> =
        database.getAllWeather()
            .map {it.asDomainModel() }

    suspend fun refreshWeather(cityName: String) {
        withContext(Dispatchers.IO) {
            var weatherList: List<WeatherForecast> = listOf()
            when (val response = network.getWeatherForecast(cityName)) {
                is ApiResult.Success -> weatherList = response.data.list
                is ApiResult.Error -> Log.e(TAG, "${response.code} ${response.message}")
                is ApiResult.Exception -> Log.e(TAG, "${response.e.cause} ${response.e.message}")
            }
            database.insertAll(weatherList.asDatabaseModel())
        }
    }
}