package com.example.test_task_numbers_facts.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "facts_database")
data class NumberFactEntity(
    val number: String = "",
    val numberFact: String = ""
) {
    @PrimaryKey(autoGenerate = true)
    var key: Int = 0
}
