package com.example.test_task_weather_forecast.data.repository

import android.util.Log
import com.example.test_task_weather_forecast.data.database.WeatherLocalDataSource
import com.example.test_task_weather_forecast.data.model.WeatherModel
import com.example.test_task_weather_forecast.data.network.ApiResult
import com.example.test_task_weather_forecast.data.network.WetherRemoteDataSource
import com.example.test_task_weather_forecast.utils.Mappers.asDatabaseModel
import com.example.test_task_weather_forecast.utils.Mappers.asDomainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

const val TAG = "WeatherRepository"

class WeatherRepository @Inject constructor(
    private val network: WetherRemoteDataSource,
    private val database: WeatherLocalDataSource
) {

    val weatherForecast: Flow<WeatherModel> =
        database.getAllWeather().filterNotNull().map {
            it.asDomainModel()
        }

    suspend fun refreshWeatherByCity(cityName: String) {
        withContext(Dispatchers.IO) {
            when (val response = network.getWeatherForecastByCity(cityName)) {
                is ApiResult.Success -> {
                    val weather = response.data
                    database.deleteAll()
                    database.insertAll(weather.asDatabaseModel())
                }
                is ApiResult.Error -> Log.e(TAG, "${response.code} ${response.message}")
                is ApiResult.Exception -> Log.e(TAG, "${response.e.cause} ${response.e.message}")
            }
        }
    }

    suspend fun refreshWeatherByLocation(latitude: Float, longitude: Float) {
        withContext(Dispatchers.IO) {
            when (val response = network.getWeatherForecastByLocation(latitude, longitude)) {
                is ApiResult.Success -> {
                    val weather = response.data
                    database.deleteAll()
                    database.insertAll(weather.asDatabaseModel())
                }
                is ApiResult.Error -> Log.e(TAG, "${response.code} ${response.message}")
                is ApiResult.Exception -> Log.e(TAG, "${response.e.cause} ${response.e.message}")
            }
        }
    }
}