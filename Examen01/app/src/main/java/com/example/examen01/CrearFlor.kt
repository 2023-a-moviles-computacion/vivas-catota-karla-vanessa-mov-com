package com.example.examen01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast

class CrearFlor : AppCompatActivity() {

    private var onDataChangedCallback: (() -> Unit)? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_flor)

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
                    BaseDeDatos.tablaFloreria!!.crearFlor(
                        nombre.text.toString(),
                        color.text.toString(),
                        esNativa.isChecked.toString(),
                        fechaLlegada.text.toString(),
                        precio.text.toString()
                    )

                    val aviso = Toast.makeText(this, "Flor creada", Toast.LENGTH_LONG)
                    aviso.show()

                    nombre.setText("")
                    color.setText("")
                    esNativa.isChecked=false
                    fechaLlegada.setText("")
                    precio.setText("")

                    onDataChangedCallback?.invoke()
                    irActividad(ListaFlores::class.java)
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
