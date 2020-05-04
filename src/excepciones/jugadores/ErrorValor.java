package excepciones.jugadores;

/**
 * @author Enrique Fernandez Santiago
 * @version 1.0
 */
public class ErrorValor extends Exception {

	private static final long serialVersionUID = 1L;

	public ErrorValor(String cadena) {
		super(cadena);
	}
}
