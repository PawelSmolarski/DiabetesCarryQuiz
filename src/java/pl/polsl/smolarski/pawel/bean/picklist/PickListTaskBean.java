/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.smolarski.pawel.bean.picklist;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.hibernate.HibernateException;
import org.primefaces.event.SelectEvent;
import pl.polsl.smolarski.pawel.bean.quiz.QuizBean;
import pl.polsl.smolarski.pawel.dao.picklist.PickListDao;
import pl.polsl.smolarski.pawel.pojo.picklisttask.PickListTask;
import org.primefaces.event.TransferEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.DualListModel;
import pl.polsl.smolarski.pawel.utils.SessionUtils;
import static pl.polsl.smolarski.pawel.utils.SessionUtils.addMessage;

/**
 * Class bean for PickList task
 *
 * @author psmolarski
 * @version 1.0
 */
@ManagedBean
@ViewScoped
public class PickListTaskBean implements Serializable
{

    private PickListTask task = new PickListTask();
    private static final PickListDao TASK_DAO = new PickListDao();

    private DualListModel<String> tasks;
    private List<String> tasksSource;
    private List<String> tasksTarget;
    private Map<String, String> questionCases;

    @PostConstruct
    public void init()
    {

        task = (PickListTask) QuizBean.getPresentTask();

        if (task != null)
        {
            tasksSource = new ArrayList<>();
            tasksTarget = new ArrayList<>();

            questionCases = new HashMap<>();
            questionCases.put("1", task.getCase1());
            questionCases.put("2", task.getCase2());
            questionCases.put("3", task.getCase3());
            questionCases.put("4", task.getCase4());

            for (String key : questionCases.keySet())
            {
                tasksSource.add(questionCases.get(key));
            }

            tasks = new DualListModel<String>(tasksSource, tasksTarget);
        }
    }

    /**
     * Method on tranfer of answer
     *
     * @param event of transfer
     */
    public void onTransfer(TransferEvent event)
    {

        addMessage("ss", "on transfer");

    }

    /**
     * Method on select of answer
     *
     * @param event of transfer
     */
    public void onSelect(SelectEvent event)
    {
        addMessage("ss", "on select");
    }

    /**
     * Method on unselect of answer
     *
     * @param event of transfer
     */
    public void onUnselect(UnselectEvent event)
    {
        addMessage("ss", "on unselect");
    }

    /**
     * Method on reorder of answer
     *
     */
    public void onReorder()
    {
        addMessage("ss", "on reorder");
    }

    public DualListModel<String> getTasks()
    {
        return tasks;
    }

    public void setTasks(DualListModel<String> tasks)
    {
        this.tasks = tasks;
    }

    /**
     * Method which use DAO to save task
     *
     * @param task to save
     */
    public void save(PickListTask task)
    {
        try
        {
            TASK_DAO.addTask(task);
            addMessage("Success!", "Task added correctly.");

        }
        catch (HibernateException e)
        {
            addMessage("Error!", "Please try again.");
            Logger.getLogger(PickListTaskBean.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    /**
     * Method which use DAO to delete task
     *
     * @param task to delete
     */
    public void delete(PickListTask task)
    {
        try
        {
            TASK_DAO.deleteTask(task.getId());
            addMessage("Success!", "Task deleted correctly.");
        }
        catch (HibernateException e)
        {
            addMessage("Error!", "Please try again.");
            Logger.getLogger(PickListTaskBean.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    /**
     * Method to get all tasks
     *
     * @return List of tasks
     */
    public static List<PickListTask> getallrecords()
    {
        List<PickListTask> tasks = new ArrayList();

        try
        {
            tasks = TASK_DAO.retrieveTask();
        }
        catch (HibernateException e)
        {
            addMessage("Error!", "Please try again.");
            Logger.getLogger(PickListTaskBean.class.getName()).log(Level.SEVERE, null, e);
        }
        return tasks;
    }

    /**
     * Method to update task
     */
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
            Logger.getLogger(PickListTaskBean.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public PickListTask getTask()
    {
        return task;
    }

    public void setTask(PickListTask task)
    {
        this.task = task;
    }

    /**
     * Method to clear task
     */
    public void clearTask()
    {
        this.task = new PickListTask();
    }

    /**
     * Method to validate user answer
     */
    public void validate()
    {
        if (tasks.getTarget().isEmpty())
        {
            addMessage("Error!", "Choose at least one");
        }
        else
        {
            List<String> tasksTarget = tasks.getTarget();
            List<String> answersKeys = new ArrayList<>();
            answersKeys = Arrays.asList(task.getAnswer().split(";"));

            List<String> answers = new ArrayList<>();

            for (String s : answersKeys)
            {
                answers.add(questionCases.get(s));
            }

            if (SessionUtils.areEqualLists(answers, tasksTarget))
            {
                QuizBean.setPoints(QuizBean.getPoints() + 1);
                System.out.println("player get points pick list");
            }

            QuizBean.game();

        }
    }
}
