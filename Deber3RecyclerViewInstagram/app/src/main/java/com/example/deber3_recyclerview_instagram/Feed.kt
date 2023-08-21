package com.example.deber3_recyclerview_instagram

class Feed(

    var user: String?,
    var imgUser: Int?,
    var descripcion: String?,
    //var numero: Int?,
    var imagen: Int?
) {
    override fun toString(): String {
        return "${user}"
    }
}