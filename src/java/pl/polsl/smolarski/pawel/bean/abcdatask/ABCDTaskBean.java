/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.smolarski.pawel.bean.abcdatask;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.hibernate.HibernateException;
import pl.polsl.smolarski.pawel.bean.BeanTaskable;
import pl.polsl.smolarski.pawel.bean.quiz.QuizBean;
import pl.polsl.smolarski.pawel.dao.abcdtask.ABCDDao;
import pl.polsl.smolarski.pawel.pojo.abcdtask.ABCDTask;
import static pl.polsl.smolarski.pawel.utils.ViewUtils.addMessage;

/**
 * Bean class for ABCD task.
 *
 * @author psmolarski
 * @version 1.0
 */
@ManagedBean
@ViewScoped
public class ABCDTaskBean implements Serializable, BeanTaskable<ABCDTask>
{

    /**
     * Static final variable to use dao
     */
    private static final ABCDDao TASK_DAO = new ABCDDao();

    /**
     * Method to receive all tasks
     *
     * @return List of get tasks
     */
    public static List<ABCDTask> getAllrecords()
    {
        List<ABCDTask> tasks = new ArrayList();
        try
        {
            tasks = TASK_DAO.retrieveTask();
        }
        catch (HibernateException e)
        {
            addMessage("Error!", "Please try again.");
            Logger.getLogger(ABCDDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return tasks;
    }

    /**
     * Variable of user choose
     */
    private int answer;
    
    /**
     * Variable of session bean to control game
     */
    @ManagedProperty("#{quizBean}")
    private QuizBean quizBean;
    
    /**
     * Local task variable
     */
    private ABCDTask task = new ABCDTask();

    /**
     * Clearing local task
     */
    @Override
    public void clearTask()
    {
        this.task = new ABCDTask();
    }

    /**
     * Method which use DAO to delete task
     *
     * @param task to delete
     */
    @Override
    public void delete(ABCDTask task)
    {
        try
        {
            TASK_DAO.deleteTask(task.getId());
            addMessage("Success!", "Task deleted correctly.");

        }
        catch (HibernateException e)
        {
            addMessage("Error!", "Please try again.");
            Logger.getLogger(ABCDTaskBean.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    public int getAnswer()
    {
        return answer;
    }

    public void setAnswer(int answer)
    {
        this.answer = answer;
    }

    public QuizBean getQuizBean()
    {
        return quizBean;
    }

    public void setQuizBean(QuizBean quizBean)
    {
        this.quizBean = quizBean;
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
     * Init method
     */
    @PostConstruct
    public void init()
    {
        task = (ABCDTask) quizBean.getPresentTask();
    }

    /**
     * Method which use DAO to save task
     *
     * @param task to save
     */
    @Override
    public void save(ABCDTask task)
    {
        try
        {
            TASK_DAO.addTask(task);
            addMessage("Success!", "Task added correctly.");

        }
        catch (HibernateException e)
        {
            addMessage("Error!", "Please try again.");
            Logger.getLogger(ABCDTaskBean.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    /**
     * Method to update present task
     */
    @Override
    public void update()
    {
        try
        {
            TASK_DAO.updateTask(task);
            addMessage("Success!", "Task updated correctly.");

        }
        catch (HibernateException e)
        {
            addMessage("Error!", "Please try again.");
            Logger.getLogger(ABCDTaskBean.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    /**
     * Method validation of user choose
     */
    @Override
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
                quizBean.setPoints(quizBean.getPoints() + 1);
            }

            quizBean.game();

        }
    }
}
