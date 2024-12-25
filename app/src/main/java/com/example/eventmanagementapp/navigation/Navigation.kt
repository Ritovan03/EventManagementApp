package com.example.eventmanagementapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.eventmanagementapp.screen.HomeContent
import com.example.eventmanagementapp.screen.HomeScreen
import com.example.eventmanagementapp.screen.LoginScreen
import com.example.eventmanagementapp.screen.ResetPass1
import com.example.eventmanagementapp.screen.ResetPass2
import com.example.eventmanagementapp.screen.ResetPass3
import com.example.eventmanagementapp.screen.SignupScreen

@Composable
fun NavGraphBuilder(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "login"
    ) {
        composable("login") {
            LoginScreen(navController = navController)
        }
        composable("signup") {
            SignupScreen(navController = navController)
        }
        composable("main") {
            HomeScreen(navController = navController)
        }

        composable("rp1") {
            ResetPass1(navController = navController)
        }

        composable("rp2") {
            ResetPass2(navController = navController)
        }

        composable("rp3") {
            ResetPass3(navController = navController)
        }
    }
}











