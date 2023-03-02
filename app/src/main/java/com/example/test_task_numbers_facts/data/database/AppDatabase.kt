package com.example.test_task_numbers_facts.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [NumberFactEntity::class], version = 1, exportSchema = false)

abstract class AppDatabase : RoomDatabase() {
    abstract fun factsDao(): FactsDao
}