package com.example.deber3_recyclerview_instagram

import android.media.Image

class Feed(
    //var image: Image,
    var nombre: String?,
    var numero: Int?
) {
    override fun toString(): String {
        return "${nombre}-${numero}"
    }
}