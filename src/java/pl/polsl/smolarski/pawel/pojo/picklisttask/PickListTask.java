/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.smolarski.pawel.pojo.picklisttask;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import pl.polsl.smolarski.pawel.utils.TaskType;
import pl.polsl.smolarski.pawel.utils.Taskable;

/**
 * ORM entity class for PickListTask
 *
 * @author psmolarski
 * @version 1.0
 */
@Entity
@NamedQuery(name = "FIND_ALL_PICK_LIST", query = "select t from PickListTask t")
@Table(name = "picklist_task")
public class PickListTask implements Serializable, Taskable
{

    @Column(name = "answer")
    private String answer;

    @Column(name = "case_1")
    private String case1;

    @Column(name = "case_2")
    private String case2;

    @Column(name = "case_3")
    private String case3;

    @Column(name = "case_4")
    private String case4;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "question")
    private String question;

    public PickListTask(Integer id, String question, String case1, String case2, String case3, String case4, String answer)
    {
        this.id = id;
        this.question = question;
        this.case1 = case1;
        this.case2 = case2;
        this.case3 = case3;
        this.case4 = case4;
        this.answer = answer;
    }

    public PickListTask()
    {
    }

    public PickListTask(Integer id)
    {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        final PickListTask other = (PickListTask) obj;
        if (!Objects.equals(this.id, other.id))
        {
            return false;
        }
        return true;
    }

    public String getAnswer()
    {
        return answer;
    }

    public void setAnswer(String answer)
    {
        this.answer = answer;
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

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getQuestion()
    {
        return question;
    }

    public void setQuestion(String question)
    {
        this.question = question;
    }

    /**
     * Method to get own type of task
     *
     * @return type of specific task
     */
    @Override
    public TaskType getType()
    {
        return TaskType.PICK_LIST;
    }

    @Override
    public int hashCode()
    {
        int hash = 5;
        hash = 47 * hash + Objects.hashCode(this.id);
        return hash;
    }

}
