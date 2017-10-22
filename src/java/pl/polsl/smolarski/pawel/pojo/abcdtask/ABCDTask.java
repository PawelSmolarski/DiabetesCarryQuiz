/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.smolarski.pawel.pojo.abcdtask;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import pl.polsl.smolarski.pawel.dao.abcdtask.ABCDDao;

/**
 * ORM entity class for ABCDTask
 * Provides CRUD using methods
 *
 * @author psmolarski
 */
@ManagedBean
@Entity
@Table(name = "abcd_task")
public class ABCDTask implements Serializable 
{
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    
    @Column(name="question")
    private String question;
    
    @Column(name="case_1")
    private String case1;
    
    @Column(name="case_2")
    private String case2;
        
    @Column(name="case_3")
    private String case3;
    
    @Column(name="case_4")
    private String case4;
                
    @Column(name="answer")
    private String answer;

    public ABCDTask(int id, String question, String case1, String case2, String case3, String case4, String answer) {
        this.id = id;
        this.question = question;
        this.case1 = case1;
        this.case2 = case2;
        this.case3 = case3;
        this.case4 = case4;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public ABCDTask() {
    }

    public ABCDTask(int id) {
        this.id = id;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCase1() {
        return case1;
    }

    public void setCase1(String case1) {
        this.case1 = case1;
    }

    public String getCase2() {
        return case2;
    }

    public void setCase2(String case2) {
        this.case2 = case2;
    }

    public String getCase3() {
        return case3;
    }

    public void setCase3(String case3) {
        this.case3 = case3;
    }

    public String getCase4() {
        return case4;
    }

    public void setCase4(String case4) {
        this.case4 = case4;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
    
    /**
     *  Method which use DAO to save task
     */
    public void save()
    {
        ABCDDao taskDao=new ABCDDao();
        taskDao.addTask(this);
    }

    /**
     * Method which use DAO to delete task
     */
    public void delete()
    {    
        ABCDDao taskDao=new ABCDDao();
        taskDao.deleteTask(id);
    }

    /**
     * Method which use DAO to get specific task
     * @return 
     */
    public List<ABCDTask> getbyid()
    { 
        ABCDDao taskDao=new ABCDDao();
        List<ABCDTask> task=taskDao.getbyID(id);
       
        question = task.get(0).question;
        case1 = task.get(0).case1;
        case2 = task.get(0).case2;
        case3 = task.get(0).case3;
        case4 = task.get(0).case4;
        answer = task.get(0).answer;
        
        return task;
    }

    public List<ABCDTask> getallrecords()
    {
        ABCDDao taskDao=new ABCDDao();
        List<ABCDTask> tasks=taskDao.retrieveTask();
        return tasks;
    }

    public void update()
    {
        ABCDDao taskDao=new ABCDDao();
        taskDao.updateTask(this);
    }
    
}
