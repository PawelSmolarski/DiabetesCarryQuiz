/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.smolarski.pawel.bean.dragdroptask;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import pl.polsl.smolarski.pawel.bean.quiz.QuizBean;
import org.primefaces.model.diagram.DefaultDiagramModel;
import pl.polsl.smolarski.pawel.dao.dragdroptask.DragDropDao;
import pl.polsl.smolarski.pawel.pojo.dragdroptask.DragDropTask;

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

    @PostConstruct
    public void init()
    {
        //TODO sczytanie do listy
        task = (DragDropTask) QuizBean.getPresentTask();

        if (task != null)
        {
        }
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

    public void validate()
    {
        //TODO

    }

}
