package com.example.deber2_sqlite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class ActualizarFloreria : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actualizar_floreria)

        val nombreFloreria = intent.getStringExtra("nombreFloreria")
        val name = findViewById<TextView>(R.id.tv_floreria3)
        name.setText(nombreFloreria)

        val botonActualizarBDD = findViewById<Button>(R.id.btn_actualizar_floreria)
        botonActualizarBDD
            .setOnClickListener {
                val idItemSeleccionado = intent.getIntExtra("idFloreriaSeleccionada",-1)
                //val id = findViewById<ListView>(
                val nombre = findViewById<EditText>(R.id.input_actualizar_nombre)
                val ubicacion = findViewById<EditText>(R.id.input_actualizar_ubicacion)
                val telefono = findViewById<EditText>(R.id.input_actualizar_telefono)
                val haceEnvio = findViewById<CheckBox>(R.id.cb_actualizar_hace_envio)
                if (nombre.text.toString().isNotEmpty() && ubicacion.text.toString().isNotEmpty()
                    && telefono.text.toString().isNotEmpty()){
                    BaseDeDatos.dbfloreria!!.actualizarFloreria(
                        idItemSeleccionado,
                        nombre.text.toString(),
                        ubicacion.text.toString(),
                        telefono.text.toString(),
                        haceEnvio.isChecked.toString()
                    )

                    Toast.makeText(this, "Florería actualizada", Toast.LENGTH_SHORT).show()
                    finish()

                        //nombre.setText("")
                        ///ubicacion.setText("")
                        //telefono.setText("")
                        //haceEnvio.isChecked=false
                        //irActividad(ListaFlorerias::class.java)
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

}
