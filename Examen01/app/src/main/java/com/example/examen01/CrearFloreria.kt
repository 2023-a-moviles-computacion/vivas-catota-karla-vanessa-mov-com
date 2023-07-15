package com.example.examen01

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ListView

class CrearFloreria : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_floreria)

        val botonCrearBDD = findViewById<Button>(R.id.btn_guardar_floreria)
        botonCrearBDD
            .setOnClickListener {
                val nombre = findViewById<EditText>(R.id.input_nombre_floreria)
                val ubicacion = findViewById<EditText>(R.id.input_ubicacion)
                val telefono = findViewById<EditText>(R.id.input_telefono)
                val haceEnvio = findViewById<CheckBox>(R.id.cb_hace_envio)
                BaseDeDatos.tablaFloreria!!.crearFloreria(
                    nombre.text.toString(),
                    ubicacion.text.toString(),
                    telefono.text.toString(),
                    haceEnvio.isChecked.toString()
                )
                irActividad(ListaFlorerias::class.java)
            }

    }

    fun irActividad(clase: Class<*>){
        val intent = Intent(this, clase)
        startActivity(intent)
    }


}