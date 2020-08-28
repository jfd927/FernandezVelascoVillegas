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
	private int pMax = 150;

	public static double BA = 60;
	private static List<Cliente> lista;

	/**
	 * Instancia una nueva Ciudad.
	 *
	 * @param el archivo
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
	public int getpMax() {
		return pMax;
	}

	/**
	 * Sets the p max.
	 *
	 * @param pMax the new p max
	 */
	public void setpMax(int pMax) {
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
				t = new Troncal(tokens[0], Double.parseDouble(tokens[1]));
				this.troncal.add(t);
			}else if(linea.startsWith("##D")) {
				d = new Distribucion(tokens[0], Double.parseDouble(tokens[1]));
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

	public void cargarClientes(String archivo) {
		lista = new ArrayList<Cliente>();
		try {
			Scanner sc = new Scanner(new File(archivo));
			while (sc.hasNextLine()) {
				String linea = sc.nextLine().trim();
				if(linea.isEmpty() || linea.startsWith("//")) continue;
				String[] tokens = linea.split(" >-");
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

	public static int indiceAvenida(int j) {
		return (j+1)*2 + (nAvenidas%2 == 0 ? -1 : 0);
	}

	public static int indiceCalle(int i) {
		return nCalles - (i < 0 ? 1 : i);
	}

	public static String indicesANombre(int i, int j) {
		return (i < 0 ? "T" : "D") + "-C" + indiceCalle(i) + "A" + indiceAvenida(j);
	}

	public static double funcionPresion(int x, int b) {
		return -0.05*x + b;
	}

	public static double funcionPresion2(int i, int j) {
		if(i < 0) {
			return funcionPresion(nAvenidas - 1 -j, 150);
		}else {
			return funcionPresion(i+1, (int) troncal.get(i).getPresion());
		}
	}

	public void generarArchivo(String ruta) {

		ArrayList<Troncal> t = new ArrayList<Troncal>();
		Map<String, TreeMap<String, Double>> mapa= new TreeMap<String, TreeMap<String, Double>>();
		for (int j = 0; j < nAvenidas/2; j++) {
			t.add(new Troncal(indicesANombre(-1, j), 0));
		}

		for (int j = t.size() - 1; j >= 0; j--) {
			double pAnterior = funcionPresion2(-1, j+1);
			double caida = 0;
			if(mapa.get("P").containsKey(indicesANombre(-1, j))) {
				caida = mapa.get("P").get(indicesANombre(-1, j))/100;
			}else {
				double pActual = funcionPresion2(-1, j);
				caida = (pAnterior - pActual) / pActual;
			}

			if(j+1 == t.size()) {
				t.get(j).setPresion(funcionPresion(-1, j));
			}else {
				t.get(j).setPresion(pAnterior / (caida + 1));
			}
		}

		for (int j = 0; j < t.size(); j++) {
			for (int i = 0; i < nCalles-3; i++) {
				double caida = 0;
				double pAnterior = 0;
				if(mapa.get("P").containsKey(indicesANombre(i, j))) {
					caida = mapa.get("P").get(indicesANombre(i, j));
				}else {

					if(i == 0) {
						pAnterior = t.get(j).getPresion();
					}else {
						pAnterior = funcionPresion2(i-1, j);
					}
					double pActual = funcionPresion2(i, j);
					caida = (pAnterior - pActual)/ pActual;
				}
				Distribucion d = new Distribucion(indicesANombre(i, j), pAnterior/(caida+1));
				t.get(j).addDistribucion(d);
			}
		}
	}

	//ALGORITMOS

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
	 * @param lista the lista
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
	 * @param d the d
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

	private void analizarConsumidores(int posH, int posV) {
		for(Parcela p : troncal.get(posH).getDistribucion().get(posV).getParcelas()) {
			if(verificarFlujo(p) >= 7.0) {
				System.out.println(p.getNombre() + " - " + p.getFlujo() +" - > " + (verificarFlujo(p)*100));
			}
		}

	}

	private double verificarFlujo(Parcela p) {
		return Math.abs((p.getFlujo() - p.getMedia()) / p.getMedia());
	}

	public void greedyPresion() {
		greedyPresiones(-1, 0, troncal.size() - 1);
		for (int i = 0; i < troncal.size(); i++) {
			greedyPresiones(i, 0, troncal.get(i).getDistribucion().size() - 1);
		}
	}

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

	public void greedyFlujos() {
		for (int i = 0; i < troncal.size(); i++) {
			GreedyFlujo(i);
		}
	}

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

	public void greedyPerdidas() {
		greedyPerdidas(-1);
		for (int i = 0; i < troncal.size(); i++) {
			greedyPerdidas(i);
		}
	}

	private void greedyPerdidas(int pos) {
		int n = pos == -1 ? troncal.size() : troncal.get(pos).getDistribucion().size();
		for (int i = 0; i < n; i++) {
			double parcelas = sumarFlujos(pos, i);
			double siguiente = pos == -1 ?
					(i-1 < 0 ? 0 : troncal.get(i-1).getDistribucion().get(0).getFlujo()):
						(i+1 == troncal.get(pos).getDistribucion().size() ? 0 : troncal.get(pos).getDistribucion().get(i+1).getFlujo());
			double miFlujo = pos == -1 ? troncal.get(i).getDistribucion().get(0).getFlujo() : troncal.get(pos).getDistribucion().get(i).getFlujo();

			if(variacionFlujo(miFlujo, parcelas, siguiente) >= 5.0) {
				if(pos == -1) {
					System.out.println(troncal.get(i).getDistribucion().get(0).getNombre());
				}else {
					System.out.println(troncal.get(pos).getDistribucion().get(i).getNombre()
							+ " - "+ troncal.get(pos).getDistribucion().get(i+1).getNombre()
							+ " - > " + variacionFlujo(miFlujo, parcelas, siguiente)*100);
				}
			}
		}

	}

	private double variacionFlujo(double miFlujo, double parcelas, double siguiente) {

		return Math.abs((miFlujo - parcelas - siguiente) / siguiente);
	}

	private double sumarFlujos(int posH, int posV) {
		if(posH == -1) return 0;
		double suma = 0;
				for(Parcela p : troncal.get(posH).getDistribucion().get(posV).getParcelas()) {
					suma += p.getFlujo();
				}
		return suma;
	}

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

	private static int mcd(int peso, boolean tipoPeso) {
		int x = mcd(peso, tipoPeso ? lista.get(0).getOp() : lista.get(0).getAt());
		for (int i = 1; i < lista.size() && x != 1; i++) {
			x = mcd(x, tipoPeso ? lista.get(i).getOp() : lista.get(i).getAt());
		}
		return x;
	}

	private static int mcd(int a, int b) {
	    while (b != 0) {
	        int temporal = b;
	        b = a % b;
	        a = temporal;
	    }
	    return a;
	}

	public void progDinamicaMI(int MI) {
		lista.sort(new OrdenarCliente(true));
		int nuevaMochila = sumarPeso(lista) - MI;
		if(nuevaMochila < 0) {
			System.out.println("No hay más soluciones");
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

	private int sumarPeso(List<Cliente> lista) {
		int suma = 0;
		for(Cliente c : lista) {
			suma += c.getOp();
		}
		return suma;
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



}
