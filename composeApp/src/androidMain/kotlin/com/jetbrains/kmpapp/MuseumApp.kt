package com.jetbrains.kmpapp

import android.app.Application
import com.jetbrains.kmpapp.di.initKoin
import com.jetbrains.kmpapp.screens.DetailViewModel
import com.jetbrains.kmpapp.screens.ListViewModel
import com.jetbrains.kmpapp.screens.UserDetailViewModel
import com.jetbrains.kmpapp.screens.UserListViewModel
import com.mmk.kmpnotifier.notification.NotifierManager
import com.mmk.kmpnotifier.notification.configuration.NotificationPlatformConfiguration
import org.koin.dsl.module

class MuseumApp : Application() {
    override fun onCreate() {
        super.onCreate()
        NotifierManager.initialize(
            configuration = NotificationPlatformConfiguration.Android(
                notificationIconResId = R.drawable.ic_launcher_foreground,
            )
        )
        initKoin(
            listOf(
                module {
                    factory { ListViewModel(get()) }
                    factory { DetailViewModel(get()) }
                    factory { UserListViewModel(get()) }
                    factory { UserDetailViewModel(get()) }
                }
            )
        )
    }
}
