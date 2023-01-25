package com.example.test_task_weather_forecast.repository

import android.util.Log
import com.example.test_task_weather_forecast.network.ApiResult
import com.example.test_task_weather_forecast.network.WetherRemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

const val TAG = "WeatherRepository"

class WeatherRepository @Inject constructor(
    private val network: WetherRemoteDataSource
) {
    suspend fun refreshWeather(cityName: String) {
        withContext(Dispatchers.IO) {
            val weatherList: List<> = listOf()
            when (val responce = network.getWeatherForecast(cityName)) {
                is ApiResult.Success -> responce.data
                is ApiResult.Error -> Log.e(TAG, "${responce.code} ${responce.message}")
                is ApiResult.Exception -> Log.e(TAG, "${responce.e.cause} ${responce.e.message}")
            }

        }
    }

}