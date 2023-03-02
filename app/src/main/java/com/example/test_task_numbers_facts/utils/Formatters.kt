package com.example.test_task_numbers_facts.utils

object Formatters {

   fun String.findNumberInSentence(): String {
       val regex = Regex("\\d+")
       val result = regex.find(this)
       return result?.value.toString()
   }
}