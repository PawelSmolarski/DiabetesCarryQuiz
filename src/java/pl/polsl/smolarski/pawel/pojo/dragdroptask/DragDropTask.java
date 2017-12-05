/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.smolarski.pawel.pojo.dragdroptask;

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
 * ORM entity class for Drag Drop Task 
 *
 * @author psmolarski
 * @version 1.0
 */
@Entity
@NamedQuery(name="FIND_ALL_DRAG_DROP", query="select t from DragDropTask t") 
@Table(name = "dragdrop_task")
public class DragDropTask implements Serializable, Taskable
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

    @Column(name = "case_5")
    private String case5;

    @Column(name = "answer_a")
    private String answerA;

    @Column(name = "answer_b")
    private String answerB;

    @Column(name = "answer_relations")
    private String answerRelations;

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.id);
        return hash;
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
        final DragDropTask other = (DragDropTask) obj;
        if (!Objects.equals(this.id, other.id))
        {
            return false;
        }
        return true;
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

    public String getCase5()
    {
        return case5;
    }

    public void setCase5(String case5)
    {
        this.case5 = case5;
    }

    public String getAnswerA()
    {
        return answerA;
    }

    public void setAnswerA(String answerA)
    {
        this.answerA = answerA;
    }

    public String getAnswerB()
    {
        return answerB;
    }

    public void setAnswerB(String answerB)
    {
        this.answerB = answerB;
    }

    public String getAnswerRelations()
    {
        return answerRelations;
    }

    public void setAnswerRelations(String answerRelations)
    {
        this.answerRelations = answerRelations;
    }

    public String getQuestion()
    {
        return question;
    }

    public void setQuestion(String question)
    {
        this.question = question;
    }

    public DragDropTask(Integer id, String question, String case1, String case2, String case3, String case4, String case5, String answerA, String answerB, String answerRelations)
    {
        this.id = id;
        this.question = question;
        this.case1 = case1;
        this.case2 = case2;
        this.case3 = case3;
        this.case4 = case4;
        this.case5 = case5;
        this.answerA = answerA;
        this.answerB = answerB;
        this.answerRelations = answerRelations;
    }

    public DragDropTask()
    {
    }

    /**
     * Method to get own type of task
     *
     * @return type of specific task
     */
    @Override
    public TaskType getType()
    {
        return TaskType.DRAG_DROP;
    }

}
