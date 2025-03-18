package br.com.fiap.telaconfiguracao

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.telaconfiguracao.ui.theme.TelaConfiguracaoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TelaConfiguracaoTheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .windowInsetsPadding(WindowInsets.systemBars)
                ) { innerPadding ->
                    ConfiguracaoScreen(modifier = Modifier.padding(innerPadding))

                }
            }
        }
    }
}

@Composable
fun ConfiguracaoScreen(modifier: Modifier = Modifier) {

    val scrollState = rememberScrollState()

    var expanderUnidade by remember { mutableStateOf(false) }

    var escolhaUnidade by remember { mutableStateOf("°C") }

    var expanderAtualizar by remember { mutableStateOf(false) }

    var escolhaIntervalo by remember { mutableStateOf("Nunca") }

    val opcoesIntervalo = listOf("Nunca", "A cada 6 horas", "A cada 12 horas", "A cada 24 horas")

    var checkedAtualizacaoAutomatica by remember { mutableStateOf(true) }

    var checkedNotificacoes by remember { mutableStateOf(true) }

    var checkedLocalizacao by remember { mutableStateOf(true) }

    var expanderLocalAtual by remember { mutableStateOf(false) }

    var expanderSobre by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            // ---- HEADER ---------
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(color = Color.Blue)
            ) {
                Text(
                    text = "Configurações de tempo",
                    fontSize = 24.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 12.dp, bottom = 24.dp)
                )
            }
            // --- ESCOLHER UNIDADE ----
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Card(
                    modifier = Modifier
                        //.height(300.dp)
                        .fillMaxWidth()
                        .height(100.dp)
                        //.size(200.dp)
                        .offset(y = (30).dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(
                            vertical = 16.dp,
                            horizontal = 32.dp
                        )
                    ) {
                        Text(
                            text = "Unidade de temperatura",
                            modifier = Modifier.padding(bottom = 8.dp),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Blue
                        )

                        Column {
                            TextButton(
                                onClick = { expanderUnidade = true },

                                ) {
                                Text("$escolhaUnidade")
                            }

                            DropdownMenu(modifier = Modifier.background(color = Color.Blue),
                                expanded = expanderUnidade,
                                onDismissRequest = { expanderUnidade = false }
                            ) {
                                listOf("°C", "°F").forEach { unit ->
                                    DropdownMenuItem(
                                        text = {
                                            Row(
                                                verticalAlignment = Alignment.CenterVertically,
                                            ) {
                                                Text(unit, color = Color.White)
                                                Spacer(modifier = Modifier.weight(1f))
                                                if (unit == escolhaUnidade) {
                                                    Icon(
                                                        imageVector = Icons.Default.Check,
                                                        contentDescription = "Selecionado",
                                                        tint = Color.White
                                                    )
                                                }
                                            }
                                        },
                                        onClick = {
                                            escolhaUnidade = unit
                                            expanderUnidade = false
                                        },
                                    )
                                }
                            }
                        }
                    }
                }

                //----------TEMPO DE ATUALIZAÇÃO-----------

                Spacer(modifier = Modifier.height(10.dp))

                Card(
                    modifier = Modifier
                        //.height(300.dp)
                        .fillMaxWidth()
                        .height(100.dp)
                        //.size(200.dp)
                        .offset(y = (30).dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(
                            vertical = 16.dp,
                            horizontal = 32.dp
                        )
                    ) {

                        Text(
                            text = "Atualização automática",
                            modifier = Modifier.padding(bottom = 8.dp),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Blue
                        )

                        TextButton(onClick = { expanderAtualizar = true }) {
                            Text("$escolhaIntervalo")
                        }

                        DropdownMenu(modifier = Modifier.background(color = Color.Blue),
                            expanded = expanderAtualizar,
                            onDismissRequest = { expanderAtualizar = false }
                        ) {
                            opcoesIntervalo.forEach { interval ->
                                DropdownMenuItem(
                                    text = { Text(interval, color = Color.White) },
                                    onClick = {
                                        escolhaIntervalo = interval
                                        expanderAtualizar = false
                                    },
                                    trailingIcon = {
                                        if (interval == escolhaIntervalo) {
                                            Icon(
                                                imageVector = Icons.Default.Check,
                                                contentDescription = "Selecionado",
                                                modifier = Modifier.size(20.dp),
                                                tint = Color.White
                                            )
                                        }
                                    }
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                //---------ATUALIZAÇÃO AUTOMÁTICA EM QUALQUER LUGAR

                Card(
                    modifier = Modifier
                        //.height(300.dp)
                        .fillMaxWidth()
                        .height(100.dp)
                        //.size(200.dp)
                        .offset(y = (30).dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                    shape = RoundedCornerShape(20.dp),
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp).padding(start = 16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Atualização automática em qualquer lugar",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Blue,
                            modifier = Modifier.weight(1f) //faz ocupar todo espaço disponível antes do switch
                        )

                        // Switch fora da Column, alinhado à direita
                        Switch(
                            checked = checkedAtualizacaoAutomatica,
                            onCheckedChange = { checkedAtualizacaoAutomatica = it }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                //------NOTIFICAÇÕES--------

                Card(
                    modifier = Modifier
                        //.height(300.dp)
                        .fillMaxWidth()
                        .height(100.dp)
                        //.size(200.dp)
                        .offset(y = (30).dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(
                            modifier = Modifier
                                //.fillMaxWidth()
                                .padding(horizontal = 16.dp)
                        ) {
                            Text(
                                text = "Notificações",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Blue
                            )
                            Text(
                                text = "Ativar",
                                fontSize = 14.sp,
                                color = Color.Gray
                            )
                        }

                        // Switch fora da Column, alinhado à direita
                        Switch(
                            checked = checkedNotificacoes,
                            onCheckedChange = { checkedNotificacoes = it },
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                //-----ACESSO A LOCALIZAÇÃO PERMISSÃO--------

                Card(
                    modifier = Modifier
                        //.height(300.dp)
                        .fillMaxWidth()
                        .height(100.dp)
                        //.size(200.dp)
                        .offset(y = (30).dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(
                            modifier = Modifier
                                //.fillMaxWidth()
                                .padding(horizontal = 16.dp)
                        ) {
                            Text(
                                text = "Acesso à localização",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Blue
                            )
                            Text(
                                text = "Permitir",
                                fontSize = 14.sp,
                                color = Color.Gray
                            )
                        }

                        // Switch fora da Column, alinhado à direita
                        Switch(
                            checked = checkedLocalizacao,
                            onCheckedChange = { checkedLocalizacao = it },
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                //-------LOCAL PADRÃO-------

                Card(
                    modifier = Modifier
                        //.height(300.dp)
                        .fillMaxWidth()
                        .height(100.dp)
                        //.size(200.dp)
                        .offset(y = (30).dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(
                            modifier = Modifier
                                //.fillMaxWidth()
                                .padding(horizontal = 16.dp)
                        ) {
                            Text(
                                text = "Local padrão",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Blue
                            )
                        }

                        Row {
                            TextButton(onClick = { expanderLocalAtual = true }) {
                                Text("Local atual")
                            }

                            DropdownMenu(modifier = Modifier.background(color = Color.Blue),
                                expanded = expanderLocalAtual,
                                onDismissRequest = { expanderLocalAtual = false }
                            ) {
                                DropdownMenuItem(
                                    text = { Text("Escolher local", color = Color.White) },
                                    onClick = {
                                        expanderLocalAtual = false
                                    }
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                //-------POLÍTICA DE PRIVACIDADE-------

                Card(
                    modifier = Modifier
                        //.height(300.dp)
                        .fillMaxWidth()
                        .height(100.dp)
                        //.size(200.dp)
                        .offset(y = (30).dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        TextButton(onClick = {}) {
                            Text(
                                text = "Política de Privacidade",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Blue,
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                //-----SOBRE------

                Card(
                    modifier = Modifier
                        //.height(300.dp)
                        .fillMaxWidth()
                        .height(100.dp)
                        //.size(200.dp)
                        .offset(y = (30).dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        TextButton(
                            onClick = { expanderSobre = true },

                            ) {

                            Text(
                                text = "Sobre o Weather Guardian",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Blue,
                                //modifier = Modifier.weight(1f)
                            )

                            DropdownMenu(modifier = Modifier.background(color = Color.Blue),
                                expanded = expanderSobre,
                                onDismissRequest = { expanderSobre = false }
                            ) {

                                DropdownMenuItem(
                                    text = {
                                        Row(
                                            verticalAlignment = Alignment.CenterVertically,
                                        ) {
                                            Text(
                                                text = "Versão 1.0.25 - Projeto FIAP ESG",
                                                color = Color.White
                                            )
                                        }
                                    },
                                    onClick = {
                                        expanderSobre = false
                                    },
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(40.dp))
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun ConfiguracaoScreenPreview() {
    ConfiguracaoScreen()
}