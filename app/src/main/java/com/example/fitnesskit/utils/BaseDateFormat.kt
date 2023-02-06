package com.example.fitnesskit.utils

import android.os.Build
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class BaseDateFormat(
    private val oldDatePatter: String,
    private val newDatePatter: String
) {

    fun getNewDateFormat(text: String): String {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val date = LocalDate.parse(text, DateTimeFormatter.ofPattern(oldDatePatter))
            date.format(DateTimeFormatter.ofPattern(newDatePatter, Locale.getDefault()))
        } else {
            val oldDateFormat = SimpleDateFormat(oldDatePatter, Locale.getDefault())
            val newDateFormat = SimpleDateFormat(newDatePatter, Locale.getDefault())
            val date = oldDateFormat.parse(text)
            newDateFormat.format(date!!)
        }
    }
}