import java.io.File
import java.util.Arrays

// ARCO
public open class Arco(val inicio: Int, val fin: Int) : Lado(inicio, fin) {

    // Retorna el vértice inicial del arco
    fun fuente() : Int {
	return inicio
    }

    // Retorna el vértice final del arco
    fun sumidero() : Int {
	return fin
    }

    // Representación del arco
    override fun toString() : String {
	return """
	inicio: ${inicio}
	fin: ${fin}
	"""
     }
} 

//ARCOCOSTO
public class ArcoCosto(val x: Int, val y: Int, val costo: Double) : Arco(x, y) {

    // Retorna el peso o costo asociado del arco
    fun costo() : Double {
	return costo
    }

    // Representación del arco
    override fun toString() : String {
	return """
	inicio: ${x}
	fin: ${y}
	peso: ${costo}
	"""
     }
} 

//ARISTA
public open class Arista(val v: Int, val u: Int) : Lado(v, u) {

    // Representación en string de la arista
    override fun toString() : String {
	return """
	v: ${v}
	w: ${u}
	"""
    }

} 

//ARISTACOSTO
public class AristaCosto(val x: Int,
			 val y: Int,
			 val costo: Double) : Comparable<AristaCosto>, Arista(x, y) {

    // Retorna el costo del arco
    fun costo() : Double {
	return costo
    }

    // Representación en string de la arista
    override fun toString() : String {
	return """
	v: ${x}
	w: ${y}
	costo: ${costo}
	"""
    }

    /* 
     Se compara dos arista con respecto a su costo. 
     Si this.obtenerCosto > other.obtenerCosto entonces
     retorna 1. Si this.obtenerCosto < other.obtenerCosto 
     entonces retorna -1. Si this.obtenerCosto == other.obtenerCosto
     entonces retorna 0 
     */
     override fun compareTo(other: AristaCosto): Int {
	 return when {
	     costo > other.costo -> 1
	     costo == other.costo -> 0
	     else -> -1
	 }
     }
} 

//COLOR
enum class Color { BLANCO, GRIS, NEGRO }

//GRAFO
interface Grafo : Iterable<Lado> {

    // Retorna el número de lados del grafo
    fun obtenerNumeroDeLados() : Int

    // Retorna el número de vértices del grafo
    fun obtenerNumeroDeVertices() : Int

    /* 
     Retorna los adyacentes de v, en este caso los lados que tienen como vértice inicial a v. 
     Si el vértice no pertenece al grafo se lanza una RuntimeException
     */
    fun adyacentes(v: Int) : Iterable<Lado>

    // Retorna el grado del grafo
    fun grado(v: Int) : Int

    // Retorna un iterador de los lados del grafo
    override operator fun iterator() : Iterator<Lado> 
}

//GRAFO_DIRIGIDO
public class GrafoDirigido : Grafo {
    var numeroDeLados = 0
    var numDeVertices = 0
    var adj = ArrayList<ArrayList<Arco>>();

    // Se construye un grafo a partir del número de vértices
    constructor(numDeVertices: Int) {
	this.numDeVertices = numDeVertices;
   	for (i in 0..numDeVertices) {
	    adj.add(ArrayList<Arco>());
	}
    }

