
package com.example.weatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.weatherapp.navigation.SetupNavGraph
import com.example.weatherapp.ui.theme.WeatherAppTheme
import com.example.weatherapp.viewmodel.SettingsViewModel
import com.example.weatherapp.viewmodel.WeatherViewModel


class MainActivity : ComponentActivity() {
    private val weatherViewModel: WeatherViewModel by viewModels()
    private val settingsViewModel: SettingsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val isDarkTheme by settingsViewModel.isDarkTheme.collectAsState()
            WeatherAppTheme(darkTheme = isDarkTheme) {
                val navController = rememberNavController()
                SetupNavGraph(
                    navController = navController,
                    weatherViewModel = weatherViewModel,
                    settingsViewModel = settingsViewModel
                )
            }
        }
    }
}

