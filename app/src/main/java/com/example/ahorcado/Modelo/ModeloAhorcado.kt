package com.example.ahorcado.Modelo

data class ModeloAhorcado(
    val nombreUsuario : String = "",
    val nivelSeleccionado : Int = -1,
    val listaPalabrasFaciles: List<String> = listOf(
        "arbol", "perro", "libro", "playa", "campo",
        "fuego", "nieve", "coche", "radio", "silla",
        "leche", "arroz", "papel", "reloj", "lapiz",
        "verde", "feliz", "comer", "volar", "mundo"
    ),
    val listaPalabrasDificiles: List<String> = listOf(
        "elefante", "mariposa", "cangrejo", "leopardo", "mosquito",
        "guitarra", "edificio", "cuaderno", "pantalla", "castillo",
        "ensalada", "almuerzo", "bizcocho", "aguacate", "bocadillo",
        "estudiar", "escribir", "trabajar", "escuchar", "entender",
        "historia", "proyecto", "aventura", "perfecto", "estrella"
    ),
    val intentosRestantes : Int = 0,
    val palabraCorrecta : String = "",
    val palabraGuionesInterfaz : String = "",

    val letrasUtilizadas : List <Char> = emptyList<Char>(),
    val palabraAdivinada : Boolean = false

)
