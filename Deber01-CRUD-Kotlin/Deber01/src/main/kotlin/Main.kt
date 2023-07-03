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
    mostrarMenuPrincipal()
}
fun mostrarMenuPrincipal(){
    println("*****MENU DEL SISTEMA DE REGISTRO*****");
    println("1. Menú de Florerias");
    println("2. Menú de Flores");
    println("3. Salir");
    println("Digite el literal deseado: ");
    var opcion = literal.nextLine()
    when(opcion){
        "1" -> mostrarMenuFloreria();
        "2" -> mostrarMenuFlor();
        else -> println("Ha salido!")
    }
}
fun mostrarMenuFloreria(){
    do {
        println("*REGISTRO DE FLORERIAS*")
        println("1. Registrar floreria");
        println("2. Listar florerias");
        println("3. Eliminar floreria");
        println("4. Actualizar floreria");
        println("5. Flores por floreria");
        println("6. Volver al menú principal");
        println("7. Salir");
        println("Digite el literal deseado: ");
        var opcion = literal.nextLine()
        when (opcion) {
            "1" -> registrarFloreria()
            "2" -> listarFlorerias()
            "3" -> eliminarFloreria()
            "4" -> actualizarFloreria()
            "4" -> mostrarFloresFloreria()
            "6" -> mostrarMenuPrincipal()
            "7" -> break
            else -> println("Opción desconocida!")
        }
    } while(opcion.toInt()!=7)
}
fun mostrarMenuFlor(){
    do {
        println("*REGISTRO DE FLORES*");
        println("1. Registrar flor");
        println("2. Listar flores");
        println("3. Eliminar flor");
        println("4. Actualizar flor");
        println("5. Volver al menú principal");
        println("6. Salir");
        println("Digite el literal deseado: ");
        var opcion = literal.nextLine()
        when (opcion) {
            "1" -> registrarFlor();
            "2" -> listarFlores();
            "3" -> eliminarFlor()
            "4" -> actualizarFlor()
            "5" -> mostrarMenuPrincipal()
            "6" -> break
            else -> println("Opción desconocida!")
        }
    }while (opcion.toInt()!=6)
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
    println("Ingrese si hace envio (S: Si, Cualquier tecla: No):");
    var haceEnvio: String = teclado.nextLine();
    var floreria = Floreria(idFloreria,nombre, direccion, telefono, transformarBoolean(haceEnvio))
    return floreria
}
//CRUD FLORERIA
fun registrarFloreria(): Unit {
    var floreria = Floreria()
    floreria = pedirDatosFloreria()
    if (floreria.nombre.isNotBlank() && floreria.direccion.isNotBlank()
         && floreria.telefono.isNotBlank()){
        val florerias = archivoFlorerias.readLines().toMutableList()
        florerias.add(floreria.toString())
        archivoFlorerias.writeText(florerias.joinToString("\n"))
        println("Floreria registrada")
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
    println("Ingrese el id de la floreria a borrar")
    val idFloreriaBorrar = readLine()
    val lineas = archivoFlorerias.readLines().toMutableList()
    for (linea in lineas) {
        var info = linea.split(",")
        var idFloreriaArchivo = info[0]
        if (idFloreriaBorrar == idFloreriaArchivo){
            var numeroLinea = lineas.indexOf(linea)
            lineas.remove(linea)
            archivoFlorerias.writeText(lineas.joinToString("\n"))
            println("Floreria eliminada del archivo correctamente.")
            break
        }
    }
}
fun actualizarFloreria(): Unit{
    var objetoEncontrado:Boolean = false
    println("Ingrese el id de la floreria a actualizar")
    val floreriaAActualizar = readLine()
    try {
        val lineas = archivoFlorerias.readLines().toMutableList()
        for (linea in lineas){
            var info = linea.split(",")
            var idFloreriaArchivo = info[0]
            if(floreriaAActualizar == idFloreriaArchivo){
                //obtengo la linea donde esta la floreria
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
    teclado.nextLine();
    println("Ingrese el nombre de la flor: " );
    var nombre: String = teclado.nextLine();
    println("Ingrese el color de la flor: " );
    var color: String = teclado.nextLine();
    println("Es nativa (S: Si, Cualquier tecla: No):" );
    var esNativa: String = teclado.nextLine();
    //----fecha
    println("Ingrese la fecha de llegada (dd/MM/yyyy): " );
    var fechaString = teclado.nextLine();
    var formato = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    var fechaLlegada = LocalDate.parse(fechaString, formato)
    //----
    println("Ingrese el precio: " );
    var precio: Double = teclado.nextDouble();
    var flor = Flor(idFloreria, idFlor, nombre, color, transformarBoolean(esNativa), fechaLlegada, precio)
    return flor
}
fun transformarBoolean(condicion: String): Boolean{
    if (condicion == "S"){
        return true
    }else{
        return false
    }
}
fun registrarFlor(): Unit {
    var flor = pedirDatosFlor()
    if (flor.nombre.isNotBlank() && flor.color.isNotBlank()){
        val flores = archivoFlores.readLines().toMutableList()
        flores.add(flor.toString())
        archivoFlores.writeText(flores.joinToString("\n"))
        println("Flor registrada")
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
fun eliminarFlor(): Unit{
    println("Ingrese el id de la flor a borrar")
    val idFlorBorrar = readLine()
    val lineas = archivoFlorerias.readLines().toMutableList()
    for (linea in lineas) {
        var info = linea.split(",")
        var idFlorArchivo = info[0]
        if (idFlorBorrar == idFlorArchivo){
            var numeroLinea = lineas.indexOf(linea)
            lineas.remove(linea)
            archivoFlorerias.writeText(lineas.joinToString("\n"))
            println("Floreria eliminada del archivo correctamente.")
            break
        }
    }
}
fun actualizarFlor(): Unit{
    var objetoEncontrado:Boolean = false
    println("Ingrese el id de la flor a actualizar")
    val florAActualizar = readLine()
    try {
        val lineas = archivoFlores.readLines().toMutableList()
        for (linea in lineas){
            var info = linea.split(",")
            var idFlorArchivo = info[0]
            if(florAActualizar == idFlorArchivo){
                //obtengo la linea donde esta la flor
                var numeroLinea = lineas.indexOf(linea)
                //pedir los datos para actualizarlos
                var infoNueva = pedirDatosFlor()
                lineas[numeroLinea]= infoNueva.toString()
                archivoFlores.writeText(lineas.joinToString("\n"))
                println("Flor actualizada del archivo correctamente.")
                objetoEncontrado = true
                break
            }
        }
        if (!objetoEncontrado){
            println("Flor no encontrada")
        }
    } catch (ex: IOException) {
        println("Error al actualizar el objeto del archivo: ${ex.message}")
    }
}

fun mostrarFloresFloreria(): Unit{
    println("Ingrese el id de la floreria")
    val idFloreria = readLine()
    val lineas = archivoFlorerias.readLines().toMutableList()
    for (linea in lineas) {
        var info = linea.split(",")
        var idFloreriaArchivo = info[0]
        if (idFloreria == idFloreriaArchivo){
            for (item in lineas){
                println(item)
            }
        }
    }
}