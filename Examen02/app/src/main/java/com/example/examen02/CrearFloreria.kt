package com.example.examen02

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class CrearFloreria : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_floreria)

        val botonCrearFloreria = findViewById<Button>(R.id.btn_guardar_floreria)
        botonCrearFloreria
            .setOnClickListener {
                val nombre = findViewById<EditText>(R.id.input_nombre_floreria)
                val ubicacion = findViewById<EditText>(R.id.input_ubicacion)
                val telefono = findViewById<EditText>(R.id.input_telefono)
                val haceEnvio = findViewById<CheckBox>(R.id.cb_hace_envio)

                val nombreFloreria = nombre.text.toString()
                val ubicacionFloreria = ubicacion.text.toString()
                val telefonoFloreria = telefono.text.toString()
                val haceEnvioFloreria = haceEnvio.isChecked

                if (nombreFloreria.toString().isNotEmpty() && ubicacionFloreria.toString()
                        .isNotEmpty()
                    && telefonoFloreria.toString().isNotEmpty()
                ) {
                    val datosFloreria = hashMapOf(
                        "nombre" to nombreFloreria,
                        "ubicacion" to ubicacionFloreria,
                        "telefono" to telefonoFloreria,
                        "haceEnvio" to haceEnvioFloreria
                    )
                    val db = Firebase.firestore
                    val coleccionFlorerias = db.collection("florerias")

                    coleccionFlorerias
                        .add(datosFloreria)
                        .addOnSuccessListener {
                            Toast.makeText(this, "Florería creada", Toast.LENGTH_SHORT).show()
                            nombre.setText("")
                            ubicacion.setText("")
                            telefono.setText("")
                            haceEnvio.isChecked = false
                            finish() // Cierra la actividad actual si es necesario
                        }
                        .addOnFailureListener {
                            Toast.makeText(this,
                                "Error al crear la florería", Toast.LENGTH_LONG).show()
                        }
                } else {
                    Toast.makeText(this, "Debe llenar los campos!", Toast.LENGTH_LONG).show()
                }
            }
    }


}