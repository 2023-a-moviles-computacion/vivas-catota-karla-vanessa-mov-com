package com.example.movilescomp2023a

class BBaseDatosMemoria {
    companion object {
        val arregloBEntrenador = arrayListOf<BEntrenador>()
        init {
            arregloBEntrenador
                .add(
                    BEntrenador(1, "Karla", "a@a.com")
                )
            arregloBEntrenador
                .add(
                    BEntrenador(2,"Vanessa", "b@b.com")
                )
            arregloBEntrenador
                .add(
                    BEntrenador(3, "Lucy", "c@c.com")
                )
        }
    }
}