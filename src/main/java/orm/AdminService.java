package orm;

import model.Admin;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import util.HibernateUtil;

import org.hibernate.query.Query;

import java.util.logging.Level;
import java.util.logging.Logger;

public class AdminService {
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
    public Admin getAdminByEmailAndPass(String emailAdmin, String password){
        Session session = this.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query query =session.createQuery("from Admin where emailAdmin = :emailAdmin and password = :password");
        query.setParameter("emailAdmin", emailAdmin);
        query.setParameter("password",password);
        Admin admin = (Admin) query.uniqueResult();
        session.close();
        return admin;

    }

}
