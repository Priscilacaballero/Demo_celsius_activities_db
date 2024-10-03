package com.example.demo_celsius_activities_db

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    // Método llamado al crear la base de datos
    override fun onCreate(db: SQLiteDatabase) {
        // SQL para crear la tabla de conversiones
        val CREATE_CONVERSION_TABLE_2 = ("CREATE TABLE $TABLE_NAME (" +
                "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COLUMN_CONV TEXT, " +  // Tipo de conversión (STRING cambiado a TEXT)
                "$COLUMN_RESULT REAL)" )
        db.execSQL(CREATE_CONVERSION_TABLE_2) // Ejecutar la creación de la tabla
    }

    // Método llamado al actualizar la base de datos
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Eliminar la tabla existente si existe
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db) // Volver a crear la tabla
    }

    // Insertar datos en la base de datos
    fun addConversionResult(conv: String, result: Double) {
        val db = this.writableDatabase // Obtener la base de datos en modo escritura
        val values = ContentValues() // Crear ContentValues para insertar datos
        values.put(COLUMN_CONV, conv) // Agregar el tipo de conversión
        values.put(COLUMN_RESULT, result) // Agregar el resultado
        db.insert(TABLE_NAME, null, values) // Insertar los valores en la tabla
        db.close() // Cerrar la base de datos
    }

    // Select para ver todos los datos en la base de datos, solo las columnas deseadas
    @SuppressLint("Range")
    fun getAllResults(): List<Pair<String, Double>> {  // Usar Pair para los resultados
        val resultList = mutableListOf<Pair<String, Double>>()  // Lista mutable de pares
        val db = this.readableDatabase // Obtener la base de datos en modo lectura
        // Consultar todos los registros de la tabla, solo las columnas deseadas
        val cursor = db.rawQuery("SELECT $COLUMN_CONV, $COLUMN_RESULT FROM $TABLE_NAME", null)
        cursor.use {
            while (cursor.moveToNext()) { // Iterar sobre el cursor
                // Obtener los valores de cada columna
                val conv = cursor.getString(cursor.getColumnIndex(COLUMN_CONV))
                val result = cursor.getDouble(cursor.getColumnIndex(COLUMN_RESULT))
                resultList.add(Pair(conv, result))  // Agregar los valores en un Pair
            }
        }
        cursor.close() // Cerrar el cursor
        return resultList // Retornar la lista de resultados
    }

    companion object {
        private const val DATABASE_VERSION = 1 // Versión de la base de datos
        private const val DATABASE_NAME = "conversiones.db" // Nombre de la base de datos
        private const val TABLE_NAME = "conversion_temperatura" // Nombre de la tabla
        private const val COLUMN_ID = "id" // Nombre de la columna ID
        private const val COLUMN_RESULT = "resultado" // Nombre de la columna resultado
        private const val COLUMN_CONV = "conversion" // Nombre de la columna tipo de conversión
    }
}
