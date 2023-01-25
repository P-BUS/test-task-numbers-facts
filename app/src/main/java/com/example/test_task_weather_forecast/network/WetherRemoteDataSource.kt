package com.example.test_task_weather_forecast.network

import javax.inject.Inject

class WetherRemoteDataSource @Inject constructor(
    private val weatherApiService: WeatherApiService
) {
    suspend fun getWeatherForecast(cityName: String): ApiResult<> =
        handleApiResponse {
            weatherApiService.getWeatherForecast(cityName)
        }
}