package com.example.eventmanagementapp.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController

@Composable
fun SignupScreen(navController: NavHostController) {
        Column(
            modifier = Modifier.fillMaxSize()
        ){
            Text(text = "Signup Screen")

            Button(onClick = { navController.navigate("home") }) {
                Text(text = "Go to Home")

            }
        }
}