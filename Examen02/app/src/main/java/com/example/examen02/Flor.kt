package com.example.examen02

class Flor(
    var nombreFloreria: String,
    var id: String,
    var nombre: String?,
    var color: String?,
    var esNativa: Boolean?,
    var fechaLlegada: String?,
    var precio: Double?,
){
    override fun toString(): String {
        return  "ID Floreria: ${nombreFloreria}" +
                "\nID: ${id}" +
                "\nNombre: ${nombre}\nColor: ${color}\nEs nativa?: ${esNativa}" +
                "\nFecha llegada: ${fechaLlegada}\n" +
                "Precio: ${precio}"
    }
}