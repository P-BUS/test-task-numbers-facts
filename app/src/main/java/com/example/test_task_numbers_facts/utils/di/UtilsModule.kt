package com.example.test_task_numbers_facts.utils.di

import com.example.test_task_numbers_facts.utils.CoroutineDispatchers
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object UtilsModule {

    @Singleton
    @Provides
    fun provideDispatchers(): CoroutineDispatchers {
        return CoroutineDispatchers()
    }
}