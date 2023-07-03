import java.io.File
import java.nio.file.StandardOpenOption
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDate.parse
import java.util.*

class Flor{
    //var idFlor: Int,
    var nombre: String
    var color: String
    var esNativa: Boolean
    var fechaLlegada: LocalDate
    var precio: Double

    constructor() {
        this.nombre = ""
        this.color = ""
        this.esNativa = false
        this.fechaLlegada = LocalDate.of(2018, 6, 25)
        this.precio = 0.00
    }

    constructor(nombre: String, color: String, esNativa: Boolean, fechaLlegada: LocalDate, precio: Double) {
        this.nombre = nombre
        this.color = color
        this.esNativa = esNativa
        this.fechaLlegada = fechaLlegada
        this.precio = precio
    }

    override fun toString(): String {
        return nombre + "," + color +
                "," + esNativa +
                "," + fechaLlegada +
                "," + precio
    }
}


/*
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
   // write(archivoFlorerias.toPath(), flor.toString().toByteArray(), StandardOpenOption.APPEND)
}
 */