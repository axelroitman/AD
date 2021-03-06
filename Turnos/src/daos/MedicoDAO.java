package daos;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import entities.EspecialidadEntity;
import entities.MedicoEntity;
import entities.UsuarioEntity;
import exceptions.MedicoException;
import exceptions.UsuarioException;
import hibernate.HibernateUtil;
import modelo.Especialidad;
import modelo.Medico;
import modelo.Usuario;

public class MedicoDAO {

    public static MedicoDAO instancia;

    public static MedicoDAO getInstancia(){
        if (instancia == null)
            instancia = new MedicoDAO();
        return instancia;
    }

    public List<Medico> getMedicos(){
        List<Medico> resultado = new ArrayList<Medico>();
        List<MedicoEntity> medicos = new ArrayList<MedicoEntity>();

        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        medicos = (List<MedicoEntity>) s.createQuery("select m from MedicoEntity m inner join m.usuario order by nombre").list();
        s.getTransaction().commit();

        for (MedicoEntity med : medicos)
            resultado.add(toNegocio(med));
        s.close();

        return resultado;
    }

    public Medico findByMatricula(String matricula) throws MedicoException{
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
               	MedicoEntity medico = (MedicoEntity) s.createQuery("from MedicoEntity m where m.matricula = ?")
                .setString(0, matricula)
                .uniqueResult();
        if (medico == null){
            return null;
        }
        return toNegocio(medico);
    }

    public Medico findByIdUsr(int idUsr) throws MedicoException{
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
               
        MedicoEntity medico = (MedicoEntity) s.createQuery("from MedicoEntity m where m.usuario.id = ?")
                .setInteger(0, idUsr)
                .uniqueResult();
        if(medico == null){
            return null;
        }
        return toNegocio(medico);
    }
    
    public void save(Medico medico){
		MedicoEntity aGrabar = toEntity(medico);
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.save(aGrabar);
		s.getTransaction().commit();
		s.close();
    }
    
    public void update(Medico medico){

		MedicoEntity aGrabar = toEntity(medico);
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.update(aGrabar);
		s.getTransaction().commit();
		s.close();
	}
    
    public void delete(Medico medico) throws MedicoException {
		Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        
		MedicoEntity aEliminar = (MedicoEntity) s.createQuery("from MedicoEntity m where m.matricula = ?")
                .setString(0, medico.getMatricula())
				.uniqueResult(); 
		s.delete(aEliminar);
		s.getTransaction().commit();
		s.close();
    }
    
    Medico toNegocio(MedicoEntity entity){
    	Collection<Especialidad> rdoEsp = new ArrayList<Especialidad>();
    	Collection<EspecialidadEntity> esp = entity.getEspecialidades();
    	for(EspecialidadEntity e : esp) {
    		rdoEsp.add(EspecialidadDAO.getInstancia().toNegocio(e));
    	}
    	
    	return new Medico (entity.getUsuario().getIdUsr(),entity.getUsuario().getUsr(),entity.getUsuario().getPass(), entity.getUsuario().getNombre(), entity.getUsuario().getTelefono(), entity.getUsuario().getDni(), entity.getUsuario().getFechaNac(), entity.getMatricula(),rdoEsp);
	}
    
	MedicoEntity toEntity(Medico medico){
		return new MedicoEntity(new UsuarioEntity(medico.getIdUsr(),medico.getUsr(),medico.getPass(), medico.getNombre(), medico.getTelefono(), medico.getDni(), medico.getFechaNac()), medico.getMatricula());
    } 
}

