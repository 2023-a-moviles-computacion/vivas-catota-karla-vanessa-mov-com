import java.io.BufferedReader
import java.io.File
import java.io.FileReader

class Floreria {
    val archivo = File("src/main/kotlin/florer.txt")
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
        floreriasLista =
            instanciarFloreria()
    }

    override fun toString(): String {
        return nombre +
                "," + direccion +
                "," + horario +
                "," + telefono +
                "," + haceEnvio;
    }
    fun instanciarFloreria(): MutableList<Floreria> {
        var floreriasLineasLista: MutableList<String> = mutableListOf()
        var floreriasLista: MutableList<Floreria> = mutableListOf()
        if (archivo.exists() && archivo.length() >= 5) {
            archivo.forEachLine { linea ->
                floreriasLineasLista.add(linea)
            }
            for (floreriaLinea in floreriasLineasLista) {
                var lineas = floreriaLinea.split(",")
                //println(lineas)
                //println(lineas[2])

                //println( lineas[0] + lineas[1] + lineas[2] + lineas[3] + lineas[4].toBoolean())
                var nombreL = lineas[0]
                var direccionL = lineas[1]
                var horarioL = lineas[2]
                var telefonoL = lineas[3]
                var haceEnvioL = lineas[4].toBoolean()
                println(floreriasLista)
                //var floreriaObjeto = Floreria(nombreL, direccionL, horarioL, telefonoL, haceEnvioL)
                var floreriaObjeto = Floreria(lineas[0], lineas[1], lineas[2], lineas[3], lineas[4].toBoolean())
                floreriasLista.add(floreriaObjeto)
            }
        }

        return  floreriasLista
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
        /*
        println(
            "\n****" +
                    "\nNOMBRE: " + floreria.nombre +
                    " \nDIRECCION: " + floreria.direccion +
                    " \nHORARIO: " + floreria.horario +
                    " \nTELEFONO: " + floreria.telefono +
                    " \nHACE ENVIO?: " + floreria.haceEnvio
        )
        */

        // floreria.nombre);

        //val archivo = File("src/main/kotlin/floreria.txt")
        //write(archivo.toPath(), floreria.toString().toByteArray(), StandardOpenOption.APPEND)
        //return floreria;

        //instanciarFloreria().add(floreria.toString())

        //val floreria = floreria.toString();
        if (nombre.isNotBlank() && direccion.isNotBlank() && horario.isNotBlank() && telefono.isNotBlank()){
            archivo.appendText("$floreria\n")
        }else{
            println("se creo la lista")
        }


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
    fun obtenerFloreriaNombre(nombreFloreria: String): Floreria?{
        println(floreriasLista)
        floreriasLista.forEach(){
            if (it.nombre == nombreFloreria){
                println(it)
                return it;
            }
        }
        return null;
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
// val floreriasLista = archivoOriginal.map { line ->
//   val datos = line.split(",")
//  Floreria(datos[0], datos[1], datos[2], datos[3], datos[4].toBoolean())
///
//val archivoActualizado = floreriasLista.filter {
//    Floreria.floreriasLista.removeIf{it.nombre == nombreFloreria}
//}
// archivo.writeText(archivoActualizado.joinToString("\n"))
//  listarFlorerias()




