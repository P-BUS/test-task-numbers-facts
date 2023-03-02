package com.example.test_task_numbers_facts.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.test_task_numbers_facts.utils.ConverterFactsList

@Database(entities = [NumberFactEntity::class], version = 1, exportSchema = false)
@TypeConverters(
    ConverterFactsList::class,
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun factsDao(): FactsDao
}