/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.smolarski.pawel.dao.picklist;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pl.polsl.smolarski.pawel.pojo.picklisttask.PickListTask;
import pl.polsl.smolarski.pawel.utils.SessionUtils;
import static pl.polsl.smolarski.pawel.utils.SessionUtils.addMessage;

/**
 *
 * @author g50-70
 */
public class PickListDao
{
    
    public void addTask(PickListTask task)
    {

        try
        {
            addTaskTransaction(task);

        }
        catch (Exception e)
        {
            addMessage("Error!", "Please try again.");
            Logger.getLogger(PickListDao.class.getName()).log(Level.SEVERE, null, e);

        }
    }

    private void addTaskTransaction(PickListTask task)
    {
        Transaction trans;
        Session session = SessionUtils.getSessionFactory().openSession();
        trans = session.beginTransaction();
        session.save(task);
        trans.commit();
        addMessage("Success!", "Task added correctly.");
    }

    public void deleteTask(int id)
    {

        try
        {

            deleteTransaction(id);
        }
        catch (Exception e)
        {
            addMessage("Error!", "Please try again.");
            Logger.getLogger(PickListDao.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    private void deleteTransaction(int id)
    {
        Transaction trans;
        Session session = SessionUtils.getSessionFactory().openSession();
        trans = session.beginTransaction();
        PickListTask task = (PickListTask) session.load(PickListTask.class, id);
        session.delete(task);
        trans.commit();
        addMessage("Success!", "Task deleted correctly.");
    }

    public List<PickListTask> retrieveTask()
    {

        List tasks = new ArrayList();
        try
        {
            tasks = retrieveTaskTransaction();

        }
        catch (Exception e)
        {
            addMessage("Error!", "Please try again.");
            Logger.getLogger(PickListDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return tasks;
    }

    private List<PickListTask> retrieveTaskTransaction()
    {
        Session session = SessionUtils.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("select t from PickListTask t");
        List tasks = query.list();
        session.getTransaction().commit();
        return tasks;
    }

    public void updateTask(PickListTask task)
    {

        try
        {
            updateTaskTransaction(task);
        }
        catch (Exception e)
        {
            addMessage("Error!", "Please try again.");
            Logger.getLogger(PickListDao.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    private void updateTaskTransaction(PickListTask task)
    {
        Transaction trans;
        Session session = SessionUtils.getSessionFactory().openSession();
        trans = session.beginTransaction();
        session.update(task);
        trans.commit();
        addMessage("Success!", "Task updated correctly.");
    }

}
