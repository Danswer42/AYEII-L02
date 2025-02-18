fun printLadosGrafo(g: Grafo) {
  for (e in g) {
println("e: ${e}")
  }   
}

fun main(args: Array<String>) {
  val archivo1 = "libGrafo\Grafo.txt"
  val archivo2 = "libGrafo\GrafoCosto.txt"
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

/*fun leerArchivo(nombreArchivo: String): List<String> {
    val lineas = mutableListOf<String>()
    try {
        val archivo = File(nombreArchivo)
        archivo.forEachLine { linea ->
            lineas.add(linea)
        }
    } catch (e: Exception) {
        println("Error al leer el archivo: ${e.message}")
    }
    return lineas
}

fun main(args: Array<String>) {
  if (args.size != 2) {
      println("Debe proporcionar dos argumentos: nombre_archivo_grafo.txt nombre_archivo_grafo_costo.txt")
      return
  }

  val archivoGrafo = args[0]
  val archivoGrafoCosto = args[1]

  val grafo = GrafoNoDirigido(archivoGrafo)
  println("Grafo desde archivo:")
  printLadosGrafo(grafo)

  val grafoCosto = GrafoNoDirigidoCosto(archivoGrafoCosto)
  println("Grafo desde archivo con costo:")
  printLadosGrafo(grafoCosto)
}*/
