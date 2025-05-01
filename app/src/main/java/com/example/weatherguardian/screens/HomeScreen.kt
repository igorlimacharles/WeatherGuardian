package com.example.weatherguardian.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.weatherguardian.R

object AppColors {
    val primary = Color(0xFF2196F3) // Azul Principal
    val secondary = Color(0xFF03A9F4) // Azul Secundário
    val accent = Color(0xFFFFC107) // Amarelo (para detalhes)
    val textColorPrimary = Color.White
    val textColorSecondary = Color.Black
    val cardBackgroundLight = Color.LightGray
    val dayBackgroundColor = Color(0xFF87CEEB) // Light Blue
    val nightBackgroundColor = Color(0xFF191970) // Midnight Blue
    val errorColor = Color(0xFFB00020) // Vermelho para erros
}

@Composable
fun HomeScreen(navController: NavController, modifier: Modifier = Modifier) {
    val context = LocalContext.current

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = AppColors.primary // Cor de fundo principal
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp) // Espaçamento uniforme
        ) {
            WeatherHeader() // Cabeçalho com informações principais do clima
            WeatherAlerts() // Alertas climáticos
            DailyForecast()  // Previsão diária

            Row( // Row para alinhar os botões horizontalmente
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = { navController.navigate("search") },
                    colors = ButtonDefaults.buttonColors(containerColor = AppColors.accent)
                ) {
                    Text(text = "Buscar por Localização", color = AppColors.textColorSecondary)
                }

                Button(
                    onClick = {
                        val intent = android.content.Intent(context, com.example.weatherguardian.screens.ConfigScreen::class.java)
                        context.startActivity(intent)
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = AppColors.accent)
                ) {
                    Text(text = "Configurações", color = AppColors.textColorSecondary)
                }
            }
        }
    }
}

@Composable
fun WeatherHeader() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.padding(16.dp)
    ) {
        Image(
            painter = painterResource(R.drawable.cloud), // Ícone do clima (ex: nuvem)
            contentDescription = "Weather Icon",
            modifier = Modifier.size(192.dp),
            colorFilter = ColorFilter.tint(AppColors.textColorPrimary) // Cor do ícone
        )

        Text(
            text = "São Paulo",
            fontSize = 36.sp,
            color = AppColors.textColorPrimary,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "32°C",
            fontSize = 48.sp,
            color = AppColors.textColorPrimary,
            fontWeight = FontWeight.Bold
        )

        // Indicador visual para separar as informações
        Divider(
            color = AppColors.textColorPrimary,
            thickness = 1.dp,
            modifier = Modifier.width(80.dp)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.fillMaxWidth()
        ) {
            WeatherDataItem(icon = R.drawable.windy, label = "Vento", value = "24 km/h")
            WeatherDataItem(icon = R.drawable.umidity, label = "Umidade", value = "87%")
        }
    }
}

@Composable
fun WeatherDataItem(icon: Int, label: String, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(icon),
                contentDescription = label,
                modifier = Modifier.size(24.dp),
                colorFilter = ColorFilter.tint(AppColors.textColorPrimary)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = label,
                fontSize = 20.sp,
                color = AppColors.textColorPrimary
            )
        }
        Text(
            text = value,
            fontSize = 14.sp,
            color = AppColors.textColorPrimary
        )
    }
}

@Composable
fun WeatherAlerts() {
    Column {
        AlertCard(text = "Predomínio de chuva na parte da tarde")
        AlertCard(text = "Possibilidade de chuva e deslizamentos!")
    }
}

@Composable
fun AlertCard(text: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = AppColors.cardBackgroundLight)
    ) {
        Text(
            text = text,
            fontSize = 18.sp,
            modifier = Modifier.padding(16.dp),
            textAlign = TextAlign.Center,
            color = AppColors.textColorSecondary
        )
    }
}

@Composable
fun DailyForecast() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = AppColors.cardBackgroundLight)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Previsão para os próximos dias",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = AppColors.textColorSecondary,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            ForecastRow(day = "Hoje", icon = R.drawable.cloud, maxTemp = "32°C", minTemp = "29°C")
            ForecastRow(day = "Amanhã", icon = R.drawable.rain, maxTemp = "28°C", minTemp = "25°C")
            // Adicione mais linhas de previsão aqui
        }
    }
}

@Composable
fun ForecastRow(day: String, icon: Int, maxTemp: String, minTemp: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Text(text = day, fontSize = 16.sp, color = AppColors.textColorSecondary)
        Image(
            painter = painterResource(icon),
            contentDescription = "Weather Icon",
            modifier = Modifier.size(32.dp)
        )
        Text(text = maxTemp, fontSize = 16.sp, color = AppColors.textColorSecondary)
        Text(text = minTemp, fontSize = 16.sp, color = AppColors.textColorSecondary)
    }
}