package project.service;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import project.entity.UserMovie;

import java.util.List;

/**
 * Created by Stephen on 1/17/2015.
 */
public class UserMovieService {

    private static UserMovieService instance = new UserMovieService();
    private SessionFactory factory;

    private UserMovieService() {
        try {
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");

            ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
            factory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static UserMovieService getInstance() {
        return instance;
    }

    public List<UserMovie> getUserMovies(long userid) {

        List<UserMovie> result = null;

        Session session = factory.openSession();

        Transaction tr = null;

        try {
            tr = session.beginTransaction();

            result = (List<UserMovie>) session.createQuery("FROM UserMovie WHERE userId = :id").setParameter("id", userid).list();

            tr.commit();

        } catch (HibernateException e) {
            if (tr != null) tr.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return result;
    }

    public List<UserMovie> getUserMovies() {

        List<UserMovie> result = null;

        Session session = factory.openSession();

        Transaction tr = null;

        try {
            tr = session.beginTransaction();

            result = (List<UserMovie>) session.createQuery("FROM UserMovie").list();

            tr.commit();

        } catch (HibernateException e) {
            if (tr != null) tr.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return result;
    }

    public void insertUserMovie(long userid, int movieid, int matches) {

        Session session = factory.openSession();

        Transaction tr = null;

        try {
            tr = session.beginTransaction();

            List<UserMovie> lum = (List<UserMovie>) session.createQuery("FROM UserMovie WHERE userId = :id AND movieid = :movid").setParameter("id", userid).setParameter("movid", movieid).list();

            UserMovie um;

            int multiplier = 1 + matches;

            if (lum.isEmpty()) {

                um = new UserMovie();

                um.setUserId(userid);
                um.setMovieid(movieid);

                um.setCounts(1 * multiplier);
            } else {

                um = lum.get(0);

                um.setCounts((um.getCounts() + 1) * multiplier);
            }

            session.saveOrUpdate(um);

            tr.commit();

        } catch (HibernateException e) {
            if (tr != null) tr.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void resetCount(long userid) {
        Session session = factory.openSession();

        Transaction tr = null;

        try {
            tr = session.beginTransaction();

            session.createQuery("DELETE FROM UserMovie WHERE userId=:id").setParameter("id", userid);

            tr.commit();

        } catch (HibernateException e) {
            if (tr != null) tr.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

}
