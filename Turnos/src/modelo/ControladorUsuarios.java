package modelo;
import java.util.*;

//Faltan métodos (16/03)
public class ControladorUsuarios {

	public static ControladorUsuarios instancia = new ControladorUsuarios();

	public ControladorUsuarios() {
		
	}
	
	public static ControladorUsuarios getInstancia(){
		return instancia;
	}
	
	
    private Collection<Usuario> usuarios;

	public Collection<Usuario> getUsuarios() {
		return usuarios;
	}


    
}