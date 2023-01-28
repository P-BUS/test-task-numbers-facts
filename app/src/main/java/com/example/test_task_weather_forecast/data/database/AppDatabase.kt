package com.example.test_task_weather_forecast.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.test_task_weather_forecast.utils.ConverterCity
import com.example.test_task_weather_forecast.utils.ConverterClouds
import com.example.test_task_weather_forecast.utils.ConverterMain
import com.example.test_task_weather_forecast.utils.ConverterSys
import com.example.test_task_weather_forecast.utils.ConverterWeather
import com.example.test_task_weather_forecast.utils.ConverterWeatherForecastList
import com.example.test_task_weather_forecast.utils.ConverterWeatherList
import com.example.test_task_weather_forecast.utils.ConverterWind

@Database(entities = [WeatherEntity::class], version = 1, exportSchema = false)
@TypeConverters(
    ConverterClouds::class,
    ConverterMain::class,
    ConverterSys::class,
    ConverterWeather::class,
    ConverterWeatherList::class,
    ConverterWind::class,
    ConverterCity::class,
    ConverterWeatherForecastList::class
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
}