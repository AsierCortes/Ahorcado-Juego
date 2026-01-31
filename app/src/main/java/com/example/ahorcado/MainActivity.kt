package com.example.ahorcado

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ahorcado.ViewModel.ViewModelAhorcado
import com.example.ahorcado.Vistas.Inicio
import com.example.ahorcado.Vistas.Juego
import com.example.ahorcado.ui.theme.AhorcadoTheme
import kotlin.getValue

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AhorcadoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Navegador(innerPadding)
                }
            }
        }
    }
}

@Composable
fun Navegador(innerPadding : PaddingValues = PaddingValues()){
    val navController  = rememberNavController()
    val controller : ViewModelAhorcado = viewModel()
    NavHost(navController, startDestination = "inicio"){

        // VENTANA LOGIN
        composable("inicio") {
            Inicio(
                innerPadding = innerPadding,
                irAlJuego = {
                    navController.navigate("juego")
                },
                controller = controller
            )
        }

        // VENTANA JUEGO
        composable ("juego"){
            Juego(innerPadding, controller)
        }
    }

}