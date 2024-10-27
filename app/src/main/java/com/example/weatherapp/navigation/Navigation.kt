
package com.example.weatherapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.weatherapp.ui.theme.MainScreen
import com.example.weatherapp.ui.theme.InfoScreen
import com.example.weatherapp.viewmodel.SettingsViewModel
import com.example.weatherapp.viewmodel.WeatherViewModel

@Composable
fun SetupNavGraph(
    navController: NavHostController,
    weatherViewModel: WeatherViewModel,
    settingsViewModel: SettingsViewModel
) {
    NavHost(navController = navController, startDestination = "main") {
        composable("main") { MainScreen(navController, weatherViewModel) }
        composable("info") { InfoScreen(navController, settingsViewModel) }
    }
}
