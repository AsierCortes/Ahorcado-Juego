package com.example.ahorcado.Vistas

import android.app.Activity
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ahorcado.ViewModel.ViewModelAhorcado

@Composable
fun Juego(
    innerPadding: PaddingValues = PaddingValues(),
    controller: ViewModelAhorcado = viewModel(),
) {
    val getDatos by controller.publicModelo.collectAsState()
    var letra by remember { mutableStateOf("") }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()
            .padding(start = 5.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "Nombre: ${getDatos.nombreUsuario}", modifier = Modifier.padding(5.dp)
            )

            Text(
                text = "Nivel Seleccionado: ${getDatos.nivelSeleccionado}",
                modifier = Modifier.padding(5.dp)
            )
        }

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(
                text = getDatos.palabraGuionesInterfaz, fontSize = 35.sp
            )
            Text(
                text = "Intentos: ${getDatos.intentosRestantes}"
            )
        }

        Text(
            text = "Palabras utilizadas: ${getDatos.letrasUtilizadas}", fontSize = 20.sp
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 25.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(25.dp)
        ) {
            TextField(
                value = letra,
                onValueChange = {
                    // Permitimos unicamente una letra
                    if(it.length <= 1){
                        letra = it
                    }
                })

            Button(
                onClick = {
                    controller.comprobarPalabraIntroducida(letra, context)
                    letra = ""
                }) {
                Text(
                    text = "Enviar"
                )
            }


            if (getDatos.palabraAdivinada) {
                DialogVictoria(getDatos.palabraCorrecta, getDatos.intentosRestantes, context, controller)
            }

            if (getDatos.intentosRestantes <= 0 && !getDatos.palabraAdivinada) {
                DialogPerder(getDatos.palabraCorrecta, context, controller)
            }
        }


    }
}


@Composable
fun DialogPerder(palabraAdivinar: String, context: Context, controller: ViewModelAhorcado) {
    Dialog(
        onDismissRequest = {Toast.makeText(context, "Pulsa uno de los dos opciones", Toast.LENGTH_SHORT).show() }
    ) {
        Card(
            shape = RoundedCornerShape(16.dp), modifier = Modifier.fillMaxWidth(),

            elevation = CardDefaults.cardElevation(8.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "HAS PERDIDO ....", textAlign = TextAlign.Center
                )

                Text(
                    text = "Palabra a adivinar -> $palabraAdivinar", textAlign = TextAlign.Center
                )

                BotonesOpciones(controller, context)

            }
        }

    }
}


@Composable
fun DialogVictoria(
    palabraAdivinar: String,
    intentos: Int,
    context: Context,
    controller: ViewModelAhorcado
) {
    Dialog(
        onDismissRequest = {Toast.makeText(context, "Pulsa uno de los dos opciones", Toast.LENGTH_SHORT).show() }
    ) {

        Card(
            shape = RoundedCornerShape(16.dp), modifier = Modifier.fillMaxWidth(),

            elevation = CardDefaults.cardElevation(8.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "HAS GANADO!!! Has adivinado la palabra", textAlign = TextAlign.Center
                )
                Text(
                    text = "Cantidad de intentos restantes: ${intentos.toString()}",
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "Palabra: $palabraAdivinar",
                    textAlign = TextAlign.Center,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )

                BotonesOpciones(controller, context)
            }
        }

    }
}
@Composable
fun BotonesOpciones(controller: ViewModelAhorcado, context: Context) {
    context as Activity

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ){

        // Reiniciar
        Button(
            onClick = {controller.reiniciarJuego()}
        ) {
            Text(
                text = "Reiniciar"
            )
        }

        Spacer(Modifier.padding(10.dp))
        // Salir
        Button(
            onClick = {context.finish()}
        ) {
            Text(
                text = "Salir"
            )
        }
    }
}