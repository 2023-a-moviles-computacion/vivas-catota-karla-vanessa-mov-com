package com.example.examen01

class Floreria (
    var id: Int,
    var nombre: String?,
    var ubicacion: String?,
    var telefono: String?
){
    override fun toString(): String {
        return "${nombre}-${ubicacion}-${telefono}"
    }
}