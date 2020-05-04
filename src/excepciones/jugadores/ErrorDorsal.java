package excepciones.jugadores;

/**
 * @author Enrique Fernandez Santiago
 * @version 1.0
 */
public class ErrorDorsal extends Exception {

	private static final long serialVersionUID = 1L;

	public ErrorDorsal(String cadena) {
		super(cadena);
	}
}
