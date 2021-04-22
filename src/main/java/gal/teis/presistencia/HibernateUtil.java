package gal.teis.presistencia;

import java.util.Objects;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 * Esta clase permite arrancar Hibernate y crear una instancia de
 * SessionFactory que utiliza el fichero de configuración (hibernate.cfg.xml)
 * donde está la información de la conexión con nuestra base de datos. A partir
 * de este objeto se puede abrir una sesión y crear transacciones.
 * El patrón Singleton garantiza que no se crearán dos de SessionFactory
 */
/**
 *
 * @author Esther Ferreiro
 */
public class HibernateUtil {

    private static final SessionFactory sessionFactory;

    static {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }


    /**
     * Close caches and connection pools
     */
    public static void shutdown() {
        if (getSessionFactory().isOpen()) {
            getSessionFactory().close();
        }
    }

}
