import java.io.BufferedReader
import java.io.File
import java.io.FileReader

class Floreria {

    //var Floreria: Int,
    var nombre: String
    var direccion: String
    var horario: String
    var telefono: String
    var haceEnvio: Boolean

    constructor() {
        this.nombre = ""
        this.direccion = ""
        this.horario = ""
        this.telefono = ""
        this.haceEnvio = false
    }

    constructor(nombre: String, direccion: String, horario: String, telefono: String, haceEnvio: Boolean) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.horario = horario;
        this.telefono = telefono;
        this.haceEnvio = haceEnvio;
    }

    var floreriasLista = mutableListOf<Floreria>()

    init {
          //floreriasLista = instanciarFloreria()
    }

    override fun toString(): String {
        return nombre + "," + direccion +
                "," + horario +
                "," + telefono +
                "," + haceEnvio
    }
}
