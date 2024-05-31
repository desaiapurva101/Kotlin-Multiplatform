package com.jetbrains.kmpapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.jetbrains.kmpapp.screens.DetailScreen
import com.jetbrains.kmpapp.screens.ListScreen
import com.jetbrains.kmpapp.screens.user.UserDetailScreen
import com.jetbrains.kmpapp.screens.user.UserListScreen
import kotlinx.serialization.Serializable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = ListScreen) {
                composable<ListScreen> {
                    ListScreen(navigateToDetails = { objectId ->
                        navController.navigate(DetailsScreen(objectId))
                    })
                }
                composable<DetailsScreen> {
                    val args = it.toRoute<DetailsScreen>()
                    DetailScreen(
                        objectId = args.objectId,
                        navigateBack = {
                            navController.popBackStack()
                        },
                        navigateToUserList = {
                            navController.navigate(UserListScreen)
                        }
                    )
                }
                composable<UserListScreen> {
                    UserListScreen(navigateToUserDetails = { objectId ->
                        navController.navigate(UserDetailsScreen(objectId))
                    })
                }
                composable<UserDetailsScreen> {
                    val args = it.toRoute<UserDetailsScreen>()
                    UserDetailScreen(
                        objectId = args.userId,
                        navigateBack = {
                            navController.popBackStack()
                        },

                    )
                }
            }
        }
    }
}

@Serializable
object ListScreen
@Serializable
object UserListScreen

@Serializable
data class DetailsScreen(
    val objectId: Int
)

@Serializable
data class UserDetailsScreen(
    val userId: Int
)