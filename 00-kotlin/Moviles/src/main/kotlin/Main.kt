import java.util.*;
fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    //Tipos de variables

    //inmutables no se reasigna
    val inmutable: String = "Vanessa";
    //inmutable = Karla";
    //Mutables - se reasignan
    var mutable: String = "Karla";
    mutable  = "Vanessa";

    // se prefiere el val antes que el var

    //duck typing
    val ejemploVariable = "Karla Vanessa";
    val edadEjemplo: Int = 17;
    ejemploVariable.trim()
    //ejemploVariable = edadEjemplo;

    //variables primitivas
    val nombreProfesor: String = "Karla Vanessa";
    val sueldo: Double = 1.2;
    val estadoCivil: Char = 'C';
    val mayorEdad: Boolean = true;
    //Clase Java
    val fechaNacimiento: Date = Date()

    //Switch
    val estadoCivilWhen = "C"
    when (estadoCivilWhen){
        ("C") -> {
            println("Casado")
        }
        ("S") -> {
            println("Soltero")
        }else -> {
        println("No sabemos");
    }
    }

    val coqueteo = if(estadoCivilWhen == "S") "Si" else "No"

    calcularSueldo(10.00);
    calcularSueldo(10.00, 12.00, 20.00) //parametros nonmbrados
    calcularSueldo(10.00, bonoEspecial = 20.00, tasa = 14.00)

    //Int -> Int? (nullable)
    //String -> String? (nullable)

    val sumaUno=suma(1,1)
    val sumaDos = Suma(null,1)
    val sumaTres = Suma(1,null)
    val sumaCuadro = Suma(null,null)
    sumaUno.sumar()
    sumaDos.sumar()
    sumaTres.sumar()
    sumaCuadro.sumar()
    println(Suma.pi)
    println(Suma.elevarAlCuadrado(2))
    println(Suma.historialSumas)

}


//es una función void -> se utiliza Unit
fun imprimirNombre(nombre: String): Unit{
    println("Nombre: ${nombre}")
}

fun calcularSueldo(
    sueldo: Double,
    tasa: Double = 12.00,
    bonoEspecial: Double? = null,
)
        : Double{
    if(bonoEspecial == null){
        return sueldo * (100/tasa)
    }else{
        return sueldo * (100/tasa) + bonoEspecial;
    }
}

abstract class NumerosJava{
    protected val numeroUno: Int
    private val numeroDos: Int
    constructor(
        uno: Int,
        dos: Int
    ){//Bloque de codigo del constructor
        this.numeroUno = uno
        this.numeroDos = dos
        println("Inicializando")
    }
}

abstract class Numeros(//Constructir PRIMARIO
    //Ejemplo:
    //uno: Int, (Parametro (sin modificador de acceso))
    //private var uno: Int, //Propiedad publica clase numeros.uno
    //var uno: Int, //Propiedad de la clase (por defecto es PUBLIC)
    //public var uno: Int,

    protected val numeroUno: Int, //propiedad de la clase protected numeros.numeroUno
    protected val numeroDos: Int, //propiedad de la clase protected numeros.numeroDos
){
    //var cedula: String = "" (pulibc es por defecto)
    //private valorCalculado: Int = 0 (private)
    init {
        this.numeroUno; this.numeroDos; //this es opcional
        numeroUno; numeroDos; // sin ek "this", es lo mismo
        println("Inicializando")
    }
}

class  Suma( //Constructor Primario Suma
    uno: Int,  //Parametro
    dos: Int  //Parametro
) : Numeros(uno, dos){ // <- constructor del padre
    init { //bloque constructor primario
        this.numeroUno; numeroUno;
        this.numeroDos; numeroDos;
    }
    constructor(//segundo constructor
        uno: Int?, // parametros
        dos: Int // parametros
    ): this(//llamada constructor primario
        if(uno == null) 0 else uno, dos
    ){//si necesitamos bloque de codigo lo usamos
        numeroUno;
    }
    constructor(//tercer constructor
        uno: Int?, // parametros
        dos: Int // parametros
    ): this(//llamada constructor primario
        uno,
        if(dos == null) 0 else dos
    )// si no lo necesitamos al bloque de codigo "{}" lo omitimos

    constructor(//cuarto constructor
        uno: Int?,
        dos: Int?
    ): this(//llamada constructor primario
        if (uno == null) 0 else uno,
        if (dos == null) 0 else dos,
    )

    public fun sumar(): Int {
        val total = numeroUno + numeroDos
        agregarHistorial(total)
        return total
    }

    companion object{
        val pi = 3.14
        fun elevarAlCuadrado(num: Int): Int{
            return num * num
        }
        val historialSumas = ArrayList<Int>()

        fun agregarHistorial(valorNuevaSuma: Int){
            historialSumas.add(valorNuevaSuma)
        }
    }
}


