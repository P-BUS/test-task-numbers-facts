package com.example.test_task_weather_forecast.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.test_task_weather_forecast.R
import com.example.test_task_weather_forecast.data.model.WeatherForecastHourly
import com.example.test_task_weather_forecast.databinding.ItemViewBinding
import com.example.test_task_weather_forecast.utils.ImageLoader


class ForecastListAdapter() :
    ListAdapter<WeatherForecastHourly, ForecastListAdapter.ListViewHolder>(DiffCallback) {

    class ListViewHolder(private var binding: ItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(forecast: WeatherForecastHourly, holder: ListViewHolder) {
            binding.tvDate.text = forecast.dtTxt
            binding.tvTemperature.text = holder.binding.tvTemperature.context.resources.getString(R.string.temperature, forecast.temp)
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
        holder.bind(getItem(position), holder)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<WeatherForecastHourly>() {
        override fun areItemsTheSame(oldItem: WeatherForecastHourly, newItem: WeatherForecastHourly): Boolean {
            return oldItem.temp == newItem.temp
        }

        override fun areContentsTheSame(oldItem: WeatherForecastHourly, newItem: WeatherForecastHourly): Boolean {
            return oldItem == newItem
        }
    }
}