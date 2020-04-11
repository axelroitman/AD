package hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import entities.EspecialidadEntity;
import entities.ListaDeEsperaEntity;
import entities.MedicoEntity;
import entities.PacienteEntity;
import entities.TurnoEntity;
import entities.UsuarioEntity;



 
public class HibernateUtil
{
    private static final SessionFactory sessionFactory;
    static
    {
        try
        {
        	 AnnotationConfiguration config = new AnnotationConfiguration();
             config.addAnnotatedClass(EspecialidadEntity.class);
             config.addAnnotatedClass(ListaDeEsperaEntity.class);
             config.addAnnotatedClass(MedicoEntity.class);
             config.addAnnotatedClass(PacienteEntity.class);
             config.addAnnotatedClass(TurnoEntity.class); 
             config.addAnnotatedClass(UsuarioEntity.class);             
             sessionFactory = config.buildSessionFactory();
        }
        catch (Throwable ex)
        {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
 
    public static SessionFactory getSessionFactory()
    {
        return sessionFactory;
    }
}
