package com.example.ahorcado.Vistas

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ahorcado.ViewModel.ViewModelAhorcado


@Composable
fun Inicio(innerPadding : PaddingValues = PaddingValues(), irAlJuego : () -> Unit, controller: ViewModelAhorcado =  viewModel()){
    var nombreIntroducido by remember { mutableStateOf("") }
    var nivelSeleccionado by remember { mutableStateOf("") }

    val context = LocalContext.current

    Column (
        modifier = Modifier
            .padding(innerPadding)      // Respetar margenes bateria
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ) {

        // Titulo
        Text(
            text = "Ahorcado",
            fontSize = 30.sp
        )

        Spacer(Modifier.padding(20.dp))

        Column {
            Text(
                text = "Nombre: "
            )
            TextField(
                onValueChange = {nombreIntroducido = it},
                value = nombreIntroducido
            )
        }
        Spacer(Modifier.padding(10.dp))

        Column {
            Text(
                text = "Seleccionar nivel (Facil / Dificil): "
            )
            TextField(
                onValueChange = {nivelSeleccionado = it},
                value = nivelSeleccionado
            )
        }

        Spacer(Modifier.padding(20.dp))

        Button(
            onClick = {
                if(controller.registrarse(nombreIntroducido, nivelSeleccionado, context)){
                    controller.generarPalabraRandom()
                    println("Inicio correcto")
                    irAlJuego()
                }
            }
        ) {
            Text(
                text = "Jugar"
            )
        }


    }
}


