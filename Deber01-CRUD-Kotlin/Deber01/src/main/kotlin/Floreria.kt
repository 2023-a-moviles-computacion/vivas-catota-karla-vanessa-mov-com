import java.io.File
import java.io.InputStream
import java.util.Scanner

class Floreria(
    var nombre: String,
    var direccion: String,
    var horario: String,
    var telefono: String,
    var haceEnvio: Boolean){

    override fun toString(): String {
        return "NOMBRE: " + nombre +
                " DIRECCION: " + direccion  +
                " HORARIO: " + horario  +
                " TELEFONO: " + telefono  +
                " HACE ENVIO?: " + haceEnvio;
    }
}

    fun listarFlorerias(): Unit{
        val archivo: InputStream = File("src/main/kotlin/floreria.txt").inputStream()
        val inputString = archivo.bufferedReader().use { it.readText() }
        println(inputString)  // muestra en consola todo el archivo
    }

fun registrarFloreria(): Unit {
    var teclado = Scanner(System.`in`)
    println("Ingrese el nombre: " );
     var nombre: String = teclado.nextLine();
    //var floreria = Floreria(teclado, teclado, teclado);
}


