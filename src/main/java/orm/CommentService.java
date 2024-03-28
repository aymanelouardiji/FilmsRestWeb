
package orm;

import model.Comment;
import model.Film;
import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import util.HibernateUtil;

import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CommentService {
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
    public void addCmnt(Comment comment, int userId, int id){
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        User user = session.get(User.class, userId);
        Film film = session.get(Film.class, id);

        // Set the associated user and film to the comment
        comment.setUser(user);
        comment.setFilm(film);

        session.save(comment);
        session.getTransaction().commit();
        System.out.println("Comment added successfully, : "+comment);
    }
    public List<Comment> getAllComments(){
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<Comment> CommentList = session.createQuery("from Comment", Comment.class).list();
        session.getTransaction().commit();
        return CommentList;
    }
    public Comment getCommentById(int id){
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        Comment comment = session.getReference(Comment.class,Integer.valueOf(id));
        session.getTransaction().commit();
        System.out.println("This is the comment that you want to see : " + comment);
        return comment;
    }
    public Comment deleteComment(int id){
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        Comment comment = session.getReference(Comment.class,Integer.valueOf(id));
        String info = "";
        if(null != comment){
            info = comment.toString();
            session.remove(comment);
        }
        session.getTransaction().commit();
        logger.info("Comment Removed Successfully, Comment details = " +comment);
        return comment;
    }
}
