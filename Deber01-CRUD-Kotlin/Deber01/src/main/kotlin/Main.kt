import java.io.File
import java.io.InputStream
import java.nio.file.Files.write
import java.nio.file.StandardOpenOption
import java.text.SimpleDateFormat
import java.util.*

var teclado = Scanner(System.`in`)
var literal = Scanner(System.`in`);

fun main(args: Array<String>) {
    mostrarMenuGeneral()

   // floreria.instanciarFloreria()
}

fun mostrarMenuGeneral(){
    println("*****SISTEMA DE REGISTRO*****");
    println("1. Registro de Florerias");
    println("2. Registro de Flores");
    println("Digite el literal deseado: ");
    when(literal.nextLine()){
        "1" -> mostrarMenuFloreria();
        "2" -> mostrarMenuFlor();
        else -> println("nose")
    }
}

fun mostrarMenuFloreria(){
    var floreria = Floreria()
    var opcion = literal.nextLine()
    do {
        println("*REGISTRO DE FLORERIAS*")
        println("1. Registrar floreria");
        println("2. Listar floreriasLista");
        println("3. Eliminar floreria");
        println("4. Salir");
        println("Digite el literal deseado: ");


        when (opcion) {
            "1" -> floreria.crearFloreria()
            "2" -> floreria.listarFlorerias()
            "3" -> floreria.obtenerFloreriaNombre(teclado.nextLine())//floreria.eliminarFloreria(teclado.nextLine())
            else -> println("nose")
        }
    } while(opcion!="4")

}

fun mostrarMenuFlor(){
    println("*REGISTRO DE FLORES*");
    println("1. Registrar flor");
    println("2. Listar flores");
    println("3. Eliminar flor");
    println("4. Eliminar flor");
    println("Digite el literal deseado: ");
    when(literal.nextLine()){
        "1" ->
            crearFlor();
        else -> println("chao")
    }
}





