import java.io.File
import java.io.IOException
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.system.exitProcess

val archivoFlorerias = File("src/main/kotlin/florerias.txt")
val archivoFlores = File("src/main/kotlin/flores.txt")
var teclado = Scanner(System.`in`)
var literal = Scanner(System.`in`);

fun main(args: Array<String>) {
    mostrarMenuPrincipal()
}
//-------------------------------------------------------------
//MENUS
fun mostrarMenuPrincipal(){
    do{
    println("▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀")
    println("-------MENU DEL SISTEMA CRUD-------");
    println("\t1. Menú de Florerias");
    println("\t2. Menú de Flores");
    println("\t3. Salir");
    print("Digite el literal deseado: ");
    var opcion = literal.nextLine()
        when(opcion){
            "1" -> mostrarMenuFloreria();
            "2" -> mostrarMenuFlor();
            "3" -> exitProcess(0)
            else -> println("Opción desconocida!")
        }
    }    while(opcion.toInt()!=3)
}
fun mostrarMenuFloreria(){
    do {
        println("▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀")
        println("*MENÚ DE FLORERIAS*")
        println("\t1. Registrar floreria");
        println("\t2. Listar florerias");
        println("\t3. Eliminar floreria");
        println("\t4. Actualizar floreria");
        println("\t5. Volver al menú principal");
        println("\t6. Salir");
        print("Digite el literal deseado: ");
        var opcion = literal.nextLine()
        when (opcion) {
            "1" -> registrarFloreria()
            "2" -> listarFlorerias()
            "3" -> eliminarFloreria()
            "4" -> actualizarFloreria()
            "5" -> mostrarMenuPrincipal()
            "6" -> exitProcess(0)
            else -> println("Opción desconocida!")
        }
    } while(opcion.toInt()!=6)
}
fun mostrarMenuFlor(){
    do {
        println("▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀")
        println("*MENÚ DE FLORES*");
        println("\t1. Registrar flor");
        println("\t2. Listar flores");
        println("\t3. Eliminar flor");
        println("\t4. Actualizar flor");
        println("\t5. Volver al menú principal");
        println("\t6. Salir");
        print("Digite el literal deseado: ");
        var opcion = literal.nextLine()
        when (opcion) {
            "1" -> registrarFlor();
            "2" -> listarFlores();
            "3" -> eliminarFlor()
            "4" -> actualizarFlor()
            "5" -> mostrarMenuPrincipal()
            "6" -> exitProcess(0)
            else -> println("Opción desconocida!")
        }
    }while (opcion.toInt()!=6)
}
//-------------------------------------------------------------
//------------------------CRUD FLORERIA
fun pedirDatosFloreria(): Floreria{
    println("Ingrese el id de la floreria: ");
    var idFloreria: Int = teclado.nextInt();
    teclado.nextLine();
    println("Ingrese el nombre de la floreria: ");
    var nombre: String = teclado.nextLine();
    println("Ingrese la ubicación de la floreria: ");
    var ubicacion: String = teclado.nextLine();
    println("Ingrese el teléfono de la floreria: ");
    var telefono: String = teclado.nextLine();
    println("Ingrese si hace envio (S: Si, Cualquier tecla: No):");
    var haceEnvio: String = teclado.nextLine();
    var floreria = Floreria(idFloreria,nombre, ubicacion, telefono, transformarBoolean(haceEnvio))
    return floreria
}

