package com.jetbrains.kmpapp.data

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class UserRepository(
    private val apiInterface: ApiInterface
    ) {
    private val scope = CoroutineScope(SupervisorJob())
    private val storedObjects = MutableStateFlow(emptyList<UserObjectListItem>())


    fun initialize() {
        scope.launch {
            refresh()
        }
    }

    private suspend fun refresh() {
        storedObjects.value = apiInterface.getUserData()
    }

    fun getObjects(): Flow<List<UserObjectListItem>> = storedObjects

    fun getObjectById(objectId: Int): Flow<UserObjectListItem?> {
        return storedObjects.map { objects ->
            objects.find { it.id == objectId }
        }
    }
}
