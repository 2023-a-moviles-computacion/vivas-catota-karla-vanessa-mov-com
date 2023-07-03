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
        else -> println("Opcion incorrecta!")
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
            else -> println("Opcion incorrecta!")
        }
    } while(opcion!="5")
}
fun mostrarMenuFlor(){
    do {
        println("*REGISTRO DE FLORES*");
        println("1. Registrar flor");
        println("2. Listar flores");
       // println("3. Eliminar flor");
       // println("4. Actualizar flor");
        println("5. Salir");
        println("Digite el literal deseado: ");
        var opcion = literal.nextLine()
        when (opcion) {
            "1" -> registrarFlor();
            "2" -> listarFlores();
            else -> println("Opcion incorrecta!")
        }
    }while (opcion!="5")
}
fun pedirDatosFloreria(): Floreria{
    println("Ingrese el id de la floreria: ");
    var idFloreria: Int = teclado.nextInt();
    teclado.nextLine();
    println("Ingrese el nombre de la floreria: ");
    var nombre: String = teclado.nextLine();
    println("Ingrese la direccion de la floreria: ");
    var direccion: String = teclado.nextLine();
    println("Ingrese el telefono de la floreria: ");
    var telefono: String = teclado.nextLine();
    println("Ingrese si hace envio (1: Si, 0: No)");
    var haceEnvio: Boolean = teclado.nextBoolean();
    var floreria = Floreria(idFloreria,nombre, direccion, telefono, haceEnvio)
    return floreria
}
//CRUD FLORERIA
fun registrarFloreria(): Unit {
    var floreria = Floreria()
    floreria = pedirDatosFloreria()
    if (floreria.nombre.isNotBlank() && floreria.direccion.isNotBlank()
         && floreria.telefono.isNotBlank()){
        archivoFlorerias.appendText( "\n$floreria")
        println("Floreria registrada")
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
    var objetoEncontrado:Boolean = false
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
                //pedir los datos para actualizarlos
                var infoNueva = pedirDatosFloreria()
                lineas[numeroLinea]= infoNueva.toString()
                archivoFlorerias.writeText(lineas.joinToString("\n"))
                println("Objeto actualizado del archivo correctamente.")
                objetoEncontrado = true
                break
            }
        }
        if (!objetoEncontrado){
            println("Floreria no encontrada")
        }
    } catch (ex: IOException) {
        println("Error al actualizar el objeto del archivo: ${ex.message}")
    }
}
//CRUD FLOR

fun pedirDatosFlor(): Flor{
    println("Ingrese el id de la floreria a la que pertenece la flor: " );
    var idFloreria: Int = teclado.nextInt();
    teclado.nextLine();
    println("Ingrese el id de la flor: " );
    var idFlor: Int = teclado.nextInt();
    println("Ingrese el nombre de la flor: " );
    var nombre: String = teclado.nextLine();
    println("Ingrese el color de la flor: " );
    var color: String = teclado.nextLine();
    println("Es nativa (1: Si, 2: No): " );
    var esNativa: String = teclado.nextLine();
    teclado.nextLine()
    //----fecha
    println("Ingrese la fecha de llegada: " );
    var fechaString = teclado.nextLine();
    var formato = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    var fechaLlegada = LocalDate.parse(fechaString, formato)
    //----
    println("Ingrese el precio: " );
    var precio: Double = teclado.nextDouble();
    var flor = Flor(idFloreria, idFlor, nombre, color, esNativa.toBoolean(), fechaLlegada, precio)
    return flor
}

fun transformarBoolean(esNativa: String): Boolean{
    if (esNativa == "1"){
        return true
    }else{
        return false
    }
}
fun registrarFlor(): Unit {
    var flor = pedirDatosFlor()
    if (flor.nombre.isNotBlank() && flor.color.isNotBlank()){
        archivoFlores.appendText( "\n$flor")
        println("Flor registrada")
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