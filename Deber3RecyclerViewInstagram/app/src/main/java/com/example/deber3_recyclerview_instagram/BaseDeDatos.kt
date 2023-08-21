package com.example.deber3_recyclerview_instagram

class BaseDeDatos {
    companion object {
        val arregloFeed = arrayListOf<Feed>()
        val arregloStories = arrayListOf<Storie>()
        init {
            arregloFeed
                .add(
                    Feed( "ezra", R.drawable.ezra, "Lindo michito", R.drawable.gatolimon)
                )
            arregloFeed
                .add(
                    Feed("momo", R.drawable.momo,"Lindo michito",  R.drawable.gatolimon)
                )
            arregloFeed
                .add(
                    Feed("lisa", R.drawable.lisa,"Lindo michito",  R.drawable.gatofondonaranja)
                )

            arregloStories
                .add(
                    Storie("ezra", R.drawable.ezra)
                )
            arregloStories
                .add(
                    Storie("gigi", R.drawable.gigi)
                )
            arregloStories
                .add(
                    Storie("lisa", R.drawable.lisa)
                )
            arregloStories
                .add(
                    Storie("mina", R.drawable.mina)
                )
            arregloStories
                .add(
                    Storie("momo", R.drawable.momo)
                )
        }
    }
}