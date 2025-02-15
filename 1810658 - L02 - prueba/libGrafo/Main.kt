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