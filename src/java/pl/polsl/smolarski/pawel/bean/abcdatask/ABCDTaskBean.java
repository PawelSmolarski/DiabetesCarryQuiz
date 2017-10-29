/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.smolarski.pawel.bean.abcdatask;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import pl.polsl.smolarski.pawel.bean.interfaces.Taskable;
import pl.polsl.smolarski.pawel.dao.abcdtask.ABCDDao;
import pl.polsl.smolarski.pawel.pojo.abcdtask.ABCDTask;
import javax.faces.event.ActionEvent;
import pl.polsl.smolarski.pawel.bean.quiz.QuizBean;
import static pl.polsl.smolarski.pawel.utils.SessionUtils.addMessage;
/**
 *
 * @author psmolarski
 */
@ManagedBean
@SessionScoped
public class ABCDTaskBean implements Serializable {
    
    private ABCDTask task = new ABCDTask();
    private static final ABCDDao taskDao = new ABCDDao();
    private int answer;

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }
    
    
    /**
     *  Method which use DAO to save task
     */
    public void save(ABCDTask task)
    {
        taskDao.addTask(task);
    }

    /**
     * Method which use DAO to delete task
     */
    public void delete(ABCDTask task)
    {    
        taskDao.deleteTask(task.getId());
    }


    public static List<ABCDTask> getallrecords()
    {
        List<ABCDTask> tasks=taskDao.retrieveTask();
        return tasks;
    }

    public void update()
    {
        taskDao.updateTask(task);
    }
    
    
    public ABCDTask getTask() {
        return task;
    }

    public void setTask(ABCDTask task) {
        this.task = task;
    }

    public void clearTask()
    {
        this.task = new ABCDTask();
    }
    
    // TODO walidacja
    public void validate()
    {
        if(answer==0)
        {
            addMessage("Error!", "Choose one of options");
        }
        else
        {
            if(task.getAnswer()==this.answer)
            {
                QuizBean.setPoints(QuizBean.getPoints() + 1);
                System.out.println("Działaaaaa " + QuizBean.getPoints());
                QuizBean.game();
            }
            else
            {
            System.out.println("Działa");
            System.out.println("Answer: " + answer);
            QuizBean.game();
            }
        }
    }
}
