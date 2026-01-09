package com.chat.presentation.feature.chat.chatlist.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.chat.domain.model.Chat
import com.chat.domain.model.User
import com.chat.domain.util.ChatStatus
import java.sql.Timestamp

@Composable
fun FeatureChats(modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
    ) {
        items(10) {
            ChatListItem(
                chat = Chat(
                    chatId = "1",
                    User(
                        userId = "1",
                        fullName = "Manish Malhotra",
                        userName = "Manish",
                        email = "manish@gmail.com",
                        profile = "https://img.freepik.com/free-photo/close-up-portrait-handsome-smiling-young-man-white-t-shirt-blurry-outdoor-nature_176420-6305.jpg?semt=ais_hybrid&w=740&q=80",
                    ),
                    status = ChatStatus.READ,
                    preview = "This is a last message from Manish Malhotra. So it's better if you get back to work as soon as possible.",
                    lastActivity = Timestamp(System.currentTimeMillis())
                ),
                navigateToConversation = {},
                expandProfile = {}
            )
        }
    }
}