/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.smolarski.pawel.pojo.diagramtask;

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
 * ORM entity class for DiagramTask
 *
 * @author psmolarski
 * @version 1.0
 */
@Entity
@NamedQuery(name = "FIND_ALL_DIAGRAM", query = "select t from DiagramTask t")
@Table(name = "diagram_task")
public class DiagramTask implements Serializable, Taskable
{

    @Column(name = "answer_relations")
    private String answerRelations;

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

    @Column(name = "case_6")
    private String case6;

    @Column(name = "case_7")
    private String case7;

    @Column(name = "case_8")
    private String case8;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    public DiagramTask(Integer id, String case1, String case2, String case3, String case4, String case5, String case6, String case7, String case8, String answerRelations)
    {
        this.id = id;
        this.case1 = case1;
        this.case2 = case2;
        this.case3 = case3;
        this.case4 = case4;
        this.case5 = case5;
        this.case6 = case6;
        this.case7 = case7;
        this.case8 = case8;
        this.answerRelations = answerRelations;
    }

    public DiagramTask()
    {
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
        final DiagramTask other = (DiagramTask) obj;
        if (!Objects.equals(this.id, other.id))
        {
            return false;
        }
        return true;
    }

    public String getAnswerRelations()
    {
        return answerRelations;
    }

    public void setAnswerRelations(String answerRelations)
    {
        this.answerRelations = answerRelations;
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

    public String getCase6()
    {
        return case6;
    }

    public void setCase6(String case6)
    {
        this.case6 = case6;
    }

    public String getCase7()
    {
        return case7;
    }

    public void setCase7(String case7)
    {
        this.case7 = case7;
    }

    public String getCase8()
    {
        return case8;
    }

    public void setCase8(String case8)
    {
        this.case8 = case8;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    /**
     * Method to get own type of task
     *
     * @return type of specific task
     */
    @Override
    public TaskType getType()
    {
        return TaskType.DIAGRAM;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.id);
        return hash;
    }

}
