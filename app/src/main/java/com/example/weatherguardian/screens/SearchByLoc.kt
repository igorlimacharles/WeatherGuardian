package com.example.weatherguardian.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherguardian.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.net.URL

@Composable
fun SearchLocationScreen() {
    var location by remember { mutableStateOf("") }
    var weatherInfo by remember { mutableStateOf("Digite uma localização") }
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Blue)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        BasicTextField(
            value = location,
            onValueChange = { location = it },
            modifier = Modifier
                .background(Color.White, shape = MaterialTheme.shapes.medium)
                .padding(12.dp)
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = {
            coroutineScope.launch(Dispatchers.IO) {
                val apiKey = "2b063b0f36eafb25ea7e0a9b4696af0a"
                val apiUrl = "https://api.openweathermap.org/data/2.5/weather?q=$location&appid=$apiKey&units=metric&lang=pt"

                try {
                    val response = URL(apiUrl).readText()
                    val jsonObject = JSONObject(response)
                    val temp = jsonObject.getJSONObject("main").getDouble("temp")
                    val description = jsonObject.getJSONArray("weather").getJSONObject(0).getString("description")

                    weatherInfo = "$location: $temp°C, $description"
                } catch (e: Exception) {
                    Log.e("API_ERROR", "Erro ao buscar dados", e)
                    weatherInfo = "Erro ao buscar dados. Verifique a cidade."
                }
            }
        }) {
            Text("Buscar")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = weatherInfo,
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun SearchByLocPreview() {
    SearchLocationScreen()
}