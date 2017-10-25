/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.smolarski.pawel.bean.abcdatask;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import pl.polsl.smolarski.pawel.dao.abcdtask.ABCDDao;
import pl.polsl.smolarski.pawel.pojo.abcdtask.ABCDTask;

/**
 *
 * @author psmolarski
 */
@ManagedBean
@ViewScoped
public class ABCDTaskBean implements Serializable {
    
    private ABCDTask task = new ABCDTask();

    /**
     *  Method which use DAO to save task
     */
    public void save(ABCDTask task)
    {
        ABCDDao taskDao=new ABCDDao();
        taskDao.addTask(task);
    }

    /**
     * Method which use DAO to delete task
     */
    public void delete(ABCDTask task)
    {    
        ABCDDao taskDao=new ABCDDao();
        taskDao.deleteTask(task.getId());
    }


    public List<ABCDTask> getallrecords()
    {
        ABCDDao taskDao=new ABCDDao();
        List<ABCDTask> tasks=taskDao.retrieveTask();
        return tasks;
    }

    public void update(ABCDTask task)
    {
        ABCDDao taskDao=new ABCDDao();
        taskDao.updateTask(task);
    }
    
    
    public ABCDTask getTask() {
        return task;
    }

    public void setTask(ABCDTask task) {
        this.task = task;
    }


    
}
