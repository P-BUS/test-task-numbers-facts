package com.example.test_task_numbers_facts.utils

import com.example.test_task_numbers_facts.data.model.NumberFact
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type


class NumberFactConverterFactory : Converter.Factory() {

    companion object {
        fun create(): NumberFactConverterFactory {
            return NumberFactConverterFactory()
        }
    }

    override fun responseBodyConverter(
        type: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): Converter<ResponseBody, *>? {
        return if (type == NumberFact::class.java) {
            Converter { value: ResponseBody ->
                val responseString = value.string().trim()
                NumberFact(responseString)
            }
        } else {
            null
        }
    }
}