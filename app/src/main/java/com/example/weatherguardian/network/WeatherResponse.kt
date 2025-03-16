package com.example.weatherguardian.network

data class WeatherResponse(
    val main: Main,
    val weather: List<Weather>
)

data class Main(
    val temp: Double, // Temperatura
    val humidity: Int // Umidade
)

data class Weather(
    val description: String // Descrição do clima (ex: "Clear sky")
)
