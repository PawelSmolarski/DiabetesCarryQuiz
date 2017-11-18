/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.smolarski.pawel.dao.diagramtask;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pl.polsl.smolarski.pawel.pojo.diagramtask.DiagramTask;
import pl.polsl.smolarski.pawel.utils.SessionUtils;

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
     *
     * @throws HibernateException
     */
    public void addTask(DiagramTask task) throws HibernateException
    {
        addTaskTransaction(task);
    }

    private void addTaskTransaction(DiagramTask task)
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
     *
     * @throws HibernateException
     */
    public void deleteTask(int id) throws HibernateException
    {

        deleteTransaction(id);

    }

    private void deleteTransaction(int id)
    {
        Transaction trans;
        Session session = SessionUtils.getSESSION_FACTORY().openSession();
        trans = session.beginTransaction();
        DiagramTask task = (DiagramTask) session.load(DiagramTask.class, id);
        session.delete(task);
        trans.commit();
    }

    /**
     * Method to get tasks from table
     *
     * @return List of get tasks
     *
     * @throws HibernateException
     */
    public List<DiagramTask> retrieveTask() throws HibernateException
    {

        List tasks = new ArrayList();

        tasks = retrieveTaskTransaction();

        return tasks;
    }

    private List<DiagramTask> retrieveTaskTransaction()
    {
        Session session = SessionUtils.getSESSION_FACTORY().openSession();
        session.beginTransaction();
        Query query = session.createQuery("select t from DiagramTask t");
        List tasks = new ArrayList();
        tasks = query.list();
        session.getTransaction().commit();
        return tasks;
    }

    /**
     * Method to update task
     *
     * @param task to update
     *
     * @throws HibernateException
     */
    public void updateTask(DiagramTask task) throws HibernateException
    {

        updateTaskTransaction(task);

    }

    private void updateTaskTransaction(DiagramTask task)
    {
        Transaction trans;
        Session session = SessionUtils.getSESSION_FACTORY().openSession();
        trans = session.beginTransaction();
        session.update(task);
        trans.commit();
    }

}
