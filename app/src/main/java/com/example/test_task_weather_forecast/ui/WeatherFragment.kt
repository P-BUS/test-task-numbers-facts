package com.example.test_task_weather_forecast.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.test_task_weather_forecast.data.model.WeatherForecast
import com.example.test_task_weather_forecast.databinding.WeatherFragmentBinding
import com.example.test_task_weather_forecast.ui.adapters.ForecastListAdapter
import com.example.test_task_weather_forecast.ui.viewmodel.WeatherViewModel
import com.example.test_task_weather_forecast.utils.ImageLoader
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

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



        sharedViewModel.refreshWeather("Lviv")

        // Get input City
        val typedCity = binding.ilInputCity.editText?.text.toString()

        lifecycleScope.launch {
            sharedViewModel.weatherForecast
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect {
                    bindForecast(it)
                    adapter.submitList(it)
                }
        }
    }

    private fun bindForecast(forecast: List<WeatherForecast>) {
        binding.tvCity.text = forecast[0].main.feelsLike.toString()
        binding.tvTemperature.text = forecast[0].main.temp.toString()
        binding.tvWeatherDescription.text = forecast[0].weather[0].description
        ImageLoader.loadImage(binding.ivWeatherIcon, forecast[0].weather[0].icon)
    }

}