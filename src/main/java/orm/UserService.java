package orm;

import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import util.HibernateUtil;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserService {
    private static final Logger logger = Logger.getLogger(UserService.class.getName());
    private final SessionFactory sessionFactory = getSessionFactory();

    protected SessionFactory getSessionFactory() {
        try {
            return HibernateUtil.getSessionFactory();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Could not create SessionFactory", e);
            throw new IllegalStateException("Could not create SessionFactory");
        }
    }
    public void addUser(User user){
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        logger.info("User saved mzyan, user info : " +user);
    }
    public List<User> getAllUsers() {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<User> UsersList = session.createQuery("from User", User.class).list();
        session.getTransaction().commit();
        return UsersList;
    }
    public User getUserById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        User user =  session.getReference(User.class, Integer.valueOf(id));
        session.getTransaction().commit();
        logger.info("User loaded successfully, User details="+user);
        return user;
    }
    public void removeUser(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        User user = session.getReference(User.class, Integer.valueOf(id));
        String info="";
        if(null != user){
            info = user.toString();
            session.remove(user);
        }
        session.getTransaction().commit();
        logger.info("User deleted successfully, User details="+info);
    }
}
