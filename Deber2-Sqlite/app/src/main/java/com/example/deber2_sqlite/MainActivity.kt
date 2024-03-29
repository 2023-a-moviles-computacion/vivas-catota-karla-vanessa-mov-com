package com.example.deber2_sqlite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        BaseDeDatos.dbfloreria = SqliteHelperFloreria(this)

        val botonListaFlorerias = findViewById<Button>(R.id.btn_lista_florerias)
        botonListaFlorerias.setOnClickListener {
            irActividad(ListaFlorerias::class.java)
        }
    }


    fun irActividad(clase: Class<*>){
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}