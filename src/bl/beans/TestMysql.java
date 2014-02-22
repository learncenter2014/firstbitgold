package bl.beans;

import java.sql.Timestamp;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistryBuilder;

public class TestMysql {
    public static void main(String[] args) {

        try {
            Configuration cfg = new Configuration();
            cfg.configure();
            SessionFactory sessionFactory = cfg.buildSessionFactory(new ServiceRegistryBuilder()
            .applySettings(cfg.getProperties()).buildServiceRegistry());         
            Session session = sessionFactory.openSession();

            Transaction tx = session.beginTransaction();
            User user = new User();
            user.setUsername("peter");
            user.setPassword("12345");
            user.setEmail("116352437@qq.com");
            user.setCreateTime(new Timestamp(System.currentTimeMillis()));
            user.setModifyTime(new Timestamp(System.currentTimeMillis()));
            session.save(user);

            tx.commit();
            session.close();

        } catch (HibernateException err) {
            err.printStackTrace();
        }
    }
}
