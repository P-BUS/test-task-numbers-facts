package com.example.test_task_numbers_facts.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface FactsDao {
    @Query("SELECT * FROM facts_database")
    fun getAllFacts(): Flow<List<NumberFactEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(fact: NumberFactEntity)

    @Query("DELETE FROM facts_database")
    suspend fun deleteAll()
}