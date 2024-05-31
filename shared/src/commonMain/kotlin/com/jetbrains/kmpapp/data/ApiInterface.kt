package com.jetbrains.kmpapp.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlin.coroutines.cancellation.CancellationException

interface ApiInterface {
    suspend fun getMuseumData(): List<MuseumObject>
    suspend fun getUserData(): ArrayList<UserObjectListItem>
}

class KtorApiInterface(private val client: HttpClient) : ApiInterface {
    companion object {
        private const val MUSEUM_API_URL =
            "https://raw.githubusercontent.com/Kotlin/KMP-App-Template-Native/main/list.json"
        private const val USER_API_URL =
            "https://jsonplaceholder.typicode.com/users"
    }

    override suspend fun getMuseumData(): List<MuseumObject> {
        return try {
            client.get(MUSEUM_API_URL).body()
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            e.printStackTrace()

            emptyList()
        }
    }

    override suspend fun getUserData(): ArrayList<UserObjectListItem> {
        return try {
            client.get(USER_API_URL).body()
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            e.printStackTrace()

            arrayListOf()
        }
    }
}