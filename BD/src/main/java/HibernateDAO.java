import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class HibernateDAO implements UserBDController {
    private static final String DEFAULT_SELECT_STATEMENT = "id != 0";
    private static final String DEFAULT_SELECT_GROUP_BY = "id";
    private static final String DEFAULT_SELECT_ORDER_BY = "id";

    @Override
    @Deprecated
    public User get(UUID uuid) throws SQLException {
        return null;
// TODO: 9/29/2020
    }

    public User get(int id){
        return HibernateSessionFactory.getSessionFactory()
                .openSession()
                .get(User.class, id);
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
        Session session = HibernateSessionFactory.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query<User> query = session.createQuery("from User"+" order by "+orderBy == null ?
                DEFAULT_SELECT_ORDER_BY : orderBy.toString());
        query.setFirstResult(limit*page);
        query.setMaxResults(limit);
        list = query.list();
        return list;
    }

    @Override
    @Deprecated
    public void connect(String url, String user, String pass) {
    }


}
