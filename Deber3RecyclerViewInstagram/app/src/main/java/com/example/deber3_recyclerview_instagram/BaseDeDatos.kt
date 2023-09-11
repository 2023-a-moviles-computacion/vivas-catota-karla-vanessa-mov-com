package com.example.deber3_recyclerview_instagram

class BaseDeDatos {
    companion object {

        val arregloFeed = arrayListOf<Feed>()
        val arregloStories = arrayListOf<Storie>()
        init {
            arregloFeed
                .add(
                    Feed( "ezra", R.drawable.ezra, "Feliz cumple amiga", "199 Me gusta", R.drawable.jenni, "Hace 10 minutos")
                )
            arregloFeed
                .add(
                    Feed("momo", R.drawable.momo,"Lindo michito",  "23 Me gusta", R.drawable.gatolimon,"Hace 52 minutos")
                )
            arregloFeed
                .add(
                    Feed("lisa", R.drawable.lisa,"Amo los gatos", "14 Me gusta", R.drawable.gatofondonaranja, "Hace 2 horas")
                )
            arregloFeed
                .add(
                    Feed("mina", R.drawable.mina,"Foto nueva",  "45 Me gusta", R.drawable.mina, "Hace 3 horas")
                )

            arregloStories
                .add(
                    Storie("Tu historia", R.drawable.usuario)
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