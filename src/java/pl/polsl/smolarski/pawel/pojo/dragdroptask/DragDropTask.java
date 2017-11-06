/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.smolarski.pawel.pojo.dragdroptask;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import pl.polsl.smolarski.pawel.utils.TaskType;
import pl.polsl.smolarski.pawel.utils.Taskable;

@Entity
@Table(name = "dragdrop_task")
public class DragDropTask implements Serializable, Taskable
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

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

    public DragDropTask()
    {
    }

    public DragDropTask(Integer id, String case1, String case2, String case3, String case4, String case5, String answerA, String answerB, String answerRelations)
    {
        this.id = id;
        this.case1 = case1;
        this.case2 = case2;
        this.case3 = case3;
        this.case4 = case4;
        this.case5 = case5;
        this.answerA = answerA;
        this.answerB = answerB;
        this.answerRelations = answerRelations;
    }

    @Override
    public TaskType getType()
    {
        return TaskType.DRAG_DROP;
    }

}