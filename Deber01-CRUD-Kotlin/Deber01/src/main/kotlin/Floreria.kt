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

    constructor(nombre: String, direccion: String, horario: String, telefono: String, haceEnvio:Boolean) {
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
                ", DIRECCION: " + direccion  +
                ", HORARIO: " + horario  +
                ", TELEFONO: " + telefono  +
                ", HACE ENVIO?: " + haceEnvio;
    }
    fun instanciarFloreria(): MutableList<Floreria> {
        println("se insat")

        if (archivo.exists()) {
            return mutableListOf()
        }
        return emptyList<Floreria>() as MutableList<Floreria>
    }

    fun crearFloreria(): Unit {
        println("Ingrese el nombre: " );
        var nombre: String = teclado.nextLine();
        println("Ingrese el direccion: " );
        var direccion: String = teclado.nextLine();
        println("Ingrese el horario: " );
        var horario: String = teclado.nextLine();
        println("Ingrese el telefono: " );
        var telefono: String = teclado.nextLine();
        println("Ingrese si hace envio (1: Si, 0: No)");
        var haceEnvio: Boolean = teclado.nextBoolean();
        var floreria = Floreria(nombre, direccion, horario, telefono, haceEnvio)
        println("\n****" +
                "\nNOMBRE: " + floreria.nombre +
                " \nDIRECCION: " + floreria.direccion  +
                " \nHORARIO: " + floreria.horario  +
                " \nTELEFONO: " + floreria.telefono  +
                " \nHACE ENVIO?: " + floreria.haceEnvio)
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

        if(archivo.exists()){
            println("**FLORERIAS**")
            for (floreria in florerias){
                println(floreria)
            }
        }else{
            println("No hay florerias registradas")
        }
    }

    fun eliminarFloreria( nombreFloreria: String):Unit{
        if(archivo.exists()){
            for (floreria in florerias){
                val lineas =  floreria.toString().split(",")
                nombre = lineas[0]
                direccion = lineas[1]
                horario = lineas[2]
                telefono = lineas[3]
                haceEnvio = lineas[4].toBoolean()
            }
        }

    }

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
    // }


}
