package exceptions;

public class AgendaException extends Exception {
	private static final long serialVersionUID = 1655005794608188535L;

	public AgendaException(String mensaje) {
		super(mensaje);
	}
}
