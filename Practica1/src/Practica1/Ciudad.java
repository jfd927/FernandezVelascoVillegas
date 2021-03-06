package Practica1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

// TODO: Auto-generated Javadoc
/**
 * La Clase Ciudad.
 */
public class Ciudad {

	/** numero avenidas. */
	private static int nAvenidas;

	/** numero calles. */
	private static int nCalles;

	/** El troncal. */
	private static ArrayList<Troncal> troncal;

	/** La presion maxima. */
	private double pMax = 150.0;

	/** The fallos. */
	private static TreeMap<String, TreeMap<String,Double>> fallos = new TreeMap<String, TreeMap<String,Double>>();

	/** The t. */
	private static ArrayList<Troncal> t;

	/** The ba. */
	public static double BA = 60;

	/** The lista. */
	private static List<Cliente> lista;

	/**
	 * Instancia una nueva Ciudad.
	 *
	 * @param archivo the archivo
	 */
	public Ciudad(String archivo) {
		try {
			cargarArchivo(archivo);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Instancia una nueva Ciudad.
	 *
	 * @param nAvenidas numero de avenidas
	 * @param nCalles numero de calles
	 * @param pMax presion maxima
	 */
	public Ciudad(int nAvenidas, int nCalles, int pMax) {
		this.nCalles = nCalles;
		this.nAvenidas = nAvenidas;
		this.pMax = pMax;
		this.troncal = new ArrayList<Troncal>();
	}

	/**
	 * Gets numero de avenidas.
	 *
	 * @return numero de avenidas
	 */
	public int getnAvenidas() {
		return nAvenidas;
	}

	/**
	 * Sets numero de avenidas.
	 *
	 * @param nAvenidas  new numero avenidas
	 */
	public void setnAvenidas(int nAvenidas) {
		this.nAvenidas = nAvenidas;
	}

	/**
	 * Gets numero de calles.
	 *
	 * @return numero de calles
	 */
	public int getnCalles() {
		return nCalles;
	}

	/**
	 * Sets numero calles.
	 *
	 * @param nCalles the new numero de calles
	 */
	public void setnCalles(int nCalles) {
		this.nCalles = nCalles;
	}

	/**
	 * Gets the troncal.
	 *
	 * @return the troncal
	 */
	public ArrayList<Troncal> getTroncal() {
		return troncal;
	}

	/**
	 * Sets the troncal.
	 *
	 * @param troncal the new troncal
	 */
	public void setTroncal(ArrayList<Troncal> troncal) {
		this.troncal = troncal;
	}

	/**
	 * Gets the p max.
	 *
	 * @return the p max
	 */
	public double getpMax() {
		return pMax;
	}

	/**
	 * Sets the p max.
	 *
	 * @param pMax the new p max
	 */
	public void setpMax(double pMax) {
		this.pMax = pMax;
	}

	//	@Override
	//	public String toString() {
	//		return "Ciudad [manometro=" + manometro + ", contador=" + contador + ", ciudad=" + Arrays.toString(ciudad)
	//				+ ", nCalle=" + nCalle + ", nAvenida=" + nAvenida + ", pMax=" + pMax + ", pMin=" + pMin + "]";
	//	}

	/**
	 * Inicializar.
	 *
	 * @param nCalle the n calle
	 * @param nAvenida the n avenida
	 */
	public void inicializar(int nCalle, int nAvenida) {
		ArrayList<Troncal> troncales = new ArrayList<Troncal>();
		for (int i = 0; i < nAvenida/2; i++) {
			Troncal t = new Troncal(troncales.get(i).getNombre(), troncales.get(i).getPresion());
			for (int j = 0; j < nCalle-3; j++) {
				Distribucion d = new Distribucion(troncales.get(i).getDistribucion().get(i).getNombre(), troncales.get(i).getDistribucion().get(i).getPresion());
			}
		}
	}

	/**
	 * Cargar archivo.
	 *
	 * @param archivo the archivo
	 * @throws FileNotFoundException the file not found exception
	 */
	public void cargarArchivo(String archivo) throws FileNotFoundException {
		Scanner sc = new Scanner(new File(archivo));
		String linea = "";
		String[] tokens = null;
		this.troncal = new ArrayList<Troncal>();
		Troncal t = null;
		Distribucion d = null;
		while (sc.hasNextLine()) {
			linea = sc.nextLine().trim();
			if(linea.isEmpty() || linea.startsWith("%")) continue;
			if(linea.startsWith("@Calles")) {
				tokens = linea.split(": ");
				this.nCalles = Integer.parseInt(tokens[1]);
				continue;
			}
			if(linea.startsWith("@Avenidas")) {
				tokens = linea.split(": ");
				this.nAvenidas = Integer.parseInt(tokens[1]);
				continue;
			}
			tokens = linea.replace("#", "").split(" ");
			if(linea.startsWith("#T")) {
				t = new Troncal(tokens[0], Double.parseDouble(tokens[1]), Double.parseDouble(tokens[2]), Double.parseDouble(tokens[3]));
				this.troncal.add(0,t);
			}else if(linea.startsWith("##D")) {
				d = new Distribucion(tokens[0], Double.parseDouble(tokens[1]), Double.parseDouble(tokens[2]), Double.parseDouble(tokens[3]));
				t.addDistribucion(d);
			}else {
				Parcela p = new Parcela(tokens[0], Double.parseDouble(tokens[1]), Double.parseDouble(tokens[2]));
				d.addParcela(p);
			}
		}

		//Suma de acumulado de flujos
		for (int i = this.troncal.size()-1; i >= 0; i--) {
			ArrayList<Distribucion> aux = troncal.get(i).getDistribucion();
			for (int j = aux.size()-2; j >= 0; j--) {
				aux.get(j).setFlujo(aux.get(j).getFlujo() + aux.get(j+1).getFlujo());
				aux.get(j).setMedia(aux.get(j).getMedia() + aux.get(j+1).getMedia());
			}
			if(i == this.troncal.size()-1) {
				this.troncal.get(i).setFlujo(aux.get(0).getFlujo());
				this.troncal.get(i).setMedia(aux.get(0).getMedia());
			}else {
				this.troncal.get(i).setFlujo(aux.get(0).getFlujo() + this.troncal.get(i+1).getFlujo());
				this.troncal.get(i).setMedia(aux.get(0).getMedia() + this.troncal.get(i+1).getMedia());
			}

		}

	}

	/**
	 * Cargar clientes.
	 *
	 * @param archivo the archivo
	 */
	public void cargarClientes(String archivo) {
		lista = new ArrayList<Cliente>();
		try {
			Scanner sc = new Scanner(new File(archivo));
			while (sc.hasNextLine()) {
				String linea = sc.nextLine().trim();
				if(linea.isEmpty() || linea.startsWith("//")) continue;
				String[] tokens = linea.split(" - ");
				String nombre = tokens[0];
				double flujo = Double.parseDouble(tokens[1]);
				double ip = Double.parseDouble(tokens[2]);
				Cliente c = new Cliente(nombre, flujo, ip);
				lista.add(c);
			}
		} catch (FileNotFoundException e) {
			System.out.println("Error en la carga del archivo");
		}
	}

	/**
	 * Indice avenida.
	 *
	 * @param j the j
	 * @return the int
	 */
	public static int indiceAvenida(int j) {
		return (j+1)*2 + (nAvenidas%2 == 0 ? -1 : 0);
	}

	/**
	 * Indice calle.
	 *
	 * @param i the i
	 * @return the int
	 */
	public static int indiceCalle(int i) {
		return nCalles - (i < 0 ? 1 : i+1);
	}

	/**
	 * Indices A nombre.
	 *
	 * @param i the i
	 * @param j the j
	 * @return the string
	 */
	public static String indicesANombre(int i, int j) {
		return (i < 0 ? "T" : "D") + "-C" + indiceCalle(i) + "A" + indiceAvenida(j);
	}

	/**
	 * Funcion presion.
	 *
	 * @param x the x
	 * @param b the b
	 * @return the double
	 */
	public static double funcionPresion(int x, int b) {
		return -0.05*x + b;
	}

	/**
	 * Funcion presion 2.
	 *
	 * @param i the i
	 * @param j the j
	 * @return the double
	 */
	public static double funcionPresion2(int i, int j) {
		if(i < 0) {
			return funcionPresion(nAvenidas - 1 -j, 150);
		}else {
			return funcionPresion(i+1, (int) t.get(j).getPresion());
		}
	}

	/**
	 * Iniciar mapa fallos.
	 */
	public void iniciarMapaFallos() {
		fallos = new TreeMap<String, TreeMap<String,Double>>();
		fallos.put("P", new TreeMap<String, Double>());
		fallos.put("F", new TreeMap<String, Double>());
		fallos.put("R", new TreeMap<String, Double>());
	}

	/**
	 * Fallo presion.
	 *
	 * @param nombre the nombre
	 * @param porcentaje the porcentaje
	 */
	public void falloPresion(String nombre, double porcentaje) {
		if(porcentaje >= 0) {
			fallos.get("P").put(nombre.toUpperCase(), porcentaje);
		}
	}

	/**
	 * Fallo flujo.
	 *
	 * @param nombre the nombre
	 * @param porcentaje the porcentaje
	 */
	public void falloFlujo(String nombre, double porcentaje) {
		if(porcentaje >= 0) {
			fallos.get("Flujo").put(nombre.toUpperCase(), porcentaje);
		}
	}

	/**
	 * Fallo perdida.
	 *
	 * @param nombre the nombre
	 * @param porcentaje the porcentaje
	 */
	public void falloPerdida(String nombre, double porcentaje) {
		if(porcentaje >= 0) {
			fallos.get("Perdidas").put(nombre.toUpperCase(), porcentaje);
		}
	}

	/**
	 * Generar archivo.
	 *
	 * @param ruta the ruta
	 * @param nCalle the n calle
	 * @param nAvenida the n avenida
	 */
	public void generarArchivo(String ruta, int nCalle, int nAvenida) {
		nCalles = nCalle;
		nAvenidas = nAvenida;
		pMax = 150.0;

		double pAnterior= pMax;
		double pPrevio = pMax;
		double pActual = pMax;
		double caida = 0;
		String nombre = "";
		//Crea la linea troncal
		t = new ArrayList<Troncal>();
		for (int j = 0; j < nAvenidas/2; j++) {
			t.add(new Troncal(indicesANombre(-1, j), 0));
		}

		//Rellena la linea troncal de presiones
		for (int j = t.size() - 1; j >= 0; j--) {
			pAnterior = funcionPresion2(-1, j+1);
			pActual = funcionPresion2(-1, j);
			pPrevio = j == nAvenidas/2-1 ? pActual : t.get(j+1).getPresion();
			caida = 0;
			nombre = indicesANombre(-1, j);
			if(fallos.get("P").containsKey(nombre)) {
				caida = fallos.get("P").get(nombre)/100;
			}else {
				caida = (pAnterior - pActual) / pActual;
			}

			t.get(j).setPresion(pPrevio / (caida + 1));
		}

		//Rellena las lineas de distribucion de presiones
		for (int j = 0; j < t.size(); j++) {
			for (int i = 0; i < nCalles-3; i++) {
				caida = 0;
				pAnterior = funcionPresion2(i-1, j);
				pActual = funcionPresion2(i, j);
				pPrevio = i == 0 ? t.get(j).getPresion() : t.get(j).getDistribucion().get(i-1).getPresion();
				nombre = indicesANombre(i, j);
				if(fallos.get("P").containsKey(nombre)) {
					caida = fallos.get("P").get(nombre);
				}else {
					caida = (pAnterior - pActual)/ pActual;
				}
				Distribucion d = new Distribucion(nombre, pPrevio/(caida+1));
				t.get(j).addDistribucion(d);
				//Agregado condicional de parcelas
				d.addParcela(generarParcela((indiceCalle(i)-1), indiceAvenida(j), fallos));
				if(j!=0 || nAvenidas%2==1) {
					d.addParcela(generarParcela((indiceCalle(i)-1), indiceAvenida(j)-1, fallos));
				}
				if(i == nCalles-4) {
					d.addParcela(generarParcela((indiceCalle(i)-2), indiceAvenida(j), fallos));
					if(j!=0 || nAvenidas%2==1) {
						d.addParcela(generarParcela((indiceCalle(i)-2), indiceAvenida(j)-1, fallos));
					}
				}
				if(i==0 && j!=t.size()-1) {
					d.addParcela(generarParcela((indiceCalle(i)), indiceAvenida(j), fallos));
				}

				if(i==0 && (j!=0 || nAvenidas%2==1)) {
					d.addParcela(generarParcela((indiceCalle(i)), indiceAvenida(j)-1, fallos));
				}

			}
		}

		//Sumar flujos de las lineas de distribucion
		for (int j = 0; j < t.size(); j++) {
			for (int i = t.get(j).getDistribucion().size()-2; i >= 0; i--) {
				t.get(j).getDistribucion().get(i).sumarFlujo(t.get(j).getDistribucion().get(i+1), fallos);
			}
			t.get(j).sumarFlujo(t.get(j).getDistribucion().get(0), fallos);
		}

		//Sumar flujos de la linea troncal
		for (int j = 1; j < t.size(); j++) {
			t.get(j).sumarFlujo(t.get(j-1), fallos);
		}

		//Guardado en archivo
		try {
			PrintWriter pw = new PrintWriter(new File(ruta));
			pw.println("%Linea de comentarios.");
			pw.println("@Calles: "+nCalles);
			pw.println("@Avenidas: "+nAvenidas);
			pw.println();
			for (int j = t.size()-1; j >= 0; j--) {
				Troncal tr = t.get(j);
				pw.println("#"+tr.getNombre()+" "+tr.getPresion()+" "+tr.getFlujo()+" "+tr.getMedia());
				ArrayList<Distribucion> dr = tr.getDistribucion();
				for (Distribucion d : dr) {
					pw.println("##"+d.getNombre()+" "+d.getPresion()+" "+d.getFlujo()+" "+d.getMedia());
					ArrayList<Parcela> pr = d.getParcelas();
					for (Parcela p : pr) {
						pw.println("###"+p.getNombre()+" "+p.getFlujo()+" "+p.getMedia());
					}
				}
			}

			pw.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Generar parcela.
	 *
	 * @param calle the calle
	 * @param avenida the avenida
	 * @param fallos the fallos
	 * @return the parcela
	 */
	private Parcela generarParcela(int calle, int avenida, Map<String, TreeMap<String, Double>> fallos) {
		String nombre = "P-C"+calle+"A"+avenida;
		double media = 100+Math.random()*3000;
		double flujo = 0;
		if(fallos.get("F").containsKey(nombre)){
			flujo = (1+fallos.get("F").get(nombre)/100.0) * media;
		}else {
			flujo = (1+ (Math.random()>0.5 ? 1 : -1)*(Math.random()*50/100.0)) * media;
		}
		Parcela p = new Parcela(nombre, flujo, media);
		return p;
	}

	/**
	 * Ver ciudad.
	 */
	public void verCiudad() {
		for (int i = 0; i < this.troncal.size(); i++) {
			System.out.println(troncal.get(i));
			for (Distribucion d : troncal.get(i).getDistribucion()) {
				System.out.println(d);
				for(Parcela p : d.getParcelas()) {
				System.out.println(p);

				}
			}
		}
	}

	//ALGORITMOS----------------------------------------------

	/**
	 * Divide Y venceras presiones.
	 */
	public void DivideYVencerasPresiones() {
		DYVPresiones(-1, 0, troncal.size() - 1);
		for (int i = 0; i < this.troncal.size(); i++) {
			DYVPresiones(i, 0, troncal.get(i).getDistribucion().size() - 1);
		}
	}


	/**
	 * Divide Y venceras presiones Troncal.
	 *
	 * @param pos the pos
	 * @param inicio the inicio
	 * @param fin the fin
	 */

	private void DYVPresiones(int pos, int inicio, int fin) {
		int inicioH = pos == -1 ? inicio : pos;
		int inicioV = pos == -1 ? 0 : inicio;
		int finH = pos == -1 ? fin : pos;
		int finV = pos == -1 ? 0 : fin;
		if(fin - inicio == 1) {
			System.out.println(troncal.get(inicioH).getDistribucion().get(inicioV).getNombre() + " - " + troncal.get(finH).getDistribucion().get(finV).getNombre()
					+ " - > " + verificarPresion(troncal.get(inicioH).getDistribucion().get(inicioV), troncal.get(finH).getDistribucion().get(finV))*100);
		}else {
			int mitad = (inicio + fin) / 2;
			int mitadH = pos == -1 ? mitad : pos;
			int mitadV = pos == -1 ? 0 : mitad;
			if(verificarPresion(troncal.get(inicioH).getDistribucion().get(inicioV), troncal.get(mitadH).getDistribucion().get(mitadV)) >= 0.1) {
				DYVPresiones(pos, inicio, mitad);
			}
			if(verificarPresion(troncal.get(mitadH).getDistribucion().get(mitadV), troncal.get(finH).getDistribucion().get(finV)) >= 0.1) {
				DYVPresiones(pos, mitad, fin);
			}
		}
	}


		/**
		 * Verificar presion.
		 *
		 * @param d1 the d 1
		 * @param d2 the d 2
		 * @return the double
		 */
		private double verificarPresion(Dispositivo d1, Dispositivo d2) {
			return Math.abs((d1.getPresion() - d2.getPresion())/d2.getPresion());

		}


	/**
	 * Divide Y venceras flujos.
	 */
	public void DivideYVencerasFlujos() {

		for (int i = 0; i < this.troncal.size(); i++) {
			DivideYVencerasFlujos(i, 1, troncal.get(i).getDistribucion().size() - 1);
		}
	}

	/**
	 * Divide Y venceras flujos.
	 *
	 * @param pos the pos
	 * @param inicio the inicio
	 * @param fin the fin
	 */
	private void DivideYVencerasFlujos(int pos, int inicio, int fin) {


		if (inicio == fin) {
			analizarConsumidores(pos, inicio);

		}else {
			int mitad = (inicio + fin) / 2;
			DivideYVencerasFlujos(pos, inicio, mitad);
			DivideYVencerasFlujos(pos, mitad + 1, fin);
		}

	}

	/**
	 * Analizar consumidores.
	 *
	 * @param posH the pos H
	 * @param posV the pos V
	 */
	private void analizarConsumidores(int posH, int posV) {
		for(Parcela p : troncal.get(posH).getDistribucion().get(posV).getParcelas()) {
			if(verificarFlujo(p) >= 7.0) {
				System.out.println(p.getNombre() + " - " + p.getFlujo() +" - > " + (verificarFlujo(p)*100));
			}
		}

	}

	/**
	 * Verificar flujo.
	 *
	 * @param p the p
	 * @return the double
	 */
	private double verificarFlujo(Parcela p) {
		return Math.abs((p.getFlujo() - p.getMedia()) / p.getMedia());
	}

	/**
	 * Greedy presion.
	 */
	public void greedyPresion() {
		greedyPresiones(-1, 0, troncal.size() - 1);
		for (int i = 0; i < troncal.size(); i++) {
			greedyPresiones(i, 0, troncal.get(i).getDistribucion().size() - 1);
		}
	}

	/**
	 * Greedy presiones.
	 *
	 * @param pos the pos
	 * @param inicio the inicio
	 * @param fin the fin
	 */
	private void greedyPresiones(int pos, int inicio, int fin) {
		int x = pos == -1 ? 0 : troncal.get(pos).getDistribucion().size() - 1;
		int inicioH = pos == -1 ? troncal.size() - 1 : pos;
		int inicioV = 0;
		int finH = pos == -1 ? x : pos;
		int finV = pos == -1 ? 0 : x;

		Dispositivo d1 = troncal.get(inicioH).getDistribucion().get(inicioV);
		Dispositivo d2 = troncal.get(finH).getDistribucion().get(finV);
		Dispositivo d3;

		while(verificarPresion(d1, d2) >= 0.1) {
			int prevH = pos == -1 ? x+1 : pos;
			int prevV = pos == -1 ? 0 : x-1;
			d3 = troncal.get(prevH).getDistribucion().get(prevV);
			if(verificarPresion(d3, d2) >= 0.1) {
				System.out.println(d3.getNombre() + " - " + d2.getNombre() + " - > "
						+ verificarPresion(d3, d2)*100);
			}
			d2 = d3;
			x = pos == -1 ? x+1 : x-1;
		}
	}

	/**
	 * Greedy flujos.
	 */
	public void greedyFlujos() {
		for (int i = 0; i < troncal.size(); i++) {
			GreedyFlujo(i);
		}
	}

	/**
	 * Greedy flujo.
	 *
	 * @param pos the pos
	 */
	private void GreedyFlujo(int pos) {
		LinkedList<Parcela> aux = new LinkedList<Parcela>();
		for (int i = 0; i < troncal.get(pos).getDistribucion().size(); i++) {
			aux.addAll(troncal.get(pos).getDistribucion().get(i).getParcelas());
		}

		aux.sort(null);

		for (Parcela p : aux) {
			if(verificarFlujo(p) >= 7) {
				System.out.println(p.getNombre() + " - " + p.getFlujo() + " - > " + (verificarFlujo(p)*100));
			}else {
				break;
			}

		}
	}

	/**
	 * Greedy perdidas.
	 */
	public void greedyPerdidas() {
		greedyPerdidas(-1);
		for (int i = 0; i < troncal.size(); i++) {
			greedyPerdidas(i);
		}
	}

	/**
	 * Greedy perdidas.
	 *
	 * @param pos the pos
	 */
	private void greedyPerdidas(int pos) {
		int n = pos == -1 ? troncal.size() : troncal.get(pos).getDistribucion().size() ;
		for (int i = 0; i < n; i++) {
			double parcelas = sumarFlujos(pos, i);
			double siguiente = pos == -1 ?
					 (i-1 < 0 ? 0 : troncal.get(i-1).getDistribucion().get(0).getFlujo()) :
						(i+1 == troncal.get(pos).getDistribucion().size() ? 0 : troncal.get(pos).getDistribucion().get(i+1).getFlujo());
			double miFlujo = pos == -1 ? troncal.get(i).getDistribucion().get(0).getFlujo() : troncal.get(pos).getDistribucion().get(i).getFlujo();

			if(variacionFlujo(miFlujo, parcelas, siguiente) >= 5.0) {
				if(pos == -1) {
					System.out.println(troncal.get(i).getDistribucion().get(0).getNombre() + " - " + (i-1 < 0 ? "" : troncal.get(i-1).getDistribucion().get(0))
							+ " � " + troncal.get(i).getDistribucion().get(0).getNombre() + " - " + troncal.get(i).getDistribucion().get(1).getNombre());
				}else {
					System.out.println(troncal.get(pos).getDistribucion().get(i).getNombre() + " - " + (i+1 == troncal.get(pos).getDistribucion().size() ? "	" : troncal.get(pos).getDistribucion().get(i+1).getNombre()));

				}
			}
		}

	}

	/**
	 * Variacion flujo.
	 *
	 * @param miFlujo the mi flujo
	 * @param parcelas the parcelas
	 * @param siguiente the siguiente
	 * @return the double
	 */
	private double variacionFlujo(double miFlujo, double parcelas, double siguiente) {

		return Math.abs((miFlujo - parcelas - siguiente) / siguiente);
	}

	/**
	 * Sumar flujos.
	 *
	 * @param posH the pos H
	 * @param posV the pos V
	 * @return the double
	 */
	private double sumarFlujos(int posH, int posV) {
		if(posH == -1) return 0;
		double suma = 0;
				for(Parcela p : troncal.get(posH).getDistribucion().get(posV).getParcelas()) {
					suma += p.getFlujo();
				}
		return suma;
	}

	/**
	 * Prog dinamica WTT.
	 *
	 * @param WTT the wtt
	 */
	public void progDinamicaWTT(int WTT) {
		lista.sort(new OrdenarCliente(false));
		int mcd = mcd(WTT, false);
		int[][] tabla = new int[lista.size()+1][WTT/mcd+1];
		for (int i = 1; i < tabla.length; i++) {
			for (int j = 1; j < tabla[0].length && j < lista.get(i-1).getAt()/mcd; j++) {
				tabla[i][j] = tabla[i-1][j];
			}
			for (int j = lista.get(i-1).getAt()/mcd; j < tabla[0].length; j++) {
				tabla[i][j] = Math.max(tabla[i-1][j], lista.get(i-1).getOp()+tabla[i-1][j-lista.get(i-1).getAt()/mcd]);
			}
		}

		int i = tabla.length-1;
		int j = tabla[0].length-1;
		while (i!=0 && j!=0) {
			if(tabla[i][j] != tabla[i-1][j]) {
				System.out.println(lista.get(i-1));
				j -= lista.get(i-1).getAt()/mcd;
			}
			i--;
		}
	}

	/**
	 * Mcd.
	 *
	 * @param peso the peso
	 * @param tipoPeso the tipo peso
	 * @return the int
	 */
	private static int mcd(int peso, boolean tipoPeso) {
		int x = mcd(peso, tipoPeso ? lista.get(0).getOp() : lista.get(0).getAt());
		for (int i = 1; i < lista.size() && x != 1; i++) {
			x = mcd(x, tipoPeso ? lista.get(i).getOp() : lista.get(i).getAt());
		}
		return x;
	}

	/**
	 * Mcd.
	 *
	 * @param a the a
	 * @param b the b
	 * @return the int
	 */
	private static int mcd(int a, int b) {
	    while (b != 0) {
	        int temporal = b;
	        b = a % b;
	        a = temporal;
	    }
	    return a;
	}

	/**
	 * Prog dinamica MI.
	 *
	 * @param MI the mi
	 */
	public void progDinamicaMI(int MI) {
		lista.sort(new OrdenarCliente(true));
		int nuevaMochila = sumarPeso(lista) - MI;
		if(nuevaMochila < 0) {
			System.out.println("No hay m�s soluciones");
			return;
		}
		int mcd = mcd(nuevaMochila, true);
		int[][] tabla = new int[lista.size()+1][nuevaMochila/mcd+1];

		for(int i = 1; i < tabla.length; i++) {
			for(int j = 1; j < tabla[0].length && j < lista.get(i-1).getAt()/mcd; j++) {
				tabla[i][j] = tabla[i-1][j];
			}
			for(int j = lista.get(i-1).getOp()/mcd; j < tabla[0].length; j++) {
				tabla[i][j] = Math.max(tabla[i-1][j], lista.get(i-1).getAt()+tabla[i-1][j-lista.get(i-1).getOp()/mcd]);
			}
		}

		int i = tabla.length-1;
		int j = tabla[0].length-1;
		while(i!= 0 && j!=0) {
			if(tabla[i][j] != tabla[i-1][j]) {
				j -= lista.get(i-1).getOp()/mcd;
			}else {
				System.out.println(lista.get(i-1));
			}
			i--;
		}
	}

	/**
	 * Sumar peso.
	 *
	 * @param lista the lista
	 * @return the int
	 */
	private int sumarPeso(List<Cliente> lista) {
		int suma = 0;
		for(Cliente c : lista) {
			suma += c.getOp();
		}
		return suma;
	}







}