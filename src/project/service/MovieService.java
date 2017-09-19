package project.service;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import project.entity.Movies;

import java.util.List;


/**
 * Created by Stephen on 1/15/2015.
 */
public class MovieService {

    private static MovieService instance = new MovieService();
    private SessionFactory factory;

    private MovieService() {
        try {
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");

            ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
            factory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static MovieService getInstance() {
        return instance;
    }

    public List<Movies> getMovies() {

        List<Movies> result = null;

        Session session = factory.openSession();

        Transaction tr = null;

        try {

            tr = session.beginTransaction();

            result = (List<Movies>) session.createQuery("FROM Movies").list();

            tr.commit();

        } catch (HibernateException e) {
            if (tr != null) tr.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return result;
    }

    public Movies getMovie(int l) {

        Movies result = null;

        Session session = factory.openSession();

        Transaction tr = null;

        try {

            tr = session.beginTransaction();

            result = (Movies) session.get(Movies.class, l);

            tr.commit();

        } catch (HibernateException e) {
            if (tr != null) tr.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return result;
    }


    public List<Movies> getMovies(int starting, int max) {

        List<Movies> result = null;

        Session session = factory.openSession();

        Transaction tr = null;

        try {

            tr = session.beginTransaction();

            result = (List<Movies>) session.createQuery("FROM Movies").setFirstResult(starting).setMaxResults(1000).list();

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
