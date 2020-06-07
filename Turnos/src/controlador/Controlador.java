package controlador;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import daos.EspecialidadDAO;
import daos.ListaDeEsperaDAO;
import daos.MedicoDAO;
import daos.PacienteDAO;
import daos.TurnoDAO;
import daos.UsuarioDAO;
import exceptions.EspecialidadException;
import exceptions.ListaDeEsperaException;
import exceptions.MedicoException;
import exceptions.PacienteException;
import exceptions.TurnoException;
import exceptions.UsuarioException;
import modelo.Especialidad;
import modelo.ItemListaDeEspera;
import modelo.Medico;
import modelo.Paciente;
import modelo.Turno;
import modelo.Usuario;
import views.EspecialidadView;
import views.MedicoView;
import views.PacienteView;
import views.TurnoView;
import views.UsuarioView;

public class Controlador {

	/*La mayorï¿½a de los mï¿½todos estï¿½n vacï¿½os, hay que armarlos. 
	Dejï¿½ algunos hechos, quizï¿½s convenga modificarlos.
	11/04 - Axel
	*/
	//Patrï¿½n Singleton para llamar al controller desde Spring.
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

	public void modificarTurno(int idTurno, int idEspecialidad, LocalTime horaInicial) throws TurnoException{
		
	}
	
	public void cambiarEstadoDeTurno() { 	
	}

	public PacienteView getPaciente (int id) {
		PacienteView resul = null;
		List<Paciente> pacientes = PacienteDAO.getInstancia().getPacientes();
		for ( Paciente pa : pacientes){
			if(id == pa.getId()){
				resul = pa.toView();
			}
		}
		return resul;
	}

