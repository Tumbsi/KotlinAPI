package com.example.weatherapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.model.WeatherResponse
import kotlinx.coroutines.flow.MutableStateFlow

import com.example.weatherapp.network.RetrofitInstance

import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {
    private val _weatherData = MutableStateFlow<WeatherResponse?>(null)
    val weatherData: StateFlow<WeatherResponse?> = _weatherData

    // Convert temperature from Celsius to Fahrenheit
    fun convertCelsiusToFahrenheit(celsius: Double): Double {
        return (celsius * 9 / 5) + 32
    }

    // Convert temperature from Fahrenheit to Celsius
    fun convertFahrenheitToCelsius(fahrenheit: Double): Double {
        return (fahrenheit - 32) * 5 / 9
    }

    fun fetchWeather(city: String, apiKey: String) {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getWeather(city, apiKey)
                _weatherData.value = response
            } catch (e: Exception) {
                // Handle error
            }
        }
    }

    fun getWeatherSuggestion(tempCelsius: Double): String {
        return when {
            tempCelsius <= 0 -> "It's freezing! Wear a heavy coat, scarf, and gloves."
            tempCelsius in 1.0..15.0 -> "It's cold. Consider wearing a jacket or sweater."
            tempCelsius in 16.0..25.0 -> "The weather is mild. A light jacket or long-sleeve shirt should be fine."
            tempCelsius in 26.0..35.0 -> "It's warm. Dress lightly and stay hydrated."
            else -> "It's hot! Wear cool, breathable clothing and drink plenty of water."
        }
    }

}