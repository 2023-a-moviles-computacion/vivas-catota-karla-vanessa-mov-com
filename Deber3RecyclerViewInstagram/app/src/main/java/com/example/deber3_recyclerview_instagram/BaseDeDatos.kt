package com.example.deber3_recyclerview_instagram

class BaseDeDatos {
    companion object {
        val arregloFeed = arrayListOf<Feed>()
        init {
            arregloFeed
                .add(
                    Feed( "Karla", "Lindo michito",1, R.drawable.gatolimon)
                )
            arregloFeed
                .add(
                    Feed("Vanessa", "Lindo michito",2,  R.drawable.gatolimon)
                )
            arregloFeed
                .add(
                    Feed("Lucy", "Lindo michito",3,  R.drawable.gatofondonaranja)
                )
        }
    }
}