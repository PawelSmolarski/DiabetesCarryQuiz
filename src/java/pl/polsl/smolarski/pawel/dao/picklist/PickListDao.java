/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.smolarski.pawel.dao.picklist;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pl.polsl.smolarski.pawel.dao.TaskRepository;
import pl.polsl.smolarski.pawel.pojo.picklisttask.PickListTask;
import pl.polsl.smolarski.pawel.utils.SessionUtils;

/**
 * Class which provides CRUD methods for Pick List task
 *
 * @author psmolarski
 * @version 1.0
 */
public class PickListDao implements TaskRepository<PickListTask>
{

    /**
     * Method to add task to table
     *
     * @param task to add
     *
     * @throws HibernateException
     */
    @Override
    public void addTask(PickListTask task) throws HibernateException
    {
        addTaskTransaction(task);
    }

    private void addTaskTransaction(PickListTask task)
    {
        Transaction trans;
        Session session = SessionUtils.getSESSION_FACTORY().openSession();
        trans = session.beginTransaction();
        session.save(task);
        trans.commit();
        session.close();
    }

    /**
     * Method to delete task to table
     *
     * @param id to delete
     *
     * @throws HibernateException
     */
    @Override
    public void deleteTask(int id) throws HibernateException
    {
        deleteTransaction(id);
    }

    private void deleteTransaction(int id)
    {
        Transaction trans;
        Session session = SessionUtils.getSESSION_FACTORY().openSession();
        trans = session.beginTransaction();
        PickListTask task = (PickListTask) session.load(PickListTask.class, id);
        session.delete(task);
        trans.commit();
        session.close();
    }

    /**
     * Method to get tasks from table
     *
     * @return List of get tasks
     *
     * @throws HibernateException
     */
    @Override
    public List<PickListTask> retrieveTask() throws HibernateException
    {
        List tasks = new ArrayList();
        tasks = retrieveTaskTransaction();
        return tasks;
    }

    private List<PickListTask> retrieveTaskTransaction()
    {
        Session session = SessionUtils.getSESSION_FACTORY().openSession();
        Query query = session.getNamedQuery("FIND_ALL_PICK_LIST");
        List tasks = new ArrayList();
        tasks = query.list();
        session.close();
        return tasks;
    }

    /**
     * Method to update task
     *
     * @param task to update
     *
     * @throws HibernateException
     */
    @Override
    public void updateTask(PickListTask task) throws HibernateException
    {
        updateTaskTransaction(task);
    }

    private void updateTaskTransaction(PickListTask task)
    {
        Transaction trans;
        Session session = SessionUtils.getSESSION_FACTORY().openSession();
        trans = session.beginTransaction();
        session.update(task);
        trans.commit();
        session.close();
    }

}
