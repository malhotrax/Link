package com.chat.presentation.feature.chat.coversation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Call
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.chat.domain.model.User
import com.chat.presentation.R
import com.chat.presentation.feature.chat.coversation.component.ConversationTopBar
import com.chat.presentation.feature.chat.coversation.component.FeatureMessages
import com.chat.presentation.feature.chat.coversation.component.MessageInput
import com.chat.presentation.ui.theme.LinkTheme
import com.chat.presentation.util.ActionButton

@Composable
fun ConversationScreen(
    user: User,
    navigateToBio: () -> Unit,
    onBackClick: () -> Unit,
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            ConversationTopBar(
                modifier = Modifier,
                title = user.userName,
                profilePic = user.profile,
                goToBio = navigateToBio,
                onBackClick = onBackClick,
                actions = listOf(
                    ActionButton(
                        title = "Call",
                        selectedIcon = Icons.Rounded.Call,
                        action = {}
                    ),
                    ActionButton(
                        title = "More",
                        selectedIcon = Icons.Rounded.MoreVert,
                        action = {}
                    )
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {

                FeatureMessages(
                    modifier = Modifier
                        .weight(0.9f)
                )
                MessageInput(
                    modifier = Modifier,
                    inputValue = "",
                    onInputValueChange = {},
                    sendMessage = {}
                )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewConversationScreen(modifier: Modifier = Modifier) {
    LinkTheme {
        ConversationScreen(
            user = User(
                userId = "1",
                fullName = "Manish Malhotra",
                userName = "Manish",
                profile = "https://img.freepik.com/free-photo/close-up-portrait-handsome-smiling-young-man-white-t-shirt-blurry-outdoor-nature_176420-6305.jpg?semt=ais_hybrid&w=740&q=80",
                email = "manish@gmail.com"
            ),
            {},
            {},
        )
    }
}