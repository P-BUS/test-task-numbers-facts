package com.example.test_task_weather_forecast.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test_task_weather_forecast.data.model.WeatherResponse
import com.example.test_task_weather_forecast.data.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.retry
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

const val TAG = "WeatherViewModel"

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository: WeatherRepository
) : ViewModel() {

    val weatherForecast: SharedFlow<WeatherResponse> =
        repository.weatherForecast
            .retry(3) { exeption ->
                (exeption is IOException).also { if (it) delay(1000) }
            }
            .shareIn(
                scope = viewModelScope,
                SharingStarted.WhileSubscribed(5000),
                1
            )

    init {
        refreshWeather("Lviv")
    }

    fun refreshWeather(cityName: String) {
        viewModelScope.launch {
            try {
                repository.refreshWeather(cityName)
            } catch (exeption: IOException) {
                Log.e(TAG, "IO Exception $exeption, you might not have internet connection")
            }
        }
    }
}