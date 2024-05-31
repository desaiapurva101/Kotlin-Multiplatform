package com.jetbrains.kmpapp.screens

import com.jetbrains.kmpapp.data.UserObjectListItem
import com.jetbrains.kmpapp.data.UserRepository
import com.rickclephas.kmm.viewmodel.KMMViewModel
import com.rickclephas.kmm.viewmodel.stateIn
import com.rickclephas.kmp.nativecoroutines.NativeCoroutinesState
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow

class UserListViewModel(userRepository: UserRepository) : KMMViewModel() {
    @NativeCoroutinesState
    val objects: StateFlow<List<UserObjectListItem>> =
        userRepository.getObjects()
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
}
