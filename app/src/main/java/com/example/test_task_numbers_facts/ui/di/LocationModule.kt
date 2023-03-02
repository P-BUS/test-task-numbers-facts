package com.example.test_task_numbers_facts.ui.di

import android.content.Context
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.qualifiers.ActivityContext

@Module
@InstallIn(FragmentComponent::class)
object LocationModule {

    @Provides
    fun provideFusedLocationProviderClient(
        @ActivityContext context: Context
    ): FusedLocationProviderClient {
        return LocationServices.getFusedLocationProviderClient(context)
    }
}