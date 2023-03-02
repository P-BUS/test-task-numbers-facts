package com.example.test_task_numbers_facts.data.database.di

import android.content.Context
import androidx.room.Room
import com.example.test_task_numbers_facts.data.database.AppDatabase
import com.example.test_task_numbers_facts.data.database.FactsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ) : AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "weather_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Module
    @InstallIn(SingletonComponent::class)
    object DaoModule {
        @Provides
        fun provideWeatherDao(database: AppDatabase): FactsDao {
            return database.factsDao()
        }
    }
}

