package com.example.deber2_sqlite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.*

private lateinit var adaptador: ArrayAdapter<Floreria>
var idItemSeleccionado = 0

class ListaFlorerias : AppCompatActivity() {
    //val listaFlorerias = obtenerFlorerias()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_florerias)


        val listViewFlorerias = findViewById<ListView>(R.id.lv_florerias)
        //le damos al adaptador contexto y la lista de florerias
        adaptador = ArrayAdapter(
            this, // Contexto
            android.R.layout.simple_list_item_1, // como se va a ver (XML)
            //listaFlorerias
            obtenerFlorerias()
        )

        listViewFlorerias.adapter = adaptador
        adaptador.notifyDataSetChanged()


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
                val intent = Intent(this,ActualizarFloreria::class.java )
                intent.putExtra("idFloreriaSeleccionada", obtenerFlorerias()[idItemSeleccionado].id)
                intent.putExtra("nombreFloreria", obtenerFlorerias()[idItemSeleccionado].nombre)
                startActivity(intent)
                return true
            }
            R.id.mi_eliminar ->{
                BaseDeDatos.dbfloreria!!.eliminarFloreria(
                    //listaFlorerias[idItemSeleccionado].id
                    obtenerFlorerias()[idItemSeleccionado].id
                )
                adaptador.remove(adaptador.getItem(idItemSeleccionado))
                Toast.makeText(this, "Florería eliminada", Toast.LENGTH_SHORT).show()
                adaptador.notifyDataSetChanged()

                //irActividad(ListaFlorerias::class.java)
                return true
            }R.id.mi_flores ->{
                val intent = Intent(this, ListaFlores::class.java)
                intent.putExtra("idFloreriaSeleccionada", obtenerFlorerias()[idItemSeleccionado].id)
                startActivity(intent)
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    fun obtenerFlorerias(): ArrayList<Floreria> {
        return BaseDeDatos.dbfloreria!!.listarFlorerias()
    }

    override fun onResume() {
        super.onResume()
        adaptador.clear()
        adaptador.addAll(obtenerFlorerias())
        adaptador.notifyDataSetChanged()
    }


    fun irActividad(clase: Class<*>){
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}
