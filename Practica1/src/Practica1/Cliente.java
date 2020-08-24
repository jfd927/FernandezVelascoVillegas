package Practica1;

public class Cliente {

	private String nombre;
	private double bc;
	private double ip;
	private double op;

	public Cliente(String nombre, double flujo, double ip, double op) {
		super();
		this.nombre = nombre;
		this.bc = flujo;
		this.ip = ip;
		this.op = Math.round(op);
	}

	public Cliente(String nombre, double flujo, double ip) {
		this(nombre, flujo, ip, Math.sqrt(flujo*flujo + ip*ip));
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getBc() {
		return bc;
	}

	public void setBc(double bc) {
		this.bc = bc;
	}

	public double getIp() {
		return ip;
	}

	public void setIp(double ip) {
		this.ip = ip;
	}

	public int getOp() {
		return (int) op;
	}

	public void setOp(double op) {
		this.op = op;
	}

	public int getAt() {
		return  (int) (Main.BA + 5*bc/1000000000 + 12*((ip/100)-7));

	}

	public String toString() {
		return this.nombre + " AT: " + getAt() + ", OP: " + getOp() + " $";

	}
}
