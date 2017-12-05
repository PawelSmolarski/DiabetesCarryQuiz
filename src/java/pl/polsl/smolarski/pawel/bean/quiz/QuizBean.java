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
import static pl.polsl.smolarski.pawel.utils.ViewUtils.addMessage;

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
    private String username;

    /**
     * Variable which contains boolean of duration of game
     */
    private boolean isStarted;

    /**
     * Variable which contains list of all tasks
     */
    private List<? extends Taskable> tasks;

    /**
     * Variable which contains user points
     */
    private int points;

    /**
     * Variable which contains present task of game
     */
    private Taskable presentTask;

    public List<? extends Taskable> getTasks()
    {
        return tasks;
    }

    public void setTasks(List<Taskable> tasks)
    {
        this.tasks = tasks;
    }

    public int getPoints()
    {
        return points;
    }
    
    public int getNonStaticPoints()
    {
        return points;
    }

    public void setPoints(int points)
    {
        this.points = points;
    }

    public String getUsername()
    {
        return username;
    }

    public boolean isStarted()
    {
        return isStarted;
    }

    public void setIsStarted(boolean isStarted)
    {
        this.isStarted = isStarted;
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
            addMessage("Error!", "Choose your name");
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
    public void game()
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
    private void redirectEndOfGame()
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
    private void redirectNextTask()
    {

        System.out.println("Gra trwa " + points);
        presentTask = tasks.get(0);
        tasks.remove(0);
        getView(presentTask.getType());
    }

    public Taskable getPresentTask()
    {
        return presentTask;
    }

    public void setPresentTask(Taskable presentTask)
    {
        this.presentTask = presentTask;
    }

    /**
     * Method to create all tasks for present game
     *
     * @return List of received tasks
     */
    private List<? extends Taskable> receiveTasks()
    {
        List<Taskable> tasks = new ArrayList<>();

        tasks.addAll(getRandomTasks(ABCDTaskBean.getAllrecords()));
        tasks.addAll(getRandomTasks(DiagramTaskBean.getAllrecords()));
        tasks.addAll(getRandomTasks(PickListTaskBean.getAllrecords()));
        tasks.addAll(getRandomTasks(DragDropTaskBean.getAllrecords()));

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
