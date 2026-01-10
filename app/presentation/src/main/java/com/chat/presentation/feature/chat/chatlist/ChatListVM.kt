package com.chat.presentation.feature.chat.chatlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chat.domain.model.Chat
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ChatListVM : ViewModel() {
    private val _uiState = MutableStateFlow(ChatListState())
    val uiState = _uiState.asStateFlow()
    private val _uiEvent = MutableSharedFlow<ChatListUiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()
    private val _chats = MutableStateFlow<List<Chat>>(emptyList())
    val chats = _chats.asStateFlow()

    fun onEvent(event: ChatListEvent) {
        when(event) {
            is ChatListEvent.DeleteChat -> onDeleteChat(event.chatId)
            ChatListEvent.Logout -> onLogout()
            is ChatListEvent.PinUnpin -> onPinUnpin(event.chatId, event.isPinned)
            ChatListEvent.NavigateToConversation -> onNavigateToConversation()
            ChatListEvent.FindNewFriends -> onFindNewFriends()
            ChatListEvent.NavigateToSearch -> onNavigateToSearch()
        }
    }

    private fun onNavigateToSearch() {
        sendUiEvent(ChatListUiEvent.NavigateToSearch)
    }

    private fun onFindNewFriends() {
        sendUiEvent(ChatListUiEvent.FindNewFriends)
    }

    private fun sendUiEvent(event: ChatListUiEvent) {
        viewModelScope.launch {
            _uiEvent.emit(event)
        }
    }
    private fun onNavigateToConversation() {
        sendUiEvent(ChatListUiEvent.NavigateToConversation)
    }
    private fun onPinUnpin(chatId: String, pinned: Boolean) {
        _chats.value = _chats.value.map {
            if(it.chatId == chatId) {
                it.copy(isPinned = pinned)
            } else {
                it
            }
        }
    }

    private fun onLogout() {

    }

    private fun onDeleteChat(chatId: String) {}
}

