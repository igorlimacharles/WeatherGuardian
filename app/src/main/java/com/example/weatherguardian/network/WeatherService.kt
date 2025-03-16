package com.example.weatherguardian.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    // Método para obter o clima atual
    @GET("weather")
    fun getWeather(
        @Query("q") city: String,
        @Query("appid") apiKey: String,
        @Query("units") units: String = "metric"
    ): Call<WeatherResponse>

    // Método para obter a previsão do tempo para os próximos dias
    @GET("forecast")
    fun getForecast(
        @Query("lat") lat: Double,         // Latitude
        @Query("lon") lon: Double,         // Longitude
        @Query("appid") apiKey: String,    // API Key
        @Query("units") units: String = "metric" // Unidades (Celsius por padrão)
    ): Call<WeatherForecastResponse>

}

