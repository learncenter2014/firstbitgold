package dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.core.spi.LogbackLock;
import bl.beans.DeviceInfoBean;
import bl.beans.UserBean;

/**
 * Simply maintained session life cycle, in the future, you guys implement a
 * actual generic dao function.
 * 
 * @author pli
 *
 */
public class MysqlHibernateDao {
    private static Logger LOG = LoggerFactory.getLogger(MysqlHibernateDao.class);

    public static final SessionFactory sessionFactory;

    static {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            Configuration configuration = new Configuration();
            configuration.configure();
            ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
            sessionFactory = new Configuration().configure().buildSessionFactory(serviceRegistry);

        } catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            LOG.error("Initial SessionFactory creation failed {}.", ex);
            throw new ExceptionInInitializerError(ex);
        }
    }


    public static Session currentSession() throws HibernateException {
        Session s = sessionFactory.getCurrentSession();
        return s;
    }

    public static void closFactory() {
        LOG.warn("close sessionFactory");
        if (sessionFactory != null)
            sessionFactory.close();
    }

    public static void main(String[] args) {
        Session session = MysqlHibernateDao.currentSession();
        session.beginTransaction();
        Query query = session.createQuery("from UserBean as p where p.Name=?");
        query.setString(0, "Ben");
        List<UserBean> userBeans = query.list();
        for (UserBean ub : userBeans) {
            System.out.println(ub.getName());
        }
    }

}
