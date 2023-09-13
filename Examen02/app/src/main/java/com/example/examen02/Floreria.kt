package com.example.examen02

class Floreria (
    var id: String,
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