package com.example.weatherguardian.screens

import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.URL
import java.net.URLEncoder
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun SearchByLocScreen(navController: NavController) {
    var location by remember { mutableStateOf("") }
    var weatherData by remember { mutableStateOf<JSONObject?>(null) }
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current  // Adiciona o contexto

    // Estado para a cor de fundo dinâmica
    var backgroundColor by remember { mutableStateOf(AppColors.dayBackgroundColor) } // Inicia com a cor do dia

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = backgroundColor // Cor de fundo dinâmica
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            LocationInputField(location = location, onLocationChange = { location = it })
            SearchButton(onSearchClicked = {
                coroutineScope.launch(Dispatchers.IO) {
                    fetchWeatherData(location) { result ->
                        weatherData = result.first
                        backgroundColor = result.second
                    }
                }
            })

            // Condicionalmente mostrar a informação meteorológica ou a mensagem de "nenhuma informação"
            if (weatherData != null) {
                // Adiciona o weight ao WeatherInfo para que ele ocupe a maior parte do espaço, mas não todo
                WeatherInfo(data = weatherData!!, modifier = Modifier.weight(1f)) // Passa o modifier
            } else {
                Text("Nenhuma informação disponível", color = AppColors.textColorPrimary)
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Button(onClick = { navController.popBackStack() }) {
                    Text(text = "Voltar para HomeScreen")
                }

                Button(onClick = {
                    val intent = Intent(context, ConfigScreen::class.java)
                    context.startActivity(intent)
                }) {
                    Text(text = "Configurações")
                }
            }

        }
    }
}

@Composable
fun LocationInputField(location: String, onLocationChange: (String) -> Unit) {
    OutlinedTextField(
        value = location,
        onValueChange = onLocationChange,
        label = { Text("Digite uma cidade", color = AppColors.textColorPrimary) },
        modifier = Modifier.fillMaxWidth(),
        textStyle = TextStyle(color = AppColors.textColorPrimary),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = AppColors.textColorPrimary,
            unfocusedBorderColor = Color.Gray,
            cursorColor = AppColors.textColorPrimary
        )
    )
}

@Composable
fun SearchButton(onSearchClicked: () -> Unit) {
    Button(onClick = onSearchClicked) {
        Text("Buscar")
    }
}

suspend fun fetchWeatherData(location: String, onResult: (Pair<JSONObject?, Color>) -> Unit) {
    val apiKey = "2b063b0f36eafb25ea7e0a9b4696af0a"
    val encodedLocation = URLEncoder.encode(location, "UTF-8")
    val apiUrl =
        "https://api.openweathermap.org/data/2.5/weather?q=$encodedLocation&appid=$apiKey&units=metric&lang=pt"

    try {
        val response = URL(apiUrl).readText()
        val jsonObject = JSONObject(response)
        val backgroundColor = getBackgroundColor(jsonObject)
        withContext(Dispatchers.Main) {
            onResult(Pair(jsonObject, backgroundColor))
        }
    } catch (e: Exception) {
        withContext(Dispatchers.Main) {
            onResult(Pair(null, AppColors.errorColor)) // Cor de erro
        }
    }
}

@Composable
fun WeatherInfo(data: JSONObject, modifier: Modifier = Modifier) { // Aceita um Modifier
    val temp = data.getJSONObject("main").getDouble("temp")
    val tempMin = data.getJSONObject("main").getDouble("temp_min")
    val tempMax = data.getJSONObject("main").getDouble("temp_max")
    val feelsLike = data.getJSONObject("main").getDouble("feels_like")
    val pressure = data.getJSONObject("main").getInt("pressure")
    val humidity = data.getJSONObject("main").getInt("humidity")
    val seaLevel = data.getJSONObject("main").optInt("sea_level", -1)
    val groundLevel = data.getJSONObject("main").optInt("grnd_level", -1)
    val visibility = data.optInt("visibility", -1)
    val windSpeed = data.getJSONObject("wind").getDouble("speed")
    val windDeg = data.getJSONObject("wind").getInt("deg")
    val clouds = data.getJSONObject("clouds").getInt("all")
    val sunrise = data.getJSONObject("sys").getLong("sunrise")
    val sunset = data.getJSONObject("sys").getLong("sunset")
    val city = data.getString("name")
    val country = data.getJSONObject("sys").getString("country")

    val dateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())

    Column(
        modifier = modifier // Aplica o modifier passado
            .fillMaxWidth()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "$city, $country",
            color = AppColors.textColorPrimary,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))

        WeatherCard("🌡 Temperatura", "$temp°C (min: $tempMin°C, max: $tempMax°C)")
        WeatherCard("🤔 Sensação térmica", "$feelsLike°C")
        WeatherCard("🔵 Pressão", "$pressure hPa")
        WeatherCard("💧 Chuva", "$humidity%")
        if (seaLevel != -1) WeatherCard("🌊 Nível do mar", "$seaLevel hPa")
        if (groundLevel != -1) WeatherCard("⛰️ Nível do solo", "$groundLevel hPa")
        if (visibility != -1) WeatherCard("👀 Visibilidade", "$visibility metros")
        WeatherCard("💨 Vento", "$windSpeed m/s (Direção: $windDeg°)")
        WeatherCard("☁ Nuvens", "$clouds% de cobertura")
        WeatherCard("🌅 Nascer do Sol", dateFormat.format(Date(sunrise * 1000)))
        WeatherCard("🌇 Pôr do Sol", dateFormat.format(Date(sunset * 1000)))
    }
}

@Composable
fun WeatherCard(title: String, value: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = AppColors.cardBackgroundLight)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start,
                color = AppColors.textColorSecondary,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                value,
                fontSize = 14.sp,
                textAlign = TextAlign.Start,
                color = AppColors.textColorSecondary,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

fun getBackgroundColor(data: JSONObject): Color {
    val sunrise = data.getJSONObject("sys").getLong("sunrise") * 1000
    val sunset = data.getJSONObject("sys").getLong("sunset") * 1000
    val currentTime = System.currentTimeMillis()

    return when {
        currentTime in sunrise..sunset -> AppColors.dayBackgroundColor // Light Blue (Dia)
        else -> AppColors.nightBackgroundColor // Midnight Blue (Noite)
    }
}