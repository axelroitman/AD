package daos;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.Session;

import entities.ListaDeEsperaEntity;
import entities.PacienteEntity;
import hibernate.HibernateUtil;
import modelo.Especialidad;
import modelo.ItemListaDeEspera;
import modelo.Medico;
import modelo.Paciente;

public class ListaDeEsperaDAO {
	public static ListaDeEsperaDAO instancia;

    public static ListaDeEsperaDAO getInstancia(){
		if(instancia == null)
            instancia = new ListaDeEsperaDAO();
            
		return instancia;
	}
    
    public Collection<ItemListaDeEspera> findByEspecialidad(int idEspecialidad) {
		Collection<ItemListaDeEspera> resultado = null;
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		Collection<ListaDeEsperaEntity> listaEsp = (Collection<ListaDeEsperaEntity>) s.createQuery("from ListaDeEsperaEntity l where l.especialidad")
				.setInteger(0,idEspecialidad)
				.list();
		s.getTransaction().commit();
		s.close();
		for(ListaDeEsperaEntity le : listaEsp) {
			resultado.add(toNegocio(le));
		}
		return resultado;
	}
    
    public Collection<ItemListaDeEspera> findByEspecialidadYMedico(int idEspecialidad, String matricula) {
    	Collection<ItemListaDeEspera> resultado = null;
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		Collection<ListaDeEsperaEntity> listaEsp = (Collection<ListaDeEsperaEntity>) s.createQuery("from ListaDeEsperaEntity l where l.especialidad = ? and l.medico = ?")
				.setInteger(0,idEspecialidad)
				.setString(1, matricula)
				.list();
		s.getTransaction().commit();
		s.close();
		
		for(ListaDeEsperaEntity le : listaEsp) {
			resultado.add(toNegocio(le));
		}
		return resultado;
	}

	private ItemListaDeEspera toNegocio(ListaDeEsperaEntity le) {
		Especialidad e = EspecialidadDAO.getInstancia().toNegocio(le.getEsp());
		Medico m = MedicoDAO.getInstancia().toNegocio(le.getMedico());
		Paciente p = PacienteDAO.getInstancia().toNegocio(le.getPaciente());
		
		return new ItemListaDeEspera(le.getId(), e, p,m);
	}
	
	public boolean existePacienteEnListaE(int idEspecialidad, int idPaciente) {
		boolean res = false;
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		Collection<ListaDeEsperaEntity> listaEsp = (Collection<ListaDeEsperaEntity>) s.createQuery("from ListaDeEsperaEntity l where l.idEspecialidad = ? and l.idPaciente = ?")
				.setInteger(0, idEspecialidad)
				.setInteger(1, idPaciente)
				.list();
		s.getTransaction().commit();
		s.close();
		if (listaEsp != null && !listaEsp.isEmpty()) {
			res = true;
		}
		
		return res;
	}
	
	public boolean existePacienteEnListaEyM(int idEspecialidad, String matricula, int idPaciente) {
		boolean res = false;
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		Collection<ListaDeEsperaEntity> listaEsp = (Collection<ListaDeEsperaEntity>) s.createQuery("from ListaDeEsperaEntity l where l.idEspecialidad = ? and l.idPaciente = ? and l.idMedico = ?")
				.setInteger(0, idEspecialidad)
				.setInteger(1, idPaciente)
				.setString(2, matricula)
				.list();
		s.getTransaction().commit();
		s.close();
		if (listaEsp != null && !listaEsp.isEmpty()) {
			res = true;
		}
		
		return res;
	}
	
	/*public void update (ListaDeEspera l) {
		ListaDeEsperaEntity aGrabar = toEntity(l);
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.update(aGrabar);
		s.getTransaction().commit();
		s.close();
	}*/
	
	public void agregarALista(ItemListaDeEspera l) {
		ListaDeEsperaEntity aGrabar = toEntity(l);
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.save(aGrabar);
		s.getTransaction().commit();
		s.close();
	}
	

	private ListaDeEsperaEntity toEntity(ItemListaDeEspera l) {
		if(l.getMedico() == null) {
			return new ListaDeEsperaEntity(l.getId(), EspecialidadDAO.getInstancia().toEntity(l.getEsp()), PacienteDAO.getInstancia().toEntity(l.getPaciente()),null);
		}
		else {
			return new ListaDeEsperaEntity(l.getId(), EspecialidadDAO.getInstancia().toEntity(l.getEsp()), PacienteDAO.getInstancia().toEntity(l.getPaciente()), MedicoDAO.getInstancia().toEntity(l.getMedico()));
		}
	}
    
}
