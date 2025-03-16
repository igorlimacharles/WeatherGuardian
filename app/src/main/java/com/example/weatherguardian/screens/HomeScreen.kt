package com.example.weatherguardian.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherguardian.R
import com.example.weatherguardian.network.ApiClient
import com.example.weatherguardian.network.WeatherResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun HomeScreen() {
    // Variáveis de estado
    var weatherData by remember { mutableStateOf<WeatherResponse?>(null) }
    var isLoading by remember { mutableStateOf(true) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    val city = "São Paulo"
    val apiKey = "74ef25b903a24f73ccb7fc9a814ccd2a"

    // Lançando a chamada da API
    LaunchedEffect(Unit) {
        withContext(Dispatchers.IO) {
            ApiClient.weatherService.getWeather(city, apiKey).enqueue(object : Callback<WeatherResponse> {
                override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>) {
                    if (response.isSuccessful) {
                        weatherData = response.body()
                        Log.d("API Success", "Dados do clima recebidos com sucesso")
                    } else {
                        errorMessage = "Erro na resposta: ${response.code()}"
                        Log.e("API Error", "Erro na resposta: ${response.errorBody()}")
                    }
                    isLoading = false
                }

                override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                    isLoading = false
                    errorMessage = "Falha na requisição: ${t.message}"
                    Log.e("API Error", "Erro na requisição: ${t.message}", t)
                }
            })
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Blue)
            .verticalScroll(rememberScrollState()),
        contentAlignment = Alignment.TopEnd
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (isLoading) {
                // Exibe uma mensagem de carregamento enquanto a API responde
                Text("Carregando...", fontSize = 24.sp, color = Color.White)
            } else {
                // Exibe erro se houver algum problema na requisição
                errorMessage?.let {
                    Text(
                        text = it,
                        fontSize = 24.sp,
                        color = Color.Red,
                        modifier = Modifier.padding(10.dp)
                    )
                }
                // Exibe os dados se a resposta for bem-sucedida
                weatherData?.let { data ->
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(R.drawable.cloud), // Substitua com seu ícone de clima
                            contentDescription = "Weather Icon",
                            modifier = Modifier.size(256.dp).padding(25.dp)
                        )

                        Text(
                            text = data.name,
                            fontSize = 48.sp,
                            color = Color.White,
                            modifier = Modifier.padding(10.dp)
                        )

                        Text(
                            text = "${data.main.temp.toInt()}ºC",
                            fontSize = 36.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(bottom = 10.dp)
                        )

                        Text(
                            text = "———————————",
                            fontSize = 32.sp,
                            color = Color.White
                        )

                        // Exibindo dados adicionais como vento e umidade
                        Row(
                            modifier = Modifier.padding(5.dp),
                            horizontalArrangement = Arrangement.spacedBy(20.dp)
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Image(
                                    painter = painterResource(R.drawable.windy), // Substitua com ícone de vento
                                    contentDescription = "Windy Icon",
                                    modifier = Modifier.size(32.dp),
                                    colorFilter = ColorFilter.tint(Color.White)
                                )

                                Text(
                                    text = "Vento",
                                    fontSize = 24.sp,
                                    color = Color.White
                                )

                                Text(
                                    text = "${data.wind.speed.toInt()} km/h",
                                    fontSize = 16.sp,
                                    color = Color.White
                                )
                            }

                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Image(
                                    painter = painterResource(R.drawable.umidity), // Substitua com ícone de umidade
                                    contentDescription = "Umidity Icon",
                                    modifier = Modifier.size(32.dp),
                                    colorFilter = ColorFilter.tint(Color.White)
                                )

                                Text(
                                    text = "Umidade",
                                    fontSize = 24.sp,
                                    color = Color.White
                                )

                                Text(
                                    text = "${data.main.humidity}%",
                                    fontSize = 16.sp,
                                    color = Color.White
                                )
                            }
                        }

                        // Adicionando cartões de previsão
                        Column(verticalArrangement = Arrangement.SpaceEvenly) {
                            Card(modifier = Modifier.padding(20.dp)) {
                                Text(
                                    text = "Predomínio de chuva na parte da tarde",
                                    fontSize = 26.sp,
                                    modifier = Modifier.padding(15.dp)
                                )
                            }

                            Card(modifier = Modifier.padding(20.dp)) {
                                Text(
                                    text = "Possibilidade de chuva e deslizamentos!",
                                    fontSize = 26.sp,
                                    modifier = Modifier.padding(25.dp)
                                )
                            }

                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .fillMaxHeight()
                                    .padding(20.dp),
                                shape = RoundedCornerShape(32.dp)
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.padding(8.dp)
                                ) {
                                    Column(horizontalAlignment = Alignment.Start) {
                                        WeatherText("Hoje")
                                        WeatherText("Amanhã")
                                        WeatherText("Hoje")
                                        WeatherText("Hoje")
                                        WeatherText("Hoje")
                                    }

                                    Column(horizontalAlignment = Alignment.Start) {
                                        Image(
                                            painter = painterResource(R.drawable.cloud),
                                            contentDescription = "Rain Icon",
                                            Modifier.size(64.dp).padding(8.dp)
                                        )
                                        Image(
                                            painter = painterResource(R.drawable.cloud),
                                            contentDescription = "Rain Icon",
                                            Modifier.size(64.dp).padding(start = 8.dp, end = 8.dp)
                                        )
                                        Image(
                                            painter = painterResource(R.drawable.cloud),
                                            contentDescription = "Rain Icon",
                                            Modifier.size(64.dp).padding(start = 8.dp, end = 8.dp)
                                        )
                                        Image(
                                            painter = painterResource(R.drawable.cloud),
                                            contentDescription = "Rain Icon",
                                            Modifier.size(64.dp).padding(start = 8.dp, end = 8.dp)
                                        )
                                        Image(
                                            painter = painterResource(R.drawable.cloud),
                                            contentDescription = "Rain Icon",
                                            Modifier.size(64.dp).padding(start = 8.dp, end = 8.dp)
                                        )
                                    }

                                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                        WeatherText("32ºC")
                                        WeatherText("32ºC")
                                        WeatherText("32ºC")
                                        WeatherText("32ºC")
                                        WeatherText("32ºC")
                                    }

                                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                        WeatherText("29ºC")
                                        WeatherText("29ºC")
                                        WeatherText("29ºC")
                                        WeatherText("29ºC")
                                        WeatherText("29ºC")
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun WeatherText(text: String) {
    Text(
        modifier = Modifier.padding(15.dp),
        text = text,
        fontSize = 20.sp,
        color = Color.Black,
        fontWeight = FontWeight.Bold
    )
}


@Preview(showSystemUi = true)
@Composable
private fun HomeScreenPreview() {
    HomeScreen()
}