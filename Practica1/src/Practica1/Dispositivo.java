package Practica1;

// TODO: Auto-generated Javadoc
/**
 * The Class Dispositivo.
 */
public class Dispositivo {

	/** The nombre. */
	protected String nombre;

	/** The presion. */
	protected double presion;

	/** The flujo. */
	protected double flujo;

	/** The media. */
	protected double media;

	/**
	 * Instantiates a new dispositivo.
	 *
	 * @param nombre the nombre
	 * @param presion the presion
	 */
	public Dispositivo(String nombre, double presion) {
		this.nombre = nombre;
		this.presion = presion;
		this.flujo = 0;
		this.media = 0;
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

	/**
	 * Gets the presion.
	 *
	 * @return the presion
	 */
	public double getPresion() {
		return presion;
	}

	/**
	 * Sets the presion.
	 *
	 * @param presion the new presion
	 */
	public void setPresion(double presion) {
		this.presion = presion;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.nombre+" Presion: "+this.presion+" Flujo: "+this.flujo+" Media: "+this.media;
	}

}
