package com.example.weatherguardian.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherguardian.R

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {

    Box(
        modifier = Modifier
            .background(color = Color.White)
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

            Row(verticalAlignment = Alignment.CenterVertically) {

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        painter = painterResource(R.drawable.cloud),
                        contentDescription = "Weather Icon", Modifier
                            .size(256.dp)
                            .padding(25.dp)

                    )

                    Text(
                        text = "São Paulo",
                        fontSize = 48.sp,
                        color = Color.White,
                        modifier = Modifier.padding(10.dp),

                        )


                    Text(
                        text = "32ºC",
                        fontSize = 36.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 10.dp)
                    )

                    Text(
                        text = "———————————",
                        fontSize = 32.sp,
                        color = Color.White,
                        modifier = Modifier.padding(0.dp),

                        )

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(5.dp)
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {

                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.padding(10.dp)
                            ) {

                                Image(
                                    painter = painterResource(R.drawable.windy),
                                    contentDescription = "Windy Icon",
                                    Modifier
                                        .size(32.dp)
                                        .padding(5.dp),
                                    colorFilter = ColorFilter.tint(Color.White)

                                )

                                Text(
                                    text = "Vento",
                                    fontSize = 32.sp,
                                    color = Color.White,
                                    modifier = Modifier.padding(0.dp),

                                    )

                            }

                            Text(
                                text = "24 km/h",
                                fontSize = 16.sp,
                                color = Color.White,
                                modifier = Modifier.padding(0.dp),

                                )

                        }
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {

                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.padding(10.dp)
                            ) {

                                Image(
                                    painter = painterResource(R.drawable.umidity),
                                    contentDescription = "Umidity Icon",
                                    Modifier
                                        .size(32.dp)
                                        .padding(5.dp),
                                    colorFilter = ColorFilter.tint(Color.White)

                                )

                                Text(
                                    text = "Umidade",
                                    fontSize = 32.sp,
                                    color = Color.White,
                                    modifier = Modifier.padding(0.dp),

                                    )

                            }

                            Text(
                                text = "87%",
                                fontSize = 16.sp,
                                color = Color.White,
                                modifier = Modifier.padding(0.dp),

                                )

                        }

                    }

                }

            }

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
                    shape = RoundedCornerShape(
                        32.dp
                    )

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
                                Modifier
                                    .size(64.dp)
                                    .padding(8.dp)
                            )
                            Image(
                                painter = painterResource(R.drawable.cloud),
                                contentDescription = "Rain Icon",
                                Modifier
                                    .size(64.dp)
                                    .padding(start = 8.dp, end = 8.dp)
                            )
                            Image(
                                painter = painterResource(R.drawable.cloud),
                                contentDescription = "Rain Icon",
                                Modifier
                                    .size(64.dp)
                                    .padding(start = 8.dp, end = 8.dp)
                            )
                            Image(
                                painter = painterResource(R.drawable.cloud),
                                contentDescription = "Rain Icon",
                                Modifier
                                    .size(64.dp)
                                    .padding(start = 8.dp, end = 8.dp)
                            )
                            Image(
                                painter = painterResource(R.drawable.cloud),
                                contentDescription = "Rain Icon",
                                Modifier
                                    .size(64.dp)
                                    .padding(start = 8.dp, end = 8.dp)
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

@Composable
fun WeatherText(text: String) {
    Text(
        modifier = Modifier.padding(15.dp),
        text = text,
        fontSize = 24.sp,
        color = Color.Black,
        fontWeight = FontWeight.Bold
    )
}


@Preview(showSystemUi = true)
@Composable
private fun HomeScreenPreview() {
    HomeScreen()
}