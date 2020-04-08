package daos;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import entities.EspecialidadEntity;
import exceptions.EspecialidadException;
import hibernate.HibernateUtil;
import modelo.Especialidad;

public class EspecialidadDAO {

    public static EspecialidadDAO instancia;

    public static EspecialidadDAO getInstancia(){
		if(instancia == null)
            instancia = new EspecialidadDAO();
            
		return instancia;
	}

    public List<Especialidad> getEspecialidades(){
		List<Especialidad> resultado = new ArrayList<Especialidad>();
		Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        
		List<EspecialidadEntity> especialidades = s.createQuery("from EspecialidadEntity order by nombre").list();
		s.getTransaction().commit();
        s.close();
        
		for(EspecialidadEntity ee : especialidades)
            resultado.add(toNegocio(ee));
            
		return resultado;
    }
    
    public Epecialidad findById(int id) throws EspecialidadException{
		Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        
		EspecialidadEntity especialidad = (EspecialidadEntity) s.createQuery("from EspecialidadEntity e where e.id = ?")
				.setInteger(0, id)
                .uniqueResult();
                
		if(especialidad == null){	
			return null;
		}	
		return toNegocio(especialidad);
    }

    /*
        EspecialidadEntity toEntity(Especialidad especialidad){
            return new EspecialidadEntity();
        }

        Especialidad toNegocio(UsuarioEntity entity){
            return new Especialidad();
        }
    
    */ 

}
