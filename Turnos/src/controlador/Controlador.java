package controlador;

import java.util.List;

import daos.TurnoDAO;
import daos.UsuarioDAO;
import exceptions.TurnoException;
import exceptions.UsuarioException;
import modelo.Turno;
import modelo.Usuario;
import views.TurnoView;
import views.UsuarioView;

public class Controlador {

	/*La mayoría de los métodos están vacíos, hay que armarlos. 
	Dejé algunos hechos, quizás convenga modificarlos.
	11/04 - Axel
	*/
	//Patrón Singleton para llamar al controller desde Spring.
	private static Controlador instancia;
	private Controlador() { }
	
	public static Controlador getInstancia() {
		if(instancia == null)
			instancia = new Controlador();
		return instancia;
	}

	
	public List<TurnoView> buscarAgendaMedico(int idMedico)
	{
		return null;
	}
	
	public List<TurnoView> buscarAgendaEspecialidad(int idEspecialid)
	{
		return null;
	}
	
	public List<TurnoView> buscarTurnosPaciente(int idPaciente)
	{
		return null;
	}
	public void registrarUsuario() {
		
	}
	public void modificarUsuario()
	{
	}
	
	public void agregarTurno() {
		
	}
	public void modificarTurno()
	{
	}
	public void eliminarTurno() {
		
	}
	public void cambiarEstadoDeTurno() { 	
	}

	public UsuarioView getUsuarioLogIn(String usuario, String password){ 
		UsuarioView resultado = null;
		List<Usuario> usuarios = UsuarioDAO.getInstancia().getUsuarios();
		for(Usuario user : usuarios) 
		{			
			if(usuario.equals(user.getUsr()) && password.equals(user.getPass())) 
			{
				resultado = user.toView();
			}
		}
		return resultado;
	}
	
	public UsuarioView getUsuario(String usuario){ 
		UsuarioView resultado = null;
		List<Usuario> usuarios = UsuarioDAO.getInstancia().getUsuarios();
		for(Usuario user : usuarios) 
		{			
			if(usuario.equals(user.getUsr())) 
			{
				resultado = user.toView();
			}
		}
		return resultado;
	}

	public TurnoView getTurno(int id){ 
		TurnoView resultado = null;
		List<Turno> turnos = TurnoDAO.getInstancia().getTurnos();
		for(Turno turno : turnos) 
		{			
			if(id == turno.getId()) 
			{
				resultado = turno.toView();
			}
		}
		return resultado;
	}

	private Usuario buscarUsuario(String username) throws UsuarioException { 
		Usuario aBuscar = null;
		try {
			aBuscar = UsuarioDAO.getInstancia().findByNombreDeUsuario(username);
		} catch (UsuarioException e) {
			throw new UsuarioException("No existe el usuario");
		}
		return aBuscar;				
	}
	
	private Turno buscarTurno(int id) throws TurnoException { 
		Turno aBuscar = null;
		try {
			aBuscar = TurnoDAO.getInstancia().findById(id);
		} catch (TurnoException e) {
			throw new TurnoException("No existe el turno");
		}
		return aBuscar;				
	}


}
