package com.example.examen02

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.*
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

var idItemSeleccionado = 0
private lateinit var adaptador: ArrayAdapter<Floreria>
class ListaFlorerias : AppCompatActivity() {

    val arregloFlorerias: ArrayList<Floreria> = arrayListOf()
    val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_florerias)

        // Configurando el list view
        val listView = findViewById<ListView>(R.id.lv_florerias)
        adaptador = ArrayAdapter(
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

        val botonCrearFloreria= findViewById<Button>(R.id.btn_crear_floreria)
        botonCrearFloreria
            .setOnClickListener {
                irActividad(CrearFloreria::class.java)
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
        val nuevaFloreria = Floreria(
            floreria.id,
            floreria.data.get("nombre") as String?,
            floreria.data.get("ubicacion") as String?,
            floreria.data.get("telefono") as String?,
            floreria.data.get("haceEnvio") as Boolean?
        )
        arregloFlorerias.add(nuevaFloreria)
    }


    fun irActividad(clase: Class<*>){
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}