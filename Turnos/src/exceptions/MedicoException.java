package exceptions;

public class MedicoException extends Exception {
	private static final long serialVersionUID = 897298784298062058L;

	public MedicoException(String mensaje) {
		super(mensaje);
	}
}
