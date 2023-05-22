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

    calcularSueldo(10.00);
    calcularSueldo(10.00, 12.00, 20.00) //parametros nonmbrados
    calcularSueldo(10.00, bonoEspecial = 20.00, tasa = 14.00)

    //Int -> Int? (nullable)
    //String -> String? (nullable)


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
}

