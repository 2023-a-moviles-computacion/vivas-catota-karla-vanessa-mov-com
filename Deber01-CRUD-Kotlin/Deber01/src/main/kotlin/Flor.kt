import java.io.File
import java.nio.file.StandardOpenOption
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDate.parse
import java.util.*

class Flor{
    var idFloreria: Int
    var idFlor: Int
    var nombre: String
    var color: String
    var esNativa: Boolean
    var fechaLlegada: LocalDate
    var precio: Double

    constructor() {
        this.idFloreria = 0
        this.idFlor = 0
        this.nombre = ""
        this.color = ""
        this.esNativa = false
        this.fechaLlegada = LocalDate.of(0, 0, 0)
        this.precio = 0.00
    }

    constructor(idFloreria: Int, idFlor: Int, nombre: String, color: String, esNativa: Boolean,
                fechaLlegada: LocalDate, precio: Double) {
        this.idFloreria = idFloreria;
        this.idFlor = idFlor;
        this.nombre = nombre
        this.color = color
        this.esNativa = esNativa
        this.fechaLlegada = fechaLlegada
        this.precio = precio
    }

    override fun toString(): String {
        return "" + idFloreria + ";" + idFlor + ";" +
        nombre + ";" + color +
                ";" + esNativa +
                ";" + fechaLlegada +
                ";" + precio
    }
}

