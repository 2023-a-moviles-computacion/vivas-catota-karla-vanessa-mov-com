package com.example.examen01

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.annotation.RequiresApi

var idItemSeleccionadoFlor = 0
private lateinit var adaptador: ArrayAdapter<Flor>
class ListaFlores : AppCompatActivity() {

    //val id = intentFlor.getIntExtra("id", -1)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_flores)

        val listViewFlores = findViewById<ListView>(R.id.lv_lista_flores)
        adaptador = ArrayAdapter(
            this, // Contexto
            android.R.layout.simple_list_item_1, // como se va a ver (XML)
            obtenerFlores()
        )
        listViewFlores.adapter = adaptador
        adaptador.notifyDataSetChanged()

        val crearFlor = CrearFlor()
        crearFlor.setOnDataChangedCallback {
            adaptador.notifyDataSetChanged()
        }

        val botonCrearFlor= findViewById<Button>(R.id.btn_crear_flor)
        botonCrearFlor
            .setOnClickListener {
                irActividad(CrearFlor::class.java)
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
                //val intent = Intent(this,ActualizarFloreria::class.java )
                //intent.putExtra("id", obtenerFlores()[idItemSeleccionado].id)
                //startActivity(intent)
                adaptador.notifyDataSetChanged()
                return true
            }
            R.id.mi_eliminar ->{
                "${idItemSeleccionado}"
              //  BaseDeDatos.tablaFlor!!.eliminarFloreriaFormulario(
               //     obtenerFlores()[idItemSeleccionado].id
                //)
                adaptador.notifyDataSetChanged()
                irActividad(ListaFlorerias::class.java)
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    private fun obtenerFlores(): ArrayList<Flor> {
        return BaseDeDatos.tablaFlor!!.listaFlores(intent.getIntExtra("id", -1))
    }

    fun irActividad(clase: Class<*>){
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}