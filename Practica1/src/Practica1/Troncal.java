package Practica1;

import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class Troncal.
 */
public class Troncal extends Dispositivo{

	/** The distribucion. */
	private ArrayList<Distribucion> distribucion;

	/**
	 * Instantiates a new troncal.
	 *
	 * @param nombre the nombre
	 * @param presion the presion
	 */
	public Troncal(String nombre, double presion) {
		super(nombre, presion);
		this.distribucion = new ArrayList<Distribucion>();
	}

	/**
	 * Gets the distribucion.
	 *
	 * @return the distribucion
	 */
	public ArrayList<Distribucion> getDistribucion() {
		return distribucion;
	}

	/**
	 * Sets the distribucion.
	 *
	 * @param distribucion the new distribucion
	 */
	public void setDistribucion(ArrayList<Distribucion> distribucion) {
		this.distribucion = distribucion;
	}

	/**
	 * Adds the distribucion.
	 *
	 * @param d the d
	 */
	public void addDistribucion(Distribucion d) {
		this.distribucion.add(d);
	}


//	@Override
//	public String toString() {
//		return "Troncal [idTroncal=" + idTroncal + ", idParcela=" + idParcela + "]";
//	}



}
