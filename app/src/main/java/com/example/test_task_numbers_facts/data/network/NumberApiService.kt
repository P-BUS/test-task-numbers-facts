package com.example.test_task_numbers_facts.data.network

import com.example.test_task_numbers_facts.data.model.NumberFact
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

const val BASE_URL = "http://numbersapi.com/"
const val ENDPOINT_RANDOME_NUMBER = "random/math"

interface NumberApiService {

    @GET("{number}")
    suspend fun getNumberFact(
        @Path("number") number: String = ""
    ): Response<NumberFact>

    @GET(ENDPOINT_RANDOME_NUMBER)
    suspend fun getRandomNumberFact(): Response<NumberFact>
}
