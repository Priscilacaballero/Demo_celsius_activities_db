/**
* Priscila Caballero, 8-1000-2151
* José Pérez, 8-993-595
* Sergio Rojas, 8-993-906
 */

package com.example.demo_celsius_activities_db

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity(), View.OnClickListener  {
    lateinit var boton1 : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        boton1 = findViewById(R.id.button1)
        boton1.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.button1 -> {
                val celsius = findViewById<EditText>(R.id.editText1)
                val resultado = celsius_fahrenheit(celsius.text.toString().toDouble())

                //Enviar datos entre actividades
                val intent = Intent(this, SecondActivity::class.java)
                intent.putExtra("RESULTADO", resultado)
                startActivity(intent)
            }
        }
    }

    private fun celsius_fahrenheit(celsius:Double): Double {
        val fahrenheit = (celsius * 9/5)+32
        return fahrenheit
    }

}
