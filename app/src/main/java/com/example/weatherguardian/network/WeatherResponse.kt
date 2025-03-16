package com.example.weatherguardian.network

data class WeatherResponse(
    val main: Main,
    val wind: Wind,
    val name: String,
    val weather: List<Weather>
)

data class Main(
    val temp: Double,
    val humidity: Int
)

data class Wind(
    val speed: Double
)

data class Weather(
    val description: String,
    val icon: String
)