    /*
     Se construye un grafo a partir de un archivo. Existen dos tipos de formatos de archivo.
     El primero solo incluye los vétices de los lados, sin los pesos. El formato es como sigue.
     La primera línea es el número de vértices. La segunda línea es el número de lados. Las siguientes líneas
     corresponden a los lados, con los vértices de un lado separados por un espacio en blanco.
     El segundo formato solo se diferencia del primero en que cada línea de los lados tiene a dos enteros
     que corresponden a los vértices de los lados y un número real que es el peso o costo del lado.
     La variable conPeso es true si el archivo de entrada contiene un formato que incluye los pesos de los
     lados. Es false si el formato solo incluye los vértices de los lados. Se asume que los datos del 
     archivo están correctos, no se verificas.
     */  
    constructor(nombreArchivo: String)  {
	var cont = 0
	var E = 0
	File(nombreArchivo).forEachLine {
	    if (cont == 0) {
		numDeVertices = it.toInt() 
		println("Numero de vertices ${numDeVertices}")
		cont++
		for (i in 0..numDeVertices) {
		    adj.add(ArrayList<Arco>());
		}
	    } else if (cont == 1) {
		println("Numero de lados ${it}")
		E = it.toInt()
		cont++
	    } else {
		if (E > (cont-2)) {
		    var tok = it.split(" ")
		    //println("Lado sin peso: ${tok.get(0)} ${tok.get(1)}")
		    agregarArco(Arco(tok.get(0).toInt(), tok.get(1).toInt()))
		    cont++
		}
	    }
	}
    }

    // Agrega un lado al digrafo
    fun agregarArco(a: Arco) : Boolean {
	(adj.get(a.fuente())).add(a);
	numeroDeLados++;
	return true
    }

    // Retorna el grado del grafo. Si el vértice no pertenece al grafo se lanza una RuntimeException
    override fun grado(v: Int) : Int {
	return 1;
    }

    // Retorna el grado exterior del grafo. Si el vértice no pertenece al grafo se lanza una RuntimeException
    fun gradoExterior(v: Int) : Int {
	return v
    }

    // Retorna el grado interior del grafo. Si el vértice no pertenece al grafo se lanza una RuntimeException
    fun gradoInterior(v: Int) : Int {
	return v
    }

    // Retorna el número de lados del grafo
    override fun obtenerNumeroDeLados() : Int {
	return numeroDeLados;
    }

    // Retorna el número de vértices del grafo
    override fun obtenerNumeroDeVertices() : Int {
	return numDeVertices;
    }

    /* 
     Retorna los adyacentes de v, en este caso los lados que tienen como vértice inicial a v. 
     Si el vértice no pertenece al grafo se lanza una RuntimeException
     */
    override fun adyacentes(v: Int) : Iterable<Arco> {
	return adj.get(v);
    }

    // Retorna todos los lados del digrafo
    override operator fun iterator() : Iterator<Arco> {
        var lados = ArrayList<Arco>()
        for (i in 0..numDeVertices) {
            for (e in adj.get(i) ) {
            lados.add(e)
	    }
	}
	return lados.iterator()
     }
     
    // String que muestra el contenido del grafo
    override fun toString() : String {
	return """
	V: ${numDeVertices}
	E: ${numeroDeLados}
	"""
     }
}

//GRAFO_DIRIGIDO_COSTO
public class GrafoDirigidoCosto : Grafo {
    var numeroDeLados = 0
    var numDeVertices = 0
    var adjList = ArrayList<ArrayList<ArcoCosto>>();

    // Se construye un grafo a partir del número de vértices
    constructor(numDeVertices: Int) {
	this.numDeVertices = numDeVertices;
   	for (i in 0..numDeVertices) {
	    adjList.add(ArrayList<ArcoCosto>());
	}
    }

    /*
     Se construye un grafo a partir de un archivo. Existen dos tipos de formatos de archivo.
     El primero solo incluye los vétices de los lados, sin los pesos. El formato es como sigue.
     La primera línea es el número de vértices. La segunda línea es el número de lados. Las siguientes líneas
     corresponden a los lados, con los vértices de un lado separados por un espacio en blanco.
     El segundo formato solo se diferencia del primero en que cada línea de los lados tiene a dos enteros
     que corresponden a los vértices de los lados y un número real que es el peso o costo del lado.
     La variable conPeso es true si el archivo de entrada contiene un formato que incluye los pesos de los
     lados. Es false si el formato solo incluye los vértices de los lados. Se asume que los datos del 
     archivo están correctos, no se verificas.
     */  
    constructor(nombreArchivo: String)  {
	var cont = 0
	var E = 0
	File(nombreArchivo).forEachLine {
	    if (cont == 0) {
		numDeVertices = it.toInt() 
		println("Numero de vertices ${numDeVertices}")
		cont++
		for (i in 0..numDeVertices) {
		    adjList.add(ArrayList<ArcoCosto>());
		}
	    } else if (cont == 1) {
		println("Numero de lados ${it}")
		E = it.toInt()
		cont++
	    } else {
		if (E > (cont-2)) {
		    var tok = it.split(" ")
		    agregarArcoCosto( ArcoCosto(tok.get(0).toInt(),
					   tok.get(1).toInt(), tok.get(2).toDouble()) )
		    cont++
		}
	    }
	}
    }

