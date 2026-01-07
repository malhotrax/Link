package com.chat.presentation.util

import android.icu.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object Helper {

    fun convertMillisToDate(millis: Long): String {
        val formatter = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault())
        return formatter.format(Date(millis))
    }
}