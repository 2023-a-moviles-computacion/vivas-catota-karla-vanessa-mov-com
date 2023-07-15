package com.example.examen01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.*


var idItemSeleccionado = 0
//val florerias = BaseDeDatos.tablaFloreria!!.listaFlorerias()
private lateinit var adaptador: ArrayAdapter<Floreria>

class ListaFlorerias : AppCompatActivity() {
/*
    companion object{
        var idItemSeleccionado = 0
    }
*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_florerias)

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
                val nombre = intent.getStringExtra("nombre")
                val ubicacion = intent.getStringExtra("ubicacion")
                val telefono = intent.getStringExtra("telefono")
                val haceEnvio = intent.getStringExtra("haceEnvio")
                BaseDeDatos.tablaFloreria!!.actualizarFloreria(
                    idItemSeleccionado, nombre.toString(),
                ubicacion.toString(), telefono.toString(), haceEnvio.toString())
                obtenerFlorerias()
                adaptador.notifyDataSetChanged()
                irActividad(ListaFlorerias::class.java)
                return true
            }
            R.id.mi_eliminar ->{
                "${idItemSeleccionado}"
                    BaseDeDatos.tablaFloreria!!.eliminarFloreriaFormulario(
                            obtenerFlorerias()[idItemSeleccionado].id
                    )
                obtenerFlorerias()
                adaptador.notifyDataSetChanged()
                irActividad(ListaFlorerias::class.java)
                // abrirDialogo()
                return true
            }R.id.mi_flores ->{
                "${idItemSeleccionado}"

                //obtenerFlorerias()
                //adaptador.notifyDataSetChanged()
                //finish()
                // abrirDialogo()
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    private fun obtenerFlorerias(): ArrayList<Floreria> {
        return BaseDeDatos.tablaFloreria!!.listaFlorerias()
    }



    fun irActividad(clase: Class<*>){
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}