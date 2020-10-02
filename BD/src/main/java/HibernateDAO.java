import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class HibernateDAO implements UserBDController {
    @Override
    @Deprecated
    public User get(UUID uuid) throws SQLException {
        return null;
// TODO: 9/29/2020
    }

    public User get(int id) {
        Session session = HibernateSessionFactory.getSessionFactory()
                .openSession();
        User u = session.get(User.class, id);
        session.close();
        return u;
    }

    @Override
    public void delete(User user) throws SQLException {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(user);
        tx1.commit();
        session.close();
    }

    @Override
    public void delete(int id) throws SQLException {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(id);
        tx1.commit();
        session.close();
    }

    @Override
    @Deprecated
    public void delete(UUID uuid) throws SQLException {
// TODO: 9/29/2020
    }

    @Override
    public void update(User user) throws SQLException {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(user);
        tx1.commit();
        session.close();
    }

    @Override
    public void create(User user) throws SQLException {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(user);
        tx1.commit();
        session.close();
    }

    @Override
    public List<User> getList(int limit, int page, Column orderBy) throws SQLException {
        List<User> list;
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        if (orderBy==null) orderBy = Column.ID;
        Query<User> query = session.createQuery("from User"+" order by "+ orderBy.toString());
        query.setFirstResult(limit*page);
        query.setMaxResults(limit);
        list = query.list();
        HibernateSessionFactory.getSessionFactory().close();
        return list;
    }

    @Override
    @Deprecated
    public void connect(String url, String user, String pass) {
    }


}
