package com.bhupen.moviedisplaylist.common.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

object DateTimeUtil {

    @SuppressLint("SimpleDateFormat")
    private val yearMonthDayFormatter = SimpleDateFormat("yyyy-MM-dd")

    @SuppressLint("SimpleDateFormat")
    private val yearMonthFormatter = SimpleDateFormat("MMMM yyyy")

    fun formatDateToString(date: Date = Date()): String =
        yearMonthDayFormatter.format(date)

    fun getYearMonthFromDateString(dateString: String): String {
        val date = yearMonthDayFormatter.parse(dateString)!!
        return yearMonthFormatter.format(date)
    }
}