package com.chat.presentation.navigation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chat.domain.repository.TokenRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppNavViewModel @Inject constructor(
    private val tokenRepository: TokenRepository,
) : ViewModel() {

    private val _authState = MutableStateFlow<AuthState>(AuthState.LoggedOut)
    val authState = _authState.asStateFlow()

    init {
        isLoggedIn()
    }

    private fun isLoggedIn() {
        viewModelScope.launch {
            _authState.value = AuthState.Loading
            tokenRepository.accessToken().collect {
                if (it.isNullOrBlank()) {
                    _authState.value = AuthState.LoggedOut
                } else {
                    _authState.value = AuthState.LoggedIn
                }
            }
        }
    }
}