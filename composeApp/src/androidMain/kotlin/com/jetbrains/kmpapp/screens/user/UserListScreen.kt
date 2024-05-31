package com.jetbrains.kmpapp.screens.user

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jetbrains.kmpapp.data.UserObjectListItem
import com.jetbrains.kmpapp.screens.EmptyScreenContent
import com.jetbrains.kmpapp.screens.UserListViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun UserListScreen(navigateToUserDetails: (objectId: Int) -> Unit) {
    val viewModel: UserListViewModel = koinViewModel()
    val objects by viewModel.objects.collectAsState()

    AnimatedContent(objects.isNotEmpty()) { objectsAvailable ->
        if (objectsAvailable) {
            UserList(
                objects = objects,
                onObjectClick = navigateToUserDetails,
            )
        } else {
            EmptyScreenContent(Modifier.fillMaxSize())
        }
    }
}

@Composable
private fun UserList(
    objects: List<UserObjectListItem>,
    onObjectClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn (
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(objects, key = { it.id }) { obj ->
            UserListItem(
                user = obj,
                onClick = { onObjectClick(obj.id) },
            )
        }
    }
}

@Composable
fun UserListItem(user: UserObjectListItem, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 16.dp, horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Load the user image asynchronously
//        AsyncImage(
//            model = user.avatar,
//            contentDescription = null,
//            modifier = Modifier.size(48.dp)
//        )

        Column {
            Text(text = user.name, style = MaterialTheme.typography.h6)
            Text(text = user.username, style = MaterialTheme.typography.body2)
            Text(text = user.email, style = MaterialTheme.typography.caption)
        }
    }
}