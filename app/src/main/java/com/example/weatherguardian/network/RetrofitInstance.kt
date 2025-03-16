package com.example.weatherguardian.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "https://api.openweathermap.org/data/2.5/"

    // Instância do Retrofit
    val api: WeatherApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()) // Usando o Gson para converter JSON
            .build()
            .create(WeatherApiService::class.java) // Criando a interface que contém os endpoints
    }
}
