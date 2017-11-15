/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.smolarski.pawel.dao.diagramtask;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pl.polsl.smolarski.pawel.pojo.diagramtask.DiagramTask;
import pl.polsl.smolarski.pawel.utils.SessionUtils;
import static pl.polsl.smolarski.pawel.utils.SessionUtils.addMessage;

/**
 * Class which provides CRUD methods for DiagramTask
 *
 * @author psmolarski
 * @version 1.0
 */
public class DiagramTaskDao
{

    /**
     * Method to add task to table
     *
     * @param task to add
     */
    public void addTask(DiagramTask task)
    {

        try
        {

            addTaskTransaction(task);
        }
        catch (Exception e)
        {
            addMessage("Error!", "Please try again.");
            Logger.getLogger(DiagramTaskDao.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    private void addTaskTransaction(DiagramTask task)
    {
        Transaction trans;
        Session session = SessionUtils.getSESSION_FACTORY().openSession();
        trans = session.beginTransaction();
        session.save(task);
        trans.commit();
        addMessage("Success!", "Task added correctly.");
    }

    /**
     * Method to delete task to table
     *
     * @param id to delete
     */
    public void deleteTask(int id)
    {

        try
        {
            deleteTransaction(id);

        }
        catch (Exception e)
        {
            addMessage("Error!", "Please try again.");
            Logger.getLogger(DiagramTaskDao.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    private void deleteTransaction(int id)
    {
        Transaction trans;
        Session session = SessionUtils.getSESSION_FACTORY().openSession();
        trans = session.beginTransaction();
        DiagramTask task = (DiagramTask) session.load(DiagramTask.class, id);
        session.delete(task);
        trans.commit();
        addMessage("Success!", "Task deleted correctly.");
    }

    /**
     * Method to get tasks from table
     *
     * @return List of get tasks
     */
    public List<DiagramTask> retrieveTask()
    {

        List tasks = new ArrayList();
        try
        {

            tasks = retrieveTaskTransaction();
        }
        catch (Exception e)
        {
            addMessage("Error!", "Please try again.");
            Logger.getLogger(DiagramTaskDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return tasks;
    }

    private List<DiagramTask> retrieveTaskTransaction()
    {
        Session session = SessionUtils.getSESSION_FACTORY().openSession();
        session.beginTransaction();
        Query query = session.createQuery("select t from DiagramTask t");
        List tasks = query.list();
        session.getTransaction().commit();
        return tasks;
    }
    
    /**
     * Method to update task
     * 
     * @param task to update
     */
    public void updateTask(DiagramTask task)
    {

        try
        {
            updateTaskTransaction(task);
        }
        catch (Exception e)
        {
            addMessage("Error!", "Please try again.");
            Logger.getLogger(DiagramTaskDao.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    private void updateTaskTransaction(DiagramTask task)
    {
        Transaction trans;
        Session session = SessionUtils.getSESSION_FACTORY().openSession();
        trans = session.beginTransaction();
        session.update(task);
        trans.commit();
        addMessage("Success!", "Task updated correctly.");
    }

}
