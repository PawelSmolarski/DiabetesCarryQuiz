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
import static pl.polsl.smolarski.pawel.utils.SessionUtils.addMessage;
import pl.polsl.smolarski.pawel.pojo.abcdtask.ABCDTask;

/**
 * Class which provides CRUD methods for ABCDTask
 * 
 * @author psmolarski
 */
public class ABCDDao 
{
    private static ABCDTask emptyObject = new ABCDTask(-1, "", "", "", "", "", "");
    
     public void addTask(ABCDTask task)
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
            ABCDTask task=(ABCDTask)session.load(ABCDTask.class, id);
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
    
    public List<ABCDTask> getbyID(int taskNo)
    {
        List<ABCDTask> task1=new ArrayList();
       
        Transaction trans;
        Session session=SessionUtils.getSessionFactory().openSession();
        try 
        {
            trans=session.beginTransaction();
            Query query=session.createQuery("select t from ABCDTask t where id= :id");
            query.setInteger("id", taskNo);
            task1=query.list();
            trans.commit();
            if(task1.isEmpty())
            {
                task1.add(emptyObject);
                throw new Exception("No data for this id");
            }
        }
        catch(Exception e)
        {
            addMessage("Error!", "Please try again.");
            e.printStackTrace();
        }
        return task1;
    }
    
    public List<ABCDTask> retrieveTask()
    {
       
        List tasks=new ArrayList();
        ABCDTask task1=new ABCDTask();
        Session session=SessionUtils.getSessionFactory().openSession();
        try
        {
            session.beginTransaction();
            Query query=session.createQuery("select t from ABCDTask t");
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
    
    public void updateTask(ABCDTask task)
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
