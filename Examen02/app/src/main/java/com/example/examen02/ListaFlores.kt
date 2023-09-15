package com.example.examen02

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.*
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


private var idFlorSeleccionada = 0
var arregloFlores: ArrayList<Flor> = arrayListOf()
class ListaFlores : AppCompatActivity() {

    private var nombreFloreria = ""
    private lateinit var adaptadorFlor: ArrayAdapter<Flor>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_flores)

        arregloFlores = ArrayList()
        //var idFloreria = intent.getStringExtra("idFloreriaSeleccionada").toString()
        nombreFloreria = intent.getStringExtra("nombreFloreria")?: ""
        val name = findViewById<TextView>(R.id.tv_floreria)
        name.setText(nombreFloreria)

        val listViewFlores = findViewById<ListView>(R.id.lv_lista_flores)
        adaptadorFlor = ArrayAdapter(
            this, // Contexto
            android.R.layout.simple_list_item_1, // como se va a ver (XML)
            arregloFlores
        )

        listViewFlores.adapter = adaptadorFlor
       // adaptadorFlor.notifyDataSetChanged()
       // obtenerFlores(nombreFloreria)

        // Cargar las flores
        val botonCargarFlores = findViewById<Button>(R.id.btn_cargar_flores)
        botonCargarFlores.setOnClickListener {
            if(nombreFloreria != "") {
                obtenerFlores(nombreFloreria, adaptadorFlor)
            }else{
                Toast.makeText(this, "Error al obtener el nombre de la floreria", Toast.LENGTH_SHORT).show()
            }

        }

        val botonCrearFlor= findViewById<Button>(R.id.btn_crear_flor)
        botonCrearFlor
            .setOnClickListener {
                val intent = Intent(this, CrearFlor::class.java)
                //intent.putExtra("idFloreriaSeleccionada", idFloreria)
                intent.putExtra("nombreFlor", nombreFloreria)
                startActivity(intent)
            }

        registerForContextMenu(listViewFlores)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        // Llenamos las opciones del menu
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_flor, menu)
        // Obtener el id del ArrayListSeleccionado
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        idFlorSeleccionada = id
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.mi_editar2 ->{
                val intent = Intent(this,ActualizarFloreria::class.java )
                //intent.putExtra("idFlorSeleccionada", obtenerFlores()[idFlorSeleccionada].id)
                intent.putExtra("nombreFlor", arregloFlores[idFlorSeleccionada].nombre)
                startActivity(intent)
                return true

            }
            R.id.mi_eliminar2 ->{
                val db = Firebase.firestore
                val floreriaRef = db.collection("flores")

                floreriaRef
                    .document(arregloFlores[idItemSeleccionado].id)
                    .delete() // elimina
                    .addOnCompleteListener { /* Si todo salio bien*/ }
                    .addOnFailureListener { /* Si algo salio mal*/ }

                adaptadorFlor.remove(adaptadorFlor.getItem(idItemSeleccionado))
                Toast.makeText(this, "Florería eliminada", Toast.LENGTH_SHORT).show()
                adaptadorFlor.notifyDataSetChanged()
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }


    fun obtenerFlores(nombreFloreria: String, adaptadorFlor: ArrayAdapter<Flor>
        ){
        val db = Firebase.firestore
        val collectionFlores = db.collection("flor")
        limpiarArreglo()
        collectionFlores
            .whereEqualTo("nombreFloreria", nombreFloreria)
            .get()
            .addOnSuccessListener {
                if(nombreFloreria.isNotBlank()){
                for (flor in it){
                    flor.id
                    anadirAArregloFlorerias(flor)
                }
                adaptadorFlor.notifyDataSetChanged()

                Toast.makeText(this, "Cargando flores", Toast.LENGTH_LONG).show()
                }else {
                    Toast.makeText(this, "Error al obtener el nombre de la florería", Toast.LENGTH_SHORT).show()
                }

            }
            .addOnFailureListener {
                Toast.makeText(this, "No se puede cargar", Toast.LENGTH_LONG).show()

            }
    }

    fun limpiarArreglo() {arregloFlores.clear()}

    fun anadirAArregloFlorerias(
        flor: QueryDocumentSnapshot
    ){
        val nuevaFlor = Flor(
            flor.data.get("nombreFloreria") as String,
            flor.data.get("id") as String,
            flor.data.get("nombre") as String?,
            flor.data.get("color") as String?,
            flor.data.get("esNativa") as Boolean?,
            flor.data.get("fechaLlegada") as String?,
            flor.data.get("precio") as Double?
        )

        arregloFlores.add(nuevaFlor)
    }
}