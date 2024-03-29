package com.example.examen01

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.widget.*

class CrearFloreria : AppCompatActivity() {

    private var onDataChangedCallback: (() -> Unit)? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_floreria)

        val botonCrearBDD = findViewById<Button>(R.id.btn_guardar_floreria)
        botonCrearBDD
            .setOnClickListener {
                val nombre = findViewById<EditText>(R.id.input_nombre_floreria)
                val ubicacion = findViewById<EditText>(R.id.input_ubicacion)
                val telefono = findViewById<EditText>(R.id.input_telefono)
                val haceEnvio = findViewById<CheckBox>(R.id.cb_hace_envio)
                if (nombre.text.toString().isNotEmpty() && ubicacion.text.toString().isNotEmpty()
                    && telefono.text.toString().isNotEmpty()){
                    BaseDeDatos.tablaFloreria!!.crearFloreria(
                        nombre.text.toString(),
                        ubicacion.text.toString(),
                        telefono.text.toString(),
                        haceEnvio.isChecked.toString()
                    )
                    nombre.setText("")
                    ubicacion.setText("")
                    telefono.setText("")
                    haceEnvio.isChecked=false

                    onDataChangedCallback?.invoke()
                    irActividad(ListaFlorerias::class.java)
                }else{
                    val aviso = Toast.makeText(this, "Debe llenar los campos!", Toast.LENGTH_LONG)
                    aviso.show()

                }
            }

    }

    fun irActividad(clase: Class<*>){
        val intent = Intent(this, clase)
        startActivity(intent)
    }

    fun setOnDataChangedCallback(callback: () -> Unit) {
        onDataChangedCallback = callback
    }

}
