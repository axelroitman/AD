package daos;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import entities.UsuarioEntity;
import exceptions.UsuarioException;
import hibernate.HibernateUtil;
import modelo.Usuario;

public class UsuarioDAO {
	public static UsuarioDAO instancia;
	
	public static UsuarioDAO getInstancia(){
		if(instancia == null)
			instancia = new UsuarioDAO();
		return instancia;
	}
	
	public List<Usuario> getUsuarios(){
		List<Usuario> resultado = new ArrayList<Usuario>();
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		List<UsuarioEntity> usuarios = s.createQuery("from UsuarioEntity order by nombre").list();
		s.getTransaction().commit();
		s.close();
		for(UsuarioEntity pe : usuarios)
			resultado.add(toNegocio(pe));
		return resultado;
	}
	
	public Usuario findById(int id) throws UsuarioException{
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		UsuarioEntity usuario = (UsuarioEntity) s.createQuery("from UsuarioEntity u where u.id = ?")
				.setInteger(0, id)
				.uniqueResult();
		if(usuario == null)
		{	
			return null;
		}	
		return toNegocio(usuario);
	}
	
	public Usuario findByNombreDeUsuario(String usuario) throws UsuarioException{
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		UsuarioEntity usr = (UsuarioEntity) s.createQuery("from UsuarioEntity u where u.usr = ?")
				.setString(0, usuario)
				.uniqueResult();
		
		if(usr == null)
		{	
			return null;
		}	
		return toNegocio(usr);
	}
	
	public void save(Usuario usuario){
		UsuarioEntity aGrabar = toEntity(usuario);
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.save(aGrabar);
		s.getTransaction().commit();
		s.close();
	}
	
	public void update(Usuario usuario){

		UsuarioEntity aGrabar = toEntity(usuario);
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.update(aGrabar);
		s.getTransaction().commit();
		s.close();
	}
	
	public void delete(Usuario usuario) throws UsuarioException {
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		UsuarioEntity aEliminar = (UsuarioEntity) s.createQuery("from UsuarioEntity u where u.id = ?")
				.setInteger(0, usuario.getId())
				.uniqueResult();
		s.delete(aEliminar);
		s.getTransaction().commit();
		s.close();
	}
	
	UsuarioEntity toEntity(Usuario usuario){
		return new UsuarioEntity(usuario.getId(),usuario.getUsr(),usuario.getPass(),usuario.getNombre(),usuario.getTelefono(),usuario.getDni(),usuario.getFechaNac());
	} 
	
	Usuario toNegocio(UsuarioEntity entity){
		return new Usuario (entity.getUsr(),entity.getPass(),entity.getNombre(),entity.getTelefono(),entity.getDni(),entity.getFechaNac());
	}
}
