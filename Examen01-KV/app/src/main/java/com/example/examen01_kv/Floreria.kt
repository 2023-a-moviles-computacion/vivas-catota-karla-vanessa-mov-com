package com.example.examen01_kv

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Floreria : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_floreria)

        val botonFlorerias = findViewById<Button>(R.id.btn_crear_floreria)
        botonFlorerias.setOnClickListener {
            irActividad(CrearFloreria::class.java)
        }

    }
    fun irActividad(clase: Class<*>){
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}