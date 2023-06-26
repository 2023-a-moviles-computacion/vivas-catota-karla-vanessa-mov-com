import java.io.File
import java.nio.file.StandardOpenOption
import java.text.SimpleDateFormat
import java.util.*

class Flor(
    //var idFlor: Int,
    var nombre: String,
    var color: String,
    var esNativa: Boolean,
    var fechaLlegada: Date,
    var precio: Double) {
}

fun crearFlor(): Unit{
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
   // write(archivo.toPath(), flor.toString().toByteArray(), StandardOpenOption.APPEND)
}