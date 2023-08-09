package com.example.deber2_sqlite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast

class CrearFloreria : AppCompatActivity() {


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
                    BaseDeDatos.dbfloreria!!.crearFloreria(
                        nombre.text.toString(),
                        ubicacion.text.toString(),
                        telefono.text.toString(),
                        haceEnvio.isChecked.toString()
                    )
                    Toast.makeText(this, "Florería creada", Toast.LENGTH_SHORT).show()

                    nombre.setText("")
                    ubicacion.setText("")
                    telefono.setText("")
                    haceEnvio.isChecked=false
                    finish()
                }else{
                    Toast.makeText(this, "Debe llenar los campos!", Toast.LENGTH_LONG).show()
                }
            }
    }


    fun irActividad(clase: Class<*>){
        val intent = Intent(this, clase)
        startActivity(intent)
    }


}