    // Agrega un lado al digrafo
    fun agregarArcoCosto(a: ArcoCosto) {
	(adjList.get(a.fuente())).add(a);
	numeroDeLados++;
    }

    // Retorna el grado del grafo. Si el vértice no pertenece al grafo se lanza una RuntimeException
    override fun grado(v: Int) : Int {
	return 1;
    }

    // Retorna el grado exterior del grafo. Si el vértice no pertenece al grafo se lanza una RuntimeException
    fun gradoExterior(v: Int) : Int {
	return v
    }

    // Retorna el grado interior del grafo. Si el vértice no pertenece al grafo se lanza una RuntimeException
    fun gradoInterior(v: Int) : Int {
	return v
    }

    // Retorna el número de lados del grafo
    override fun obtenerNumeroDeLados() : Int {
	return numeroDeLados;
    }

    // Retorna el número de vértices del grafo
    override fun obtenerNumeroDeVertices() : Int {
	return numDeVertices;
    }

    /* 
     Retorna los adyacentes de v, en este caso los lados que tienen como vértice inicial a v. 
     Si el vértice no pertenece al grafo se lanza una RuntimeException
     */
    override fun adyacentes(v: Int) : Iterable<ArcoCosto> {
	return adjList.get(v);
    }

    // Retorna todos los lados del digrafo con costo
    override operator fun iterator() : Iterator<ArcoCosto> {
	var lados = ArrayList<ArcoCosto>()
	for (i in 0..numDeVertices) {
	    for (e in adjList.get(i) ) {
	   	lados.add(e)
	    }
	}
	return lados.iterator()
     }

    
    // String que muestra el contenido del grafo
    override fun toString() : String {
	return """
	V: ${numDeVertices}
	E: ${numeroDeLados}
	"""
     }
}

//GRAFOS_NO_DIRIGIDOS
public class GrafoNoDirigido: Grafo {
    var numeroDeLados = 0
    var numDeVertices = 0
    var adj = ArrayList<ArrayList<Arista>>();
    var ari = Arista(1,2)

    // Se construye un grafo a partir del número de vértices
    constructor(numDeVertices: Int) {
	this.numDeVertices = numDeVertices;
   	for (i in 0..numDeVertices) {
	    adj.add(ArrayList<Arista>());
	}
    }

    /*
     Se construye un grafo a partir de un archivo. Existen dos tipos de formatos de archivo.
     El primero solo incluye los vétices de los lados, sin los pesos. El formato es como sigue.
     La primera línea es el número de vértices. La segunda línea es el número de lados. Las siguientes líneas
     corresponden a los lados, con los vértices de un lado separados por un espacio en blanco.
     El segundo formato solo se diferencia del primero en que cada línea de los lados tiene a dos enteros
     que corresponden a los vértices de los lados y un número real que es el peso o costo del lado.
     La variable conPeso es true si el archivo de entrada contiene un formato que incluye los pesos de los
     lados. Es false si el formato solo incluye los vértices de los lados. Se asume que los datos del 
     archivo están correctos, no se verificas.
     */  
    constructor(nombreArchivo: String) {
	var cont = 0
	var E = 0
	File(nombreArchivo).forEachLine {
	    if (cont == 0) {
		numDeVertices = it.toInt() 
		println("Numero de vertices ${numDeVertices}")
		cont++
		for (i in 0..numDeVertices) {
		    adj.add(ArrayList<Arista>());
		}
	    } else if (cont == 1) {
		println("Numero de lados ${it}")
		E = it.toInt()
		cont++
	    } else {
		if (E > (cont-2)) {
		    var tok = it.split(" ")
		    //println("Lado sin peso: ${tok.get(0)} ${tok.get(1)}")
		    agregarArista(Arista(tok.get(0).toInt(), tok.get(1).toInt()))
		    cont++
		}
	    }
	}
    }

