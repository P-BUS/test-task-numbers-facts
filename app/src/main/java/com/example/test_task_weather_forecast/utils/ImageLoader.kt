package com.example.test_task_weather_forecast.utils

import android.widget.ImageView
import androidx.core.net.toUri
import coil.load
import com.example.test_task_weather_forecast.R

object ImageLoader {
    fun loadImage(imageView: ImageView, imageCode: String) {
        val imageUrl = "http://openweathermap.org/img/wn/$imageCode@2x.png"
        imageUrl.let {
            val imageUri = imageUrl.toUri().buildUpon().scheme("https").build()
            imageView.load(imageUri) {
                placeholder(R.drawable.loading_animation)
                error(R.drawable.ic_broken_image)
            }
        }
    }
}