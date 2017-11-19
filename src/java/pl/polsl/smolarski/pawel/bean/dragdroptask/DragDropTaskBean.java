/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.smolarski.pawel.bean.dragdroptask;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.hibernate.HibernateException;
import org.primefaces.event.DragDropEvent;
import pl.polsl.smolarski.pawel.bean.quiz.QuizBean;
import pl.polsl.smolarski.pawel.dao.dragdroptask.DragDropDao;
import pl.polsl.smolarski.pawel.pojo.dragdroptask.DragDropTask;
import static pl.polsl.smolarski.pawel.utils.SessionUtils.addMessage;

/**
 * Bean class for DragDrop task.
 *
 * @author psmolarski
 * @version 1.0
 */
@ManagedBean
@ViewScoped
public class DragDropTaskBean implements Serializable
{

    /**
     * Local task variable
     */
    private DragDropTask task = new DragDropTask();

    /**
     * Constant variable for DAO
     */
    private static final DragDropDao TASK_DAO = new DragDropDao();

    /**
     * List of object to drag
     */
    private List<SpecificCase> tasksToDrop;

    /**
     * List of object to drop
     */
    private List<SpecificCase> droppedAnswerA;

    /**
     * List of object to drop
     */
    private List<SpecificCase> droppedAnswerB;

    /**
     * Map of answers
     */
    private Map<String, String> answerRelations;

    /**
     * Initialization method
     */
    @PostConstruct
    public void init()
    {
        task = (DragDropTask) QuizBean.getPresentTask();
        if (task != null)
        {
            presentTask();
        }

    }

    /**
     * Method to return answer to draggable list
     *
     * @param task to return
     */
    public void returnAnswerA(SpecificCase task)
    {
        tasksToDrop.add(task);
        droppedAnswerA.remove(task);

    }

    /**
     * Method to return answer to draggable list
     *
     * @param task to return
     */
    public void returnAnswerB(SpecificCase task)
    {

        tasksToDrop.add(task);
        droppedAnswerB.remove(task);

    }

    private void presentTask()
    {
        tasksToDrop = new ArrayList<>();
        droppedAnswerA = new ArrayList<>();
        droppedAnswerB = new ArrayList<>();
        createAnswerMap();
        tasksToDrop.add(new SpecificCase(task.getCase1(), answerRelations.get("1")));
        tasksToDrop.add(new SpecificCase(task.getCase2(), answerRelations.get("2")));
        tasksToDrop.add(new SpecificCase(task.getCase3(), answerRelations.get("3")));
        tasksToDrop.add(new SpecificCase(task.getCase4(), answerRelations.get("4")));
        tasksToDrop.add(new SpecificCase(task.getCase5(), answerRelations.get("5")));
    }

    /**
     * Method creating answer map.
     */
    private void createAnswerMap()
    {
        answerRelations = new HashMap<>();
        String answers = this.task.getAnswerRelations();
        answerRelations = Pattern.compile("\\s*;\\s*")
                .splitAsStream(answers.trim())
                .map(s -> s.split("-", 2))
                .collect(Collectors.toMap(a -> a[0], a -> a[1]));
    }

    /**
     * Method to move object after drop
     *
     * @param ddEvent of move
     */
    public void onTaskDropAnswerA(DragDropEvent ddEvent)
    {
        SpecificCase taskDrop = ((SpecificCase) ddEvent.getData());

        droppedAnswerA.add(taskDrop);
        tasksToDrop.remove(taskDrop);
    }

    /**
     * Method to move object after drop
     *
     * @param ddEvent of move
     */
    public void onTaskDropAnswerB(DragDropEvent ddEvent)
    {
        SpecificCase taskDrop = ((SpecificCase) ddEvent.getData());
        droppedAnswerB.add(taskDrop);
        tasksToDrop.remove(taskDrop);

    }

    /**
     * Method which use DAO to save task
     *
     * @param task to save
     */
    public void save(DragDropTask task)
    {
        try
        {
            TASK_DAO.addTask(task);
            addMessage("Success!", "Task added correctly.");

        }
        catch (HibernateException e)
        {
            addMessage("Error!", "Please try again.");
            Logger.getLogger(DragDropTaskBean.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    /**
     * Method which use DAO to delete task
     *
     * @param task to delete
     */
    public void delete(DragDropTask task)
    {
        try
        {
            TASK_DAO.deleteTask(task.getId());
            addMessage("Success!", "Task deleted correctly.");
        }
        catch (HibernateException e)
        {
            addMessage("Error!", "Please try again.");
            Logger.getLogger(DragDropTaskBean.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    /**
     * Method to get all tasks
     *
     * @return List of tasks
     */
    public static List<DragDropTask> getallrecords()
    {
        List<DragDropTask> tasks = new ArrayList();
        try
        {
            tasks = TASK_DAO.retrieveTask();
        }
        catch (HibernateException e)
        {
            addMessage("Error!", "Please try again.");
            Logger.getLogger(DragDropTaskBean.class.getName()).log(Level.SEVERE, null, e);
        }
        return tasks;
    }

    /**
     * Update task
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
            Logger.getLogger(DragDropTaskBean.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public DragDropTask getTask()
    {
        return task;
    }

    public void setTask(DragDropTask task)
    {
        this.task = task;
    }

    /**
     * To clear local task
     */
    public void clearTask()
    {
        this.task = new DragDropTask();
    }

    public List<SpecificCase> getTasksToDrop()
    {
        return tasksToDrop;
    }

    public void setTasksToDrop(List<SpecificCase> tasksToDrop)
    {
        this.tasksToDrop = tasksToDrop;
    }

    public List<SpecificCase> getDroppedAnswerA()
    {
        return droppedAnswerA;
    }

    public void setDroppedAnswerA(List<SpecificCase> droppedAnswerA)
    {
        this.droppedAnswerA = droppedAnswerA;
    }

    public List<SpecificCase> getDroppedAnswerB()
    {
        return droppedAnswerB;
    }

    public void setDroppedAnswerB(List<SpecificCase> droppedAnswerB)
    {
        this.droppedAnswerB = droppedAnswerB;
    }

    public Map<String, String> getAnswerRelations()
    {
        return answerRelations;
    }

    public void setAnswerRelations(Map<String, String> answerRelations)
    {
        this.answerRelations = answerRelations;
    }

    /**
     * Validation method of user answer.
     */
    public void validate()
    {
        if (isCorrect() == true)
        {
            System.out.println("points from drag drop");
            QuizBean.setPoints(QuizBean.getPoints() + 1);
        }
        QuizBean.game();

    }

    /**
     * Method to check if particular answers is correct
     *
     * @return are answers correct
     */
    private boolean isCorrect()
    {
        for (SpecificCase c : droppedAnswerA)
        {
            if (!c.getWhichAnswer().equals("1"))
            {
                return false;
            }
        }
        for (SpecificCase c : droppedAnswerB)
        {
            if (!c.getWhichAnswer().equals("2"))
            {
                return false;
            }
        }
        for(SpecificCase c: tasksToDrop)
        {
            if(!c.getWhichAnswer().equals(""))
            {
                return false;
            }
        }
        
        return true;
    }
}
