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
    println("*REGISTRO DE FLORERIAS*")
    println("1. Registrar floreria");
    println("2. Listar florerias");
    println("3. Eliminar floreria");
    println("Digite el literal deseado: ");
    var opcion = literal.nextLine()
    while(opcion!="4") {
        when (opcion) {
            "1" -> crearFloreria()
            "2" -> listarFlorerias()
            "3" -> eliminarFloreria(teclado.nextLine())
            else -> println("nose")
        }
        mostrarMenuFloreria()
    }
}

fun mostrarMenuFlor(){
    println("*REGISTRO DE FLORES*");
    println("1. Registrar flor");
    println("2. Listar flores");
    println("3. Eliminar flor");
    println("Digite el literal deseado: ");
    when(literal.nextLine()){
        "1" ->
            crearFlor();
        else -> println("chao")
    }
}



