package com.example.deber3_recyclerview_instagram

class Feed(

    var user: String?,
    var imgUser: Int?,
    var descripcion: String?,
    var likes: String?,
    var imagen: Int?,
    var hora: String?,

) {
    override fun toString(): String {
        return "${user}"
    }
}