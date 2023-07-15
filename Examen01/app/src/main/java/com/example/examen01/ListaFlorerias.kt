package com.example.examen01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView



class ListaFlorerias : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_florerias)


        val florerias = BaseDeDatos.tablaFloreria!!.listaFlorerias()

        val listViewFlorerias = findViewById<ListView>(R.id.lv_florerias)
        val adaptador = ArrayAdapter(
            this, // Contexto
            android.R.layout.simple_list_item_1, // como se va a ver (XML)
            florerias
        )
        listViewFlorerias.adapter = adaptador
        adaptador.notifyDataSetChanged()


        val botonCrearFloreria= findViewById<Button>(R.id.btn_crear_floreria)
        botonCrearFloreria.setOnClickListener {
            irActividad(CrearFloreria::class.java)
        }
    }


    fun irActividad(clase: Class<*>){
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}