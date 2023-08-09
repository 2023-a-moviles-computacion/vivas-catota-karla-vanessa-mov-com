package com.example.deber2_sqlite

class Flor (
    var idFloreria: Int,
    var id: Int,
    var nombre: String?,
    var color: String?,
    var esNativa: Boolean?,
    var fechaLlegada: String?,
    var precio: Double?,
){
    override fun toString(): String {
        return  "ID Floreria: ${idFloreria}" +
                "\nID: ${id}" +
                "\nNombre: ${nombre}\nColor: ${color}\nEs nativa?: ${esNativa}" +
                "\nFecha llegada: ${fechaLlegada}\n" +
                "Precio: ${precio}"
    }
}