package com.example.deber2_sqlite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.*

private lateinit var adaptadorFlor: ArrayAdapter<Flor>
var idFlorSeleccionada = 0
class ListaFlores : AppCompatActivity() {

    private lateinit var idFloreria: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_flores)

        idFloreria = intent.getIntExtra("idFloreriaSeleccionada",0).toString()

        val name = findViewById<TextView>(R.id.tv_floreria)
        name.setText(idFloreria)

        val listViewFlores = findViewById<ListView>(R.id.lv_lista_flores)
        adaptadorFlor = ArrayAdapter(
            this, // Contexto
            android.R.layout.simple_list_item_1, // como se va a ver (XML)
            obtenerFlores()
        )

        listViewFlores.adapter = adaptadorFlor
        adaptadorFlor.notifyDataSetChanged()

        val botonCrearFlor= findViewById<Button>(R.id.btn_crear_flor)
        botonCrearFlor
            .setOnClickListener {
                val intent = Intent(this, CrearFlor::class.java)
                intent.putExtra("idFloreriaSeleccionada", idFloreria.toInt())
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
                /*
                val intent = Intent(this,ActualizarFlor::class.java )
                intent.putExtra("id", obtenerFlores()[idItemSeleccionado].id)
                startActivity(intent)

                 */
                return true

            }
            R.id.mi_eliminar2 ->{
                /*BaseDeDatos.dbfloreria!!.eliminarFlor(

                    obtenerFlores()[idItemSeleccionado].id
                )
                adaptador.remove(adaptador.getItem(idItemSeleccionado))
                adaptador.notifyDataSetChanged()

                 */
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    private fun obtenerFlores(): ArrayList<Flor> {
        //val idFloreria = intent.getIntExtra("idFloreriaSeleccionada",0)
        return BaseDeDatos.dbfloreria!!.listarFlores(idFloreria.toInt())
        //return BaseDeDatos.tablaFloreria!!.listaFlores(idFloreria)
    }


    override fun onResume() {
        super.onResume()
            adaptadorFlor.clear()
            adaptadorFlor.addAll(obtenerFlores())
            adaptadorFlor.notifyDataSetChanged()
    }


    fun irActividad(clase: Class<*>){
        val intent = Intent(this, clase)
        //intent.putExtra("idFloreria", idFloreria)
        startActivity(intent)
    }
}