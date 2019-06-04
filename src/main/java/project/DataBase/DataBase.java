package main.java.project.DataBase;

import main.java.project.model.Game;
import main.java.project.model.User;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.imageio.spi.ServiceRegistry;
import java.util.ArrayList;
import java.util.List;

public class DataBase {

    public static SessionFactory factory;
    public static ServiceRegistry serviceRegistry;

    public static int insertUser(User u)
    {
        factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        Session session = factory.openSession();
        Transaction tx = null;
        Integer userIdSaved = null;
        try {
            tx = session.beginTransaction();
            userIdSaved = (Integer) session.save(u);
            tx.commit();
        } catch(HibernateException ex) {
            if(tx != null)
                tx.rollback();
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return userIdSaved;
    }

    public static Integer InsertGame(Game game){
        factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        Session session = factory.openSession();
        Transaction tx = null;
        Integer gameIdSaved = null;
        try {
            tx = session.beginTransaction();
            gameIdSaved = (Integer) session.save(game);
            tx.commit();
        } catch(HibernateException ex) {
            if(tx != null)
                tx.rollback();
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return gameIdSaved;
    }

    public static List listUsers()
    {
        factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        Session sesn = factory.openSession();
        Transaction tx = null;
        List users = new ArrayList();
        try{
            tx = sesn.beginTransaction();
            users = (List)sesn.createQuery("From User").list();
            tx.commit();
        } catch(HibernateException e) {
            e.printStackTrace();
        } finally {
            sesn.close();
        }
        return users;
    }

    public static boolean selectUser(String username,String password){
        List<User> users = listUsers();
        Boolean valid = false;

        for (User user : users){
            if(user.getPassword().equals(password) && user.getUsername().equals(username)){
                valid = true;
                break;
            }
        }
        return valid;
   }

}