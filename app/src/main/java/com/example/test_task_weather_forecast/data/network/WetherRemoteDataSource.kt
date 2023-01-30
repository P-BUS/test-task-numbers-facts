package com.example.test_task_weather_forecast.data.network

import com.example.test_task_weather_forecast.data.model.WeatherResponse
import javax.inject.Inject

class WetherRemoteDataSource @Inject constructor(
    private val weatherApiService: WeatherApiService
) {
    suspend fun getWeatherForecastByCity(cityName: String): ApiResult<WeatherResponse> =
        handleApiResponse {
            weatherApiService.getWeatherForecast(cityName = cityName)
        }

    suspend fun getWeatherForecastByLocation(latitude: Float, longitude: Float): ApiResult<WeatherResponse> =
        handleApiResponse {
            weatherApiService.getWeatherForecast( latitude = latitude, longitude = longitude)
        }
}