package com.example.test_task_weather_forecast.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.test_task_weather_forecast.data.model.*

@Entity(tableName = "weather_database")
data class WeatherEntity(
    @PrimaryKey(autoGenerate = true) val key: String,
    val clouds: Clouds,
    val dt: Int,
    @ColumnInfo(name = "dt_txt") val dtTxt: String,
    val main: Main,
    val pop: Int,
    val sys: Sys,
    val visibility: Int,
    val weather: List<Weather>,
    val wind: Wind
)
