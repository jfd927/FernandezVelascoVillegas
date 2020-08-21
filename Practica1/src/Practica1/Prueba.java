package Practica1;

import java.io.File;

// TODO: Auto-generated Javadoc
/**
 * The Class Prueba.
 */
public class Prueba {

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
		Ciudad c = new Ciudad(ruta+"datos01.txt");
		c.verCiudad();
	}

}
