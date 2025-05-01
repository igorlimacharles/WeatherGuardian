package com.example.weatherguardian

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.weatherguardian.screens.ConfiguracaoScreen
import com.example.weatherguardian.screens.HomeScreen
import com.example.weatherguardian.screens.SearchByLocScreen
import com.example.weatherguardian.ui.theme.WeatherGuardianTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherGuardianTheme { // Apply your theme here
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    WeatherGuardianApp()
                }
            }
        }
    }
}

@Composable
fun WeatherGuardianApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(navController = navController)
        }
        composable("search") {
            SearchByLocScreen(navController = navController)
        }
    }
}


