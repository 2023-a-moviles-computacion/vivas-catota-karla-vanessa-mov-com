package com.example.deber2_sqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class ActualizarFlor : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actualizar_flor)

        val nombreFlor = intent.getStringExtra("nombreFlor")
        val name = findViewById<TextView>(R.id.tv_flor)
        name.setText(nombreFlor)

        val botonActualizarBDD = findViewById<Button>(R.id.btn_actualizar_flor)
        botonActualizarBDD
            .setOnClickListener {
                val idFlorSeleccionada = intent.getIntExtra("idFlorSeleccionada",-1)

                val nombre = findViewById<EditText>(R.id.input_nombre_flor_actualizar)
                val color = findViewById<EditText>(R.id.input_color_flor_actualizar)
                val esNativa = findViewById<CheckBox>(R.id.cb_es_nativa_actualizar)
                val fechaLlegada = findViewById<EditText>(R.id.input_fecha_llegada_actualizar)
                val precio = findViewById<EditText>(R.id.input_precio_actualizar)

                if (nombre.text.toString().isNotEmpty() && color.text.toString().isNotEmpty()
                    && fechaLlegada.text.toString().isNotEmpty()
                    && precio.text.toString().isNotEmpty()){
                    BaseDeDatos.dbfloreria!!.actualizarFlor(
                        idFlorSeleccionada,
                        nombre.text.toString(),
                        color.text.toString(),
                        esNativa.isChecked.toString(),
                        fechaLlegada.text.toString(),
                        precio.text.toString(),
                    )

                    Toast.makeText(this, "Flor actualizada", Toast.LENGTH_SHORT).show()
                    finish()

                }else{
                    val aviso = Toast.makeText(this, "Debe llenar los campos!", Toast.LENGTH_LONG)
                    aviso.show()

                }
            }
    }
}