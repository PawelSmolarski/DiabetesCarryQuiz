/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.smolarski.pawel.dao.diagramtask;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pl.polsl.smolarski.pawel.pojo.diagramtask.DiagramTask;
import pl.polsl.smolarski.pawel.utils.SessionUtils;
import static pl.polsl.smolarski.pawel.utils.SessionUtils.addMessage;

/**
 *
 * @author g50-70
 */
public class DiagramTaskDao {
    
     public void addTask(DiagramTask task)
    {
        Transaction trans;
        Session session=SessionUtils.getSessionFactory().openSession();
        try 
        {
            trans=session.beginTransaction();
            session.save(task);
            trans.commit();
            addMessage("Success!", "Task added correctly.");

        } 
        catch (Exception e) 
        {
            addMessage("Error!", "Please try again.");
            e.printStackTrace();
        }
    }
    
    public void deleteTask(int id)
    {
        Transaction trans;
        Session session=SessionUtils.getSessionFactory().openSession();
        try 
        {
            trans=session.beginTransaction();
            DiagramTask task=(DiagramTask)session.load(DiagramTask.class, id);
            session.delete(task);
            trans.commit();
            addMessage("Success!", "Task deleted correctly.");

        } 
        catch (Exception e) 
        {
            addMessage("Error!", "Please try again.");
            e.printStackTrace();
        }
    }
    
    public List<DiagramTask> retrieveTask()
    {
       
        List tasks=new ArrayList();
        Session session=SessionUtils.getSessionFactory().openSession();
        try
        {
            session.beginTransaction();
            Query query=session.createQuery("select t from DiagramTask t");
            tasks=query.list();
            session.getTransaction().commit();
           
        }
        catch(Exception e)
        {
            addMessage("Error!", "Please try again.");
            e.printStackTrace();
        }
        return tasks;
    }
    
    public void updateTask(DiagramTask task)
    {
        Transaction trans;
        Session session=SessionUtils.getSessionFactory().openSession();
        try 
        {
            trans=session.beginTransaction();
            session.update(task);
            trans.commit();
            addMessage("Success!", "Task updated correctly.");
        }
        catch(Exception e)
        {
            addMessage("Error!", "Please try again.");
            e.printStackTrace();
        }
        
    }
    
    
}
