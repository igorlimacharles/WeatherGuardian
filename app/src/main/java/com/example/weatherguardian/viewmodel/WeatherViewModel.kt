package com.example.weatherguardian.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherguardian.network.WeatherService
import com.example.weatherguardian.network.WeatherResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WeatherViewModel : ViewModel() {
    private val api = Retrofit.Builder()
        .baseUrl("https://api.openweathermap.org/data/2.5/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(WeatherService::class.java)

    var weatherData: WeatherResponse? = null
    var isLoading: Boolean = false
    var errorMessage: String = ""

    fun fetchWeather(city: String, apiKey: String) {
        isLoading = true

        viewModelScope.launch {
            try {
                val response = api.getWeather(city, apiKey).execute()
                if (response.isSuccessful) {
                    weatherData = response.body()
                } else {
                    errorMessage = "Failed to load data"
                }
            } catch (e: Exception) {
                errorMessage = "An error occurred: ${e.message}"
            } finally {
                isLoading = false
            }
        }
    }
}
