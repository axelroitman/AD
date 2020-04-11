package daos;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import entities.EspecialidadEntity;
import entities.ListaDeEsperaEntity;
import exceptions.EspecialidadException;
import hibernate.HibernateUtil;
import modelo.Especialidad;
import modelo.ListaDeEspera;

public class ListaDeEsperaDAO {
	public static ListaDeEsperaDAO instancia;

    public static ListaDeEsperaDAO getInstancia(){
		if(instancia == null)
            instancia = new ListaDeEsperaDAO();
            
		return instancia;
	}

    //REPLANTEAR. 11/04 - Axel
    
    /*public List<ListaDeEspera> getTodosLosRegistros(){
		List<ListaDeEspera> resultado = new ArrayList<ListaDeEspera>();
		Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        
		List<ListaDeEsperaEntity> listas = s.createQuery("from ListaDeEsperaEntity").list();
		s.getTransaction().commit();
        s.close();
        
		for(ListaDeEsperaEntity lee : listas)
            resultado.add(toNegocio(lee));
            
		return resultado;
    }
    
    public ListaDeEspera findById(int id) throws EspecialidadException{
		Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        
		ListaDeEsperaEntity listaDeEspera = (ListaDeEsperaEntity) s.createQuery("from ListaDeEsperaEntity e where e.id = ?")
				.setInteger(0, id)
                .uniqueResult();
                
		if(listaDeEspera == null){	
			return null;
		}	
		return toNegocio(listaDeEspera);
    }
	EspecialidadEntity toEntity(ListaDeEspera listaDeEspera){
		return new EspecialidadEntity(especialidad.getId(), especialidad.getNombre());
	} 
	
    Especialidad toNegocio(EspecialidadEntity entity){
        return new Especialidad(entity.getId(), entity.getNombre());
    }*/
}
