package controlador;

public class Controlador {
	//Definir estado a medida que se definan sus métodos. (30/03)
	
	//Patrón Singleton para llamar al controller desde Spring.
	private static Controlador instancia;
	private Controlador() { }
	
	public static Controlador getInstancia() {
		if(instancia == null)
			instancia = new Controlador();
		return instancia;
	}

	
}
