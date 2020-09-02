package Practica1;

import java.util.Comparator;

// TODO: Auto-generated Javadoc
/**
 * The Class OrdenarCliente.
 */
public class OrdenarCliente implements Comparator<Cliente> {

	/** The tipo peso. */
	private static boolean tipoPeso;

	/**
	 * Instantiates a new ordenar cliente.
	 *
	 * @param tipoPeso the tipo peso
	 */
	public OrdenarCliente(boolean tipoPeso) {
		this.tipoPeso = tipoPeso;
	}

	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	public int compare(Cliente c1, Cliente c2) {
		return tipoPeso ? Integer.compare(c1.getOp(), c2.getOp()) : Integer.compare(c1.getAt(), c2.getAt());
	}
}
