package com.ahmrh.newsapp.common.util

import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Date
import java.util.concurrent.TimeUnit
import kotlin.time.Duration

object DateUtils {

    fun stringToDate(dateString: String): Date {
        val trimmedDateString = dateString.substring(0, 19) // Extracts "yyyy-MM-dd'T'HH:mm:ss"

        val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        return formatter.parse(trimmedDateString)
    }
    fun getElapsedTime(date: Date): String {
        val now = Calendar.getInstance()
        val then = Calendar.getInstance()
        then.time = date

        val diffInMs = now.timeInMillis - then.timeInMillis

        val seconds = TimeUnit.MILLISECONDS.toSeconds(diffInMs)
        val minutes = TimeUnit.MILLISECONDS.toMinutes(diffInMs)
        val hours = TimeUnit.MILLISECONDS.toHours(diffInMs)
        val days = TimeUnit.DAYS.convert(diffInMs, TimeUnit.MILLISECONDS)
        val months = days / 30 // Approximate calculation
        val years = days / 365 // Approximate calculation

        if (years > 0) return "$years year${if (years > 1) "s" else ""} ago"
        if (months > 0) return "$months month${if (months > 1) "s" else ""} ago"
        if (days > 0) return "$days day${if (days > 1) "s" else ""} ago"
        if (hours > 0) return "$hours hour${if (hours > 1) "s" else ""} ago"
        if (minutes > 0) return "$minutes minute${if (minutes > 1) "s" else ""} ago"
        if (seconds > 0) return "$seconds second${if (seconds > 1) "s" else ""} ago"

        return "Just Now"
    }
}