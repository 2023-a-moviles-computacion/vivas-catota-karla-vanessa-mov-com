package com.example.examen01_kv

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val botonFlorerias = findViewById<Button>(R.id.btn_florerias)
        botonFlorerias.setOnClickListener {
            irActividad(Floreria::class.java)
        }

        val botonFlores = findViewById<Button>(R.id.btn_flores)
        botonFlores.setOnClickListener {
            irActividad(Flor::class.java)
        }
    }

    fun irActividad(clase: Class<*>){
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}