package com.jetbrains.kmpapp.data

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class MuseumRepository(
    private val apiInterface: ApiInterface
    ) {
    private val scope = CoroutineScope(SupervisorJob())
    private val storedObjects = MutableStateFlow(emptyList<MuseumObject>())


    fun initialize() {
        scope.launch {
            refresh()
        }
    }

    suspend fun refresh() {
        storedObjects.value = apiInterface.getMuseumData()
    }

    fun getObjects(): Flow<List<MuseumObject>> = storedObjects

    fun getObjectById(objectId: Int): Flow<MuseumObject?> {
        return storedObjects.map { objects ->
            objects.find { it.objectID == objectId }
        }
    }
}
