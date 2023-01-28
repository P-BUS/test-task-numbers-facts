package com.example.test_task_weather_forecast.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.test_task_weather_forecast.data.model.*

@Entity(tableName = "weather_database")
data class WeatherEntity(
    @PrimaryKey(autoGenerate = true)
    val key: Int,
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<WeatherForecast>,
    val message: Int
)
