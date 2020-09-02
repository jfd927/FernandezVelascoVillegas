package Practica1;

// TODO: Auto-generated Javadoc
/**
 * The Class Cliente.
 */
public class Cliente {

	/** The nombre. */
	private String nombre;

	/** The bc. */
	private double bc;

	/** The ip. */
	private double ip;

	/** The op. */
	private double op;

	/**
	 * Instantiates a new cliente.
	 *
	 * @param nombre the nombre
	 * @param flujo the flujo
	 * @param ip the ip
	 * @param op the op
	 */
	public Cliente(String nombre, double flujo, double ip, double op) {
		super();
		this.nombre = nombre;
		this.bc = flujo;
		this.ip = ip;
		this.op = Math.round(op);
	}

	/**
	 * Instantiates a new cliente.
	 *
	 * @param nombre the nombre
	 * @param flujo the flujo
	 * @param ip the ip
	 */
	public Cliente(String nombre, double flujo, double ip) {
		this(nombre, flujo, ip, Math.sqrt(flujo*flujo + ip*ip));
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
	 * Gets the bc.
	 *
	 * @return the bc
	 */
	public double getBc() {
		return bc;
	}

	/**
	 * Sets the bc.
	 *
	 * @param bc the new bc
	 */
	public void setBc(double bc) {
		this.bc = bc;
	}

	/**
	 * Gets the ip.
	 *
	 * @return the ip
	 */
	public double getIp() {
		return ip;
	}

	/**
	 * Sets the ip.
	 *
	 * @param ip the new ip
	 */
	public void setIp(double ip) {
		this.ip = ip;
	}

	/**
	 * Gets the op.
	 *
	 * @return the op
	 */
	public int getOp() {
		return (int) op;
	}

	/**
	 * Sets the op.
	 *
	 * @param op the new op
	 */
	public void setOp(double op) {
		this.op = op;
	}

	/**
	 * Gets the at.
	 *
	 * @return the at
	 */
	public int getAt() {
		return  (int) (Ciudad.BA + 5*bc/1000000000 + 12*((ip/100)-7));

	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return this.nombre + " AT: " + getAt() + ", OP: " + getOp() + " $";

	}
}
