package com.example.examen02

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ListaFlorerias : AppCompatActivity() {

    val arregloFlorerias: ArrayList<Floreria> = arrayListOf()
    val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_florerias)

        // Configurando el list view
        val listView = findViewById<ListView>(R.id.lv_florerias)
        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            arregloFlorerias
        )
        listView.adapter = adaptador
        adaptador.notifyDataSetChanged()

        // Cargar las florerias
        val botonCargarFlorerias = findViewById<Button>(R.id.btn_cargar_florerias)
        botonCargarFlorerias.setOnClickListener {
            obtenerFlorerias(adaptador);
            Toast.makeText(this, "Cargando florerias", Toast.LENGTH_LONG).show()
        }
    }

    fun obtenerFlorerias(
        adaptador: ArrayAdapter<Floreria>){

        val collectionFlorerias = db.collection("florerias")
        limpiarArreglo()
        collectionFlorerias
            .get()
            .addOnSuccessListener {
                for (floreria in it){
                    //val id = document.getString("nombre")
                    anadirAArregloFlorerias(floreria)
                }
                adaptador.notifyDataSetChanged()
            }
            .addOnFailureListener {
                Toast.makeText(this, "No se puede cargar", Toast.LENGTH_LONG).show()
            }
    }

    fun limpiarArreglo() {arregloFlorerias.clear()}

    fun anadirAArregloFlorerias(
        floreria: QueryDocumentSnapshot
    ){
        // ciudad.id
        val nuevaCiudad = Floreria(
            floreria.id,
            floreria.data.get("nombre") as String?,
            floreria.data.get("ubicacion") as String?,
            floreria.data.get("telefono") as String?,
            floreria.data.get("haceEnvio") as Boolean?
        )
        arregloFlorerias.add(nuevaCiudad)
    }
}