    // Agrega un lado al grafo no dirigido
    fun agregarArista(a: Arista) {
	(adj.get(a.v)).add(a);
	(adj.get(a.u)).add(a);
	numeroDeLados++;
    }

    // Retorna el número de lados del grafo
    override fun obtenerNumeroDeLados() : Int {
	return numeroDeLados;
    }

    // Retorna el número de vértices del grafo
    override fun obtenerNumeroDeVertices() : Int {
	return numDeVertices;
    }

    // Retorna los lados adyacentes al vértice v, es decir, los lados que contienen al vértice v
    override fun adyacentes(v: Int) : Iterable<Arista> {
	return adj.get(v);
    }

    
/*
    fun aristas() : Iterable<Arista> {
	var lados = ArrayList<Arista>()
	for (i in 0..numDeVertices) {
	    for (e in adj.get(i) )
	    if (e.u > e.v) {
		lados.add(e)
	    }
	}
	return lados
    }
*/
/*
     inner class ListaIterato(l: ListaEnlazadaSimple<T>) : Iterator<T> {
	var actual = l.cabeza
	override fun hasNext(): Boolean = (actual != null) 
	override fun next(): T {
	    if (actual == null) {
		throw NoSuchElementException("Error, no hay mas elementos que iterar")
	    }
	    val valor = actual!!.valor
	    actual = actual?.proximo
	    return valor
	}
    }
 */
    // Retorna todos los lados del grafo no dirigido
     override operator fun iterator() : Iterator<Arista> {
	 var lados = ArrayList<Arista>()
	 for (i in 0..numDeVertices) {
	     for (e in adj.get(i) )
	     if (e.u > e.v) {
		 lados.add(e)
	     }
	 }
	 return lados.iterator()
     }

     // Grado del grafo
    override fun grado(v: Int) : Int {
	return 1;
    }

    // Retorna un string con una representación del grafo, en donde se nuestra todo su contenido
    override fun toString() : String {
	return """
	V: ${numDeVertices}
	E: ${numeroDeLados}
	"""
     }
}

//GRAFOS_NO_DIRIGIDO_COSTO
public class GrafoNoDirigidoCosto: Grafo {
    var numeroDeLados = 0
    var numDeVertices = 0
    var adj = ArrayList<ArrayList<AristaCosto>>();
    var ari = AristaCosto(1,2, 0.0)

    // Se construye un grafo a partir del número de vértices
    constructor(numDeVertices: Int) {
	this.numDeVertices = numDeVertices;
   	for (i in 0..numDeVertices) {
	    adj.add(ArrayList<AristaCosto>());
	}
    }

    /*
     Se construye un grafo a partir de un archivo. Existen dos tipos de formatos de archivo.
     El primero solo incluye los vétices de los lados, sin los pesos. El formato es como sigue.
     La primera línea es el número de vértices. La segunda línea es el número de lados. Las siguientes líneas
     corresponden a los lados, con los vértices de un lado separados por un espacio en blanco.
     El segundo formato solo se diferencia del primero en que cada línea de los lados tiene a dos enteros
     que corresponden a los vértices de los lados y un número real que es el peso o costo del lado.
     La variable conPeso es true si el archivo de entrada contiene un formato que incluye los pesos de los
     lados. Es false si el formato solo incluye los vértices de los lados. Se asume que los datos del 
     archivo están correctos, no se verificas.
     */  
    constructor(nombreArchivo: String) {
	var cont = 0
	var E = 0
	File(nombreArchivo).forEachLine {
	    if (cont == 0) {
		numDeVertices = it.toInt() 
		println("Numero de vertices ${numDeVertices}")
		cont++
		for (i in 0..numDeVertices) {
		    adj.add(ArrayList<AristaCosto>());
		}
	    } else if (cont == 1) {
		println("Numero de lados ${it}")
		E = it.toInt()
		cont++
	    } else {
		if (E > (cont-2)) {
		    var tok = it.split(" ")
		    //println("Lado con peso: ${tok.get(0)} ${tok.get(1)} ${tok.get(2)}")
		    agregarAristaCosto( AristaCosto(tok.get(0).toInt(), tok.get(1).toInt(), tok.get(2).toDouble()) )
		    cont++
		}
	    }
	}
    }

