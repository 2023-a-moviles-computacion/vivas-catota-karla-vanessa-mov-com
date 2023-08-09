package com.example.deber2_sqlite

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
    //metodos base
    override fun onCreate(db: SQLiteDatabase?) {
        val scriptSQLCrearTablaFloreria =
            """
               CREATE TABLE FLORERIA(
               id INTEGER PRIMARY KEY AUTOINCREMENT,
               nombre VARCHAR(50),
               ubicacion VARCHAR(50),
               telefono VARCHAR(50),
               haceEnvio VARCHAR(50)
               ) 
            """.trimIndent()
        db?.execSQL(scriptSQLCrearTablaFloreria)

        val scriptSQLCrearTablaFlor =
            """
               CREATE TABLE FLOR(
               idFloreria INTEGER,
               id INTEGER PRIMARY KEY AUTOINCREMENT,
               nombre VARCHAR(50),
               color VARCHAR(50),
               esNativa VARCHAR(50),
               fechaLlegada VARCHAR(50),
               precio REAL
               ) 
            """.trimIndent()
        db?.execSQL(scriptSQLCrearTablaFlor)
    }

    //***********CRUD*************************

    fun crearFloreria(
        nombre: String,
        ubicacion: String,
        telefono: String,
        haceEnvio: String
    ): Boolean {
        val basedatosEscritura = writableDatabase
        val valoresAGuardar = ContentValues()
        valoresAGuardar.put("nombre", nombre)
        valoresAGuardar.put("ubicacion", ubicacion)
        valoresAGuardar.put("telefono", telefono)
        valoresAGuardar.put("haceEnvio", haceEnvio)
        val resultadoGuardar = basedatosEscritura
            .insert(
                "FLORERIA", // Nombre tabla
                null,
                valoresAGuardar // valores
            )
        basedatosEscritura.close()
        return if (resultadoGuardar.toInt() == -1) false else true
    }


    fun listarFlorerias(): ArrayList<Floreria> {
        val baseDatosLectura = readableDatabase
        val arreglo = arrayListOf<Floreria>()
        val scriptConsultaLectura =
            """
                SELECT * FROM FLORERIA 
            """.trimIndent()
        val cursor = baseDatosLectura.rawQuery(scriptConsultaLectura, null)
        if(cursor.moveToFirst()){
            do{
                val id = cursor.getInt(0)
                val nombre = cursor.getString(1)
                val ubicacion = cursor.getString(2)
                val telefono = cursor.getString(3)
                val haceEnvio = cursor.getString(4)
                val floreria = Floreria(id, nombre, ubicacion, telefono, haceEnvio.toBoolean())
                arreglo.add(floreria)
            }while (cursor.moveToNext())
        }
        cursor.close()
        baseDatosLectura.close()
        return arreglo
    }

    fun actualizarFloreria(
        id:Int,
        nombre: String,
        ubicacion: String,
        telefono: String,
        haceEnvio: String
    ):Boolean{
        val conexionEscritura = writableDatabase
        val valoresAActualizar = ContentValues()
        valoresAActualizar.put("nombre", nombre)
        valoresAActualizar.put("ubicacion", ubicacion)
        valoresAActualizar.put("telefono", telefono)
        valoresAActualizar.put("haceEnvio", haceEnvio)
        // where ID = ?
        val parametrosConsultaActualizar = arrayOf( id.toString() )
        val resultadoActualizacion = conexionEscritura
            .update(
                "FLORERIA", // Nombre tabla
                valoresAActualizar, // Valores
                "id=?", // Consulta Where
                parametrosConsultaActualizar
            )
        conexionEscritura.close()
        return if(resultadoActualizacion.toInt() == -1) false else true
    }

    fun eliminarFloreria(id:Int):Boolean{
        val conexionEscritura = writableDatabase
        // where ID = ?
        val parametrosConsultaDelete = arrayOf( id.toString() )
        val resultadoEliminacion = conexionEscritura
            .delete(
                "FLORERIA", // Nombre tabla
                "id=?", // Consulta Where
                parametrosConsultaDelete
            )
        conexionEscritura.close()
        return if(resultadoEliminacion.toInt() == -1) false else true
    }

    //****flores
    fun crearFlor(
        idFloreria: Int,
        nombre: String,
        color: String,
        esNativa: String,
        fechaLlegada: String,
        precio: String
    ): Boolean {
        val basedatosEscritura = writableDatabase
        val valoresAGuardar = ContentValues()
        valoresAGuardar.put("idFloreria", idFloreria)
        valoresAGuardar.put("nombre", nombre)
        valoresAGuardar.put("color", color)
        valoresAGuardar.put("esNativa", esNativa)
        valoresAGuardar.put("fechaLlegada", fechaLlegada)
        valoresAGuardar.put("precio", precio)
        val resultadoGuardar = basedatosEscritura
            .insert(
                "FLOR", // Nombre tabla
                null,
                valoresAGuardar // valores
            )
        basedatosEscritura.close()
        return if (resultadoGuardar.toInt() == -1) false else true
    }

    fun listarFlores(idFloreria: Int): ArrayList<Flor> {
        val baseDatosLectura = readableDatabase
        val arreglo = arrayListOf<Flor>()
        val scriptConsultaLectura =
            """
                SELECT * FROM FLOR WHERE idFloreria = ?
            """.trimIndent()
        val parametrosConsultaListaFlores = arrayOf( idFloreria.toString() )
        val cursor = baseDatosLectura.rawQuery(scriptConsultaLectura, parametrosConsultaListaFlores)
        if(cursor.moveToFirst()){
            do{
                val id = cursor.getInt(1)
                val nombre = cursor.getString(2)
                val color = cursor.getString(3)
                val esNativa = cursor.getString(4)
                val fechaLlegada = cursor.getString(5)
                val precio = cursor.getDouble(6)
                val flor = Flor(idFloreria, id, nombre, color, esNativa.toBoolean(),
                    fechaLlegada, precio)
                arreglo.add(flor)
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