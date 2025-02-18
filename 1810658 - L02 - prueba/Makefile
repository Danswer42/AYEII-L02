KC= kotlinc
KFLAG= -cp
GLIB= ./libGrafo
KOTLIN_LIB = C:/kotlinc/lib
LIBGRAPH= libGrafoR/libGrafoR.jar

all: $(LIBGRAPH) $(GLIB)/Main.class  # Depende del JAR y del archivo Main.class

# Regla para crear el archivo JAR
$(LIBGRAPH): $(GLIB)/Color.class\
	$(GLIB)/Lado.class\
	$(GLIB)/Arco.class\
	$(GLIB)/ArcoCosto.class\
	$(GLIB)/Arista.class\
	$(GLIB)/AristaCosto.class\
	$(GLIB)/Grafo.class\
	$(GLIB)/GrafoDirigido.class\
	$(GLIB)/GrafoDirigidoCosto.class\
	$(GLIB)/GrafoNoDirigido.class\
	$(GLIB)/GrafoNoDirigidoCosto.class
	jar -cvf $@ $(GLIB)/*.class

# Reglas de compilación para cada archivo .kt
$(GLIB)/Color.class: $(GLIB)/Color.kt
	$(KC) $(GLIB)/Color.kt -d $(GLIB)

$(GLIB)/Lado.class: $(GLIB)/Lado.kt
	$(KC) $(GLIB)/Lado.kt -d $(GLIB)

$(GLIB)/Arco.class: $(GLIB)/Arco.kt $(GLIB)/Lado.class  # Dependencia de Lado.class
	$(KC) $(KFLAG) $(GLIB);$(KOTLIN_LIB) $(GLIB)/Arco.kt -d $(GLIB)

$(GLIB)/ArcoCosto.class: $(GLIB)/ArcoCosto.kt $(GLIB)/Arco.class  # Dependencia de Arco.class
	$(KC) $(KFLAG) $(GLIB);$(KOTLIN_LIB) $(GLIB)/ArcoCosto.kt -d $(GLIB)

$(GLIB)/Arista.class: $(GLIB)/Arista.kt
	$(KC) $(KFLAG) $(GLIB);$(KOTLIN_LIB) $(GLIB)/Arista.kt -d $(GLIB)

$(GLIB)/AristaCosto.class: $(GLIB)/AristaCosto.kt $(GLIB)/Arista.class  # Dependencia de Arista.class
	$(KC) $(KFLAG) $(GLIB);$(KOTLIN_LIB) $(GLIB)/AristaCosto.kt -d $(GLIB)

$(GLIB)/Grafo.class: $(GLIB)/Grafo.kt
	$(KC) $(KFLAG) $(GLIB);$(KOTLIN_LIB) $(GLIB)/Grafo.kt -d $(GLIB)

$(GLIB)/GrafoDirigido.class: $(GLIB)/GrafoDirigido.kt $(GLIB)/Grafo.class  # Dependencia de Grafo.class
	$(KC) $(KFLAG) $(GLIB);$(KOTLIN_LIB) $(GLIB)/GrafoDirigido.kt -d $(GLIB)

$(GLIB)/GrafoDirigidoCosto.class: $(GLIB)/GrafoDirigidoCosto.kt $(GLIB)/GrafoDirigido.class  # Dependencia de GrafoDirigido.class
	$(KC) $(KFLAG) $(GLIB);$(KOTLIN_LIB) $(GLIB)/GrafoDirigidoCosto.kt -d $(GLIB)

$(GLIB)/GrafoNoDirigido.class: $(GLIB)/GrafoNoDirigido.kt $(GLIB)/Grafo.class  # Dependencia de Grafo.class
	$(KC) $(KFLAG) $(GLIB);$(KOTLIN_LIB) $(GLIB)/GrafoNoDirigido.kt -d $(GLIB)

$(GLIB)/GrafoNoDirigidoCosto.class: $(GLIB)/GrafoNoDirigidoCosto.kt $(GLIB)/GrafoNoDirigido.class
	$(KC) $(KFLAG) $(GLIB);$(KOTLIN_LIB) $(GLIB)/GrafoNoDirigidoCosto.kt -d $(GLIB)

$(GLIB)/Main.class: $(GLIB)/Main.kt $(LIBGRAPH)  # Dependencia del JAR
	$(KC) $(KFLAG) $(LIBGRAPH);$(KOTLIN_LIB) $(GLIB)/Main.kt -d $(GLIB)

clean:
	rm -rf $(LIBGRAPH) $(GLIB)/*.class