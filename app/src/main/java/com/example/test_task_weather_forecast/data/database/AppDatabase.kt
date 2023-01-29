package com.example.test_task_weather_forecast.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.test_task_weather_forecast.utils.ConverterWeatherForecastList
import com.example.test_task_weather_forecast.utils.ConverterWeatherHourly
import com.example.test_task_weather_forecast.utils.ConverterWeatherList

@Database(entities = [WeatherEntity::class], version = 1, exportSchema = false)
@TypeConverters(
    ConverterWeatherHourly::class,
    ConverterWeatherList::class,
    ConverterWeatherForecastList::class
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
}