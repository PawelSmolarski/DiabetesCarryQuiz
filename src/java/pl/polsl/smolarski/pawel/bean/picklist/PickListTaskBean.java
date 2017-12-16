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
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.hibernate.HibernateException;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.TransferEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.DualListModel;
import pl.polsl.smolarski.pawel.bean.BeanTaskable;
import pl.polsl.smolarski.pawel.bean.quiz.QuizBean;
import pl.polsl.smolarski.pawel.dao.picklist.PickListDao;
import pl.polsl.smolarski.pawel.pojo.picklisttask.PickListTask;
import pl.polsl.smolarski.pawel.utils.ModelUtils;
import static pl.polsl.smolarski.pawel.utils.ViewUtils.addMessage;

/**
 * Class bean for PickList task
 *
 * @author psmolarski
 * @version 1.0
 */
@ManagedBean
@ViewScoped
public class PickListTaskBean implements Serializable, BeanTaskable<PickListTask>
{

    /**
     * Variable to use DAO
     */
    private static final PickListDao TASK_DAO = new PickListDao();

    /**
     * Method to get all tasks
     *
     * @return List of tasks
     */
    public static List<PickListTask> getAllrecords()
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
     * Map with question and answers combination
     */
    private Map<String, String> questionCases;

    /**
     * Variable of session bean to control game
     */
    @ManagedProperty("#{quizBean}")
    private QuizBean quizBean;

    /**
     * Present task
     */
    private PickListTask task = new PickListTask();

    /**
     * List for keeping left and right side questions
     */
    private DualListModel<String> tasks;

    /**
     * List for keeping left side tasks
     */
    private List<String> tasksSource;

    /**
     * List for keeping right side tasks
     */
    private List<String> tasksTarget;

    /**
     * Method to clear task
     */
    @Override
    public void clearTask()
    {
        this.task = new PickListTask();
    }

    /**
     * Method which use DAO to delete task
     *
     * @param task to delete
     */
    @Override
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

    public QuizBean getQuizBean()
    {
        return quizBean;
    }

    public void setQuizBean(QuizBean quizBean)
    {
        this.quizBean = quizBean;
    }

    public PickListTask getTask()
    {
        return task;
    }

    public void setTask(PickListTask task)
    {
        this.task = task;
    }
    
    public DualListModel<String> getTasks()
    {
        return tasks;
    }
    
    public void setTasks(DualListModel<String> tasks)
    {
        this.tasks = tasks;
    }

    @PostConstruct
    public void init()
    {
        
        task = (PickListTask) quizBean.getPresentTask();
        
        if (task != null)
        {
            tasksSource = new ArrayList<>();
            tasksTarget = new ArrayList<>();
            
            questionCases = new HashMap<>();
            questionCases.put("1", task.getCase1());
            questionCases.put("2", task.getCase2());
            questionCases.put("3", task.getCase3());
            questionCases.put("4", task.getCase4());
            questionCases.put("", "");
            
            for (String key : questionCases.keySet())
            {
                tasksSource.add(questionCases.get(key));
            }
            
            tasks = new DualListModel<>(tasksSource, tasksTarget);
        }
    }

    /**
     * Method on reorder of answer
     *
     */
    public void onReorder()
    {
    }

    /**
     * Method on select of answer
     *
     * @param event of transfer
     */
    public void onSelect(SelectEvent event)
    {
        
    }

    /**
     * Method on tranfer of answer
     *
     * @param event of transfer
     */
    public void onTransfer(TransferEvent event)
    {
        
    }

    /**
     * Method on unselect of answer
     *
     * @param event of transfer
     */
    public void onUnselect(UnselectEvent event)
    {
    }

    /**
     * Method which use DAO to save task
     *
     * @param task to save
     */
    @Override
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
     * Method to update task
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
            Logger.getLogger(PickListTaskBean.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    /**
     * Method to validate user answer
     */
    @Override
    public void validate()
    {
        
        List<String> tasksTarget = tasks.getTarget();
        if (tasksTarget.isEmpty())
        {
            tasksTarget.add("");
        }
        List<String> answersKeys = new ArrayList<>();
        answersKeys = Arrays.asList(task.getAnswer().split(";"));
        
        List<String> answers = new ArrayList<>();
        
        for (String s : answersKeys)
        {
            answers.add(questionCases.get(s));
        }
        
        if (ModelUtils.areEqualLists(answers, tasksTarget))
        {
            quizBean.setPoints(quizBean.getPoints() + 1);
        }
        
        quizBean.game();
    }
}