	public MedicoView getMedico(String matricula) {
		MedicoView medico;
		medico = null;
		try {
			medico = MedicoDAO.getInstancia().findByMatricula(matricula).toView();
		} catch (MedicoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return medico;
	}

	public MedicoView getInfoMedico(String matricula) { //NO DEBERÍA DEVOLVER UN MEDICOVIEW -> VER DEFINICIÓN DE API REST
		MedicoView medico= null;
		return medico;

	}

	public List<TurnoView> getTurnosMedicoPorDia(String matricula, int idEspecialidad) {
		List<Turno> turnos = TurnoDAO.getInstancia().findByEspecialidadYMedico(idEspecialidad, matricula);
		List<TurnoView> turnosPorDia = new ArrayList<TurnoView>();
		for(Turno t : turnos) {
			turnosPorDia.add(t.toView());			
		}
		return turnosPorDia;
	}
	
	public void agregarTurno(Date fecha, int idEspecialidad, String matricula, int idPaciente) throws TurnoException 
	{
		Especialidad especialidad = null;
		Medico medico = null;
		Paciente paciente = null;
		
			try {
				especialidad = buscarEspecialidad(idEspecialidad);
				medico = buscarMedico(matricula);
				paciente = buscarPaciente(idPaciente);				
			} catch (EspecialidadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (MedicoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (PacienteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		Turno turno = new Turno(fecha, especialidad, medico, paciente);
	}

	
	public void agregarTurnos(int idEspecialidad, String matricula, int duracion, LocalTime horaInicial, LocalTime horaFinal, Date fechaInicial, Date fechaFinal, boolean lunes, boolean martes, boolean miercoles, boolean jueves, boolean viernes, boolean sabado, boolean domingo) throws TurnoException {
		
	}
	
	public void agregarAListaDeEspera(int idPaciente, int idEspecialidad, String matricula) throws ListaDeEsperaException {
		ItemListaDeEspera le = null;
		Paciente p = null;
		Especialidad e = null;
		try {
			e = EspecialidadDAO.getInstancia().findById(idEspecialidad);
		} catch (EspecialidadException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			p = PacienteDAO.getInstancia().findById(idPaciente);
		} catch (PacienteException exc) {
			exc.printStackTrace();
		}
		if (matricula.isEmpty()) {
			if(ListaDeEsperaDAO.getInstancia().existePacienteEnListaE(idEspecialidad, idPaciente)) {
				throw new ListaDeEsperaException("El paciente ya existe en la lista");
			}
			else {
				le = new ItemListaDeEspera(e, p, null);
			}
		}
		else {
			Medico m = null;

			try {
				m = MedicoDAO.getInstancia().findByMatricula(matricula);
			} catch (MedicoException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if(ListaDeEsperaDAO.getInstancia().existePacienteEnListaEyM(idEspecialidad, matricula, idPaciente)) {
				throw new ListaDeEsperaException("El paciente ya existe en la lista");
			}
			else {
				le = new ItemListaDeEspera(e, p, m);
			}
		}
		ListaDeEsperaDAO.getInstancia().agregarALista(le);		
	}
	

	public List<TurnoView> getTurnosPaciente(int idPaciente, boolean proximos) {
		List<TurnoView> lista = new ArrayList<TurnoView>();
		return lista;
	}

	public List<TurnoView> getTurnosMedico(int idMedico, int estado) {
		List<TurnoView> turnosMedico = new ArrayList<TurnoView>();
		return turnosMedico;
	}

	public List<TurnoView> getTurnosMedicoPorEspecialidad(int idMedico, int idEspecialidad, int estado) {
		List<TurnoView> turnosPorEspecialidad = new ArrayList<TurnoView>();
		return turnosPorEspecialidad;
	}

	public List<TurnoView> getCantidadTurnosDisponiblesPorDiaDeUnaEspecialidad(int idEspecialidad){
		List<TurnoView> disponibles = new ArrayList<TurnoView>();
		return disponibles;
	}

	public List<TurnoView> getCantidadTurnosDisponiblesPorDiaDeUnaEspecialidadYMedico(int idEspecialidad, String matricula){
		List<TurnoView> disponibles = new ArrayList<TurnoView>();
		return disponibles;
	}

	public List<EspecialidadView> getEspecialidades() {
		List<EspecialidadView> lista = new ArrayList<EspecialidadView>();
		List<Especialidad> especialidades = EspecialidadDAO.getInstancia().getEspecialidades();
		for(Especialidad e: especialidades) 
		{
			lista.add(e.toView());
		}
		return lista;
	}

	public List<TurnoView> obtenerAgendaMedico(String matricula) {
		List<TurnoView> agenda = new ArrayList<TurnoView>();
		return agenda;
	}
	
	public List<MedicoView> getMedicosPorEspecialidad(int idEspecialidad) {
		List<MedicoView> lista = new ArrayList<MedicoView>();
		return lista;
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

	public void eliminarTurnos(LocalTime horaInicial, LocalTime horaFinal, Date fechaInicial, Date fechaFinal, boolean lunes, boolean martes, boolean miercoles, boolean jueves, boolean viernes, boolean sabado, boolean domingo) throws TurnoException{

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
	public List<TurnoView> getTurnosPacientePorEstado(int idPaciente, int estado){
		List<TurnoView> turnoPorEstado = new ArrayList<TurnoView>();
		return turnoPorEstado;
	}

	public void cambiarEstadoDeTurno(int idTurno)throws TurnoException{
	}
	
	public TurnoView buscarProxTurnoPaciente( int idPaciente) {
		TurnoView proximo = null;
		return proximo;
	}

	public TurnoView getTurno(int id) throws TurnoException{ 
		TurnoView resultado = null;
		try {
			Turno turno = TurnoDAO.getInstancia().findById(id);
			if(turno != null)
			{
				resultado = turno.toView();
			}
		} catch (TurnoException e) {
			throw new TurnoException("No existe el turno");
		}
		/*for(Turno turno : turnos) 
		{			
			if(id == turno.getId()) 
			{
				resultado = turno.toView();
			}
		}*/
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
		aBuscar = TurnoDAO.getInstancia().findById(id);
		if(aBuscar == null) {
			throw new TurnoException("No existe el turno");
		}
		return aBuscar;				
	}
	private Especialidad buscarEspecialidad(int id) throws EspecialidadException { 
		Especialidad aBuscar = null;
		try {
			aBuscar = EspecialidadDAO.getInstancia().findById(id);
		} catch (EspecialidadException e) {
			throw new EspecialidadException("No existe el turno");
		}
		return aBuscar;				
	}
	private Medico buscarMedico(String matricula) throws MedicoException { 
		Medico aBuscar = null;
		try {
			aBuscar = MedicoDAO.getInstancia().findByMatricula(matricula);
		} catch (MedicoException e) {
			throw new MedicoException("No existe el medico");
		}
		return aBuscar;				
	}
	private Paciente buscarPaciente(int id) throws PacienteException { 
		Paciente aBuscar = null;
		try {
			aBuscar = PacienteDAO.getInstancia().findById(id);
		} catch (PacienteException e) {
			throw new PacienteException("No existe el paciente");
		}
		return aBuscar;				
	}
}
