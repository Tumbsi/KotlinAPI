
package com.example.weatherapp.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.weatherapp.viewmodel.WeatherViewModel

@Composable
fun MainScreen(navController: NavController, weatherViewModel: WeatherViewModel) {
    var city by remember { mutableStateOf(TextFieldValue()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = city,
            onValueChange = { city = it },
            label = { Text("Enter city") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            weatherViewModel.fetchWeather(city.text, "API HERE")
        }) {
            Text("Get Weather")
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Display weather information with theme-based text colors
        val weatherData = weatherViewModel.weatherData.collectAsState().value
        weatherData?.let {
            Text("City: ${it.name}", color = MaterialTheme.colorScheme.onBackground)
            Text("Temperature: ${it.main.temp}°C", color = MaterialTheme.colorScheme.onBackground)

            // Temperature conversion
            val fahrenheitTemp = weatherViewModel.convertCelsiusToFahrenheit(it.main.temp)
            Text(
                "Temperature (Fahrenheit): ${"%.2f".format(fahrenheitTemp)} °F",
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Weather-based suggestion in a card with theme-based color
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f))
            ) {
                Text(
                    text = "Suggestion: ${weatherViewModel.getWeatherSuggestion(it.main.temp)}",
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(16.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text("Description: ${it.weather[0].description}", color = MaterialTheme.colorScheme.onBackground)
            Text("Humidity: ${it.main.humidity}%", color = MaterialTheme.colorScheme.onBackground)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            navController.navigate("info")
        }) {
            Text("App Info & Settings")
        }
    }
}
