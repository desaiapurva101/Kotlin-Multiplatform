package com.jetbrains.kmpapp.di

import com.jetbrains.kmpapp.data.KtorApiInterface
import com.jetbrains.kmpapp.data.ApiInterface
import com.jetbrains.kmpapp.data.MuseumRepository
import com.jetbrains.kmpapp.data.UserRepository
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module

val dataModule = module {
    single {
        val json = Json { ignoreUnknownKeys = true }
        HttpClient {
            install(ContentNegotiation) {
                // TODO Fix API so it serves application/json
                json(json, contentType = ContentType.Any)
            }
        }
    }

    single<ApiInterface> { KtorApiInterface(get()) }
    single {
        MuseumRepository(get()).apply {
            initialize()
        }
    }
    single {
        UserRepository(get()).apply {
            initialize()
        }
    }
}

fun initKoin() = initKoin(emptyList())

fun initKoin(extraModules: List<Module>) {
    startKoin {
        modules(
            dataModule,
            *extraModules.toTypedArray(),
        )
    }
}
