package com.example.test_task_weather_forecast.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.test_task_weather_forecast.data.model.City
import com.example.test_task_weather_forecast.data.model.WeatherForecast

@Entity(tableName = "weather_database")
data class WeatherEntity(
    @PrimaryKey(autoGenerate = true)
    val key: Int = 0,
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<WeatherForecast>,
    val message: Int
)
