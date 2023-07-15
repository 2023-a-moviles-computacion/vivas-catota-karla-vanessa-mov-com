package com.example.examen01

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SqliteHelperFloreria(
    contexto: Context?,): SQLiteOpenHelper(
    contexto,
    "florerias", // nombre BDD
    null,
    1
) {
    override fun onCreate(db: SQLiteDatabase?) {
        val scriptSQLCrearTablaFloreria =
            """
               CREATE TABLE FLORERIA(
               id INTEGER PRIMARY KEY AUTOINCREMENT,
               nombre VARCHAR(50),
               ubicacion VARCHAR(50),
               telefono VARCHAR(50)
               ) 
            """.trimIndent()
        db?.execSQL(scriptSQLCrearTablaFloreria)
    }

    fun crearFloreria(
        nombre: String,
        ubicacion: String,
        telefono: String,
    ): Boolean {
        val basedatosEscritura = writableDatabase
        val valoresAGuardar = ContentValues()
        valoresAGuardar.put("nombre", nombre)
        valoresAGuardar.put("ubicacion", ubicacion)
        valoresAGuardar.put("telefono", telefono)
        val resultadoGuardar = basedatosEscritura
            .insert(
                "FLORERIA", // Nombre tabla
                null,
                valoresAGuardar // valores
            )
        basedatosEscritura.close()
        return if (resultadoGuardar.toInt() == -1) false else true
    }
/*
    fun listaFlorerias(): ArrayList<Floreria> {
        val baseDatosLectura = readableDatabase
        val arreglo = arrayListOf<Floreria>()
        val scriptConsultaLectura = """
            SELECT * FROM FLORERIA 
        """.trimIndent()
        val cursor = baseDatosLectura.rawQuery(scriptConsultaLectura, null)
        while (cursor.moveToNext()) {
            val id = cursor.getInt(1)
            val nombre = cursor.getString(2)
            val ubicacion = cursor.getString(3)
            val telefono = cursor.getString(4)
            val floreria = Floreria(id, nombre, ubicacion, telefono)
            arreglo.add(floreria)
        }
        cursor.close()
        baseDatosLectura.close()
        return arreglo
    }
*/

    fun listaFlorerias(): ArrayList<Floreria> {
        val baseDatosLectura = readableDatabase
        val arreglo = arrayListOf<Floreria>()
        val scriptConsultaLectura = """
            SELECT * FROM FLORERIA 
        """.trimIndent()
        val cursor = baseDatosLectura.rawQuery(scriptConsultaLectura, null)
        if(cursor.moveToFirst()){
            do{
                val id = cursor.getInt(0)
                val nombre = cursor.getString(1)
                val ubicacion = cursor.getString(2)
                val telefono = cursor.getString(3)
                val floreria = Floreria(id, nombre, ubicacion, telefono)
                arreglo.add(floreria)
            }while (cursor.moveToNext())
        }
        cursor.close()
        baseDatosLectura.close()
        return arreglo
    }



    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
}