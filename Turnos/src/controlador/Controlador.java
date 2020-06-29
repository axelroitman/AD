package controlador;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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

	
	public List<TurnoView> buscarAgendaMedico(String matricula) {
		List<Turno> turnos = TurnoDAO.getInstancia().findByMedico(matricula);
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
		Turno t = buscarTurno(idTurno);
		try {
			Especialidad e = buscarEspecialidad(idEspecialidad);
		} catch (EspecialidadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		t.getFecha().setHours(horaInicial.getHour());
		t.getFecha().setMinutes(horaInicial.getMinute());
		TurnoDAO.getInstancia().update(t);
	}

	public PacienteView getPaciente (int id) {
		PacienteView resul = null;
		List<Paciente> pacientes = PacienteDAO.getInstancia().getPacientes();
		for ( Paciente pa : pacientes){
			if(id == pa.getIdPaciente()){
				resul = pa.toView();
			}
		}
		return resul;
	}
	
	public PacienteView getPacientePorIdUsuario (int idUsuario) {
		PacienteView resul = null;
		List<Paciente> pacientes = PacienteDAO.getInstancia().getPacientes();
		for ( Paciente pa : pacientes){
			if(idUsuario == pa.getIdUsr()){
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
	
	public MedicoView getMedicoPorIdUsuario (int idUsuario) {
		MedicoView resul = null;
		List<Medico> medicos = MedicoDAO.getInstancia().getMedicos();
		for ( Medico m : medicos){
			if(idUsuario == m.getIdUsr()){
				resul = m.toView();
			}
		}
		return resul;
	}

	public Map<String, String> getInfoMedico(String matricula) {
		
		Map<String, String> infoMedico = new HashMap<String, String>();
		Date fecha = new Date();
		
		int cantTurnosHoy = 0;
		int cantTurnosMan = 0;
		String primerTurnoHoy = "";
		String ultimoTurnoHoy = "";
		String primerTurnoMan = "";
		String ultimoTurnoMan = "";
		
		List<Turno> turnosHoy = TurnoDAO.getInstancia().findByMedicoYDia(matricula, fecha);
		
		for(Turno t: turnosHoy)
		{
			if(cantTurnosHoy == 0) 
			{
				primerTurnoHoy = t.getFecha().toString();
			}
			ultimoTurnoHoy = t.getFecha().toString();
			cantTurnosHoy++;
		}

		Date fechaMan = new Date();
		Calendar c = Calendar.getInstance(); 
		c.setTime(fecha); 
		c.add(Calendar.DATE, 1);
		fechaMan = c.getTime();
		
		List<Turno> turnosMan = TurnoDAO.getInstancia().findByMedicoYDia(matricula, fechaMan);

		for(Turno t: turnosMan)
		{
			if(cantTurnosMan == 0) 
			{
				primerTurnoMan = t.getFecha().toString();
			}
			ultimoTurnoMan = t.getFecha().toString();
			cantTurnosMan++;
		}
		
		infoMedico.put("cantTurnosHoy", Integer.toString(cantTurnosHoy));
		infoMedico.put("horaPrimerTurnoHoy", primerTurnoHoy);
		infoMedico.put("horaUltimoTurnoHoy", ultimoTurnoHoy);
		infoMedico.put("cantTurnosMan", Integer.toString(cantTurnosMan));
		infoMedico.put("horaPrimerTurnoMan", primerTurnoMan);
		infoMedico.put("horaUltimoTurnoMan", ultimoTurnoMan);
		
		return infoMedico;

	}

	public List<TurnoView> getTurnosMedicoPorDia(String matricula, Date fecha) {
		List<Turno> turnos = TurnoDAO.getInstancia().findByMedicoYDia(matricula, fecha);
		List<TurnoView> turnosPorDia = new ArrayList<TurnoView>();
		for(Turno t : turnos) {
			turnosPorDia.add(t.toView());			
		}
		return turnosPorDia;
	}
	
	
	public void agregarTurno(Date fecha, int idEspecialidad, String matricula) throws TurnoException 
	{
		Especialidad especialidad = null;
		try {
			especialidad = buscarEspecialidad(idEspecialidad);
		} catch (EspecialidadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Medico medico = null;
		try {
			medico = buscarMedico(matricula);
		} catch (MedicoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(!TurnoDAO.getInstancia().existeTurno(fecha, idEspecialidad, matricula)) {
			Turno turno = new Turno(fecha, especialidad, medico);
			TurnoDAO.getInstancia().save(turno);
		}
		else {
			throw new TurnoException("El turno ya existe");
		}
	}
	
	@SuppressWarnings("deprecation")
	public void agregarTurnos(int idEspecialidad, String matricula, int duracion, LocalTime horaInicial, LocalTime horaFinal, Date fechaInicial, Date fechaFinal, boolean lunes, boolean martes, boolean miercoles, boolean jueves, boolean viernes, boolean sabado, boolean domingo) throws TurnoException {
		Especialidad esp = null;
		Medico med = null;
		try {
			esp = buscarEspecialidad(idEspecialidad);
		} catch (EspecialidadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			med = buscarMedico(matricula);
		} catch (MedicoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Calendar c = Calendar.getInstance();
		int diaSemana;
			
		for(Date inicial = fechaInicial; inicial.before(fechaFinal) || inicial.equals(fechaFinal) ; inicial.setDate(inicial.getDate()+1)) {
			c.setTime(inicial);
			diaSemana = c.get(Calendar.DAY_OF_WEEK);
			if(lunes && diaSemana == Calendar.MONDAY) {
				for(LocalTime in = horaInicial; in.isBefore(horaFinal) ; in = in.plusMinutes(duracion)) {
					inicial.setHours(in.getHour());
					inicial.setMinutes(in.getMinute());
					Turno t = new Turno(inicial, esp, med);
					TurnoDAO.getInstancia().save(t);
				}
			}
			else if(martes && diaSemana == Calendar.TUESDAY) {
				for(LocalTime in = horaInicial; in.isBefore(horaFinal) ; in = in.plusMinutes(duracion)) {
					inicial.setHours(in.getHour());
					inicial.setMinutes(in.getMinute());
					Turno t = new Turno(inicial, esp, med);
					TurnoDAO.getInstancia().save(t);
				}
			}
			else if(miercoles && diaSemana == Calendar.WEDNESDAY) {
				for(LocalTime in = horaInicial; in.isBefore(horaFinal) ; in = in.plusMinutes(duracion)) {
					inicial.setHours(in.getHour());
					inicial.setMinutes(in.getMinute());
					Turno t = new Turno(inicial, esp, med);
					TurnoDAO.getInstancia().save(t);
				}
			}
			else if(jueves && diaSemana == Calendar.THURSDAY) {
				for(LocalTime in = horaInicial; in.isBefore(horaFinal) ; in = in.plusMinutes(duracion)) {
					inicial.setHours(in.getHour());
					inicial.setMinutes(in.getMinute());
					Turno t = new Turno(inicial, esp, med);
					TurnoDAO.getInstancia().save(t);
				}
			}
			else if(viernes && diaSemana == Calendar.FRIDAY) {
				for(LocalTime in = horaInicial; in.isBefore(horaFinal) ; in = in.plusMinutes(duracion)) {
					inicial.setHours(in.getHour());
					inicial.setMinutes(in.getMinute());
					Turno t = new Turno(inicial, esp, med);
					TurnoDAO.getInstancia().save(t);
				}
			}
			else if(sabado && diaSemana == Calendar.SATURDAY) {
				for(LocalTime in = horaInicial; in.isBefore(horaFinal) ; in = in.plusMinutes(duracion)) {
					inicial.setHours(in.getHour());
					inicial.setMinutes(in.getMinute());
					Turno t = new Turno(inicial, esp, med);
					TurnoDAO.getInstancia().save(t);
				}
			}
			else if(domingo && diaSemana == Calendar.SUNDAY) {
				for(LocalTime in = horaInicial; in.isBefore(horaFinal) ; in = in.plusMinutes(duracion)) {
					inicial.setHours(in.getHour());
					inicial.setMinutes(in.getMinute());
					Turno t = new Turno(inicial, esp, med);
					TurnoDAO.getInstancia().save(t);
				}
			}
			else if(!lunes && !martes && !miercoles && !jueves && !viernes && !sabado && !domingo) {
				for(LocalTime in = horaInicial; in.isBefore(horaFinal) ; in = in.plusMinutes(duracion)) {
					inicial.setHours(in.getHour());
					inicial.setMinutes(in.getMinute());
					Turno t = new Turno(inicial, esp, med);
					TurnoDAO.getInstancia().save(t);
				}
			}			
		}

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
		if (matricula != null && matricula.isEmpty()) {
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
		List<Turno> turnos = null;
		Date fecha = new Date();
		if(proximos == true) 
		{
			turnos = TurnoDAO.getInstancia().findByPacienteYFechaPosterior(idPaciente, fecha);
		}
		else 
		{
			turnos = TurnoDAO.getInstancia().findByPacienteYFechaAnterior(idPaciente, fecha);
			
		}
		
		for(Turno t: turnos) 
		{
			lista.add(t.toView());
		}
		return lista;
	}

	public List<TurnoView> getTurnosMedico(String matricula, Integer estado) {
		List<TurnoView> turnosMedico = new ArrayList<TurnoView>();
		try {
			Medico m = buscarMedico(matricula);
		} catch (MedicoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(estado == null) {
			Collection<Turno> turnos = TurnoDAO.getInstancia().findByMedico(matricula);
			for(Turno t : turnos) {
				turnosMedico.add(t.toView());
			}
		}
		else {
			Collection<Turno> turnos = TurnoDAO.getInstancia().findByMedicoYEstado(matricula, estado.intValue());
			for(Turno t : turnos) {
				turnosMedico.add(t.toView());
			}
		}
		return turnosMedico;
	}

	public Collection<TurnoView> getTurnosMedicoPorEspecialidad(String matricula, int idEspecialidad, Integer estado) {
		Collection<TurnoView> turnosPorEspecialidadyMedico = new ArrayList<TurnoView>();
		try {
			Medico m = buscarMedico(matricula);
		} catch (MedicoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Especialidad e = buscarEspecialidad(idEspecialidad);
		} catch (EspecialidadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(estado==null)
		{
			Collection<Turno> ts = TurnoDAO.getInstancia().findByEspecialidadYMedico(idEspecialidad, matricula);
			for(Turno t : ts) {
				turnosPorEspecialidadyMedico.add(t.toView());
			}
		}
		else {
			Collection<Turno> ts = TurnoDAO.getInstancia().findByEspecialidadMedicoYEstado(idEspecialidad, matricula,estado.intValue());
			for(Turno t : ts) {
				turnosPorEspecialidadyMedico.add(t.toView());
			}
		}
		
		return turnosPorEspecialidadyMedico;
	}

	public Map<Date,Integer> getCantidadTurnosDisponiblesPorDiaDeUnaEspecialidad(int idEspecialidad){
		Map<Date,Integer> resultado = new HashMap<Date,Integer>();
		
		Collection<Turno> turnos = TurnoDAO.getInstancia().findByEspecialidadYEstado(idEspecialidad, 1);
		for(Turno t : turnos) {
			Date fechaSinHora = new Date();
			Calendar c = Calendar.getInstance(); 
			c.setTime(t.getFecha()); 
			c.set(Calendar.HOUR_OF_DAY, 0);
			c.set(Calendar.MINUTE, 0);
			c.set(Calendar.SECOND, 0);
			
			fechaSinHora = c.getTime();

			if(!resultado.containsKey(fechaSinHora))
			{
				resultado.put(fechaSinHora, 1);	
			}
			else 
			{
				int disponibles = resultado.get(fechaSinHora) + 1;
				resultado.put(fechaSinHora, disponibles);
			}			
		}
		
		return resultado;
	}

	public Map<Date,Integer> getCantidadTurnosDisponiblesPorDiaDeUnaEspecialidadYMedico(int idEspecialidad, String matricula){
		Map<Date,Integer> resultado = new HashMap<Date,Integer>();
		
		Collection<Turno> turnos = TurnoDAO.getInstancia().findByEspecialidadMedicoYEstado(idEspecialidad, matricula, 1);
		for(Turno t : turnos) {
			Date fechaSinHora = new Date();
			Calendar c = Calendar.getInstance(); 
			c.setTime(t.getFecha()); 
			c.set(Calendar.HOUR_OF_DAY, 0);
			c.set(Calendar.MINUTE, 0);
			c.set(Calendar.SECOND, 0);
			
			fechaSinHora = c.getTime();

			if(!resultado.containsKey(fechaSinHora))
			{
				resultado.put(fechaSinHora, 1);	
			}
			else 
			{
				int disponibles = resultado.get(fechaSinHora) + 1;
				resultado.put(fechaSinHora, disponibles);
			}			
		}
		
		return resultado;
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
		
		List<Medico> medicos = MedicoDAO.getInstancia().getMedicos();
		
		for(Medico m: medicos) 
		{
			for(Especialidad esp: m.getEspecialidades()) 
			{
				if(esp.getIdEspecialidad() == idEspecialidad) 
				{
					lista.add(m.toView());
				}
			}
		}
		
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

	public void eliminarTurnos(String matricula, LocalTime horaInicial, LocalTime horaFinal, Date fechaInicial, Date fechaFinal, boolean lunes, boolean martes, boolean miercoles, boolean jueves, boolean viernes, boolean sabado, boolean domingo) throws TurnoException{
		Medico med = null;
		try {
			med = buscarMedico(matricula);
		} catch (MedicoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Calendar c = Calendar.getInstance();
		int diaSemana;
			
		for(Date fecha = fechaInicial; fecha.before(fechaFinal) || fecha.equals(fechaFinal) ; fecha.setDate(fecha.getDate()+1)) {
			List<Turno> turnosDelDia = TurnoDAO.getInstancia().findByMedicoYDia(matricula, fecha);
			Date fechaInicioDia = new Date();
			Calendar c = Calendar.getInstance(); 
			c.setTime(fecha); 
			c.set(Calendar.HOUR_OF_DAY, horaInicial.getHour());
			c.set(Calendar.MINUTE, horaInicial.getMinute());
			c.set(Calendar.SECOND, horaInicial.getSecond());
			fechaInicioDia = c.getTime();
			Date fechaFinDia = new Date();			
			c.setTime(fecha); 
			c.set(Calendar.HOUR_OF_DAY, horaFinal.getHour());
			c.set(Calendar.MINUTE, horaFinal.getMinute());
			c.set(Calendar.SECOND, horaFinal.getSecond());
			fechaFinDia = c.getTime();
			
			
			diaSemana = c.get(Calendar.DAY_OF_WEEK);
			
			if(lunes && diaSemana == Calendar.MONDAY) {				
				for(Turno t: turnosDelDia) 
				{
					Calendar cal = Calendar.getInstance(); 
					cal.setTime(t.getFecha()); 
					Date fechaAComparar = cal.getTime();

					
					if((fechaAComparar.after(fechaInicioDia) && fechaAComparar.before(fechaFinDia)) || fechaAComparar.equals(fechaInicioDia)  || fechaAComparar.equals(fechaFinDia)) {
						TurnoDAO.getInstancia().delete(t);
					}

				}
			}
			else if(martes && diaSemana == Calendar.TUESDAY) {
				for(Turno t: turnosDelDia) 
				{
					Calendar cal = Calendar.getInstance(); 
					cal.setTime(t.getFecha()); 
					Date fechaAComparar = cal.getTime();

					
					if((fechaAComparar.after(fechaInicioDia) && fechaAComparar.before(fechaFinDia)) || fechaAComparar.equals(fechaInicioDia)  || fechaAComparar.equals(fechaFinDia)) {
						TurnoDAO.getInstancia().delete(t);
					}

				}
			}
			else if(miercoles && diaSemana == Calendar.WEDNESDAY) {
				for(Turno t: turnosDelDia) 
				{
					Calendar cal = Calendar.getInstance(); 
					cal.setTime(t.getFecha()); 
					Date fechaAComparar = cal.getTime();

					
					if((fechaAComparar.after(fechaInicioDia) && fechaAComparar.before(fechaFinDia)) || fechaAComparar.equals(fechaInicioDia)  || fechaAComparar.equals(fechaFinDia)) {
						TurnoDAO.getInstancia().delete(t);
					}

				}
			}
			else if(jueves && diaSemana == Calendar.THURSDAY) {
				for(Turno t: turnosDelDia) 
				{
					Calendar cal = Calendar.getInstance(); 
					cal.setTime(t.getFecha()); 
					Date fechaAComparar = cal.getTime();

					
					if((fechaAComparar.after(fechaInicioDia) && fechaAComparar.before(fechaFinDia)) || fechaAComparar.equals(fechaInicioDia)  || fechaAComparar.equals(fechaFinDia)) {
						TurnoDAO.getInstancia().delete(t);
					}

				}
			}
			else if(viernes && diaSemana == Calendar.FRIDAY) {
				for(Turno t: turnosDelDia) 
				{
					Calendar cal = Calendar.getInstance(); 
					cal.setTime(t.getFecha()); 
					Date fechaAComparar = cal.getTime();

					
					if((fechaAComparar.after(fechaInicioDia) && fechaAComparar.before(fechaFinDia)) || fechaAComparar.equals(fechaInicioDia)  || fechaAComparar.equals(fechaFinDia)) {
						TurnoDAO.getInstancia().delete(t);
					}

				}

			}
			else if(sabado && diaSemana == Calendar.SATURDAY) {
				for(Turno t: turnosDelDia) 
				{
					Calendar cal = Calendar.getInstance(); 
					cal.setTime(t.getFecha()); 
					Date fechaAComparar = cal.getTime();

					
					if((fechaAComparar.after(fechaInicioDia) && fechaAComparar.before(fechaFinDia)) || fechaAComparar.equals(fechaInicioDia)  || fechaAComparar.equals(fechaFinDia)) {
						TurnoDAO.getInstancia().delete(t);
					}

				}

			}
			else if(domingo && diaSemana == Calendar.SUNDAY) {
				for(Turno t: turnosDelDia) 
				{
					Calendar cal = Calendar.getInstance(); 
					cal.setTime(t.getFecha()); 
					Date fechaAComparar = cal.getTime();

					
					if((fechaAComparar.after(fechaInicioDia) && fechaAComparar.before(fechaFinDia)) || fechaAComparar.equals(fechaInicioDia)  || fechaAComparar.equals(fechaFinDia)) {
						TurnoDAO.getInstancia().delete(t);
					}

				}
			}
			else if(!lunes && !martes && !miercoles && !jueves && !viernes && !sabado && !domingo){
				for(Turno t: turnosDelDia) 
				{
					Calendar cal = Calendar.getInstance(); 
					cal.setTime(t.getFecha()); 
					Date fechaAComparar = cal.getTime();

					
					if((fechaAComparar.after(fechaInicioDia) && fechaAComparar.before(fechaFinDia)) || fechaAComparar.equals(fechaInicioDia)  || fechaAComparar.equals(fechaFinDia)) {
						TurnoDAO.getInstancia().delete(t);
					}

				}
			}
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
	
	public List<TurnoView> getTurnosPacientePorEstado(int idPaciente, int estado){
		List<TurnoView> turnosPorEstado = new ArrayList<TurnoView>();
		
		List<Turno> turnos = TurnoDAO.getInstancia().findByPacienteYEstado(idPaciente, estado);
		for(Turno t: turnos) 
		{
			turnosPorEstado.add(t.toView());
		}
		
		return turnosPorEstado;
	}

	public void cambiarEstadoDeTurno(int idTurno, Integer idPaciente, Integer asistencia, Integer disponibilidad)throws TurnoException{
		Turno turno = buscarTurno(idTurno);
		Paciente paciente = null;
		if(idPaciente != null) {
			try {
				paciente = buscarPaciente(idPaciente.intValue());
			} catch (PacienteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(asistencia != null) {
			turno.setAsistencia(asistencia.intValue());
		}
		if(disponibilidad != null) {
			turno.setDisponibilidad(disponibilidad.intValue());
		}
		if(paciente != null) {
			turno.setPaciente(paciente);
		}
		
		TurnoDAO.getInstancia().update(turno);
	}
	
	public TurnoView buscarProxTurnoPaciente(int idPaciente) {
		TurnoView proximo = null;
		Date fecha = new Date();
		
		List<TurnoView> proximosTurnos = getTurnosPaciente(idPaciente, true);
		
		int i = 0;
		
		for(TurnoView t: proximosTurnos) 
		{
			if(i == 0) 
			{
				proximo = t;
			}
			i++;
		}
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
		aBuscar = UsuarioDAO.getInstancia().findByNombreDeUsuario(username);
		if(aBuscar == null) {
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
		aBuscar = EspecialidadDAO.getInstancia().findById(id);
		if(aBuscar == null) {
			throw new EspecialidadException("No existe el turno");
		}
		return aBuscar;				
	}
	private Medico buscarMedico(String matricula) throws MedicoException { 
		Medico aBuscar = null;
		aBuscar = MedicoDAO.getInstancia().findByMatricula(matricula);
		if(aBuscar == null) {
			throw new MedicoException("No existe el medico");
		}
		return aBuscar;				
	}
	private Paciente buscarPaciente(int id) throws PacienteException { 
		Paciente aBuscar = null;
		aBuscar = PacienteDAO.getInstancia().findById(id);
		if(aBuscar == null) {
			throw new PacienteException("No existe el paciente");
		}
		return aBuscar;				
	}
}
