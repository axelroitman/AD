package controlador;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import daos.TurnoDAO;
import daos.UsuarioDAO;
import exceptions.ListaDeEsperaException;
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

	
	public List<TurnoView> buscarAgendaMedico(int idMedico) {
		List<Turno> turnos = TurnoDAO.getInstancia().findByMedico(idMedico);
		List<TurnoView> rdo = new ArrayList<TurnoView>();
		for(Turno t : turnos) {
			rdo.add(t.toView());
		}
		
		return rdo;		
	}
	
	public List<TurnoView> buscarAgendaEspecialidad(int idEspecialidad) {
		List<Turno> turnos = TurnoDAO.getInstancia().findByEspecialidad(idEspecialidad);
		List<TurnoView> rdo = new ArrayList<TurnoView>();
		for(Turno t : turnos) {
			rdo.add(t.toView());
		}
		
		return rdo;		
	}
	
	public List<TurnoView> buscarTurnosPaciente(int idPaciente) {
		List<Turno> turnos = TurnoDAO.getInstancia().findByPaciente(idPaciente);
		List<TurnoView> rdo = new ArrayList<TurnoView>();
		for(Turno t : turnos) {
			rdo.add(t.toView());
		}
		
		return rdo;	
	}

	public void modificarTurno(int idTurno, int idEspecialidad, LocalTime horaInicial){
		
	}
	
	public void cambiarEstadoDeTurno() { 	
	}

	public PacienteView getPaciente (int id) {
		PacienteView resul = null;
		List<Paciente> pacientes = PacienteDAO.getInstancia().getPacientes();
		for ( Paciente pa : pacientes){
			if( id = pa.getId()){
				resul = pa.toView();
			}
		}
		return resul;
	}

	public MedicoView getMedico(String matricula) {
		MedicoView resul = null;
		List<Medico> medicos = MedicoDAO.getInstancia().getMedicos();
		for ( Medico med : medicos ){
			if( matricula.equals(med.getMatricula()) ){
				resul = med.toView();
			}
		}
		return resul;
	}

	public void getInfoMedico(String matricula) {

	}

	public void getTurnosMedicoPorDia(int idMedico, int idEspecialidad) {

	}

	public void agregarTurno(Date fecha, LocalTime hora, int idEspecialidad, String matricula, int idPaciente) throws TurnoException {
		
	}
	
	public void agregarTurnos(int idEspecialidad, String matricula, int duracion, LocalTime horaInicial, LocalTime horaFinal, Date fechaInicial, Date fechaFinal, boolean lunes, boolean martes, boolean miercoles, boolean jueves, boolean viernes, boolean sabado, boolean domingo) throws TurnoException {
		
	}
	
	public void agregarAListaDeEspera(int idPaciente, int idEspecialidad, String matricula) throws ListaDeEsperaException {
		
	}

	public void getTurnosPaciente(int idPaciente, boolean proximos) {
	}

	public void getTurnosMedico(int idMedico, int estado) {
	}

	public void getTurnosMedicoPorEspecialidad(int idMedico, int idEspecialidad, int estado) {
	}

	public void getCantidadTurnosDisponiblesPorDiaDeUnaEspecialidad(int idEspecialidad){
	}

	public void getCantidadTurnosDisponiblesPorDiaDeUnaEspecialidadYMedico(int idEspecialidad, String matricula){
	}

	public void getEspecialidades() {
	}

	public void obtenerAgendaMedico(String matricula) {
	}
	
	public void getMedicosPorEspecialidad(int idEspecialidad) {
	}
	public void eliminarTurno(int id) throws TurnoException {
		Turno aEliminar = null;
		try {
			aEliminar = buscarTurno(id);
			if(aEliminar != null){
				TurnoDAO.getInstancia().delete(aEliminar);
			}
		} catch (TurnoException e) {
			// TODO Auto-generated catch block
			throw new TurnoException("No se puede eliminar el turno");
		}
		
	}

	public void eliminarTurnos(LocalTime horaInicial, LocalTime horaFinal, Date fechaInicial, Date fechaFinal, boolean lunes, boolean martes, boolean miercoles, boolean jueves, boolean viernes, boolean sabado, boolean domingo) {

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
	public void getTurnosPacientePorEstado(int idPaciente, int estado){
	}

	public void cambiarEstadoDeTurno(int idTurno){
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
