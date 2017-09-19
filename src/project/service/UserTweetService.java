package project.service;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import project.entity.UserTweet;

import java.util.Calendar;
import java.util.List;

/**
 * Created by Stephen on 1/17/2015.
 */
public class UserTweetService {

    private static UserTweetService instance = new UserTweetService();
    private SessionFactory factory;

    private UserTweetService() {
        try {
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");

            ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
            factory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static UserTweetService getInstance() {
        return instance;
    }

    public void insertUserTweet(UserTweet ut) {

        Session session = factory.openSession();

        Transaction tr = null;

        try {
            tr = session.beginTransaction();

            session.save(ut);

            tr.commit();

        } catch (HibernateException e) {
            if (tr != null) tr.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public List<UserTweet> getUserTweet(long userID) {

        List<UserTweet> result = null;

        Session session = factory.openSession();

        Transaction tr = null;

        try {
            tr = session.beginTransaction();

            result = (List<UserTweet>) session.createQuery("FROM UserTweet WHERE userId = :id").setParameter("id", userID).list();

            tr.commit();

        } catch (HibernateException e) {
            if (tr != null) tr.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return result;
    }

    public void cleanUserTweet(long userid) {

        Session session = factory.openSession();

        Transaction tr = null;

        try {
            tr = session.beginTransaction();

            Calendar cal = Calendar.getInstance();

            cal.add(Calendar.YEAR, -1);

            session.createQuery("DELETE FROM UserTweet WHERE userId = :id AND tweetDate < :date").setParameter("id", userid).setParameter("date", cal);

            tr.commit();

        } catch (HibernateException e) {
            if (tr != null) tr.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

}
