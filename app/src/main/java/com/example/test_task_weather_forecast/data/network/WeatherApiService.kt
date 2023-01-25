package com.example.test_task_weather_forecast.data.network

import com.example.test_task_weather_forecast.data.model.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL = "api.openweathermap.org/"
const val ENDPOINT = "data/2.5/forecast/daily"
const val API_KEY = "6ee76d21efd76f29a47aa8098659a46e"


interface WeatherApiService {

    @GET(ENDPOINT)
    suspend fun getWeatherForecast(
        @Query("q") cityName: String = "Lviv",
        @Query("lat") latitude: Float = 44.34F,
        @Query("lon") longitude: Float = -2.15F,
        @Query("cnt") numberOfTimestamps: Int = 5,
        @Query("appid") apiKey: String = API_KEY,
        @Query("units") measurementStandard: String = "metric"
    ): Response<WeatherResponse>
}