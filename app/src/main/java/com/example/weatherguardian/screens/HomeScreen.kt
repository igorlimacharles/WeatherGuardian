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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
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
            .background(color = Color.Blue),
        contentAlignment = Alignment.TopEnd
    ) {

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Row(verticalAlignment = Alignment.CenterVertically) {

                Column() {
                    Text(
                        text = "São Paulo",
                        fontSize = 32.sp,
                        color = Color.White,
                        modifier = Modifier.padding(top = 100.dp, bottom = 10.dp),

                        )

                    Text(
                        text = "32ºC",
                        fontSize = 32.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 100.dp)
                    )
                }

                Image(
                    painter = painterResource(R.drawable.cloud),
                    contentDescription = "Weather Icon", Modifier.size(128.dp)

                )
            }

            Column (verticalArrangement = Arrangement.SpaceEvenly) {
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
                        .fillMaxHeight(),
                    shape = RoundedCornerShape(
                        topStart = 32.dp,
                        topEnd = 32.dp,
                    )

                ) {

                    Row (horizontalArrangement = Arrangement.SpaceAround) {

                        Column(horizontalAlignment = Alignment.CenterHorizontally ) {
                            Image(
                                painter = painterResource(R.drawable.cloud),
                                contentDescription = "Rain Icon",
                                Modifier.size(64.dp)
                            )

                            Text(
                                text = "32ºC",
                                fontSize = 32.sp,
                                color = Color.Black,
                                fontWeight = FontWeight.Bold,
                            )
                        }
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Image(
                                painter = painterResource(R.drawable.rain),
                                contentDescription = "Rain Icon",
                                Modifier.size(64.dp)
                            )

                            Text(
                                text = "32ºC",
                                fontSize = 32.sp,
                                color = Color.Black,
                                fontWeight = FontWeight.Bold,
                            )
                        }
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Image(
                                painter = painterResource(R.drawable.rain),
                                contentDescription = "Rain Icon",
                                Modifier.size(64.dp)
                            )

                            Text(
                                text = "32ºC",
                                fontSize = 32.sp,
                                color = Color.Black,
                                fontWeight = FontWeight.Bold,
                            )
                        }

                    }
                }

            }

        }

    }

}

@Preview(showSystemUi = true)
@Composable
private fun HomeScreenPreview() {
    HomeScreen()
}