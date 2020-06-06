package daos;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.Session;

import entities.ListaDeEsperaEntity;
import entities.PacienteEntity;
import hibernate.HibernateUtil;
import modelo.Especialidad;
import modelo.ListaDeEspera;
import modelo.Medico;
import modelo.Paciente;

public class ListaDeEsperaDAO {
	public static ListaDeEsperaDAO instancia;

    public static ListaDeEsperaDAO getInstancia(){
		if(instancia == null)
            instancia = new ListaDeEsperaDAO();
            
		return instancia;
	}
    
    public ListaDeEspera findByEspecialidad(int idEspecialidad) {
		ListaDeEspera resultado;
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		ListaDeEsperaEntity listaEsp = (ListaDeEsperaEntity) s.createQuery("from ListaDeEsperaEntity l where l.especialidad")
				.setInteger(0,idEspecialidad)
				.uniqueResult();
		s.getTransaction().commit();
		s.close();
		resultado = toNegocio(listaEsp);
		return resultado;
	}
    
    public ListaDeEspera findByEspecialidadYMedico(int idEspecialidad, String matricula) {
		ListaDeEspera resultado;
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		ListaDeEsperaEntity listaEsp = (ListaDeEsperaEntity) s.createQuery("from ListaDeEsperaEntity l where l.especialidad = ? and l.medico = ?")
				.setInteger(0,idEspecialidad)
				.setString(1, matricula)
				.uniqueResult();
		s.getTransaction().commit();
		s.close();
		resultado = toNegocio(listaEsp);
		return resultado;
	}

	private ListaDeEspera toNegocio(ListaDeEsperaEntity le) {
		Especialidad e = EspecialidadDAO.getInstancia().toNegocio(le.getEsp());
		List<Paciente> p = new ArrayList<Paciente>();
		Medico m = MedicoDAO.getInstancia().toNegocio(le.getMedico());
		
		for(PacienteEntity pa : le.getPacientes()) {
			p.add(PacienteDAO.getInstancia().toNegocio(pa));
		}
				
		return new ListaDeEspera(le.getId(), e, p,m);
	}
	
	public boolean existeE(int idEspecialidad) {
		boolean res = false;
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		Collection<ListaDeEsperaEntity> listaEsp = (Collection<ListaDeEsperaEntity>) s.createQuery("from ListaDeEsperaEntity l where l.especialidad = ?")
				.setInteger(0, idEspecialidad)
				.list();
		s.getTransaction().commit();
		s.close();
		if (!listaEsp.isEmpty()) {
			res = true;
		}
		
		return res;
	}
	
	public boolean existeEyM(int idEspecialidad, String matricula) {
		boolean res = false;
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		ListaDeEsperaEntity listaEsp = (ListaDeEsperaEntity) s.createQuery("from ListaDeEsperaEntity l where l.especialidad = ? and l.medico = ?")
				.setInteger(0, idEspecialidad)
				.setString(1, matricula)
				.uniqueResult();
		s.getTransaction().commit();
		s.close();
		if (listaEsp != null) {
			res = true;
		}
		
		return res;
	}
	
	public void update (ListaDeEspera l) {
		ListaDeEsperaEntity aGrabar = toEntity(l);
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.update(aGrabar);
		s.getTransaction().commit();
		s.close();
	}
	

	private ListaDeEsperaEntity toEntity(ListaDeEspera l) {
		Collection<PacienteEntity> pac = null;
		
		for(Paciente p : l.getPacientes()) {
			pac.add(PacienteDAO.instancia.toEntity(p));
		}
		return new ListaDeEsperaEntity(l.getId(), EspecialidadDAO.getInstancia().toEntity(l.getEsp()), pac, MedicoDAO.getInstancia().toEntity(l.getMedico()));
	}

    
}
