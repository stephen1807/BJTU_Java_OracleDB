package project.service;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import project.entity.UserInfo;

/**
 * Created by Stephen on 1/17/2015.
 */
public class UserInfoService {

    private static UserInfoService instance = new UserInfoService();
    private SessionFactory factory;

    private UserInfoService() {
        try {
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");

            ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
            factory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static UserInfoService getInstance() {
        return instance;
    }

    public UserInfo getUserInfo(long id) {

        UserInfo result = null;

        Session session = factory.openSession();

        Transaction tr = null;

        try {
            tr = session.beginTransaction();

            result = (UserInfo) session.get(UserInfo.class, id);

            tr.commit();

        } catch (HibernateException e) {
            if (tr != null) tr.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return result;
    }

    public void updateUserInfo(UserInfo newData) {

        Session session = factory.openSession();

        Transaction tr = null;

        try {
            tr = session.beginTransaction();

            session.saveOrUpdate(newData);

            tr.commit();

        } catch (HibernateException e) {
            if (tr != null) tr.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