    // Agrega un lado al grafo no dirigido
    fun agregarAristaCosto(a: AristaCosto) {
	(adj.get(a.v)).add(a);
	(adj.get(a.u)).add(a);
	numeroDeLados++;
    }

    // Retorna el número de lados del grafo
    override fun obtenerNumeroDeLados() : Int {
	return numeroDeLados;
    }

    // Retorna el número de vértices del grafo
    override fun obtenerNumeroDeVertices() : Int {
	return numDeVertices;
    }

    // Retorna los lados adyacentes al vértice v, es decir, los lados que contienen al vértice v
    override fun adyacentes(v: Int) : Iterable<AristaCosto> {
	return adj.get(v);
    }

    // Retorna todos los lados del grafo no dirigido
     override operator fun iterator() : Iterator<AristaCosto> {
	 var lados = ArrayList<AristaCosto>()
	 for (i in 0..numDeVertices) {
	     for (e in adj.get(i) )
	     if (e.u > e.v) {
		 lados.add(e)
	     }
	 }
	 return lados.iterator()
     }
    
    // Grado del grafo
    override fun grado(v: Int) : Int {
	return 1;
    }

    // Retorna un string con una representación del grafo, en donde se nuestra todo su contenido
    override fun toString() : String {
	return """
	V: ${numDeVertices}
	E: ${numeroDeLados}
	"""
     }
}

//LADO
open class Lado(val a: Int, val b: Int) {

    // Retorna cualquiera de los dos vértices del grafo
    fun cualquieraDeLosVertices() : Int {
	return a
    }

    // Dado un vertice w, si w == a entonces retorna b, de lo contrario si w == b  entonces retorna a,  y si w no es igual a a ni a b, entonces se lanza una RuntimeExpception 
    fun elOtroVertice(w: Int) : Int {
	if (w == a) {
	    return b
	} else if (w == b) {
	    return a
	} else {
	    throw RuntimeException()
	}
    }
}

//MAIN
fun printLadosGrafo(g: Grafo) {
    for (e in g) {
	println("e: ${e}")
    }   
}

fun main(args: Array<String>) {
    val archivo1 = 2
    val archivo2 = 3
    var g = GrafoNoDirigido(10)
    g.agregarArista(Arista(1, 2))
    g.agregarArista(Arista(3, 2))
    g.agregarArista(Arista(7, 2))
    g.agregarArista(Arista(7, 4))
    g.agregarArista(Arista(5, 7))
    g.agregarArista(Arista(5, 6))
    g.agregarArista(Arista(8, 9))
    println("g: ${g}")
    val arista1 = AristaCosto(1, 2, 3.9)
    val arista2 = AristaCosto(1, 2, 4.9)
    println("arista1: ${arista1}")
    println("arista2: ${arista2}")
    println("comparacion a1 a2 : ${arista1.compareTo(arista2)}")
    println("Grafo desde archivo")
    g = GrafoNoDirigido(archivo1)
    printLadosGrafo(g)
    println("Grafo desde archivo con costo")
    val g1 = GrafoNoDirigidoCosto(archivo2)
    printLadosGrafo(g1) 
    val gd = GrafoDirigidoCosto(archivo2)
    printLadosGrafo(gd) 
}