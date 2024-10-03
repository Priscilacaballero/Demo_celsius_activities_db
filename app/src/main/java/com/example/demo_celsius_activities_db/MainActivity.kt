package com.example.demo_celsius_activities_db

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup

class MainActivity : AppCompatActivity(), View.OnClickListener {
    // Declaración de variables para los componentes de la interfaz
    lateinit var boton1: Button
    lateinit var radg: RadioGroup
    lateinit var radb1: RadioButton
    lateinit var radb2: RadioButton
    lateinit var radb3: RadioButton

    // Método llamado al crear la actividad
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) // Establecer el layout
        boton1 = findViewById(R.id.button1) // Inicializar el botón
        radg = findViewById(R.id.radioGroup) // Inicializar el RadioGroup
        radb1 = findViewById(R.id.radioButton) // Inicializar el primer RadioButton
        radb2 = findViewById(R.id.radioButton2) // Inicializar el segundo RadioButton
        radb3 = findViewById(R.id.radioButton3) // Inicializar el tercer RadioButton
        boton1.setOnClickListener(this) // Configurar el listener para el botón
    }

    // Método llamado al hacer clic en el botón
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.button1 -> {
                val celsius = findViewById<EditText>(R.id.editText1) // Obtener el valor ingresado
                val celsiusValue = celsius.text.toString().toDouble() // Convertir a Double

                var resultado = 0.0  // Inicializar resultado
                var tipoConversion = ""  // Inicializar tipo de conversión

                // Verificar qué opción fue seleccionada
                when (radg.checkedRadioButtonId) {
                    R.id.radioButton -> {
                        resultado = celsius_fahrenheit(celsiusValue) // Conversión a Fahrenheit
                        tipoConversion = "Fahrenheit"
                    }
                    R.id.radioButton2 -> {
                        resultado = celsius_kelvin(celsiusValue) // Conversión a Kelvin
                        tipoConversion = "Kelvin"
                    }
                    R.id.radioButton3 -> {
                        resultado = celsius_rankine(celsiusValue) // Conversión
                        resultado = celsius_rankine(celsiusValue) // Conversión a Rankine
                        tipoConversion = "Rankine"
                    }
                }

                // Enviar datos entre actividades
                val intent = Intent(this, SecondActivity::class.java)
                intent.putExtra("TIPO_CONVERSION", tipoConversion) // Pasar el tipo de conversión
                intent.putExtra("RESULTADO", resultado) // Pasar el resultado de la conversión
                startActivity(intent) // Iniciar SecondActivity
            }
        }
    }

    // Método para convertir Celsius a Fahrenheit
    private fun celsius_fahrenheit(celsius: Double): Double {
        return (celsius * 9 / 5) + 32 // Fórmula de conversión
    }

    // Método para convertir Celsius a Kelvin
    private fun celsius_kelvin(celsius: Double): Double {
        return celsius + 273.15 // Fórmula de conversión
    }

    // Método para convertir Celsius a Rankine
    private fun celsius_rankine(celsius: Double): Double {
        return (celsius + 273.15) * 9 / 5 // Fórmula de conversión
    }
}
