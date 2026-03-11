package com.example.ahorcado.ViewModel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.ahorcado.Modelo.ModeloAhorcado
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ViewModelAhorcado : ViewModel() {
    private val privateModelo = MutableStateFlow(ModeloAhorcado())
    val publicModelo = privateModelo.asStateFlow()
    var mapLetraYPos: MutableMap<Char, List<Int>> = mutableMapOf()
    // Palabra usuario es para comprobar que lleva el usuario
    var palabraUsuario: MutableList<Char> = mutableListOf()
    var letrasUtilizadas: MutableList<Char> = mutableListOf()

    /*
        Plateamiento:
        1. Comprobamos que el nombre no este vacio. De lo contrario lanzamos un push
        2. Comprobamos que el nivel este bien introducido. De lo contrario lanzamos un push
        3. Si estos dos son correctos actualizamos el model
        4. Cambiamos de pantalla
     */
    fun registrarse(nombre: String, nivel: String, context: Context): Boolean {
        var nombreCorrecto = false
        var nivelCorrecto = false
        var nivelNumero = -1
        // 1.
        if (!nombre.isBlank()) {                // isBlank comprueba "" (sin espacios), " " (con un espacio), "  " (con muchos espacios)
            nombreCorrecto = true
        } else {
            Toast.makeText(context, "Nombre Inválido", Toast.LENGTH_SHORT).show()
        }
        // 2.
        if (nivel.equals("Facil", true)) {
            nivelNumero = 0       // Fácil
            nivelCorrecto = true
        } else if (nivel.equals("Dificil", true)) {
            nivelNumero = 1       // Dificil
            nivelCorrecto = true
        } else {
            Toast.makeText(context, "Nivel Seleccionado Incorrecto", Toast.LENGTH_SHORT).show()
        }
        // 3.
        if (nombreCorrecto && nivelCorrecto) {
            privateModelo.update {
                it.copy(
                    nombreUsuario = nombre,
                    nivelSeleccionado = nivelNumero
                )
            }
            return true
        } else {
            return false
        }
    }


    fun generarPalabraRandom() {
        val getDificultad = publicModelo.value.nivelSeleccionado
        var palabraRandom: List<Char> = emptyList()
        val palabraGuionesInterfaz: StringBuilder = StringBuilder()
        var intentosRestantes = 0

        // Sacamos la palabra random de la lista
        if (getDificultad == 0) {
            palabraRandom = publicModelo.value.listaPalabrasFaciles.random().toCharArray().toList()
            intentosRestantes = 15
            // DIFICIL
        } else {
            palabraRandom =
                publicModelo.value.listaPalabrasDificiles.random().toCharArray().toList()
            intentosRestantes = 10
        }

        var contador = 0

        // Recorremos la palabra random (List<Char>)
        for (letra in palabraRandom) {
            // Para que la List <Char> palabra usuario empiece como lo ve el usuario
            palabraUsuario.add('_')

            // En esta lista vamos a guardar las pos de las letras
            var posLetra: MutableList<Int> = mutableListOf()

            // comprueba que no se haya contado esa letra ya en el map (a, a)
            if (!mapLetraYPos.containsKey(letra)) {
                val letraComprobar = letra

                // Contamos cuantas veces aparece esa letra a -> arar
                for (i in 0 until palabraRandom.size) {
                    if (palabraRandom[i] == letraComprobar) {
                        posLetra.add(i)     // Guardamos la pos
                    }
                }
                // Guardamos en el map
                mapLetraYPos.put(letraComprobar, posLetra)
            }
            // Rellenamos el strinbuilder _ _ _ _ _ _ _
            if (contador == palabraRandom.size - 1) {
                palabraGuionesInterfaz.append('_')      // Si es la última no se le añade el espacio
            } else {
                palabraGuionesInterfaz.append('_')
                palabraGuionesInterfaz.append(' ')
            }
            contador++;     // Par comprobar cuando es la ultima vuelta
        }
        // Actualizamos el modelo
        privateModelo.update {
            it.copy(
                palabraCorrecta = palabraRandom.joinToString(""),   // Juntamos con el join
                palabraGuionesInterfaz = palabraGuionesInterfaz.toString(),
                intentosRestantes = intentosRestantes
            )
        }
        println("")
        println("Dificultad seleccionada: " + getDificultad)
        println("Palabra: " + palabraRandom)
        println("Map: " + mapLetraYPos)
        println("Palabra interfaz: " + palabraGuionesInterfaz.toString())
        println("")
    }

    /*
        Planteamiento
        1. Recorremos la palabra correcta
        2. Comprobamos si contiene la letra en el map
        3. Si es así:

     */
    fun comprobarPalabraIntroducida(letraStr: String, context: Context) {
        if (letraStr.isBlank() || letraStr.toLongOrNull() != null) {
            Toast.makeText(context, "Introduce una letra (a-z)", Toast.LENGTH_SHORT).show()
        }
        else if(letrasUtilizadas.contains(letraStr.lowercase().first())){
            Toast.makeText(context, "La ${letraStr.lowercase().first()} ya ha sido utilizada", Toast.LENGTH_SHORT).show()
        }
        else {
            // letraAComprobar
            val letra = letraStr.lowercase().first()
            // Nº intentos
            var intentosRestantes = privateModelo.value.intentosRestantes
            var adivinoPalabra = false
            // Letras utilizadas la añadimos
            letrasUtilizadas.add(letra)
            println("Letra a comprobar: $letra -> ${privateModelo.value.palabraCorrecta}")
            println("Map -> ${mapLetraYPos} ")
            // SI ha acertaod la letra, si la letra se encuentra dentro del map
            if (mapLetraYPos.containsKey(letra)) {
                // Sacamos las posiciones donde se encuentra la letra
                val posLetra = mapLetraYPos[letra]
                println("La letra ${letra} se encuentra en los siguientes indices: ${mapLetraYPos}")
                // Stringbuilder (_ _ _ _ _ _ _) el inicial
                var palabraInterfaz = StringBuilder()
                // Palabra usuario para ver que letras lleva adivinadas
                for (posLetraCorrecta in posLetra!!) {
                    palabraUsuario[posLetraCorrecta] = letra
                    println(palabraUsuario)
                }
                // Modificamos el strinbuilder
                for (i in 0 until palabraUsuario.size) {
                    // Si es la ultima no hace falta poner espacio de más
                    if (i == palabraUsuario.size - 1) {
                        palabraInterfaz.append(palabraUsuario[i])
                    } else {
                        palabraInterfaz.append(palabraUsuario[i])
                        palabraInterfaz.append(" ")
                    }
                }
                // Si la palabra es exactamente igual que la palabra correcta, signfica que ya la ha adivinado
                if (palabraUsuario.joinToString("")
                        .equals(privateModelo.value.palabraCorrecta, true)
                ) {
                    println("Has adivinado la palabra")
                    adivinoPalabra = true
                }
                // Ambos tienen que ser lo mismo
                println("Palabra usuario -> $palabraUsuario")
                println("Palabra interfaz -> $palabraInterfaz")
                privateModelo.update {
                    it.copy(
                        palabraGuionesInterfaz = palabraInterfaz.toString(),
                        letrasUtilizadas = letrasUtilizadas,
                        palabraAdivinada = adivinoPalabra
                    )
                }
            // Si no adivina la letra intentos --
            } else {
                intentosRestantes--
                privateModelo.update {
                    it.copy(
                        intentosRestantes = intentosRestantes,
                        letrasUtilizadas = letrasUtilizadas
                    )
                }
            }
        }
    }

    // Reiniciar juego con el mismo nivel
    fun reiniciarJuego() {
        palabraUsuario.clear()
        mapLetraYPos.clear()
        letrasUtilizadas.clear()
        // Reiniciamos todoos los valores, con tan solo generarPalabra() ya se resetea
        // tanto palabraCorrecta, como palabraInterfazGuiones e intentos
        privateModelo.update {
            it.copy(
                palabraAdivinada = false,
                letrasUtilizadas = emptyList<Char>()
            )
        }
        generarPalabraRandom()
    }
}

