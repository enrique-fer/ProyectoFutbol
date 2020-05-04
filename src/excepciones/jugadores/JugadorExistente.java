package excepciones.jugadores;

/**
 * @author Enrique Fernandez Santiago
 * @version 1.0
 */
public class JugadorExistente extends Exception {

	private static final long serialVersionUID = 1L;

	public JugadorExistente(String cadena) {
		super(cadena);
	}
}
