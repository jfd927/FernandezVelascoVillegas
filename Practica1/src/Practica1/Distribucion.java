package Practica1;

import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class Distribucion.
 */
public class Distribucion extends Dispositivo{

	/** The parcelas. */
	private ArrayList<Parcela> parcelas;

	/**
	 * Instantiates a new distribucion.
	 *
	 * @param nombre the nombre
	 * @param presion the presion
	 */
	public Distribucion(String nombre, double presion) {
		super(nombre, presion);
		this.parcelas = new ArrayList<Parcela>();
	}

	/**
	 * Gets the parcelas.
	 *
	 * @return the parcelas
	 */
	public ArrayList<Parcela> getParcelas() {
		return parcelas;
	}


	/**
	 * Sets the parcelas.
	 *
	 * @param parcelas the new parcelas
	 */
	public void setParcelas(ArrayList<Parcela> parcelas) {
		this.parcelas = parcelas;
	}

	/**
	 * Adds the parcela.
	 *
	 * @param p the p
	 */
	public void addParcela(Parcela p) {
		this.parcelas.add(p);
		this.flujo += p.getFlujo();
		this.media += p.getMedia();
	}

//	@Override
//	public String toString() {
//		return this.nombre+" Presion: "+this.presion+" Flujo: "+this.flujo+" Media: "+this.media;
//	}



}
