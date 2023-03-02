package com.example.test_task_numbers_facts.utils

import androidx.room.TypeConverter
import com.example.test_task_numbers_facts.data.database.NumberFactEntity
import com.example.test_task_numbers_facts.data.model.*
import com.google.gson.Gson

class ConverterFactsList {
    @TypeConverter
    fun factsListToJson(value: List<NumberFactEntity>?) =
        Gson().toJson(value)

    @TypeConverter
    fun jsonToFactsList(value: String) =
        Gson().fromJson(value, Array<NumberFactEntity>::class.java).toList()
}



