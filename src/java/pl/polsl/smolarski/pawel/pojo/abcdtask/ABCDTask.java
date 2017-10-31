/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.smolarski.pawel.pojo.abcdtask;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import pl.polsl.smolarski.pawel.utils.Taskable;
import pl.polsl.smolarski.pawel.utils.TaskType;

/**
 * ORM entity class for ABCDTask Provides CRUD using methods
 *
 * @author psmolarski
 */
@Entity
@Table(name = "abcd_task")
public class ABCDTask implements Serializable, Taskable
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "question")
    private String question;

    @Column(name = "case_1")
    private String case1;

    @Column(name = "case_2")
    private String case2;

    @Column(name = "case_3")
    private String case3;

    @Column(name = "case_4")
    private String case4;

    @Column(name = "answer")
    private Integer answer;

    public ABCDTask(Integer id, String question, String case1, String case2, String case3, String case4, Integer answer)
    {
        this.id = id;
        this.question = question;
        this.case1 = case1;
        this.case2 = case2;
        this.case3 = case3;
        this.case4 = case4;
        this.answer = answer;
    }

    public String getQuestion()
    {
        return question;
    }

    public void setQuestion(String question)
    {
        this.question = question;
    }

    public ABCDTask()
    {
    }

    public ABCDTask(Integer id)
    {
        this.id = id;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getCase1()
    {
        return case1;
    }

    public void setCase1(String case1)
    {
        this.case1 = case1;
    }

    public String getCase2()
    {
        return case2;
    }

    public void setCase2(String case2)
    {
        this.case2 = case2;
    }

    public String getCase3()
    {
        return case3;
    }

    public void setCase3(String case3)
    {
        this.case3 = case3;
    }

    public String getCase4()
    {
        return case4;
    }

    public void setCase4(String case4)
    {
        this.case4 = case4;
    }

    public Integer getAnswer()
    {
        return answer;
    }

    public void setAnswer(Integer answer)
    {
        this.answer = answer;
    }

    @Override
    public TaskType getType()
    {
        return TaskType.ABCD;
    }

}
