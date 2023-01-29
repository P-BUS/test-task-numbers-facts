package com.example.test_task_weather_forecast.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import com.example.test_task_weather_forecast.data.model.WeatherForecast
import kotlinx.coroutines.flow.Flow


@Dao
interface WeatherDao {

    @Query("SELECT * FROM weather_database")
    fun getAllWeather(): Flow<WeatherEntity>

    //@Query("SELECT * FROM weather_database WHERE ")
    @Upsert()
    suspend fun insertAll(weatherForecast: WeatherEntity)

    @Upsert()
    suspend fun insertWeatherList(weatherForecast: WeatherEntity)


}