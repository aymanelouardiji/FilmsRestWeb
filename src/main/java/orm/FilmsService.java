package orm;

import model.Film;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import util.HibernateUtil;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FilmsService {
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
    public void addFilm(Film film){
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.save(film);
        session.getTransaction().commit();
        System.out.println("Film saved mzyaaan , Film infoo :" +film);
    }
    public void updateFilm (Film film){
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.merge(film);
        session.getTransaction().commit();
        System.out.println("Film saved mzyaaan , Film infoo :" +film);
    }
    public List<Film> getAllFilms(){
        Session session = this.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Film> filmsList = session.createQuery("from Film", Film.class).list();
        session.getTransaction().commit();
        return filmsList;
    }
    public Film getFilmsById(int id){
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        Film film = session.getReference(Film.class, Integer.valueOf(id));
        session.getTransaction().commit();
        System.out.println("THIS IS THE YOUR FILMS DETAILS =" + film);
        return film;
    }
    public void removeFilm(int id){
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        Film film = session.getReference(Film.class, Integer.valueOf(id));
        String info = "";
        if (null != film){
            info = film.toString();
            session.remove(film);
        }
        session.getTransaction().commit();
        logger.info("User deleted successfully, User details="+info);
    }
}
