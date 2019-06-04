package main.java.project.service;

import main.java.project.DataBase.DataBase;
import main.java.project.model.Game;
import main.java.project.model.User;
import org.apache.log4j.Logger;

import java.sql.SQLException;

public class Auth {
    final static Logger logger = Logger.getLogger(String.valueOf(Auth.class));
    public static boolean checkUser(String username,String password){
        if(DataBase.selectUser(username,password)){
            return true;
        }
        return false;
    }

    public static boolean registerUser(User user) throws SQLException {
        Integer k = DataBase.insertUser(user);
        if(k > -1) {
            logger.info(String.format("USER REGISTERED ID %s ",k));
            return true;
        }
        return false ;
    }
}
