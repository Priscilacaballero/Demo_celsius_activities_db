package com.example.demo_celsius_activities_db

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {

    // Método llamado al crear la actividad
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second) // Establecer el layout

        // Reemplazar el contenedor de fragmentos con Fragment_Resultados
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, Fragment_Resultados())
            .commit()

        // Ejecutar la respuesta del MainActivity cuando se crea
        val resultado = intent.getDoubleExtra("RESULTADO", 0.0) // Obtener el resultado de la conversión
        val tipoConversion = intent.getStringExtra("TIPO_CONVERSION") // Obtener el tipo de conversión

        // Guardar el resultado en la base de datos
        val dbHelper = DatabaseHelper(this) // Instancia de DatabaseHelper
        dbHelper.addConversionResult(tipoConversion ?: "", resultado) // Añadir resultado a la base de datos
    }
}
