import java.io.BufferedReader
import java.io.File
import java.io.FileReader

class Floreria {

    var idFloreria: Int
    var nombre: String
    var direccion: String
    var telefono: String
    var haceEnvio: Boolean
    //var flores: MutableList<Flor> = mutableListOf()

    constructor() {

        this.idFloreria = 0
        this.nombre = ""
        this.direccion = ""
        this.telefono = ""
        this.haceEnvio = false
    }

    constructor( idFloreria: Int, nombre: String, direccion: String, telefono: String, haceEnvio: Boolean) {

        this.idFloreria = idFloreria;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.haceEnvio = haceEnvio;
    }

    var floreriasLista = mutableListOf<Floreria>()

    override fun toString(): String {
        return "" + idFloreria + "," + nombre + "," + direccion +
                "," + telefono +
                "," + haceEnvio
    }

    //Perdon Karlita xd, solo queria crear algo en android studio xd
}
