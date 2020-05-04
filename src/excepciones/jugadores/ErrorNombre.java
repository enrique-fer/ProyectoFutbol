package excepciones.jugadores;

/**
 * @author Enrique Fernandez Santiago
 * @version 1.0
 */
public class ErrorNombre extends Exception {

	private static final long serialVersionUID = 1L;

	public ErrorNombre(String cadena) {
		super(cadena);
	}
}
