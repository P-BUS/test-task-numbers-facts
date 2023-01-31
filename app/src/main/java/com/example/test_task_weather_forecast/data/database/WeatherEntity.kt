package com.example.test_task_weather_forecast.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.test_task_weather_forecast.data.model.WeatherForecastHourly

@Entity(tableName = "weather_database")
data class WeatherEntity(
    val cityName: String = "",
    val lat: Double = 0.0,
    val lon: Double = 0.0,
    val list: List<WeatherForecastHourly> = listOf()
) {
    @PrimaryKey(autoGenerate = true)
    var key: Int = 0
}
