package com.example.test_task_numbers_facts.data.network

import com.example.test_task_numbers_facts.data.model.NumberFact
import javax.inject.Inject

class NumberFactsRemoteDataSource @Inject constructor(
    private val weatherApiService: NumberApiService
) {
    suspend fun getNumberFact(number: String): ApiResult<NumberFact> =
        handleApiResponse {
            weatherApiService.getNumberFact(number = number)
        }

    suspend fun getRandomNumberFact(): ApiResult<NumberFact> =
        handleApiResponse {
            weatherApiService.getRandomNumberFact()
        }


}