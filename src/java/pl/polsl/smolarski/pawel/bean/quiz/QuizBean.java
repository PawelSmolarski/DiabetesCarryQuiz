/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.smolarski.pawel.bean.quiz;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import pl.polsl.smolarski.pawel.bean.abcdatask.ABCDTaskBean;
import pl.polsl.smolarski.pawel.bean.interfaces.Taskable;
import pl.polsl.smolarski.pawel.dao.login.LoginDao;
import pl.polsl.smolarski.pawel.dao.player.PlayerDao;
import pl.polsl.smolarski.pawel.pojo.player.Player;
import pl.polsl.smolarski.pawel.utils.SessionUtils;

/**
 *
 * @author g50-70
 */
@ManagedBean
@SessionScoped
public class QuizBean implements Serializable{
    
    private static String username;
    private static boolean isStarted;
    private static List<? extends Taskable> tasks;
    private static int points;

    public static List<? extends Taskable> getTasks() {
        return tasks;
    }

    public static void setTasks(List<Taskable> tasks) {
        QuizBean.tasks = tasks;
    }

    public static int getPoints() {
        return points;
    }

    public static void setPoints(int points) {
        QuizBean.points = points;
    }
    
    public String getUsername() {
        return username;
    }

    public static boolean isStarted() {
        return isStarted;
    }

    public static void setIsStarted(boolean isStarted) {
        QuizBean.isStarted = isStarted;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public void start()
    {
        if(isStarted == false)
        {
            isStarted = true;
            HttpSession session = SessionUtils.getSession();
            session.setAttribute("player", username);
        }
        if(tasks == null)
        {
            System.out.println("null list");
            tasks = receiveTasks();
        }

        game();
    }
    
    public static void game()
    {
                // TODO end of game
        if(tasks.isEmpty())
        {
            Player player = new Player();
            player.setName(username);
            player.setPoints(points);
            new PlayerDao().addPlayer(player);
            FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, "/game/hall_of_fame.xhtml");

        }
        else
        {
            System.out.println("Gra trwa " + points);
            Taskable presentTask = tasks.get(0);
            tasks.remove(0);
            presentTask.getView();
        }
    }
    
    private static List<? extends Taskable> receiveTasks()
    {
        List<? extends Taskable> tasks;     
        tasks = ABCDTaskBean.getallrecords();
        return tasks;
    }
}
