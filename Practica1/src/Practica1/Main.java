package Practica1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

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
			int opcionesMenu = leerOpciones("Introduzca una opcion: ", 1, 7);
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
			DyVPresion();
			break;
		case 4:
			DYVFlujos();
			break;
		case 5:
			GreedyPresiones();
			break;
		case 6:
			GreedyFlujos();
			break;
		case 7:
			GreedyPerdidas();
			break;
		}

	}


	/**
	 * Menu.
	 */
	private static void menu() {
		System.out.println("MENU");
		System.out.println("1. Cargar archivo");
		System.out.println("2. Generar ciudad");
		System.out.println("3. DyV para detectar pérdida de presiones");
		System.out.println("4. DyV para detectar consumos superiores al 700%");
		System.out.println("5. Greedy para detectar pérdida de presiones");
		System.out.println("6. Greedy para detectar consumos superiores al 700%");
		System.out.println("7. Greedy para detectar consumos superiores al 500%");

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
		System.out.println("\nA continuación una representación de los datos del archivo:\n");
		ciudad.verCiudad();
	}


	private static void DyVPresion() {
		String archivo = menuCargarArchivo();
		try {
			ciudad.cargarArchivo(archivo);

		} catch (FileNotFoundException e) {
			System.out.println("No es posible cargar el archivo");
		}

		System.out.println("\nA continuación la solución del DYV de presiones (se indica donde se encuentran las pérdidas):\n");
		ciudad.DivideYVencerasPresiones();

	}

	private static void DYVFlujos() {
		String archivo = menuCargarArchivo();
		try {
			ciudad.cargarArchivo(archivo);

		} catch (FileNotFoundException e) {
			System.out.println("No es posible cargar el archivo");
		}
		System.out.println("\nA continuación la solución del DYV de flujos (se indican consumos superiores):\n");
		ciudad.DivideYVencerasFlujos();
	}

	private static void GreedyPresiones(){
		String archivo = menuCargarArchivo();
		try {
			ciudad.cargarArchivo(archivo);

		} catch (FileNotFoundException e) {
			System.out.println("No es posible cargar el archivo");
		}
		System.out.println("\nA continuación la solución del Greedy de presiones (se indica donde se encuentran las pérdidas):\n");
		ciudad.greedyPresion();
	}

	private static void GreedyFlujos() {
		String archivo = menuCargarArchivo();
		try {
			ciudad.cargarArchivo(archivo);

		} catch (FileNotFoundException e) {
			System.out.println("No es posible cargar el archivo");
		}
		System.out.println("\nA continuación la solución del Greedy de flujos (se indican consumos superiores al 700%):\n");
		ciudad.greedyFlujos();

	}

	private static void GreedyPerdidas() {
		String archivo = menuCargarArchivo();
		try {
			ciudad.cargarArchivo(archivo);

		} catch (FileNotFoundException e) {
			System.out.println("No es posible cargar el archivo");
		}
		System.out.println("\nA continuación la solución del Greedy de flujos (se indican consumos superiores al 500%):\n");
		ciudad.greedyPerdidas();

	}



	private static void MenuGenerarArchivo() {

		Scanner sc = new Scanner(System.in);
		System.out.println("Introduzca el nombre del archivo: ");
		String nombre = sc.nextLine();
		System.out.println("Introduzca el número de calles: ");
		int nCalles = Integer.parseInt(sc.nextLine());
		System.out.println("Introduzca el número de avenidas: ");
		int nAvenidas = Integer.parseInt(sc.nextLine());
		String nombreCompleto = nombre + ".txt";
		Ciudad c = new Ciudad(nCalles, nAvenidas, 150);
		c.generarArchivo(ruta + nombreCompleto);
//		c = new Ciudad(ruta + nombreCompleto);
//		System.out.println(c);

	}

	/**
	 * Menu cargar archivo.
	 *
	 * @return the string
	 */
	private static String menuCargarArchivo() {
		File dir = new File (ruta);
		File[] archivos = dir.listFiles();
		for (int i = 0; i < archivos.length; i++) {
			System.out.println((i+1)+"-"+archivos[i].getName());
		}
		int opcionesMenu = leerOpciones("Seleccione un caso de prueba: ", 1, archivos.length) - 1;
		return archivos[opcionesMenu].getAbsolutePath();
	}

}
