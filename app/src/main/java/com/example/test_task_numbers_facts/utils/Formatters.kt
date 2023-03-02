package com.example.test_task_numbers_facts.utils

import android.util.Log
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


const val BASIC_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss"
const val OUTPUT_FORMAT = "dd.MM.yyyy HH:mm"

object Formatters {

   fun String.findNumberInSentence(): String {
       val regex = Regex("\\d+")
       val result = regex.find(this)
       return result?.value.toString()
   }
}