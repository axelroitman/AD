package daos;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;

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

	public List<Turno> findByMedico(int idMedico) {
		List<Turno> resultado = new ArrayList<Turno>();
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		List<TurnoEntity> turnos = s.createQuery("from TurnoEntity t where t.medico.id = ?")
				.setInteger(0,idMedico)
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
		List<TurnoEntity> turnos = s.createQuery("from TurnoEntity t where t.especialidad.id = ? and t.medico = ?")
				.setInteger(0,idEspecialidad)
				.setString(0, matricula)
				.list();
		s.getTransaction().commit();
		s.close();
		for(TurnoEntity tur : turnos)
			resultado.add(toNegocio(tur));
		return resultado;
	}
	
	public void save(Turno turno){
		TurnoEntity aGrabar = toEntity(turno);
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
		TurnoEntity aEliminar = (TurnoEntity) s.createQuery("from TurnoEntity u where u.id = ?")
				.setInteger(0, turno.getId())
				.uniqueResult();
		s.delete(aEliminar);
		s.getTransaction().commit();
		s.close();
	}
	
	TurnoEntity toEntity(Turno turno){
		return new TurnoEntity(turno.getId(), turno.getFecha(), turno.getHora(), turno.getPrecio(), turno.getAsistencia(), turno.getJustifInasistencia(), turno.getDisponibilidad(), EspecialidadDAO.getInstancia().toEntity(turno.getEspecialidad()), MedicoDAO.getInstancia().toEntity(turno.getMedico()), PacienteDAO.getInstancia().toEntity(turno.getPaciente()));
	} 
	
	Turno toNegocio(TurnoEntity entity){
		
		return new Turno(entity.getId(), entity.getFecha(), entity.getHora(), entity.getPrecio(), entity.getAsistencia(), entity.getJustifInasistencia(), entity.getDisponibilidad(), EspecialidadDAO.getInstancia().toNegocio(entity.getEspecialidad()), MedicoDAO.getInstancia().toNegocio(entity.getMedico()), PacienteDAO.getInstancia().toNegocio(entity.getPaciente()));		
	}

}
