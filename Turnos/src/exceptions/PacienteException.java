package exceptions;

public class PacienteException extends Exception {
	private static final long serialVersionUID = 1449660608999559349L;

	public PacienteException(String mensaje) {
		super(mensaje);
	}
}
