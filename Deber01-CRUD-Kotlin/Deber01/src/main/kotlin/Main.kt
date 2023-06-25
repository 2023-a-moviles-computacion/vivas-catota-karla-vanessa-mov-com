import java.io.File
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
    when(literal.nextLine()){
        "1" ->  ingresarDatosFloreria();
        "2" -> listarFlorerias()
        else -> println("nose")
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
            ingresarDatosFlor();

        else -> println("chao")
    }
}

fun ingresarDatosFloreria(): Floreria{
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
    println(floreria);

    val archivo = File("src/main/kotlin/floreria.txt")
    write(archivo.toPath(), floreria.toString().toByteArray(), StandardOpenOption.APPEND)
    return floreria;
}

fun ingresarDatosFlor(): Unit{
    println("Ingrese el nombre: " );
    var nombre: String = teclado.nextLine();
    println("Ingrese el color: " );
    var color: String = teclado.nextLine();
    println("Ingrese si es nativa: " );
    var esNativa: Boolean = teclado.nextBoolean();
    println("Ingrese la fecha de llegada: " );
    var fechaLlegada: Date = SimpleDateFormat().parse(teclado.nextLine());

    println("Ingrese el precio: ");
    var precio: Double = teclado.nextDouble();
    var flor = Flor(nombre, color, esNativa, fechaLlegada, precio)
    println(flor);

    val archivo = File("src/main/kotlin/flor.txt")
    write(archivo.toPath(), flor.toString().toByteArray(), StandardOpenOption.APPEND)

}