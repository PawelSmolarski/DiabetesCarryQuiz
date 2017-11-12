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
import org.primefaces.event.DragDropEvent;
import pl.polsl.smolarski.pawel.bean.quiz.QuizBean;
import org.primefaces.model.diagram.DefaultDiagramModel;
import pl.polsl.smolarski.pawel.dao.dragdroptask.DragDropDao;
import pl.polsl.smolarski.pawel.pojo.dragdroptask.DragDropTask;
import static pl.polsl.smolarski.pawel.utils.SessionUtils.addMessage;

/**
 *
 * @author psmolarski
 */
@ManagedBean
@ViewScoped
public class DragDropTaskBean implements Serializable
{

    private DefaultDiagramModel model;

    private boolean suspendEvent;

    private DragDropTask task = new DragDropTask();
    private static final DragDropDao taskDao = new DragDropDao();

//    private List<String> tasksToDrop;
//    private List<String> droppedAnswerA;
//    private List<String> droppedAnswerB;
    private List<SpecificCase> tasksToDrop;
    private List<SpecificCase> droppedAnswerA;
    private List<SpecificCase> droppedAnswerB;
    private Map<String, String> answerRelations;

    private String selectedString;

    @PostConstruct
    public void init()
    {
        //TODO sczytanie do listy
        task = (DragDropTask) QuizBean.getPresentTask();
        tasksToDrop = new ArrayList<>();
        droppedAnswerA = new ArrayList<>();
        droppedAnswerB = new ArrayList<>();
        createAnswerMap();
//        try
//        {
            tasksToDrop.add(new SpecificCase(task.getCase1(), answerRelations.get("1")));
            tasksToDrop.add(new SpecificCase(task.getCase2(), answerRelations.get("2")));
            tasksToDrop.add(new SpecificCase(task.getCase3(), answerRelations.get("3")));
            tasksToDrop.add(new SpecificCase(task.getCase4(), answerRelations.get("4")));
            tasksToDrop.add(new SpecificCase(task.getCase5(), answerRelations.get("5")));
//        }
//        catch (NullPointerException e)
//        {
//            Logger.getLogger(DragDropTaskBean.class.getName()).log(Level.SEVERE, "Incompatible anser relation of record " + task.getId(), e);
//        }

        //-----------------------------------
        // TODO wymienic za to u góry i zmienic frontend
//        tasksToDrop = new ArrayList<>();
//        tasksToDrop.add("lol");
//        tasksToDrop.add("ww");
//        droppedAnswerA = new ArrayList<>();
//        droppedAnswerB = new ArrayList<>();
        //------------------------
    }

    public void returnAnswerA(SpecificCase task)
    {
        addMessage("Returned", "Success");
        tasksToDrop.add(task);
        droppedAnswerA.remove(task);

    }

    public void returnAnswerB(SpecificCase task)
    {

        tasksToDrop.add(task);
        droppedAnswerB.remove(task);

    }

    private void createAnswerMap()
    {
        answerRelations = new HashMap<>();
        // tODO uncomment
        String answers = this.task.getAnswerRelations();
        // String answers = "1-1;2-1;3-2;4-2;5-2";

        answerRelations = Pattern.compile("\\s*;\\s*")
                .splitAsStream(answers.trim())
                .map(s -> s.split("-", 2))
                .collect(Collectors.toMap(a -> a[0], a -> a[1]));
    }

    // TODO dodac
    public void onTaskDropAnswerA(DragDropEvent ddEvent)
    {
        SpecificCase task = ((SpecificCase) ddEvent.getData());

        droppedAnswerA.add(task);
        tasksToDrop.remove(task);
    }

    public void onTaskDropAnswerB(DragDropEvent ddEvent)
    {
        SpecificCase task = ((SpecificCase) ddEvent.getData());
        droppedAnswerB.add(task);
        tasksToDrop.remove(task);
    }

//    public void onTaskDropAnswerA(DragDropEvent ddEvent)
//    {
//        String task = ((String) ddEvent.getData());
//
//        droppedAnswerA.add(task);
//        tasksToDrop.remove(task);
//    }
//
//    public void onTaskDropAnswerB(DragDropEvent ddEvent)
//    {
//        String task = ((String) ddEvent.getData());
//        droppedAnswerB.add(task);
//        tasksToDrop.remove(task);
//    }
    public DefaultDiagramModel getModel()
    {
        return model;
    }

    public void setModel(DefaultDiagramModel model)
    {
        this.model = model;
    }

    public boolean isSuspendEvent()
    {
        return suspendEvent;
    }

    public void setSuspendEvent(boolean suspendEvent)
    {
        this.suspendEvent = suspendEvent;
    }

    public String getSelectedString()
    {
        return selectedString;
    }

    public void setSelectedString(String selectedString)
    {
        this.selectedString = selectedString;
    }

    /**
     * Method which use DAO to save task
     */
    public void save(DragDropTask task)
    {
        taskDao.addTask(task);
    }

    /**
     * Method which use DAO to delete task
     */
    public void delete(DragDropTask task)
    {
        taskDao.deleteTask(task.getId());
    }

    public static List<DragDropTask> getallrecords()
    {
        List<DragDropTask> tasks = taskDao.retrieveTask();
        return tasks;
    }

    public void update()
    {
        taskDao.updateTask(task);
    }

    public DragDropTask getTask()
    {
        return task;
    }

    public void setTask(DragDropTask task)
    {
        this.task = task;
    }

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

    public void validate()
    {
        //TODO
        if (isCorrect() == true)
        {
            System.out.println("points from drag drop");
            QuizBean.setPoints(QuizBean.getPoints() + 1);
        }
        QuizBean.game();

    }

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
            if (!c.getWhichAnswer().equals("2"));
            {
                return false;
            }
        }
        return true;
    }
}
