package controlador;

public class Controlador {
	//Definir estado a medida que se definan sus m�todos. (30/03)
	
	//Patr�n Singleton para llamar al controller desde Spring.
	private static Controlador instancia;
	private Controlador() { }
	
	public static Controlador getInstancia() {
		if(instancia == null)
			instancia = new Controlador();
		return instancia;
	}

	
}
