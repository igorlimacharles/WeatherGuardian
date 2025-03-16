package com.example.weatherguardian.network


data class WeatherForecastResponse(
    val list: List<ForecastItem>
)

data class ForecastItem(
    val dt: Long, // Data em formato timestamp
    val temp: Temp,
    val weather: List<Weather>,
)

data class Temp(
    val day: Double, // Temperatura média do dia
    val min: Double, // Temperatura mínima
    val max: Double  // Temperatura máxima
)