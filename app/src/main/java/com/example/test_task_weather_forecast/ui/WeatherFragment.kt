package com.example.test_task_weather_forecast.ui

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.test_task_weather_forecast.data.model.WeatherModel
import com.example.test_task_weather_forecast.data.model.WeatherResponse
import com.example.test_task_weather_forecast.databinding.WeatherFragmentBinding
import com.example.test_task_weather_forecast.ui.adapters.ForecastListAdapter
import com.example.test_task_weather_forecast.ui.viewmodel.WeatherViewModel
import com.example.test_task_weather_forecast.utils.ImageLoader
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

const val TAG = "WeatherFragment"

@AndroidEntryPoint
class WeatherFragment : Fragment() {
    private val sharedViewModel: WeatherViewModel by activityViewModels()
    private lateinit var binding: WeatherFragmentBinding
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = WeatherFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = binding.rvWeatherScroll
        val adapter = ForecastListAdapter()
        recyclerView.adapter = adapter


        binding.etEditCity.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                val editText = binding.etEditCity.text.toString()
                sharedViewModel.refreshWeather(editText)
                true
            } else {
                false
            }
        }

        lifecycleScope.launch {
            sharedViewModel.weatherForecast
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .distinctUntilChanged()
                .collect {
                    bindForecast(it)
                    adapter.submitList(it.list)
                }
        }
    }

    private fun bindForecast(forecast: WeatherModel) {
        binding.tvCity.text = forecast.cityName
        binding.tvTemperature.text = forecast.list.first().temp.toString()
        binding.tvWeatherDescription.text = forecast.list.first().weather.last().description
        ImageLoader.loadImage(binding.ivWeatherIcon, forecast.list.first().weather.first().icon)
    }
}