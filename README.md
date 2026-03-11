# 🕹️ Ahorcado Android Compose

<p align="center">
  <b>Una implementación moderna y reactiva del clásico juego del Ahorcado para Android.</b>
  <br>
  Desarrollado con <b>Kotlin</b> y <b>Jetpack Compose</b> bajo la arquitectura <b>MVVM</b>.
</p>

---

## 📖 Resumen del Proyecto

<p align="justify">
Este proyecto es una aplicación móvil nativa que digitaliza la experiencia tradicional del juego del Ahorcado. El objetivo es simple pero desafiante: el usuario debe adivinar la palabra oculta antes de que el dibujo del ahorcado se complete. La aplicación gestiona dinámicamente el estado de la partida, valida las entradas del usuario en tiempo real y ofrece una interfaz gráfica moderna que reacciona a cada intento, ya sea un acierto o un error.
</p>

<p align="center">
  <img width="300" alt="Estructura Carpetas" src="https://github.com/user-attachments/assets/eda4e3f4-ee3b-4c31-88c5-39b2394af845" />
</p>

---

## 🏗️ Arquitectura y Componentes Clave

La aplicación sigue estrictamente el patrón **MVVM (Model-View-ViewModel)**, garantizando una separación clara de responsabilidades y un código mantenible.

<details>
<summary><b>📂 Detalles de la Estructura</b></summary>
<br>

| Componente | Clases Relacionadas | Responsabilidad |
| :--- | :--- | :--- |
| **Model** | `ModeloAhorcado.kt` | Es una `data class` que representa el estado inmutable del juego: palabra actual, intentos restantes, letras usadas, nombre del jugador y dificultad. |
| **View** | `Inicio.kt`, `Juego.kt`, `MainActivity.kt` | Construidas con Jetpack Compose. Dibujan la UI de forma declarativa. `MainActivity` gestiona la navegación entre pantallas (`NavHost`). |
| **ViewModel** | `ViewModelAhorcado.kt` | El núcleo lógico. Maneja el estado mediante `MutableStateFlow`, procesa los intentos del usuario, genera palabras aleatorias y actualiza la UI de forma segura. |

</details>

---

## ✨ Funcionalidades Ilustradas

A continuación, se detalla el flujo de la aplicación mostrando cómo la interfaz reacciona a la lógica del juego.

### 1. Configuración de Partida
Antes de empezar, el usuario debe identificarse y seleccionar un nivel de desafío.

<table width="100%">
  <tr>
    <td width="50%">
      <b>Descripción:</b>
      <ul>
        <li>La pantalla inicial (`Inicio.kt`) solicita el nombre del jugador.</li>
        <li>Validación activa: El botón de jugar alerta si el campo está vacío.</li>
        <li>Dificultad seleccionable (Fácil/Difícil) que afecta directamente al número de intentos y complejidad de la palabra.</li>
      </ul>
    </td>
    <td width="50%" align="center">
      <img width="250" alt="PantallaInicial" src="https://github.com/user-attachments/assets/f49ced0c-09ec-465b-a62f-39b2a99d58b9" />
    </td>
  </tr>
</table>

### 2. El Bucle de Juego
Una vez iniciada la partida, el ViewModel genera la palabra y el estado inicial.

<table width="100%">
  <tr>
    <td width="50%" align="center">
      <b>Inicio</b><br><br>
      <img width="200" alt="PantallaAhorcado" src="https://github.com/user-attachments/assets/f49ced0c-09ec-465b-a62f-39b2a99d58b9" />
    </td>
    <td width="50%" align="center">
      <b>Partida en Progreso</b><br><br>
      <img width="200" alt="PantallaAhorcadoConPAlabras" src="https://github.com/user-attachments/assets/d3454c1f-df3d-4c18-86c6-67eeb07af84b" />
    </td>
  </tr>
  <tr>
    <td width="50%" align="center">
      <b>Validación de Entrada</b><br><br>
      <img width="200" alt="ValidacionEntrada" src="https://github.com/user-attachments/assets/e6385584-c296-4605-b0d8-ee2c5daa12f5" />
    </td>
    <td width="50%" align="center">
      <b>Gestión de Duplicados</b><br><br>
      <img width="200" alt="palabrayautilizada" src="https://github.com/user-attachments/assets/1aa474e4-2a3a-4718-b790-17b145f2d13b" />
    </td>
  </tr>
  <tr>
    <td colspan="2">
      <p align="center"><i>El estado se actualiza en tiempo real: se destapan letras acertadas y se listan los fallos. El sistema notifica mediante alertas visuales (Toasts) si la entrada está en blanco, es un número o si se intenta usar una letra repetida.</i></p>
    </td>
  </tr>
</table>

### 3. Resolución de la Partida
El ViewModel detecta automáticamente el final de la partida y muestra un diálogo emergente con el resultado.

<table width="100%">
  <tr>
    <td width="50%" align="center">
      <b>Pantalla de Victoria</b><br>
      <i>(La palabra ha sido completada)</i><br><br>
      <img width="250" alt="PalabraAdivinada" src="https://github.com/user-attachments/assets/f0c979b3-3774-4ab1-a3ce-9d4740c9be14" />
    </td>
    <td width="50%" align="center">
      <b>Pantalla de Derrota</b><br>
      <i>(Se han agotado los intentos)</i><br><br>
      <img width="250" alt="PantallaPerdido" src="https://github.com/user-attachments/assets/79483d94-59e0-4c9d-8e06-f236e56837cc" />
    </td>
  </tr>
</table>

Ambos diálogos muestran el resultado final, revelan la palabra correcta y ofrecen opciones claras para **reiniciar** una nueva partida (volviendo a la configuración inicial) o **salir** de la aplicación.

---
