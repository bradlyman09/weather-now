package com.example.playground.utils

import android.text.format.DateFormat
import java.util.*

fun Long.timestampToTime() : String {
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = this * 1000

    return DateFormat.format("HH:mm", calendar).toString()
}

fun Long.timestampToDate() : String {
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = this * 1000

    return DateFormat.format("MMMM dd", calendar).toString()
}