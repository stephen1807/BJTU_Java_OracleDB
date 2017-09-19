package project.service;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import java.util.List;

/**
 * Created by Stephen on 1/20/2015.
 */
public class GenreService {

    private static GenreService instance = new GenreService();
    private SessionFactory factory;

    private GenreService() {

        try {
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");

            ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
            factory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }

    }

    public static GenreService getInstance() {
        return instance;
    }

    public List<String> getGenres(long l) {

        List<String> result = null;

        Session session = factory.openSession();

        Transaction tr = null;

        try {

            tr = session.beginTransaction();

            result = (List<String>) session.createQuery("SELECT genre FROM Genres WHERE movieid = :movid").setParameter("movid", l).list();

            tr.commit();

        } catch (HibernateException e) {
            if (tr != null) tr.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return result;
    }
}
