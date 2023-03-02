package com.example.test_task_numbers_facts.utils

import com.example.test_task_numbers_facts.data.database.NumberFactEntity
import com.example.test_task_numbers_facts.data.model.*
import com.example.test_task_numbers_facts.utils.Formatters.findNumberInSentence


object  Mappers {
    fun List<NumberFactEntity>.asDomainModel(): List<NumberFactModel> {
        return map {
            NumberFactModel(
                numberFact = it.numberFact,
                number = it.number
            )
        }
    }

    fun NumberFact.asDatabaseModel(): NumberFactEntity {
        return NumberFactEntity(
            numberFact = numberFact,
            number = numberFact.findNumberInSentence()
        )
    }
}
