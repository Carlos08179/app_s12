📱 LoginApp – Validación en tiempo real con ViewModel y LiveData
LoginApp es una aplicación Android desarrollada en Kotlin que implementa un formulario de inicio de sesión con validación de datos en tiempo real, utilizando los componentes ViewModel y LiveData de la arquitectura MVVM recomendada por Google.

🧠 Características principales
Validación del correo electrónico con formato estándar.

Validación dinámica de la contraseña mientras el usuario escribe.

La contraseña debe cumplir los siguientes requisitos:

✅ Mínimo 8 caracteres.

✅ Al menos una letra mayúscula.

✅ Al menos un número.

✅ Al menos un carácter especial.

Visualización en tiempo real del cumplimiento de cada requisito:

Requisitos cumplidos se muestran en verde.

Requisitos pendientes se muestran en rojo.

El botón de login solo se habilita cuando todos los campos son válidos.

Interfaz limpia y reactiva, separando la lógica de UI y datos.

🛠️ Tecnologías utilizadas
Kotlin

Android Studio

ViewModel (androidx.lifecycle)

LiveData

View Binding

Material Design

🗂️ Estructura
MainActivity.kt: interfaz de usuario que observa los datos y muestra el formulario.

LoginViewModel.kt: contiene la lógica de validación y gestiona el estado del formulario.

activity_main.xml: diseño de la interfaz con campos de entrada y mensajes dinámicos.
