package com.example.test_task_weather_forecast.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.test_task_weather_forecast.data.model.WeatherForecast
import com.example.test_task_weather_forecast.databinding.ItemViewBinding
import com.example.test_task_weather_forecast.utils.ImageLoader

class ForecastListAdapter() :
    ListAdapter<WeatherForecast, ForecastListAdapter.ListViewHolder>(DiffCallback) {

    class ListViewHolder(private var binding: ItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(forecast: WeatherForecast) {
            binding.tvDate.text = forecast.dtTxt
            binding.tvTemperature.text = forecast.main.temp.toString()
            ImageLoader.loadImage(binding.ivItemWeatherIcon, forecast.weather[0].icon)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val viewHolder = ListViewHolder(
            ItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
        return viewHolder
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object DiffCallback : DiffUtil.ItemCallback<WeatherForecast>() {
        override fun areItemsTheSame(oldItem: WeatherForecast, newItem: WeatherForecast): Boolean {
            return oldItem.main.temp == newItem.main.temp
        }

        override fun areContentsTheSame(
            oldItem: WeatherForecast,
            newItem: WeatherForecast
        ): Boolean {
            return oldItem == newItem
        }
    }
}