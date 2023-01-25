package com.example.test_task_weather_forecast.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test_task_weather_forecast.data.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

const val TAG = "WeatherViewModel"

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository: WeatherRepository
) : ViewModel() {

    init {
        refreshWeather("Lviv")
    }


    private fun refreshWeather(cityName: String) {
        viewModelScope.launch {
            try {
                repository.refreshWeather(cityName)
            } catch (exeption: IOException) {
                Log.e(TAG, "IO Exception $exeption, you might not have internet connection")
            }
        }
    }

}