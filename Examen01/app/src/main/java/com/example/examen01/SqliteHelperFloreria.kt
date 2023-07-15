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
               telefono VARCHAR(50),
               haceEnvio VARCHAR(50)
               ) 
            """.trimIndent()
        db?.execSQL(scriptSQLCrearTablaFloreria)
    }

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
                val haceEnvio = cursor.getString(4)
                val floreria = Floreria(id, nombre, ubicacion, telefono, haceEnvio.toBoolean())
                arreglo.add(floreria)
            }while (cursor.moveToNext())
        }
        cursor.close()
        baseDatosLectura.close()
        return arreglo
    }

    fun eliminarFloreriaFormulario(id:Int):Boolean{
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
/*
    fun consultarFloreriaPorID(id: Int): Floreria{
        val baseDatosLectura = readableDatabase
        val scriptConsultaLectura = """
            SELECT * FROM FLORERIA WHERE ID = ?
        """.trimIndent()
        val parametrosConsultaLectura = arrayOf(id.toString())
        val resultadoConsultaLectura = baseDatosLectura.rawQuery(
            scriptConsultaLectura, // Consulta
            parametrosConsultaLectura, // Parametros
        )

        // logica busqueda
        val existeUsuario = resultadoConsultaLectura.moveToFirst()
        val usuarioEncontrado = Floreria(0, "" , "", "",false)
        val arreglo = arrayListOf<Floreria>()
        if(existeUsuario){
            do{
                val id= resultadoConsultaLectura.getInt(0) // Indice 0
                val nombre = resultadoConsultaLectura.getString(1)
                val ubicacion = resultadoConsultaLectura.getString(2)
                val telefono = resultadoConsultaLectura.getString(3)
                val haceEnvio = resultadoConsultaLectura.getString(4)
                if(id != null){
                    // llenar el arreglo con un nuevo BEntrenador
                    usuarioEncontrado.id = id
                    usuarioEncontrado.nombre = nombre
                    usuarioEncontrado.ubicacion = ubicacion
                    usuarioEncontrado.telefono = telefono
                    usuarioEncontrado.haceEnvio = haceEnvio.toBoolean()
                }
            } while (resultadoConsultaLectura.moveToNext())
        }
        resultadoConsultaLectura.close()
        baseDatosLectura.close()
        return usuarioEncontrado
    }
*/

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
}