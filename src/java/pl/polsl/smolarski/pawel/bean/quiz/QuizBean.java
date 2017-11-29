/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.smolarski.pawel.bean.quiz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import pl.polsl.smolarski.pawel.bean.abcdatask.ABCDTaskBean;
import pl.polsl.smolarski.pawel.bean.diagramtask.DiagramTaskBean;
import pl.polsl.smolarski.pawel.bean.dragdroptask.DragDropTaskBean;
import pl.polsl.smolarski.pawel.bean.picklist.PickListTaskBean;
import pl.polsl.smolarski.pawel.utils.Taskable;
import pl.polsl.smolarski.pawel.dao.player.PlayerDao;
import pl.polsl.smolarski.pawel.pojo.player.Player;
import pl.polsl.smolarski.pawel.utils.SessionUtils;
import pl.polsl.smolarski.pawel.utils.TaskType;

/**
 *
 * @author psmolarski
 * @version 1.0
 */
@ManagedBean
@SessionScoped
public class QuizBean implements Serializable
{

    /**
     * Variable which contains user name
     */
    private static String username;

    /**
     * Variable which contains boolean of duration of game
     */
    private static boolean isStarted;

    /**
     * Variable which contains list of all tasks
     */
    private static List<? extends Taskable> tasks;

    /**
     * Variable which contains user points
     */
    private static int points;

    /**
     * Variable which contains present task of game
     */
    private static Taskable presentTask;

    public static List<? extends Taskable> getTasks()
    {
        return tasks;
    }

    public static void setTasks(List<Taskable> tasks)
    {
        QuizBean.tasks = tasks;
    }

    public static int getPoints()
    {
        return points;
    }
    
    public int getNonStaticPoints()
    {
        return points;
    }

    public static void setPoints(int points)
    {
        QuizBean.points = points;
    }

    public String getUsername()
    {
        return username;
    }

    public static boolean isStarted()
    {
        return isStarted;
    }

    public static void setIsStarted(boolean isStarted)
    {
        QuizBean.isStarted = isStarted;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    /**
     * Method that invokes start of game
     */
    public void start()
    {
        if (username == null || username.isEmpty())
        {
            SessionUtils.addMessage("Error!", "Choose your name");
        }
        else
        {
            if (isStarted == false)
            {
                playerSessionCreate();
            }
            if (tasks == null)
            {
                tasks = receiveTasks();
            }

            game();
        }
    }

    /**
     * Method which creates player session
     */
    private void playerSessionCreate()
    {
        isStarted = true;
        points = 0;
        HttpSession session = SessionUtils.getSession();
        session.setAttribute("player", username);
    }

    /**
     * Method in which game last
     */
    public static void game()
    {
        if (tasks == null || tasks.isEmpty())
        {
            redirectEndOfGame();
        }
        else
        {
            redirectNextTask();
        }
    }

    /**
     * Method in which game is ending
     */
    private static void redirectEndOfGame()
    {
        tasks = null;
        isStarted = false;
        Player player = new Player();
        player.setName(username);
        player.setPoints(points);
        new PlayerDao().addPlayer(player);
        HttpSession session = SessionUtils.getSession();
        session.removeAttribute("player");
        FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, "/game/hall_of_fame.xhtml");

    }

    /**
     * Method to invoke next task
     */
    private static void redirectNextTask()
    {

        System.out.println("Gra trwa " + points);
        presentTask = tasks.get(0);
        tasks.remove(0);
        getView(presentTask.getType());
    }

    public static Taskable getPresentTask()
    {
        return presentTask;
    }

    public static void setPresentTask(Taskable presentTask)
    {
        QuizBean.presentTask = presentTask;
    }

    /**
     * Method to create all tasks for present game
     *
     * @return List of received tasks
     */
    private static List<? extends Taskable> receiveTasks()
    {
        List<Taskable> tasks = new ArrayList<>();

        tasks.addAll(getRandomTasks(ABCDTaskBean.getallrecords()));
        tasks.addAll(getRandomTasks(DiagramTaskBean.getallrecords()));
        tasks.addAll(getRandomTasks(PickListTaskBean.getallrecords()));
        tasks.addAll(getRandomTasks(DragDropTaskBean.getallrecords()));

        Collections.shuffle(tasks, new Random(System.currentTimeMillis()));
        return tasks;
    }

    /**
     * Method which redirect to corresponding view of task
     *
     * @param type of Task
     */
    public static void getView(TaskType type)
    {
        FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, type.getURL());
    }

    /**
     * Method which get 3 random tasks from given List of all tasks
     *
     * @param tasks to randomly get
     * @return List of 3 randomed specific tasks
     */
    private static List<? extends Taskable> getRandomTasks(List<? extends Taskable> tasks)
    {
        List<Taskable> randomTasks = new ArrayList<>();
        Random rand = new Random(System.currentTimeMillis());
        if (tasks.size() <= 3)
        {
            randomTasks.addAll(tasks);
            return randomTasks;
        }
        else
        {
            for (int i = 0; i < 3; ++i)
            {
                int randomIndex = rand.nextInt(tasks.size());
                randomTasks.add(tasks.get(randomIndex));
                tasks.remove(randomIndex);
            }
            return randomTasks;
        }
    }
}
