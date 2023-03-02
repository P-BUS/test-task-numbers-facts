package com.example.test_task_numbers_facts.data.repository

import android.util.Log
import com.example.test_task_numbers_facts.data.database.NumberFactsLocalDataSource
import com.example.test_task_numbers_facts.data.model.NumberFactModel
import com.example.test_task_numbers_facts.data.network.ApiResult
import com.example.test_task_numbers_facts.data.network.NumberFactsRemoteDataSource
import com.example.test_task_numbers_facts.utils.Mappers.asDatabaseModel
import com.example.test_task_numbers_facts.utils.Mappers.asDomainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

const val TAG = "NumberRepository"

class NumberFactsRepository @Inject constructor(
    private val network: NumberFactsRemoteDataSource,
    private val database: NumberFactsLocalDataSource
) {

    val factsStream: Flow<List<NumberFactModel>> =
        database.getAllFacts()
            .filterNotNull()
            .map {
                it.asDomainModel()
            }

    suspend fun retrieveFact(number: String) {
        withContext(Dispatchers.IO) {
            when (val response = network.getNumberFact(number)) {
                is ApiResult.Success -> {
                    val numberFact = response.data
                    database.insertAll(numberFact.asDatabaseModel())
                }
                is ApiResult.Error -> Log.e(TAG, "${response.code} ${response.message}")
                is ApiResult.Exception -> Log.e(TAG, "${response.e.cause} ${response.e.message}")
            }
        }
    }

    suspend fun retrieveRandomFact() {
        withContext(Dispatchers.IO) {
            when (val response = network.getRandomNumberFact()) {
                is ApiResult.Success -> {
                    val numberFact = response.data
                    database.insertAll(numberFact.asDatabaseModel())
                }
                is ApiResult.Error -> Log.e(TAG, "${response.code} ${response.message}")
                is ApiResult.Exception -> Log.e(TAG, "${response.e.cause} ${response.e.message}")
            }
        }
    }
}