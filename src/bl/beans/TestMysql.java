package bl.beans;

import java.sql.Timestamp;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class TestMysql {
    public static void main(String[] args) {

        try {
            SessionFactory sf = new Configuration().configure().buildSessionFactory();
            Session session = sf.openSession();

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
