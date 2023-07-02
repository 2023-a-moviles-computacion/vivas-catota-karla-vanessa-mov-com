import java.io.File
import java.io.IOException
import java.util.*


val archivoFlorerias = File("src/main/kotlin/florer.txt")
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
        println("4. Salir");
        println("Digite el literal deseado: ");
        var opcion = literal.nextLine()
        when (opcion) {
            "1" -> registrarFloreria()
            "2" -> listarFlorerias()
            "3" -> eliminarFloreria()
            "5" -> instanciarFloreria()
            //"3" -> obtenerFloreriaNombre(teclado.nextLine())//floreria.eliminarFloreria(teclado.nextLine())
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
    var opcion = literal.nextLine()
    when(opcion){
        "1" ->
            crearFlor();
        else -> println("chao")
    }
}

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
        archivoFlorerias.appendText("$floreria\n")
    }else{
        println("se creo la lista")
    }


    //return floreria;
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

fun instanciarFloreria(): MutableList<Floreria> {
    var floreriasLineasLista: MutableList<String> = mutableListOf()
    var floreriasLista: MutableList<Floreria> = mutableListOf()
    if (archivoFlorerias.exists()  && archivoFlorerias.readText().isNotEmpty()) {

        for (floreriaLinea in archivoFlorerias.readLines()) {
            var lineas = floreriaLinea.split(",")
            println(lineas[1])
            //println(floreriasLista)
            //var floreriaObjeto = Floreria(nombreL, direccionL, horarioL, telefonoL, haceEnvioL)
            var floreriaObjeto = Floreria(lineas[0], lineas[1], lineas[2], lineas[3], lineas[4].toBoolean())
            floreriasLista.add(floreriaObjeto)
        }
    }else{
        println("No existe el archivo")
    }
    return floreriasLista
}

fun eliminarFloreria(){
    println("Ingrese la floreria a borrar")
    val textoABorrar = readLine()
    try {
        val archivo = File("src/main/kotlin/florer.txt")
        val lineas: List<String> = archivo.readLines()
        for (linea in lineas){
            var florerias = linea.split(",")
            var nombre = florerias[0]
            if(textoABorrar == nombre){
                    //val lineasActualizadas = lineas.filter { it != textoABorrar }
                    val lineasActualizadas = lineas.filter { !it.contains(nombre) }
                    archivo.writeText(lineasActualizadas.joinToString("\n"))
                    println("Objeto borrado del archivo correctamente.")
                break
            }
        }
    } catch (ex: IOException) {
        println("Error al borrar el objeto del archivo: ${ex.message}")
    }
}
