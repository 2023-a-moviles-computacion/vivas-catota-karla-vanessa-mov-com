import java.io.File
import java.io.InputStream
import java.util.Scanner

val archivo = File("src/main/kotlin/florer.txt")
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

    var florerias = mutableListOf<Floreria>()

    init {
        florerias = instanciarFloreria()
    }

    override fun toString(): String {
        return "NOMBRE: " + nombre +
                ", DIRECCION: " + direccion +
                ", HORARIO: " + horario +
                ", TELEFONO: " + telefono +
                ", HACE ENVIO?: " + haceEnvio;
    }

    fun instanciarFloreria(): MutableList<Floreria> {
        var floreriaLista: MutableList<Floreria> = mutableListOf()
        if (archivo.exists()) {
            for (floreria in florerias) {
                var lineas = floreria.toString().split(",")
                var nombreL = lineas[0]
                var direccionL = lineas[1]
                var horarioL = lineas[2]
                var telefonoL = lineas[3]
                var haceEnvioL = lineas[4].toBoolean()
                var floreriaO = Floreria(nombreL, direccionL, horarioL, telefonoL, haceEnvioL)
                floreriaLista.add(floreriaO)
                //println(floreriaLista)
            }
        }
        return floreriaLista;
    }

    fun crearFloreria(): Unit {
        println("Ingrese el nombre: ");
        var nombre: String = teclado.nextLine();
        println("Ingrese el direccion: ");
        var direccion: String = teclado.nextLine();
        println("Ingrese el horario: ");
        var horario: String = teclado.nextLine();
        println("Ingrese el telefono: ");
        var telefono: String = teclado.nextLine();
        println("Ingrese si hace envio (1: Si, 0: No)");
        var haceEnvio: Boolean = teclado.nextBoolean();
        var floreria = Floreria(nombre, direccion, horario, telefono, haceEnvio)
        println(
            "\n****" +
                    "\nNOMBRE: " + floreria.nombre +
                    " \nDIRECCION: " + floreria.direccion +
                    " \nHORARIO: " + floreria.horario +
                    " \nTELEFONO: " + floreria.telefono +
                    " \nHACE ENVIO?: " + floreria.haceEnvio
        )
        // floreria.nombre);

        //val archivo = File("src/main/kotlin/floreria.txt")
        //write(archivo.toPath(), floreria.toString().toByteArray(), StandardOpenOption.APPEND)
        //return floreria;

        //instanciarFloreria().add(floreria.toString())

        //val floreria = floreria.toString();
        archivo.appendText("$floreria\n")

        //return floreria;
    }

    fun listarFlorerias(): Unit {

        val florerias = archivo.readLines()

        if (archivo.exists()) {
            println("**FLORERIAS**")
            for (floreria in florerias) {
                println(floreria)
            }
        } else {
            println("No hay florerias registradas")
        }
    }

}
/*
    fun eliminarFloreria( nombreFloreria: String):Unit{
                //floreriaLista.removeIf{nombreL == nombreFloreria}

                //println(floreriaLista)
    }
*/



//}
    // val archivo = File("src/main/kotlin/florer.txt");
    //val archivoOriginal = File("src/main/kotlin/florer.txt").readLines();
    // val florerias = archivoOriginal.map { line ->
    //   val datos = line.split(",")
    //  Floreria(datos[0], datos[1], datos[2], datos[3], datos[4].toBoolean())
    ///
    //val archivoActualizado = florerias.filter {
    //    Floreria.florerias.removeIf{it.nombre == nombreFloreria}
//}
    // archivo.writeText(archivoActualizado.joinToString("\n"))
    //  listarFlorerias()




