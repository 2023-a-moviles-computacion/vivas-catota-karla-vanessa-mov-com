package com.example.examen01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class ListaFlorerias : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_florerias)

        val botonListView = findViewById<Button>(R.id.btn_crear_floreria)
        botonListView.setOnClickListener {
            irActividad(CrearFloreria::class.java)
        }
    }

    fun irActividad(clase: Class<*>){
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}