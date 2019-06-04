package main.java.project.service;

import main.java.project.DataBase.DataBase;
import main.java.project.model.Game;
import org.apache.log4j.Logger;


public class LibService {
    final static Logger logger = Logger.getLogger(String.valueOf(LibService.class));
    public static boolean addGame(Game game) {

        Integer k = DataBase.InsertGame(game);
        if (k > -1) {
            logger.info(String.format("GAME REGISTERED ID %s ",k));
            return true;
        }
        return false;
    }
}
