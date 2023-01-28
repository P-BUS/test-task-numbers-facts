package com.example.test_task_weather_forecast.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.test_task_weather_forecast.data.model.City
import com.example.test_task_weather_forecast.data.model.WeatherForecast

@Entity(tableName = "weather_database")
data class WeatherEntity(
    val city: City?,
    val cnt: Int? = 0,
    val cod: String? = "",
    val list: List<WeatherForecast>? = listOf(),
    val message: Int? = 0
) {
    @PrimaryKey(autoGenerate = true)
    var key: Int? = 0
}
