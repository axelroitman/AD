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

	/*La mayor�a de los m�todos est�n vac�os, hay que armarlos. 
	Dej� algunos hechos, quiz�s convenga modificarlos.
	11/04 - Axel
	*/
	//Patr�n Singleton para llamar al controller desde Spring.
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
	
	public void cambiarEstadoDeTurno() { 	
	}

	/*public PacienteView get Paciente (int id){
		PacienteView resul = null;
		List<Paciente> pacientes = PacienteDAO.getInstancia().getPacientes();
		for ( Paciente pa : pacientes){
			if( id = pa.getId()){
				resul = pa.toView();
			}
		}
		return resul;
	}

	public MedicoView getMedico(string matricula){
		MedicoView resul = null;
		List<Medico> medicos = MedicoDAO.getInstancia().getMedicos();
		for ( Medico med : medicos ){
			if( matricula.equals(med.getMatricula()) ){
				resul = med.toView();
			}
		}
		return resul;
	}
	*/

	public void eliminarTurno(int id) throws TurnoException {
		Turno aEliminar = null;
		try {
			aEliminar = buscarTurno(id);
			if(aEliminar != null){
				TurnoDAO.getInstancia().delete(aEliminar);
			}
		} catch (TurnoException e) {
			// TODO Auto-generated catch block
			throw new TurnoException("no se puede eliminar el turno");
		}
		
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
