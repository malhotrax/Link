package com.chat.presentation.feature.chat.chatlist

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ChatListVM : ViewModel() {
    private val _uiState = MutableStateFlow(ChatListState())
    val uiState = _uiState.asStateFlow()


}