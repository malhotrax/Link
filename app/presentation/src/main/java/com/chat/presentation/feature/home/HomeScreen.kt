package com.chat.presentation.feature.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.chat.presentation.feature.chat.chatlist.ChatListScreen
import com.chat.presentation.feature.home.component.TopNavigation
import com.chat.presentation.feature.groups.GroupScreen
import com.chat.presentation.feature.home.component.BottomNavigation
import com.chat.presentation.feature.profile.ProfileScreen
import com.chat.presentation.feature.profile.component.profileItems
import com.chat.presentation.feature.requests.RequestsScreen
import com.chat.presentation.util.BottomNavActionButton
import com.chat.presentation.util.PrimaryTabs
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    navigateToConversation: () -> Unit,
    navigateToSearch: () -> Unit,
    navigateToFindNewFriends: () -> Unit,
) {
    val primaryTabs = PrimaryTabs.entries
    val pagerState = rememberPagerState(pageCount = { primaryTabs.size})
    val scope = rememberCoroutineScope()
    val currentTab = primaryTabs[pagerState.currentPage]
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        topBar = {

            TopNavigation(
                title = currentTab.title,
                logOut = {}
            )
        },
        bottomBar = {
            BottomNavigation(
                modifier = Modifier,
                actionButtons = primaryTabs.mapIndexed { index, tab ->
                    BottomNavActionButton(
                        title = tab.title,
                        unselectedIcon = tab.unselectedIcon,
                        selectedIcon = tab.selectedIcon,
                        isSelected = pagerState.currentPage == index,
                        action = {
                            scope.launch {
                                pagerState.scrollToPage(index)
                            }
                        }
                    )
                }
            )
        },
        floatingActionButton = if(currentTab.title != PrimaryTabs.PROFILE.title) {
            {
                FloatingActionButton(
                    onClick = {},
                ){
                    Icon(Icons.Rounded.Add, "Add")
                }
            }
        } else { {} }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier
            ) { pageIndex ->
                when(primaryTabs[pageIndex]) {
                    PrimaryTabs.CHATS -> ChatListScreen(
                        navigateToConversation = navigateToConversation,
                        navigateToSearch = navigateToSearch,
                        navigateToFindNewFriends = navigateToFindNewFriends
                    )
                    PrimaryTabs.GROUPS -> GroupScreen()
                    PrimaryTabs.REQUESTS -> RequestsScreen()
                    PrimaryTabs.PROFILE -> ProfileScreen(profileItems = profileItems, "https://img.freepik.com/free-photo/close-up-portrait-handsome-smiling-young-man-white-t-shirt-blurry-outdoor-nature_176420-6305.jpg?semt=ais_hybrid&w=740&q=80", {},{})
                }
            }
        }
    }
}