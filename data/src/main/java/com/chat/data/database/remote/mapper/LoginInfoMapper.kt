package com.chat.data.database.remote.mapper

import com.chat.data.database.remote.dto.model.UserDataWithToken
import com.chat.domain.model.User
import com.chat.domain.model.login.LoginCredential
import com.chat.domain.model.login.LoginInfo

fun LoginInfo.toDto() : UserDataWithToken {
    return UserDataWithToken (
        refreshToken = this.refreshToken,
        accessToken = this.accessToken,
        user = this.user.toDto()
    )
}

fun UserDataWithToken.toDomain(): LoginInfo {
    return LoginInfo(
        refreshToken = this.refreshToken,
        accessToken = this.accessToken,
        user = this.user.toDomain()
    )
}