import java.io.File
import java.io.InputStream
import java.util.Scanner

class Floreria(
    //var Floreria: Int,
    var nombre: String,
    var direccion: String,
    var horario: String,
    var telefono: String,
    var haceEnvio: Boolean){

    companion object {
        public val florerias = mutableListOf<Floreria>()

    }
    override fun toString(): String {
        return "NOMBRE: " + nombre +
                " DIRECCION: " + direccion  +
                " HORARIO: " + horario  +
                " TELEFONO: " + telefono  +
                " HACE ENVIO?: " + haceEnvio;
    }


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

    Floreria.florerias.add(floreria)
    val archivo = File("src/main/kotlin/florer.txt")
    //val floreria = floreria.toString();
    archivo.appendText("$floreria\n")

    //return floreria;

}
    fun listarFlorerias(): Unit{
        //val archivo: InputStream = File("src/main/kotlin/floreria.txt").inputStream()
        //val inputString = archivo.bufferedReader().use { it.readText() }
        //println(inputString)  // muestra en consola todo el archivo
        val archivo = File("src/main/kotlin/florer.txt").readText();
        println("$archivo\n")
        if (Floreria.florerias.isEmpty()){
            println("ayuda")
        }
        for(elemento in Floreria.florerias){
            println(elemento)
        }
        //var floreria: Floreria;
        //return contenido.toString()
    }

    fun eliminarFloreria(nombreFloreria: String):Unit{
        //val archivo = File("src/main/kotlin/florer.txt");
        //val archivoOriginal = File("src/main/kotlin/florer.txt").readLines();
        //val archivoActualizado = archivoOriginal.filter { it != floreriaEliminar }
        //archivo.writeText(archivoActualizado.joinToString("\n"))
        //listarFlorerias()
        Floreria.florerias.removeIf{it.nombre == nombreFloreria}
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


