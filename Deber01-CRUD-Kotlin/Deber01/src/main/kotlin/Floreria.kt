class Floreria {

    var idFloreria: Int
    var nombre: String
    var ubicacion: String
    var telefono: String
    var haceEnvio: Boolean

    constructor() {

        this.idFloreria = 0
        this.nombre = ""
        this.ubicacion = ""
        this.telefono = ""
        this.haceEnvio = false
    }

    constructor( idFloreria: Int, nombre: String, direccion: String, telefono: String, haceEnvio: Boolean) {
        this.idFloreria = idFloreria;
        this.nombre = nombre;
        this.ubicacion = direccion;
        this.telefono = telefono;
        this.haceEnvio = haceEnvio;
    }

    override fun toString(): String {
        return "" + idFloreria + ";" + nombre + ";" + ubicacion +
                ";" + telefono +
                ";" + haceEnvio
    }
}
