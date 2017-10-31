/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.smolarski.pawel.bean.diagramtask;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import pl.polsl.smolarski.pawel.bean.quiz.QuizBean;
import pl.polsl.smolarski.pawel.dao.diagramtask.DiagramTaskDao;
import pl.polsl.smolarski.pawel.pojo.diagramtask.DiagramTask;

/**
 *
 * @author g50-70
 */
@ManagedBean
@ViewScoped
public class DiagramTaskBean implements Serializable
{

    private DiagramTask task = new DiagramTask();
    private static final DiagramTaskDao taskDao = new DiagramTaskDao();

    @PostConstruct
    public void init()
    {
        task = (DiagramTask) QuizBean.getPresentTask();
    }

    /**
     * Method which use DAO to save task
     */
    public void save(DiagramTask task)
    {
        taskDao.addTask(task);
    }

    /**
     * Method which use DAO to delete task
     */
    public void delete(DiagramTask task)
    {
        taskDao.deleteTask(task.getId());
    }

    public static List<DiagramTask> getallrecords()
    {
        List<DiagramTask> tasks = taskDao.retrieveTask();
        return tasks;
    }

    public void update()
    {
        taskDao.updateTask(task);
    }

    public DiagramTask getTask()
    {
        return task;
    }

    public void setTask(DiagramTask task)
    {
        this.task = task;
    }

    public void clearTask()
    {
        this.task = new DiagramTask();
    }

}
