package com.chat.presentation.util

import android.util.Patterns

object Validator {
    fun checkEmail(email: String): Boolean  {
        return !(email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches())
    }

    fun checkPassword(password: String): Boolean {
        return password.length < 6
    }

    fun checkDateOfBirth(dateOfBirth: String): Boolean {
        return dateOfBirth.isNotEmpty()
    }

    fun checkUserName(username: String): Boolean {
        return username.trim().isNotEmpty()
    }

    fun checkFullName(fullName: String): Boolean {
        return fullName.trim().isNotEmpty()
    }


}