package Practica1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;

// TODO: Auto-generated Javadoc
/**
 * The Class Main.
 */
public class Main {

	/** The ciudad. */
	private static Ciudad ciudad;



	/** The ruta. */
	private static String ruta = System.getProperty("user.dir")+File.separator
			+ "src" + File.separator
			+ "Practica1" + File.separator
			+ "casosPrueba" + File.separator;


	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		inicializarEstructuras();
		do {
			menu();
			int opcionesMenu = leerOpciones("Introduzca una opcion: ", 1, 9);
			ejecutarOpcion(opcionesMenu);

		} while (true);

	}

	/**
	 * Inicializar estructuras.
	 */
	private static void inicializarEstructuras() {
		ciudad = new Ciudad(3, 4, 0);

	}

	/**
	 * Leer opciones.
	 *
	 * @param mensaje the mensaje
	 * @param minimo the minimo
	 * @param maximo the maximo
	 * @return the int
	 */
	private static int leerOpciones(String mensaje, int minimo, int maximo) {
		return leerNumero(mensaje, minimo, maximo);
	}

	/**
	 * Leer numero.
	 *
	 * @param mensaje the mensaje
	 * @param minimo the minimo
	 * @param maximo the maximo
	 * @return the int
	 */
	private static int leerNumero(String mensaje, int minimo, int maximo) {
		Scanner sc = new Scanner(System.in);
		do {
			try {
				System.out.println(mensaje);
				int numero = Integer.parseInt(sc.nextLine());
				if(numero < minimo || numero > maximo) {
					System.out.println("Error, debe ser un numero entre "+minimo+" y "+maximo);
				}else {
					return numero;
				}
			} catch (NumberFormatException e) {
				System.out.println("Error, no es un valor permitido");
			}
		} while (true);
	}

	/**
	 * Ejecutar opcion.
	 *
	 * @param opcion the opcion
	 */
	private static void ejecutarOpcion(int opcion) {
		switch (opcion) {
		case 1:
			cargarArchivo();
			break;
		case 2:
			MenuGenerarArchivo();
			break;
		case 3:
			verDatosCiudad();
			break;
		case 4:
			ejecutarAlgoritmos();
			break;
		case 5:
			tiemposEjecucion();
			break;
		case 6:
			progDinWTT();
			break;
		case 7:
			progDinMI();
			break;
		case 8:
			ejecutarTiempos2();
			break;
		case 9:
			System.exit(0);
			break;

		}

	}


	/**
	 * Menu.
	 */
	private static void menu() {
		System.out.println("------------------------------------------------------------------");
		System.out.println("------------------------------MENÚ--------------------------------");
		System.out.println("------------------------------------------------------------------");
		System.out.println("1. Cargar archivo de ciudad ya creado");
		System.out.println("2. Generar archivo de ciudad");
		System.out.println("3. Ver datos de la ciudad a partir de un archivo.");
		System.out.println("4. Ejecutar todos los algoritmos para un archivo de ciudad.");
		System.out.println("5. Visualizar tiempos de ejecución de los algoritmos.");
		System.out.println("6. Algoritmo de programación dinámica para WTT");
		System.out.println("7. Algoritmo de programación dinámica para MI");
		System.out.println("8. Visualizar tiempos de ejecución de los algoritmos de prog. dinámica.");
		System.out.println("------------------------------------------------------------------");
		System.out.println("9. Salir");
		System.out.println("------------------------------------------------------------------\n");


	}

	/**
	 * Cargar archivo.
	 */
	private static void cargarArchivo() {
		String archivo = menuCargarArchivo();
		try {
			ciudad.cargarArchivo(archivo);
		} catch (FileNotFoundException e) {
			System.out.println("No es posible cargar el archivo");
		}
		System.out.println("\nEl archivo ha sido cargado correctamente.\n");
	}

	/**
	 * Ver datos ciudad.
	 */
	private static void verDatosCiudad() {
		String archivo = menuCargarArchivo();
		try {
			ciudad.cargarArchivo(archivo);
		} catch (FileNotFoundException e) {
			System.out.println("No es posible cargar el archivo");
		}
		System.out.println("\nA continuación una representación de los datos del archivo:\n");
		ciudad.verCiudad();
	}



	/**
	 * Menu generar archivo.
	 */
	private static void MenuGenerarArchivo() {

		Scanner sc = new Scanner(System.in);
		System.out.println("Introduzca el nombre del archivo: ");
		String nombre = sc.nextLine();
		System.out.println("Introduzca el número de calles: ");
		String nCalles = sc.nextLine();
		System.out.println("Introduzca el número de avenidas: ");
		String nAvenidas = sc.nextLine();
		int numeroCalles = 0;
		int numeroAvenidas = 0;
		try {
			numeroCalles = Integer.parseInt(nCalles);
			numeroAvenidas = Integer.parseInt(nAvenidas);
		} catch (NumberFormatException e) {
			System.out.println("Error, no es un valor permitido.");
		}
		String nombreCompleto = nombre + ".txt";
		///Preguntar al usuario si quiere introducir fallos
		System.out.println("¿Quiere introducir fallos?");
		System.out.println("1. Si\n2. No");
		String f = sc.nextLine();
		try {
			int fa = Integer.parseInt(f);
			if(fa < 1 || fa > 2) {
				System.out.println("Error, debe ser un numero entre "+ 1 +" y "+ 2);
			}else {
				if(fa == 1) {
					System.out.println("**Ejemplo fallo: P-C1A1; P indica Parcela, D indica distribución, T indica troncal; C indica calle; A indica avenida;");
					System.out.println("Introduzca el fallo: ");
					String fallo = sc.nextLine();
					System.out.println("Introduce el porcentaje de fallo: ");
					String porcentajeFallo = sc.nextLine();
					int pFallo = 0;
					try {
						pFallo = Integer.parseInt(porcentajeFallo);
					} catch (NumberFormatException e) {
						System.out.println("Error, no es un valor permitido.");
					}
					ciudad.iniciarMapaFallos();
					////Introducir fallos por parte del usuario
					ciudad.falloPresion(fallo, pFallo);
					ciudad.generarArchivo(ruta + nombreCompleto, numeroCalles, numeroAvenidas);
				}
				ciudad.falloPresion("", 0);
				ciudad.generarArchivo(ruta + nombreCompleto, numeroCalles, numeroAvenidas);
			}
		} catch (NumberFormatException e) {
			System.out.println("Error, no es un valor permitido");
		}




//		c = new Ciudad(ruta + nombreCompleto);
//		System.out.println(c);

	}

	/**
	 * Ejecutar algoritmos.
	 */
	private static void ejecutarAlgoritmos(){
		String archivo = menuCargarArchivo();
		try {
			ciudad.cargarArchivo(archivo);
		} catch (FileNotFoundException e) {
			System.out.println("No es posible cargar el archivo");
		}
		System.out.println("\nA continuación la solución del DYV de presiones (se indica donde se encuentran las pérdidas):");
		ciudad.DivideYVencerasPresiones();
		System.out.println("\nA continuación la solución del DYV de flujos (se indican consumos superiores):");
		ciudad.DivideYVencerasFlujos();
		System.out.println("\nA continuación la solución del Greedy de presiones (se indica donde se encuentran las pérdidas):");
		ciudad.greedyPresion();
		System.out.println("\nA continuación la solución del Greedy de flujos (se indican consumos superiores al 700%):");
		ciudad.greedyFlujos();
		System.out.println("\nA continuación la solución del Greedy de flujos (se indican consumos superiores al 500%) Alg pérdidas en la red a2/:");
		ciudad.greedyPerdidas();
		System.out.println();
	}

	/**
	 * Tiempos ejecucion.
	 */
	private static void tiemposEjecucion(){
		String archivo = menuCargarArchivo();
		try {
			ciudad.cargarArchivo(archivo);
		} catch (FileNotFoundException e) {
			System.out.println("No es posible cargar el archivo");
		}
		System.out.println("Los resultados se encuentran en la parte inferior del todo.");

		long startDyvPresiones = System.nanoTime();
		for (int i = 0; i < 10; i++) {
			ciudad.DivideYVencerasPresiones();
		}
		long endDyvPresiones = System.nanoTime();

		long startDyvFlujos = System.nanoTime();
		for (int i = 0; i < 10; i++) {
			ciudad.DivideYVencerasFlujos();
		}
		long endDyvFlujos = System.nanoTime();

		long startGreedyPresion = System.nanoTime();
		for (int i = 0; i < 10; i++) {
			ciudad.greedyPresion();
		}
		long endGreedyPresion = System.nanoTime();

		long startGreedyFlujo = System.nanoTime();
		for (int i = 0; i < 10; i++) {
			ciudad.greedyFlujos();
		}
		long endGreedyFlujo = System.nanoTime();

		long startGreedyPerdida = System.nanoTime();
		for (int i = 0; i < 10; i++) {
			ciudad.greedyPerdidas();
		}
		long endGreedyPerdida = System.nanoTime();



		System.out.println("--------------------------------------------------------------------------------");
		System.out.println("-----------------------------------RESULTADOS-----------------------------------");
		System.out.println("--------------------------------------------------------------------------------");
		System.out.println("\nA continuación los tiempos de ejecución del DYV de presiones (nanosegundos):");
		System.out.println((endDyvPresiones-startDyvPresiones)/10);
		System.out.println("\nA continuación los tiempos de ejecución del DYV de flujos (nanosegundos):");
		System.out.println((endDyvFlujos-startDyvFlujos)/10);
		System.out.println("\nA continuación los tiempos de ejecución del Greedy de presiones (nanosegundos):");
		System.out.println((endGreedyPresion-startGreedyPresion)/10);
		System.out.println("\nA continuación los tiempos de ejecución del Greedy de flujos (nanosegundos):");
		System.out.println((endGreedyFlujo-startGreedyFlujo)/10);
		System.out.println("\nA continuación los tiempos de ejecución del Greedy de pérdidas (nanosegundos):");
		System.out.println((endGreedyPerdida-startGreedyPerdida)/10);
		System.out.println("--------------------------------------------------------------------------------");
		System.out.println("--------------------------------------------------------------------------------\n");


	}

	/**
	 * Prog din WTT.
	 */
	private static void progDinWTT(){
		String archivo = menuCargarArchivo();
		ciudad.cargarClientes(archivo);
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduzca un valor de WTT:");
		String WTTSTring = sc.nextLine();
		try {
			int WTT = Integer.parseInt(WTTSTring);
			System.out.println("\n A continuación el resultado de programación dinámica para WTT:");
			ciudad.cargarClientes(archivo);
			ciudad.progDinamicaWTT(WTT);
		} catch (NumberFormatException e) {
			System.out.println("Error, no es un valor permitido.");
		}
	}

	/**
	 * Prog din MI.
	 */
	private static void progDinMI(){
		String archivo = menuCargarArchivo();
		ciudad.cargarClientes(archivo);;
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduzca un valor de MI:");
		String MIString = sc.nextLine();
		try {
			int MI = Integer.parseInt(MIString);
			System.out.println("\n A continuación el resultado de programación dinámica para MI:");
			ciudad.cargarClientes(archivo);
			ciudad.progDinamicaMI(MI);
		} catch (NumberFormatException e) {
			System.out.println("Error, no es un valor permitido.");
		}

	}

	/**
	 * Ejecutar tiempos 2.
	 */
	private static void ejecutarTiempos2() {
		String archivo = menuCargarArchivo();
		ciudad.cargarClientes(archivo);

		Scanner sc = new Scanner(System.in);

		System.out.println("Introduzca un valor de WTT:");
		String WTTString = sc.nextLine();
		System.out.println("Introduzca un valor de MI:");
		String MIString = sc.nextLine();
		try {
			int WTT = Integer.parseInt(WTTString);
			int MI = Integer.parseInt(MIString);

			System.out.println("\n A continuación el resultado de programación dinámica para MI:");

			long startDinWTT = System.nanoTime();
			for (int i = 0; i < 10; i++) {
				ciudad.progDinamicaWTT(WTT);
			}
			long endDinWTT = System.nanoTime();


			System.out.println("\n A continuación el resultado de programación dinámica para MI:");

			long startDinMI = System.nanoTime();
			for (int i = 0; i < 10; i++) {
				ciudad.progDinamicaMI(MI);
			}
			long endDinMI = System.nanoTime();


			System.out.println("--------------------------------------------------------------------------------");
			System.out.println("-----------------------------------RESULTADOS-----------------------------------");
			System.out.println("--------------------------------------------------------------------------------");
			System.out.println("\nA continuación los tiempos de ejecución de prog. dinámica para WTT (nanosegundos):");
			System.out.println((endDinWTT-startDinWTT)/10);
			System.out.println("\nA continuación los tiempos de ejecución de prog. dinámica para MI (nanosegundos):");
			System.out.println((endDinMI-startDinMI)/10);
			System.out.println("--------------------------------------------------------------------------------");
			System.out.println("--------------------------------------------------------------------------------\n");

		} catch (NumberFormatException e) {
			System.out.println("Error, no es un valor permitido.");
		}


		try {

		} catch (NumberFormatException e) {
			System.out.println("Error, no es un valor permitido.");
		}

	}


	/**
	 * Menu cargar archivo.
	 *
	 * @return the string
	 */
	private static String menuCargarArchivo() {
		System.out.println("\nCasos de prueba:");
		File dir = new File (ruta);
		File[] archivos = dir.listFiles();
		for (int i = 0; i < archivos.length; i++) {
			System.out.println((i+1)+"-"+archivos[i].getName());
		}
		int opcionesMenu = leerOpciones("Seleccione un caso de prueba: ", 1, archivos.length) - 1;
		return archivos[opcionesMenu].getAbsolutePath();
	}

}