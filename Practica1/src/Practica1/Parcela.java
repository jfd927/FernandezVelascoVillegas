package Practica1;

// TODO: Auto-generated Javadoc
/**
 * The Class Parcela.
 */
public class Parcela implements Comparable<Parcela>{

	/** The flujo. */
	private double flujo;

	/** The media. */
	private double media;

	/** The nombre. */
	private String nombre;

	/**
	 * Instantiates a new parcela.
	 *
	 * @param nombre the nombre
	 * @param flujo the flujo
	 * @param media the media
	 */
	public Parcela(String nombre, double flujo, double media) {
		this.flujo = flujo;
		this.media = media;
		this.nombre = nombre;
	}

	/**
	 * Gets the flujo.
	 *
	 * @return the flujo
	 */
	public double getFlujo() {
		return flujo;
	}

	/**
	 * Sets the flujo.
	 *
	 * @param flujo the new flujo
	 */
	public void setFlujo(double flujo) {
		this.flujo = flujo;
	}

	/**
	 * Gets the media.
	 *
	 * @return the media
	 */
	public double getMedia() {
		return media;
	}

	/**
	 * Sets the media.
	 *
	 * @param media the new media
	 */
	public void setMedia(double media) {
		this.media = media;
	}

	/**
	 * Gets the nombre.
	 *
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Sets the nombre.
	 *
	 * @param nombre the new nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.nombre+" Flujo: "+this.flujo+", Media: "+this.media;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Parcela o) {
		double var1 = (this.flujo - this.media) / this.media;
		double var2 = (o.flujo - this.media) / o.media;
		return - Double.compare(var1, var2);
	}




}