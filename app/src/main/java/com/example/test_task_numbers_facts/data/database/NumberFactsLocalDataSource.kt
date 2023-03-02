package com.example.test_task_numbers_facts.data.database

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class NumberFactsLocalDataSource @Inject constructor(
    private val database: AppDatabase
) {
    fun getAllFacts(): Flow<List<NumberFactEntity>> =
        database.factsDao().getAllFacts()

    suspend fun insertAll(fact: NumberFactEntity) =
        database.factsDao().insertAll(fact)

    suspend fun deleteAll() =
        database.factsDao().deleteAll()
}