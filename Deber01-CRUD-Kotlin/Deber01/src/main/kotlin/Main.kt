import java.io.File
import java.io.IOException
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.util.*

val archivoFlorerias = File("src/main/kotlin/florerias.txt")
val archivoFlores = File("src/main/kotlin/flores.txt")
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
    var opcion = literal.nextLine()
    when(opcion){
        "1" -> mostrarMenuFloreria();
        "2" -> mostrarMenuFlor();
        else -> println("nose")
    }
}
fun mostrarMenuFloreria(){
    do {
        println("*REGISTRO DE FLORERIAS*")
        println("1. Registrar floreria");
        println("2. Listar florerias");
        println("3. Eliminar floreria");
        println("4. Actualizar floreria");
        println("5. Salir");
        println("Digite el literal deseado: ");
        var opcion = literal.nextLine()
        when (opcion) {
            "1" -> registrarFloreria()
            "2" -> listarFlorerias()
            "3" -> eliminarFloreria()
            "4" -> actualizarFloreria()
            //"3" -> obtenerFloreriaNombre(teclado.nextLine())//floreria.eliminarFloreria(teclado.nextLine())
            else -> println("nose")
        }
    } while(opcion!="5")
}

fun mostrarMenuFlor(){
    do {
        println("*REGISTRO DE FLORES*");
        println("1. Registrar flor");
        println("2. Listar flores");
        println("3. Eliminar flor");
        println("4. Eliminar flor");
        println("5. Salir");
        println("Digite el literal deseado: ");
        var opcion = literal.nextLine()
        when (opcion) {
            "1" -> registrarFlor();
            "2" -> listarFlores();
            else -> println("chao")
        }
    }while (opcion!="5")
}

//CRUD FLORERIA
fun registrarFloreria(): Unit {
    var floreria = Floreria()
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
     floreria = Floreria(nombre, direccion, horario, telefono, haceEnvio)
    if (nombre.isNotBlank() && direccion.isNotBlank() && horario.isNotBlank() && telefono.isNotBlank()){
        archivoFlorerias.appendText( "\n$floreria")
        //archivoFlorerias.appendText("\n"+floreria.toString())
    }else{
        println("Datos invalidos!")
    }
}

fun listarFlorerias(): Unit {
    val florerias = archivoFlorerias.readLines()
    if (archivoFlorerias.exists()) {
        println("**FLORERIAS**")
        for (floreria in florerias) {
            println(floreria)
        }
    } else {
        println("No hay florerias registradas")
    }
}

fun eliminarFloreria(): Unit{
    println("Ingrese el nombre de la floreria a borrar")
    val floreriaABorrar = readLine()
    try {
        val lineas = archivoFlorerias.readLines()
        for (linea in lineas){
            var info = linea.split(",")
            var nombre = info[0]
            if(floreriaABorrar == nombre){
                    //val lineasActualizadas = lineas.filter { it != textoABorrar }
                    val lineasActualizadas = lineas.filter { !it.contains(nombre) }
                    archivoFlorerias.writeText(lineasActualizadas.joinToString("\n"))
                    println("Objeto borrado del archivo correctamente.")
                break
            }
        }
    } catch (ex: IOException) {
        println("Error al borrar el objeto del archivo: ${ex.message}")
    }
}

fun actualizarFloreria(): Unit{
    println("Ingrese el nombre de la floreria a actualizar")
    val floreriaAActualizar = readLine()
    try {
        val lineas = archivoFlorerias.readLines().toMutableList()
        for (linea in lineas){
            var info = linea.split(",")
            var nombre = info[0]
            if(floreriaAActualizar == nombre){
                //val lineasActualizadas = lineas.filter { it != textoABorrar }
                val lineaPorActualizar = lineas.filter { it.contains(nombre) }
                var numeroLinea = lineas.indexOf(linea)
                println("DATOS:");
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

                var infoNueva = Floreria(nombre, direccion, horario, telefono, haceEnvio)
                lineas[numeroLinea]= infoNueva.toString()
                archivoFlorerias.writeText(lineas.joinToString("\n"))
                println("Objeto actualizado del archivo correctamente.")
                break
            }else{
                println("Floreria no encontrada")
            }
        }
    } catch (ex: IOException) {
        println("Error al actualizar el objeto del archivo: ${ex.message}")
    }
}

//CRUD FLOR
fun registrarFlor(): Unit {
    var flor = Flor()
    println("Ingrese el nombre: " );
    var nombre: String = teclado.nextLine();
    println("Ingrese el color: " );
    var color: String = teclado.nextLine();
    println("Ingrese si es nativa: " );
    var esNativa: Boolean = teclado.nextBoolean();
    teclado.nextLine()
    //----fecha
    println("Ingrese la fecha de llegada: " );
    var fechaString = teclado.nextLine();
    var formato = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    var fechaLlegada = LocalDate.parse(fechaString, formato)
    //----
    println("Ingrese el precio: " );
    var precio: Double = teclado.nextDouble();
    flor = Flor(nombre, color, esNativa, fechaLlegada, precio)
    if (nombre.isNotBlank() && color.isNotBlank()){
        archivoFlores.appendText( "\n$flor")
        //archivoFlorerias.appendText("\n"+floreria.toString())
    }else{
        println("Datos invalidos!")
    }
}

fun listarFlores(): Unit {
    val flores = archivoFlores.readLines()
    if (archivoFlores.exists()) {
        if (flores.size>0){
            println("**FLORES**")
            for (flor in flores) {
                println(flor)
            }
        }else{
            println("No hay flores registradas")
        }
    } else {
        println("No hay lista de flores")
    }
}

