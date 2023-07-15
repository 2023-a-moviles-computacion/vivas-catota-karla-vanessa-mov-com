package com.example.examen01

class Floreria (
    var id: Int,
    var nombre: String?,
    var ubicacion: String?,
    var telefono: String?,
    var haceEnvio: Boolean?
){
    override fun toString(): String {
        return  "ID: ${id}\nNombre: ${nombre}\nUbicacion: ${ubicacion}\nTelefono: ${telefono}" +
                "\nHace envio?: ${haceEnvio}"
    }
}