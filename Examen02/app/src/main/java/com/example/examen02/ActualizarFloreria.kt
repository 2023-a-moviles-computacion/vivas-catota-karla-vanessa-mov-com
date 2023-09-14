package com.example.examen02

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ActualizarFloreria : AppCompatActivity() {

    private val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actualizar_floreria)

        val nombreFloreria = intent.getStringExtra("nombreFloreria")
        val name = findViewById<TextView>(R.id.tv_floreria3)
        name.setText(nombreFloreria)

        val botonActualizarBDD = findViewById<Button>(R.id.btn_actualizar_floreria)
        botonActualizarBDD
            .setOnClickListener {
                val idItemSeleccionado = intent.getStringExtra("idFloreriaSeleccionada")
                val nombre = findViewById<EditText>(R.id.input_actualizar_nombre)
                val ubicacion = findViewById<EditText>(R.id.input_actualizar_ubicacion)
                val telefono = findViewById<EditText>(R.id.input_actualizar_telefono)
                val haceEnvio = findViewById<CheckBox>(R.id.cb_actualizar_hace_envio)

                val nombreFloreriaActualizado = nombre.text.toString()
                val ubicacionFloreriaActualizado = ubicacion.text.toString()
                val telefonoFloreriaActualizado = telefono.text.toString()
                val haceEnvioFloreriaActualizado = haceEnvio.isChecked

                if (nombre.text.toString().isNotEmpty() && ubicacion.text.toString().isNotEmpty()
                    && telefono.text.toString().isNotEmpty()
                ) {

                    val floreriaAtualizada = hashMapOf(
                        "nombre" to nombreFloreriaActualizado,
                        "ubicacion" to ubicacionFloreriaActualizado,
                        "telefono" to telefonoFloreriaActualizado,
                        "haceEnvio" to haceEnvioFloreriaActualizado
                    )
                    val coleccionFlorerias = db.collection("florerias")

                    coleccionFlorerias.document(idItemSeleccionado.toString()).set(floreriaAtualizada)
                        .addOnSuccessListener {
                            Toast.makeText(this, "Florería actualizada", Toast.LENGTH_SHORT).show()
                            finish()
                        }
                        .addOnFailureListener {
                            Toast.makeText(this, "Error al actualizar la florería", Toast.LENGTH_LONG).show()
                        }
                } else {
                   Toast.makeText(this, "Debe llenar los campos!", Toast.LENGTH_LONG).show()
                }
            }
    }

}