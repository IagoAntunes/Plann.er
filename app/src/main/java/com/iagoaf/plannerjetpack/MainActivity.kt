package com.iagoaf.plannerjetpack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.iagoaf.plannerjetpack.src.registerUser.presentation.RegisterUserScreen
import com.iagoaf.plannerjetpack.src.splashScreen.presentation.SplashScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = "splash_screen",
                builder = {
                    composable("splash_screen") {
                        SplashScreen(navController)
                    }
                    composable("register_user_screen") {
                        RegisterUserScreen()
                    }
                }
            )

        }
    }
}

