package com.example.examen02

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class CrearFlor : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_flor)

       // var idFloreria = intent.getStringExtra("idFloreriaSeleccionada").toString()
        var nombreFloreria = intent.getStringExtra("nombreFloreria").toString()
        val name = findViewById<TextView>(R.id.tv_floreria2)
        name.setText(nombreFloreria)

        val botonCrearFlor = findViewById<Button>(R.id.btn_guardar_flor)
        botonCrearFlor
            .setOnClickListener {
                val nombre = findViewById<EditText>(R.id.input_nombre_flor)
                val color = findViewById<EditText>(R.id.input_color_flor)
                val esNativa = findViewById<CheckBox>(R.id.cb_es_nativa)
                val fechaLlegada = findViewById<EditText>(R.id.input_fecha_llegada)
                val precio = findViewById<EditText>(R.id.input_precio)

                val nombreFlor = nombre.text.toString()
                val colorFlor = color.text.toString()
                val esNativaFlor = esNativa.isChecked
                val fechaLlegadaFlor = fechaLlegada.text.toString()
                val precioFlor = precio.text.toString().toDouble()

                if (nombreFlor.isNotEmpty() && colorFlor.isNotEmpty()
                    && fechaLlegadaFlor.isNotEmpty()

                ) {
                    val datosFlor = hashMapOf(
                        "nombreFloreria" to nombreFloreria,
                        "nombre" to nombreFlor,
                        "color" to colorFlor,
                        "esNativa" to esNativaFlor,
                        "fechaLlegada" to fechaLlegadaFlor,
                        "precio" to precioFlor,
                    )
                    val db = Firebase.firestore
                    val coleccionFlor = db.collection("flor")

                    coleccionFlor
                        .add(datosFlor)
                        .addOnSuccessListener {
                            Toast.makeText(this, "Flor creada", Toast.LENGTH_SHORT).show()
                            nombre.setText("")
                            color.setText("")
                            esNativa.isChecked=false
                            fechaLlegada.setText("")
                            precio.setText("")
                            finish()
                        }
                        .addOnFailureListener {
                            Toast.makeText(this,
                                "Error al crear la flor", Toast.LENGTH_LONG).show()
                        }
                } else {
                    Toast.makeText(this, "Debe llenar los campos!", Toast.LENGTH_LONG).show()
                }
            }
    }
}