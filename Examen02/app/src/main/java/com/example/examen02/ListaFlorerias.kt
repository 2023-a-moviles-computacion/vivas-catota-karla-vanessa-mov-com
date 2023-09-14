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
        val listViewFlorerias = findViewById<ListView>(R.id.lv_florerias)
        adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            arregloFlorerias
        )
        listViewFlorerias.adapter = adaptador
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

        registerForContextMenu(listViewFlorerias)

    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        // Llenamos las opciones del menu
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_floreria, menu)
        // Obtener el id del ArrayListSeleccionado
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        idItemSeleccionado = id
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.mi_editar ->{
                val intent = Intent(this, ActualizarFloreria::class.java )
                intent.putExtra("idFloreriaSeleccionada", arregloFlorerias[idItemSeleccionado].id)
                intent.putExtra("nombreFloreria", arregloFlorerias[idItemSeleccionado].nombre)
                startActivity(intent)
                return true
            }
            R.id.mi_eliminar ->{
                    val floreriaRef = db.collection("florerias")

                floreriaRef
                        .document(arregloFlorerias[idItemSeleccionado].id)
                        .delete() // elimina
                        .addOnCompleteListener { /* Si todo salio bien*/ }
                        .addOnFailureListener { /* Si algo salio mal*/ }

                adaptador.remove(adaptador.getItem(idItemSeleccionado))
                Toast.makeText(this, "Florería eliminada", Toast.LENGTH_SHORT).show()
                adaptador.notifyDataSetChanged()

                return true
            }R.id.mi_flores ->{
                //val intent = Intent(this, ListaFlores::class.java)
               // intent.putExtra("idFloreriaSeleccionada", obtenerFlorerias()[idItemSeleccionado].id)
                startActivity(intent)
                return true
            }
            else -> super.onContextItemSelected(item)
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