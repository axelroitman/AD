package daos;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import entities.ListaDeEsperaEntity;
import entities.TurnoEntity;
import exceptions.TurnoException;
import hibernate.HibernateUtil;
import modelo.Turno;

public class TurnoDAO {

	public static TurnoDAO instancia;
	
	public static TurnoDAO getInstancia(){
		if(instancia == null)
			instancia = new TurnoDAO();
		return instancia;
	}
	
	public List<Turno> getTurnos(){
		List<Turno> resultado = new ArrayList<Turno>();
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		List<TurnoEntity> turnos = s.createQuery("from TurnoEntity order by fecha, hora").list();
		s.getTransaction().commit();
		s.close();
		for(TurnoEntity te : turnos)
			resultado.add(toNegocio(te));
		return resultado;
	}
	
	public Turno findById(int id) throws TurnoException{
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		TurnoEntity turno = (TurnoEntity) s.createQuery("from TurnoEntity t where t.id = ?")
				.setInteger(0, id)
				.uniqueResult();
		if(turno == null)
		{	
			return null;
		}	
		return toNegocio(turno);
	}

	public List<Turno> findByMedico(String matricula) {
		List<Turno> resultado = new ArrayList<Turno>();
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		List<TurnoEntity> turnos = s.createQuery("from TurnoEntity t where t.medico.id = ?")
				.setString(0,matricula)
				.list();
		s.getTransaction().commit();
		s.close();
		for(TurnoEntity tur : turnos)
			resultado.add(toNegocio(tur));
		return resultado;
	}

	public List<Turno> findByPaciente(int idPaciente) {
		List<Turno> resultado = new ArrayList<Turno>();
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		List<TurnoEntity> turnos = s.createQuery("from TurnoEntity t where t.paciente.id = ?")
				.setInteger(0,idPaciente)
				.list();
		s.getTransaction().commit();
		s.close();
		for(TurnoEntity tur : turnos)
			resultado.add(toNegocio(tur));
		return resultado;
	}

	public List<Turno> findByEspecialidad(int idEspecialidad) {
		List<Turno> resultado = new ArrayList<Turno>();
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		List<TurnoEntity> turnos = s.createQuery("from TurnoEntity t where t.especialidad.id = ?")
				.setInteger(0,idEspecialidad)
				.list();
		s.getTransaction().commit();
		s.close();
		for(TurnoEntity tur : turnos)
			resultado.add(toNegocio(tur));
		return resultado;
	}
	
	public List<Turno> findByEspecialidadYMedico(int idEspecialidad, String matricula) {
		List<Turno> resultado = new ArrayList<Turno>();
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		List<TurnoEntity> turnos = s.createQuery("from TurnoEntity t where t.especialidad = ? and t.medico = ?")
				.setInteger(0,idEspecialidad)
				.setString(1, matricula)
				.list();
		s.getTransaction().commit();
		s.close();
		for(TurnoEntity tur : turnos)
			resultado.add(toNegocio(tur));
		return resultado;
	}
	
	public List<Turno> findDisponibleByMedicoYDia(String matricula, Date fecha) {
		Date fechaFin = new Date();
		Calendar c = Calendar.getInstance(); 
		c.setTime(fecha); 
		c.add(Calendar.DATE, 1);
		fechaFin = c.getTime();

		List<Turno> resultado = new ArrayList<Turno>();
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		List<TurnoEntity> turnos = s.createQuery("from TurnoEntity t where t.medico = ? and t.fecha >= ? and t.fecha < ? and t.disponibilidad = ? ORDER BY t.fecha")
				.setString(0, matricula)
				.setDate(1, fecha)
				.setDate(2, fechaFin)
				.setInteger(3,1)
				.list();
		s.getTransaction().commit();
		s.close();
		for(TurnoEntity tur : turnos)
			resultado.add(toNegocio(tur));
		return resultado;
	}
	
	public List<Turno> findByMedicoYDia(String matricula, Date fecha) {
		Date fechaFin = new Date();
		Calendar c = Calendar.getInstance(); 
		c.setTime(fecha); 
		c.add(Calendar.DATE, 1);
		fechaFin = c.getTime();

		List<Turno> resultado = new ArrayList<Turno>();
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		List<TurnoEntity> turnos = s.createQuery("from TurnoEntity t where t.medico = ? and t.fecha >= ? and t.fecha < ? ORDER BY t.fecha")
				.setString(0, matricula)
				.setDate(1, fecha)
				.setDate(2, fechaFin)				
				.list();
		s.getTransaction().commit();
		s.close();
		for(TurnoEntity tur : turnos)
			resultado.add(toNegocio(tur));
		return resultado;
	}
	
