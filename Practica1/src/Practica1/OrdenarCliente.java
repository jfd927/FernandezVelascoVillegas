package Practica1;

import java.util.Comparator;

public class OrdenarCliente implements Comparator<Cliente> {

	private static boolean tipoPeso;

	public OrdenarCliente(boolean tipoPeso) {
		this.tipoPeso = tipoPeso;
	}

	public int compare(Cliente c1, Cliente c2) {
		return tipoPeso ? Integer.compare(c1.getOp(), c2.getOp()) : Integer.compare(c1.getAt(), c2.getAt());
	}
}
