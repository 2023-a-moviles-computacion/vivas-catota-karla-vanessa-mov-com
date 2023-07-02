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
          floreriasLista = instanciarFloreria()
    }

    override fun toString(): String {
        return nombre +
                "," + direccion +
                "," + horario +
                "," + telefono +
                "," + haceEnvio
    }
}
/*
    fun instanciarFloreria(): MutableList<Floreria> {
        var floreriasLineasLista: MutableList<String> = mutableListOf()
        var floreriasLista: MutableList<Floreria> = mutableListOf()
        if (archivoFlorerias.exists() && archivoFlorerias.length() >= 5) {
            archivoFlorerias.forEachLine { linea ->
                floreriasLineasLista.add(linea)
            }
            for (floreriaLinea in floreriasLineasLista) {
                var lineas = floreriaLinea.split(",")
                println(lineas)
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

*/

/*
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

 */
/*
    fun eliminarFloreria( nombreFloreria: String):Unit{
                //floreriaLista.removeIf{nombreL == nombreFloreria}

                //println(floreriaLista)
    }
*/

//}
// val archivoFlorerias = File("src/main/kotlin/florer.txt");
//val archivoOriginal = File("src/main/kotlin/florer.txt").readLines();
// val floreriasLista = archivoOriginal.map { line ->
//   val datos = line.split(",")
//  Floreria(datos[0], datos[1], datos[2], datos[3], datos[4].toBoolean())
///
//val archivoActualizado = floreriasLista.filter {
//    Floreria.floreriasLista.removeIf{it.nombre == nombreFloreria}
//}
// archivoFlorerias.writeText(archivoActualizado.joinToString("\n"))
//  listarFlorerias()