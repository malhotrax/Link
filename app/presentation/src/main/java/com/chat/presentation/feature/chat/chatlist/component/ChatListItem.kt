package com.chat.presentation.feature.chat.chatlist.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.chat.domain.model.Chat
import com.chat.domain.model.User
import com.chat.domain.util.ChatStatus
import java.sql.Timestamp

@Composable
fun ChatListItem(
    modifier: Modifier = Modifier,
    chat: Chat,
    navigateToConversation: () -> Unit,
    expandProfile: () -> Unit
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = navigateToConversation)
            .padding(start = 8.dp, top = 10.dp, bottom = 10.dp, end = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center

        ) {
            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .clip(CircleShape)
                    .clickable(onClick = expandProfile)
                    .size(50.dp)
                ,
            ) {
                AsyncImage(
                    model = chat.user.profile,
                    contentDescription = "Profile Image",
                    contentScale = ContentScale.Crop
                )
            }

            Column(
                modifier = Modifier
                    .weight(0.8f)
                    .padding(start = 8.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        text = chat.user.userName,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onBackground,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = chat.lastActivity.toString(),
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Gray
                    )
                }
                Text(
                    modifier = Modifier.padding(4.dp),
                    text = chat.preview,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun PreviewChatListItem() {
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