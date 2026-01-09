package com.chat.presentation.util

import android.util.Log
import android.util.Patterns
import java.util.Calendar

object Validator {
    fun checkEmail(email: String): Boolean  {
        return !(email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email.trim()).matches())
    }

    fun checkPassword(password: String): Boolean {
        return password.length < 6
    }

    fun checkDateOfBirth(dateOfBirth: String): Boolean {
        val parts = dateOfBirth.split("/")
        Log.d("DOB",parts[2])
        val year = parts[2].toInt()
        Log.d("DOB",year.toString())
        val calendar = Calendar.getInstance()
        val currentYear = calendar.get(Calendar.YEAR)
        val age = currentYear - year
        return age < 6
    }

    fun checkUserName(username: String): Boolean {
        return username.isBlank()
    }

    fun checkFullName(fullName: String): Boolean {
        return fullName.isBlank()
    }


}