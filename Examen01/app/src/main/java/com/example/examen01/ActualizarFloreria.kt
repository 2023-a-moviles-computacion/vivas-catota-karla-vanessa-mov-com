package com.example.examen01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
//import com.example.examen01.ListaFlorerias.Companion.idItemSeleccionado

class ActualizarFloreria : AppCompatActivity() {

    private var onDataChangedCallback: (() -> Unit)? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actualizar_floreria)

        /*

        val intentExplicito = Intent(this, ListaFlorerias::class.java)
        intentExplicito.putExtra("nombre", nombre.toString())
        intentExplicito.putExtra("ubicacion", ubicacion.toString())
        intentExplicito.putExtra("telefono", telefono.toString())
        intentExplicito.putExtra("haceEnvio", haceEnvio.toString())
            //6.launch(intentExplicito)
*/
        val botonActualizarBDD = findViewById<Button>(R.id.btn_actualizar_floreria)
        botonActualizarBDD
            .setOnClickListener {
                val id = intent.getIntExtra("id", -1)
                //val id = findViewById<ListView>(
                val nombre = findViewById<EditText>(R.id.input_actualizar_nombre)
                val ubicacion = findViewById<EditText>(R.id.input_actualizar_ubicacion)
                val telefono = findViewById<EditText>(R.id.input_actualizar_telefono)
                val haceEnvio = findViewById<CheckBox>(R.id.cb_actualizar_hace_envio)
                if (nombre.text.toString().isNotEmpty() && ubicacion.text.toString().isNotEmpty()
                    && telefono.text.toString().isNotEmpty()){
                    BaseDeDatos.tablaFloreria!!.actualizarFloreria(
                        id,
                        nombre.text.toString(),
                        ubicacion.text.toString(),
                        telefono.text.toString(),
                        haceEnvio.isChecked.toString()
                    )

                    Toast.makeText(this, "Florería actualizada", Toast.LENGTH_SHORT).show()
                    //finish()

                    //nombre.setText("")
                    ///ubicacion.setText("")
                    //telefono.setText("")
                    //haceEnvio.isChecked=false
                    onDataChangedCallback?.invoke()
                    irActividad(ListaFlorerias::class.java)
                    //mandarDatos()
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


/*
    fun mandarDatos(){
        val intent = Intent(this,ListaFlorerias::class.java )
        intent.putExtra("nombre", nombre.toString())
        intent.putExtra("ubicacion", ubicacion.toString())
        intent.putExtra("telefono", telefono.toString())
        intent.putExtra("haceEnvio", haceEnvio.toString())

        startActivity(intent)
    }
*/






