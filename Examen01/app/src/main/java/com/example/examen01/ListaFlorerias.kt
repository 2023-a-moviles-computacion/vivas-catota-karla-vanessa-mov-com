package com.example.examen01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.*


var idItemSeleccionado = 0
private lateinit var adaptador: ArrayAdapter<Floreria>
val listaFlorerias = BaseDeDatos.tablaFloreria!!.listaFlorerias()

class ListaFlorerias : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_florerias)



        val intent = Intent(this, ListaFlores::class.java)
        intent.putExtra("id", idItemSeleccionado)

        val listViewFlorerias = findViewById<ListView>(R.id.lv_florerias)
        adaptador = ArrayAdapter(
            this, // Contexto
            android.R.layout.simple_list_item_1, // como se va a ver (XML)
            obtenerFlorerias()
        )
        listViewFlorerias.adapter = adaptador
        adaptador.notifyDataSetChanged()

        val crearFloreria = CrearFloreria()
        crearFloreria.setOnDataChangedCallback {
            adaptador.notifyDataSetChanged()
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
                "${idItemSeleccionado}"
                val intent = Intent(this,ActualizarFloreria::class.java )
                intent.putExtra("id", obtenerFlorerias()[idItemSeleccionado].id)
                startActivity(intent)
                adaptador.notifyDataSetChanged()
                return true
            }
            R.id.mi_eliminar ->{
                "${idItemSeleccionado}"
                    BaseDeDatos.tablaFloreria!!.eliminarFloreriaFormulario(
                            obtenerFlorerias()[idItemSeleccionado].id
                    )
                adaptador.notifyDataSetChanged()
                irActividad(ListaFlorerias::class.java)
                return true
            }R.id.mi_flores ->{
                "${idItemSeleccionado}"
                irActividad(ListaFlores::class.java, listaFlorerias[idItemSeleccionado].id)
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    fun obtenerFlorerias(): ArrayList<Floreria> {
        return BaseDeDatos.tablaFloreria!!.listaFlorerias()
    }


    fun irActividad(clase: Class<*>){
        val intent = Intent(this, clase)
        startActivity(intent)
    }

    fun irActividad(clase: Class<*>, id: Int){
        val intent = Intent(this, clase)
        intent.putExtra("id", id)
        startActivity(intent)
    }


}