	public List<Turno> findByEspecialidadYDia(int idEspecialidad, Date fecha) {
		Date fechaFin = new Date();
		Calendar c = Calendar.getInstance(); 
		c.setTime(fecha); 
		c.add(Calendar.DATE, 1);
		fechaFin = c.getTime();

		List<Turno> resultado = new ArrayList<Turno>();
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		List<TurnoEntity> turnos = s.createQuery("from TurnoEntity t where t.especialidad = ? and disponibilidad=? and t.fecha >= ? and t.fecha < ? ORDER BY t.fecha")
				.setInteger(0, idEspecialidad)
				.setInteger(1,1)
				.setDate(2, fecha)
				.setDate(3, fechaFin)				
				.list();
		s.getTransaction().commit();
		s.close();
		for(TurnoEntity tur : turnos)
			resultado.add(toNegocio(tur));
		return resultado;
	}
	
	public List<Turno> findByEspecialidadYMedicoYDia(int idEspecialidad, Date fecha, String matricula) {
		Date fechaFin = new Date();
		Calendar c = Calendar.getInstance(); 
		c.setTime(fecha); 
		c.add(Calendar.DATE, 1);
		fechaFin = c.getTime();

		List<Turno> resultado = new ArrayList<Turno>();
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		List<TurnoEntity> turnos = s.createQuery("from TurnoEntity t where t.especialidad = ? and disponibilidad=? and t.fecha >= ? and t.fecha < ? and t.medico=? ORDER BY t.fecha")
				.setInteger(0, idEspecialidad)
				.setInteger(1,1)
				.setDate(2, fecha)
				.setDate(3, fechaFin)
				.setString(4, matricula)
				.list();
		s.getTransaction().commit();
		s.close();
		for(TurnoEntity tur : turnos)
			resultado.add(toNegocio(tur));
		return resultado;
	}
	
	public List<Turno> findByPacienteYEstado(int idPaciente, int estado) {
		List<Turno> resultado = new ArrayList<Turno>();
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		List<TurnoEntity> turnos = s.createQuery("from TurnoEntity t where t.paciente = ? and t.disponibilidad = ?")
				.setInteger(0,idPaciente)
				.setInteger(1, estado)
				.list();
		s.getTransaction().commit();
		s.close();
		for(TurnoEntity tur : turnos)
			resultado.add(toNegocio(tur));
		return resultado;
	}

	
	public List<Turno> findByPacienteYFechaAnterior(int idPaciente, Date fecha) {
		List<Turno> resultado = new ArrayList<Turno>();
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		List<TurnoEntity> turnos = s.createQuery("from TurnoEntity t where t.paciente = ? and t.fecha < ? order by t.fecha desc")
				.setInteger(0,idPaciente)
				.setTimestamp(1, fecha)
				.list();
		s.getTransaction().commit();
		s.close();
		for(TurnoEntity tur : turnos)
			resultado.add(toNegocio(tur));
		return resultado;
	}

	public List<Turno> findByPacienteYFechaPosterior(int idPaciente, Date fecha) {
		List<Turno> resultado = new ArrayList<Turno>();
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		List<TurnoEntity> turnos = s.createQuery("from TurnoEntity t where t.paciente = ? and t.fecha >= ? order by t.fecha asc")
				.setInteger(0,idPaciente)
				.setTimestamp(1, fecha)
				.list();
		s.getTransaction().commit();
		s.close();
		for(TurnoEntity tur : turnos)
			resultado.add(toNegocio(tur));
		return resultado;
	}
	
	public void save(Turno turno){
		TurnoEntity aGrabar = toEntitySave(turno);
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.save(aGrabar);
		s.getTransaction().commit();
		s.close();
	}
	
	public void update(Turno turno){

		TurnoEntity aGrabar = toEntity(turno);
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.update(aGrabar);
		s.getTransaction().commit();
		s.close();
	}
	
	public void delete(Turno turno) throws TurnoException {
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		TurnoEntity aEliminar = (TurnoEntity) s.createQuery("from TurnoEntity t where t.id = ?")
				.setInteger(0, turno.getId())
				.uniqueResult();
		s.delete(aEliminar);
		s.getTransaction().commit();
		s.close();
	}	
	
	TurnoEntity toEntity(Turno turno){
		if(turno.getPaciente() == null)
		{
			return new TurnoEntity(turno.getId(), turno.getFecha(), turno.getPrecio(), turno.getAsistencia(), turno.getJustifInasistencia(), turno.getDisponibilidad(), EspecialidadDAO.getInstancia().toEntity(turno.getEspecialidad()), MedicoDAO.getInstancia().toEntity(turno.getMedico()), null);			
		}
		else 
		{
			return new TurnoEntity(turno.getId(), turno.getFecha(), turno.getPrecio(), turno.getAsistencia(), turno.getJustifInasistencia(), turno.getDisponibilidad(), EspecialidadDAO.getInstancia().toEntity(turno.getEspecialidad()), MedicoDAO.getInstancia().toEntity(turno.getMedico()), PacienteDAO.getInstancia().toEntity(turno.getPaciente()));
		} 
	}
	TurnoEntity toEntitySave(Turno turno) {
		return new TurnoEntity(turno.getFecha(), EspecialidadDAO.getInstancia().toEntity(turno.getEspecialidad()),MedicoDAO.getInstancia().toEntity(turno.getMedico()));
	}
	
