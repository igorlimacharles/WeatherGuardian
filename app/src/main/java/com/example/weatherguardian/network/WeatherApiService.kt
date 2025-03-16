package com.example.weatherguardian.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    @GET("weather")
    fun getWeather(
        @Query("q") city: String, // Nome da cidade
        @Query("appid") apiKey: String, // Sua chave da API
        @Query("units") units: String = "metric" // Unidades de medida (Celsius)
    ): Call<WeatherResponse>
}
