package com.example.test_task_weather_forecast.ui.di

import com.example.test_task_weather_forecast.MainActivity
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
object LocationModule {

    @Provides
    fun provideFusedLocationProviderClient(
        activity: MainActivity
    ): FusedLocationProviderClient {
        return LocationServices.getFusedLocationProviderClient(activity)
    }
}