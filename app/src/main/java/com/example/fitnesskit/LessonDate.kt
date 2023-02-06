package com.example.fitnesskit

import java.text.SimpleDateFormat
import java.util.*

class LessonDate(date: String) : Comparable<LessonDate> {

    var date: Date
    var oldDate: String
    private val formatter: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)

    init {
        oldDate = date
        this.date = formatter.parse(date) as Date
    }

    override fun compareTo(other: LessonDate): Int {
        val calendar1 = Calendar.getInstance().apply {
            time = date
        }
        val calendar2 = Calendar.getInstance().apply {
            time = other.date
        }

        val month1 = calendar1.get(Calendar.MONTH)
        val month2 = calendar2.get(Calendar.MONTH)

        if (month1 < month2)
            return -1
        else if (month1 == month2)
            return calendar1.get(Calendar.DAY_OF_MONTH) - calendar2.get(Calendar.DAY_OF_MONTH)

        else return 1
    }
}