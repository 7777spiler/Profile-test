package com.example.profilepage

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun CustomNavHost(users: MutableList<User>): NavHostController {

    val navHostController = rememberNavController()
    NavHost(
        navController = navHostController,
        startDestination = "login"
    ) {
        composable("login") {
            LoginPage(navHostController)
        }
        composable(
            route = "profileList",
        ) {
            ProfileListPage(
                navController = navHostController,
                users = users
            )
        }
        composable(
            route = "profile/{userId}",
            arguments = listOf(
                navArgument("userId") {
                    type = NavType.IntType
                },
            )
        ) {
            val userId = it.arguments?.getInt("userId")!!

            ProfilePage(
                user = users.get(userId.toInt())
            )
        }
    }
    return navHostController
}