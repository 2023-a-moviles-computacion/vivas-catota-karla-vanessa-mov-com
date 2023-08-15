package com.example.deber3_recyclerview_instagram

class Feed(

    var nombre: String?,
    var descripcion: String?,
    var numero: Int?,
    var imagen: Int?
) {
    override fun toString(): String {
        return "${nombre}-${numero}"
    }
}