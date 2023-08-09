package com.example.deber2_sqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class CrearFlor : AppCompatActivity() {

    private lateinit var idFloreria: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_flor)

        idFloreria = intent.getIntExtra("idFloreriaSeleccionada",0).toString()


        val name = findViewById<TextView>(R.id.tv_floreria2)
        name.setText(idFloreria)

        val botonCrearBDD = findViewById<Button>(R.id.btn_guardar_flor)
        botonCrearBDD
            .setOnClickListener {
                val nombre = findViewById<EditText>(R.id.input_nombre_flor)
                val color = findViewById<EditText>(R.id.input_color_flor)
                val esNativa = findViewById<CheckBox>(R.id.cb_es_nativa)
                val fechaLlegada = findViewById<EditText>(R.id.input_fecha_llegada)
                val precio = findViewById<EditText>(R.id.input_precio)
                if (nombre.text.toString().isNotEmpty() && color.text.toString().isNotEmpty()
                    && fechaLlegada.text.toString().isNotEmpty()
                    && precio.text.toString().isNotEmpty()){
                    BaseDeDatos.dbfloreria!!.crearFlor(
                        idFloreria.toInt(),
                        nombre.text.toString(),
                        color.text.toString(),
                        esNativa.isChecked.toString(),
                        fechaLlegada.text.toString(),
                        precio.text.toString()
                    )

                    Toast.makeText(this, "Flor creada", Toast.LENGTH_LONG).show()

                    nombre.setText("")
                    color.setText("")
                    esNativa.isChecked=false
                    fechaLlegada.setText("")
                    precio.setText("")
                    finish()

                }else{
                    Toast.makeText(this, "Debe llenar los campos!", Toast.LENGTH_LONG).show()
                }

            }
    }

}