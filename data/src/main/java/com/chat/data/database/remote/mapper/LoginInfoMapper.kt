package com.chat.data.database.remote.mapper

import com.chat.data.database.remote.dto.model.UserDataWithToken
import com.chat.domain.model.login.LoginInfo


fun UserDataWithToken.toDomain(): LoginInfo {
    return LoginInfo(
        refreshToken = this.refreshToken,
        accessToken = this.accessToken,
        user = this.user.toDomain()
    )
}