/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.smolarski.pawel.bean.picklist;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
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
 *
 * @author psmolarski
 */
@ManagedBean
@ViewScoped
public class PickListTaskBean implements Serializable
{

    private PickListTask task = new PickListTask();
    private static final PickListDao taskDao = new PickListDao();

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

//            tasksSource.add("San Francisco");
//            tasksSource.add("London");
//            tasksSource.add("Paris");
//            tasksSource.add("Istanbul");
//            tasksSource.add("Berlin");
//            tasksSource.add("Barcelona");
//            tasksSource.add("Rome");
            tasks = new DualListModel<String>(tasksSource, tasksTarget);
        }
    }

    public void onTransfer(TransferEvent event)
    {

        addMessage("ss", "on transfer");

    }

    public void onSelect(SelectEvent event)
    {
        addMessage("ss", "on select");
    }

    public void onUnselect(UnselectEvent event)
    {
        addMessage("ss", "on unselect");
    }

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
     */
    public void save(PickListTask task)
    {
        taskDao.addTask(task);
    }

    /**
     * Method which use DAO to delete task
     */
    public void delete(PickListTask task)
    {
        taskDao.deleteTask(task.getId());
    }

    public static List<PickListTask> getallrecords()
    {
        List<PickListTask> tasks = taskDao.retrieveTask();
        return tasks;
    }

    public void update()
    {
        taskDao.updateTask(task);
    }

    public PickListTask getTask()
    {
        return task;
    }

    public void setTask(PickListTask task)
    {
        this.task = task;
    }

    public void clearTask()
    {
        this.task = new PickListTask();
    }

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
            
            for(String s : answersKeys)
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
