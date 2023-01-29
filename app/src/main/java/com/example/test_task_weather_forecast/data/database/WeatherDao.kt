package com.example.test_task_weather_forecast.data.database

import androidx.room.Dao
import androidx.room.Delete
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

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(weatherForecast: WeatherEntity)

    @Query("DELETE FROM weather_database")
    suspend fun deleteAll()
}