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
  <img width="300" alt="Estructura Carpetas" src="https://github.com/user-attachments/assets/de3fbe49-4b3d-4053-95ef-9fdc91659dd0" />
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
        <li>Validación activa: El botón de jugar se deshabilita si el campo está vacío.</li>
        <li>Dificultad seleccionable (Fácil/Difícil) que afecta directamente al número de intentos y complejidad de la palabra.</li>
      </ul>
    </td>
    <td width="50%" align="center">
      <img width="250" alt="PantallaInicial" src="https://github.com/user-attachments/assets/d43067d9-4a0a-430a-9750-3c6a55d74f66" />
    </td>
  </tr>
</table>

### 2. El Bucle de Juego
Una vez iniciada la partida, el ViewModel genera la palabra y el estado inicial.

<table width="100%">
  <tr>
    <td width="33%" align="center">
      <b>Inicio</b><br><br>
      <img width="200" alt="PantallaAhorcado" src="https://github.com/user-attachments/assets/88109306-152d-4220-b030-4be93dcfd06f"/>
    </td>
    <td width="33%" align="center">
      <b>Partida en Progreso</b><br><br>
      <img width="200" alt="PantallaAhorcadoConPAlabras" src="https://github.com/user-attachments/assets/438a8f48-fb6b-4bc1-a59f-3f0b810e0fe6" />
    </td>
    <td width="33%" align="center">
      <b>Gestión de Duplicados</b><br><br>
      <img width="200" alt="palabrayautilizada" src="https://github.com/user-attachments/assets/67c24df7-e408-4ed3-9885-91aeff842732" />
    </td>
  </tr>
  <tr>
    <td colspan="3">
      <p align="center"><i>El estado se actualiza en tiempo real: se destapan letras acertadas, se listan los fallos y se dibuja el ahorcado. El sistema detecta y notifica si intentas usar una letra ya probada.</i></p>
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
      <img width="250" alt="PalabraAdivinada" src="https://github.com/user-attachments/assets/d0be9c0d-d418-4b52-8b95-e9586ed31638" />
    </td>
    <td width="50%" align="center">
      <b>Pantalla de Derrota</b><br>
      <i>(Se han agotado los intentos)</i><br><br>
      <img width="250" alt="PantallaPerdido" src="https://github.com/user-attachments/assets/ca42f2d5-ae13-4878-93ce-07622ddf438d" />
    </td>
  </tr>
</table>

Ambos diálogos muestran el resultado final, revelan la palabra correcta y ofrecen opciones claras para **reiniciar** una nueva partida (volviendo a la configuración inicial) o **salir** de la aplicación.

---
