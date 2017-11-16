/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.smolarski.pawel.dao.abcdtask;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pl.polsl.smolarski.pawel.utils.SessionUtils;
import pl.polsl.smolarski.pawel.pojo.abcdtask.ABCDTask;

/**
 * Class which provides CRUD methods for ABCDTask
 *
 * @author psmolarski
 * @version 1.0
 */
public class ABCDDao
{

    /**
     * Method to add task to table
     *
     * @param task to add
     */
    public void addTask(ABCDTask task)
    {

        addTaskTransaction(task);

    }

    private void addTaskTransaction(ABCDTask task)
    {
        Transaction trans;
        Session session = SessionUtils.getSESSION_FACTORY().openSession();
        trans = session.beginTransaction();
        session.save(task);
        trans.commit();
    }

    /**
     * Method to delete task to table
     *
     * @param id to delete
     */
    public void deleteTask(int id)
    {

        deleteTransaction(id);

    }

    private void deleteTransaction(int id)
    {
        Transaction trans;
        Session session = SessionUtils.getSESSION_FACTORY().openSession();
        trans = session.beginTransaction();
        ABCDTask task = (ABCDTask) session.load(ABCDTask.class, id);
        session.delete(task);
        trans.commit();
    }

    /**
     * Method to get tasks from table
     *
     * @return List of get tasks
     */
    public List<ABCDTask> retrieveTask()
    {

        return retrieveTaskTransaction();

    }

    private List<ABCDTask> retrieveTaskTransaction()
    {
        Session session = SessionUtils.getSESSION_FACTORY().openSession();
        session.beginTransaction();
        Query query = session.createQuery("select t from ABCDTask t");
        List tasks = new ArrayList();
        tasks = query.list();
        session.getTransaction().commit();
        return tasks;
    }

    /**
     * Method to update task
     *
     * @param task to update
     */
    public void updateTask(ABCDTask task)
    {

        updateTaskTransaction(task);

    }

    private void updateTaskTransaction(ABCDTask task)
    {
        Transaction trans;
        Session session = SessionUtils.getSESSION_FACTORY().openSession();
        trans = session.beginTransaction();
        session.update(task);
        trans.commit();
    }

}
