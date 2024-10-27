
package com.example.weatherapp.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button // Use Button from Material3
import androidx.compose.material3.MaterialTheme // Use MaterialTheme from Material3
import androidx.compose.material3.Switch // Use Switch from Material3
import androidx.compose.material3.Text // Use Text from Material3
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.weatherapp.viewmodel.SettingsViewModel

@Composable
fun InfoScreen(navController: NavController, settingsViewModel: SettingsViewModel) {
    val isDarkTheme by settingsViewModel.isDarkTheme.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Weather App", style = MaterialTheme.typography.headlineLarge, color = MaterialTheme.colorScheme.onBackground)
        Spacer(modifier = Modifier.height(16.dp))
        Text("This app provides weather information using OpenWeatherMap.", color = MaterialTheme.colorScheme.onBackground)
        Switch(
            checked = isDarkTheme,
            onCheckedChange = { settingsViewModel.toggleTheme() }
        )
        Button(onClick = { navController.popBackStack() }) {
            Text("Back", color = MaterialTheme.colorScheme.onBackground)
        }

    }
}

