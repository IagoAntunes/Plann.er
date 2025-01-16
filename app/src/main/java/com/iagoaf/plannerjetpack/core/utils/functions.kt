package com.iagoaf.plannerjetpack.core.utils

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

fun convertMillisToDate(millis: Long): String {
    val calendar = Calendar.getInstance().apply {
        timeInMillis = millis
        add(Calendar.DAY_OF_MONTH, 1)
        set(Calendar.HOUR_OF_DAY, 0)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
    }
    val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return formatter.format(calendar.time)
}