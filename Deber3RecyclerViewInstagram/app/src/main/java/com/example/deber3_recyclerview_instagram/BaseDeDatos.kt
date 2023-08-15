package com.example.deber3_recyclerview_instagram

class BaseDeDatos {
    companion object {
        val arregloFeed = arrayListOf<Feed>()
        init {
            arregloFeed
                .add(
                    Feed( "Karla", 1)
                )
            arregloFeed
                .add(
                    Feed("Vanessa", 2)
                )
            arregloFeed
                .add(
                    Feed("Lucy", 3)
                )
        }
    }
}