	Turno toNegocio(TurnoEntity entity){
		if(entity.getPaciente() == null) 
		{
			return new Turno(entity.getId(), entity.getFecha(), entity.getPrecio(), entity.getAsistencia(), entity.getJustifInasistencia(), entity.getDisponibilidad(), EspecialidadDAO.getInstancia().toNegocio(entity.getEspecialidad()), MedicoDAO.getInstancia().toNegocio(entity.getMedico()), null);		
			
		}
		else 
		{
			return new Turno(entity.getId(), entity.getFecha(), entity.getPrecio(), entity.getAsistencia(), entity.getJustifInasistencia(), entity.getDisponibilidad(), EspecialidadDAO.getInstancia().toNegocio(entity.getEspecialidad()), MedicoDAO.getInstancia().toNegocio(entity.getMedico()), PacienteDAO.getInstancia().toNegocio(entity.getPaciente()));		
			
		}
	}

	public boolean existeTurno(Date fecha, int idEspecialidad, String matricula) {
		boolean res = false;
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		TurnoEntity turEn = (TurnoEntity) s.createQuery("from TurnoEntity t where t.especialidad = ? and t.medico = ? and t.fecha = ?")
				.setInteger(0, idEspecialidad)
				.setString(1, matricula)
				.setTimestamp(2, fecha)
				.uniqueResult();
		s.getTransaction().commit();
		s.close();
		if (turEn != null) {
			res = true;
		}
		
		return res;
	}
	
	public Collection<Turno> findByEspecialidadMedicoYEstado(int idEspecialidad, String matricula, int estado) {
		List<Turno> resultado = new ArrayList<Turno>();
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		List<TurnoEntity> turnos = s.createQuery("from TurnoEntity t where t.medico = ? and t.especialidad = ? and t.disponibilidad = ? order by t.fecha")
				.setString(0,matricula)
				.setInteger(1,idEspecialidad)
				.setInteger(2, estado)
				.list();
		s.getTransaction().commit();
		s.close();
		for(TurnoEntity tur : turnos)
			resultado.add(toNegocio(tur));
		return resultado;
	}

	public Collection<Turno> findByMedicoYEstado(String matricula, int estado) {
		List<Turno> resultado = new ArrayList<Turno>();
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		List<TurnoEntity> turnos = s.createQuery("from TurnoEntity t where t.medico = ? and t.disponibilidad = ?")
				.setString(0,matricula)
				.setInteger(1, estado)
				.list();
		s.getTransaction().commit();
		s.close();
		for(TurnoEntity tur : turnos)
			resultado.add(toNegocio(tur));
		return resultado;
	}

	public List<Turno> findByEspecialidadYEstado(int idEspecialidad, int estado) {
		List<Turno> resultado = new ArrayList<Turno>();
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		List<TurnoEntity> turnos = s.createQuery("from TurnoEntity t where t.especialidad = ? and t.disponibilidad = ? order by t.fecha")
				.setInteger(0,idEspecialidad)
				.setInteger(1, estado)
				.list();
		s.getTransaction().commit();
		s.close();
		for(TurnoEntity tur : turnos)
			resultado.add(toNegocio(tur));
		return resultado;		
	}
	
	public List<Turno> findByEspecialidadYEstadoPosteriores(int idEspecialidad, int estado) {
		List<Turno> resultado = new ArrayList<Turno>();
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		List<TurnoEntity> turnos = s.createQuery("from TurnoEntity t where t.especialidad = ? and t.disponibilidad = ? and t.fecha >= ? order by t.fecha")
				.setInteger(0,idEspecialidad)
				.setInteger(1, estado)
				.setTimestamp(2, new Date())
				.list();
		s.getTransaction().commit();
		s.close();
		for(TurnoEntity tur : turnos)
			resultado.add(toNegocio(tur));
		return resultado;		
	}
	
	public Collection<Turno> findByEspecialidadMedicoYEstadoPosteriores(int idEspecialidad, String matricula, int estado) {
		List<Turno> resultado = new ArrayList<Turno>();
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		List<TurnoEntity> turnos = s.createQuery("from TurnoEntity t where t.medico = ? and t.especialidad = ? and t.disponibilidad = ? and t.fecha >= ? order by t.fecha")
				.setString(0,matricula)
				.setInteger(1,idEspecialidad)
				.setInteger(2, estado)
				.setTimestamp(3, new Date())
				.list();
		s.getTransaction().commit();
		s.close();
		for(TurnoEntity tur : turnos)
			resultado.add(toNegocio(tur));
		return resultado;
	}



}
