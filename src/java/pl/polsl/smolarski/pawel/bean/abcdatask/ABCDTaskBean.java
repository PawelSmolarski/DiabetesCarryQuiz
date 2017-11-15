/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.smolarski.pawel.bean.abcdatask;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import pl.polsl.smolarski.pawel.dao.abcdtask.ABCDDao;
import pl.polsl.smolarski.pawel.pojo.abcdtask.ABCDTask;
import pl.polsl.smolarski.pawel.bean.quiz.QuizBean;
import static pl.polsl.smolarski.pawel.utils.SessionUtils.addMessage;

/**
 * Bean class for ABCD task.
 *
 * @author psmolarski
 * @version 1.0
 */
@ManagedBean
@ViewScoped
public class ABCDTaskBean implements Serializable
{

    /**
     * Local task variable
     */
    private ABCDTask task = new ABCDTask();
    
    /**
     * Static final variable to use dao
     */
    private static final ABCDDao TASK_DAO = new ABCDDao();
    
    /**
     * Variable of user choose
     */
    private int answer;

    /**
     * Init method
     */
    @PostConstruct
    public void init()
    {
        task = (ABCDTask) QuizBean.getPresentTask();
    }

    public int getAnswer()
    {
        return answer;
    }

    public void setAnswer(int answer)
    {
        this.answer = answer;
    }

    /**
     * Method which use DAO to save task
     * @param task to save
     */
    public void save(ABCDTask task)
    {
        TASK_DAO.addTask(task);
    }

    /**
     * Method which use DAO to delete task
     * @param task to delete
     */
    public void delete(ABCDTask task)
    {
        TASK_DAO.deleteTask(task.getId());
    }

    /**
     * Method to receive all tasks
     * 
     * @return List of get tasks
     */
    public static List<ABCDTask> getallrecords()
    {
        List<ABCDTask> tasks = TASK_DAO.retrieveTask();
        return tasks;
    }

    /**
     * Method to update present task
     */
    public void update()
    {
        TASK_DAO.updateTask(task);
    }

    public ABCDTask getTask()
    {
        return task;
    }

    public void setTask(ABCDTask task)
    {
        this.task = task;
    }

    /**
     * Clearing local task
     */
    public void clearTask()
    {
        this.task = new ABCDTask();
    }

    /**
     * Method validation of user choose
     */
    public void validate()
    {
        if (answer == 0)
        {
            addMessage("Error!", "Choose one of options");
        }
        else
        {
            if (task.getAnswer() == this.answer)
            {
                QuizBean.setPoints(QuizBean.getPoints() + 1);
            }

            QuizBean.game();

        }
    }
}
