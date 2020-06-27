package daos;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import entities.PacienteEntity;
import entities.UsuarioEntity;
import exceptions.PacienteException;
import exceptions.UsuarioException;
import hibernate.HibernateUtil;
import modelo.Paciente;
import modelo.Usuario;

public class PacienteDAO {
	public static PacienteDAO instancia;
		
	public static PacienteDAO getInstancia(){
		if(instancia == null)
			instancia = new PacienteDAO();
		return instancia;
	}
	
	public List<Paciente> getPacientes(){
		List<Paciente> resultado = new ArrayList<Paciente>();
		List<PacienteEntity> pacientes = new ArrayList<PacienteEntity>();

		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		pacientes = (List<PacienteEntity>) s.createQuery("select p from PacienteEntity p inner join p.usuario order by nombre").list();		
		s.getTransaction().commit();
		
		for(PacienteEntity pe : pacientes)
			resultado.add(toNegocio(pe));
		s.close();
		
		return resultado;
	}

	public Paciente findById(int id) throws PacienteException{
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		PacienteEntity paciente = (PacienteEntity) s.createQuery("from PacienteEntity p where p.id = ?")
				.setInteger(0, id)
				.uniqueResult();
		if(paciente == null)
		{	
			return null;
		}	
		return toNegocio(paciente);
	}
	
	public Paciente findByIdUsr(int idUsr) throws PacienteException{
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		PacienteEntity paciente = (PacienteEntity) s.createQuery("from PacienteEntity p where p.usuario.id = ?")
				.setInteger(0, idUsr)
				.uniqueResult();
		if(paciente == null)
		{	
			return null;
		}	
		return toNegocio(paciente);
	}
	
	public void save(Paciente paciente){
		PacienteEntity aGrabar = toEntity(paciente);
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.save(aGrabar);
		s.getTransaction().commit();
		s.close();
	}
	
	public void update(Paciente paciente){

		PacienteEntity aGrabar = toEntity(paciente);
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.update(aGrabar);
		s.getTransaction().commit();
		s.close();
	}
	
	public void delete(Paciente paciente) throws UsuarioException {
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		PacienteEntity aEliminar = (PacienteEntity) s.createQuery("from PacienteEntity p where p.id = ?")
				.setInteger(0, paciente.getIdPaciente())
				.uniqueResult();
		s.delete(aEliminar);
		s.getTransaction().commit();
		s.close();
	}
	
	Paciente toNegocio(PacienteEntity entity){
		return new Paciente (entity.getId(),entity.getUsuario().getIdUsr(),entity.getUsuario().getUsr(),entity.getUsuario().getPass(), entity.getUsuario().getNombre(), entity.getUsuario().getTelefono(), entity.getUsuario().getDni(), entity.getUsuario().getFechaNac(), entity.getFechaVtoCuota());
	}
	
	PacienteEntity toEntity(Paciente paciente){
		return new PacienteEntity(paciente.getIdPaciente(), new UsuarioEntity(paciente.getIdUsr(),paciente.getUsr(),paciente.getPass(), paciente.getNombre(), paciente.getTelefono(), paciente.getDni(), paciente.getFechaNac()),paciente.getFechaVtoCuota());
	} 
	
	
}