fun registrarFloreria(): Unit {
    var floreria = Floreria()
    floreria = pedirDatosFloreria()
    if (floreria.nombre.isNotBlank() && floreria.ubicacion.isNotBlank()
         && floreria.telefono.isNotBlank()){
        val florerias = archivoFlorerias.readLines().toMutableList()
        florerias.add(floreria.toString())
        archivoFlorerias.writeText(florerias.joinToString("\n"))
        println("Floreria registrada")
    }else{
        println("Datos inválidos!")
    }
}
fun listarFlorerias(): Unit {
    val florerias = archivoFlorerias.readLines()
    if (archivoFlorerias.exists()) {
        if (florerias.isNotEmpty()){
            println("▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀")
            println("****          FLORERIAS        ****")
            for (floreria in florerias) {
                println("▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀")
                var detalle = floreria.split(";")
                println("\tId de la florería: " + detalle[0] + "\n\tNombre: " + detalle[1] + "\n\tUbicación: " + detalle[2]
                + "\n\tTeléfono: " + detalle[3] + "\n\tHace envio?: " + detalle[4])
            }
        } else {
            println("No hay florerias registradas")
        }
    }else {
        println("No existe el archivo de florerias")
    }
}
fun eliminarFloreria(): Unit{
    print("Ingrese el id de la floreria a eliminar-> ")
    val idFloreriaBorrar = readLine()
    val lineas = archivoFlorerias.readLines().toMutableList()
    for (linea in lineas) {
        var info = linea.split(";")
        var idFloreriaArchivo = info[0]
        if (idFloreriaBorrar == idFloreriaArchivo){
            lineas.remove(linea)
            archivoFlorerias.writeText(lineas.joinToString("\n"))
            println("Florería eliminada del archivo correctamente.")
            break
        }else{
            println("La florería no se ha encontrado")
        }
    }
}
fun actualizarFloreria(): Unit{
    var objetoEncontrado:Boolean = false
    print("Ingrese el id de la floreria a actualizar-> ")
    val floreriaAActualizar = readLine()
    try {
        val lineas = archivoFlorerias.readLines().toMutableList()
        for (linea in lineas){
            var info = linea.split(";")
            var idFloreriaArchivo = info[0]
            if(floreriaAActualizar == idFloreriaArchivo){
                //obtengo la linea donde esta la floreria
                var numeroLinea = lineas.indexOf(linea)
                //pedir los datos para actualizarlos
                var infoNueva = pedirDatosFloreria()
                lineas[numeroLinea]= infoNueva.toString()
                archivoFlorerias.writeText(lineas.joinToString("\n"))
                println("Florería actualizado del archivo correctamente.")
                objetoEncontrado = true
                break
            }
        }
        if (!objetoEncontrado){
            println("Florería no encontrada")
        }
    } catch (ex: IOException) {
        println("Error al actualizar la florería del archivo: ${ex.message}")
    }
}
//------------------------------------------------------------
//----------------------CRUD FLOR
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
    println("Ingrese el precio por unidad: " );
    var precio: Double = teclado.nextDouble();
    var flor = Flor(idFloreria, idFlor, nombre, color, transformarBoolean(esNativa), fechaLlegada, precio)
    return flor
}

fun registrarFlor(): Unit {
    var flor = pedirDatosFlor()
    if (flor.nombre.isNotBlank() && flor.color.isNotBlank()){
        val flores = archivoFlores.readLines().toMutableList()
        flores.add(flor.toString())
        archivoFlores.writeText(flores.joinToString("\n"))
        println("Flor registrada")
    }else{
        println("Datos inválidos!")
    }
}
fun listarFlores(): Unit {
    val flores = archivoFlores.readLines()
    if (archivoFlores.exists()) {
        if (flores.isNotEmpty()){
            println("▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀")
            println("****           FLORES          ****")
            for (flor in flores) {
                println("▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀")
                var detalle = flor.split(";")
                println("\tId de la floreria: " + detalle[0] + "\n\tId de la flor: " + detalle[1]
                        + "\n\tNombre: " + detalle[2] + "\n\tColor: " + detalle[3]
                        + "\n\tEs nativa?: " + detalle[4] + "\n\tFecha de llegada: " + detalle[5]
                        + "\n\tPrecio por unidad: " + detalle[6])
            }
        }else{
            println("No hay flores registradas")
        }
    } else {
        println("No existe el archivo de flores")
    }
}
fun eliminarFlor(): Unit{
    print("Ingrese el id de la flor a eliminar-> ")
    val idFlorBorrar = readLine()
    val lineas = archivoFlores.readLines().toMutableList()
    for (linea in lineas) {
        var info = linea.split(";")
        var idFlorArchivo = info[1]
        if (idFlorBorrar == idFlorArchivo){
            lineas.remove(linea)
            archivoFlores.writeText(lineas.joinToString("\n"))
            println("Flor eliminada del archivo correctamente.")
            break
        }else{
            println("La flor no se ha encontrado")
        }
    }
}
fun actualizarFlor(): Unit{
    var objetoEncontrado:Boolean = false
    print("Ingrese el id de la flor a actualizar-> ")
    val florAActualizar = readLine()
    try {
        val lineas = archivoFlores.readLines().toMutableList()
        for (linea in lineas){
            var info = linea.split(";")
            var idFlorArchivo = info[1]
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
        println("Error al actualizar la flor del archivo: ${ex.message}")
    }
}

fun transformarBoolean(condicion: String): Boolean{
    if (condicion == "S" || condicion == "s"){
        return true
    }else{
        return false
    }
}