/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.smolarski.pawel.bean.diagramtask;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.hibernate.HibernateException;
import pl.polsl.smolarski.pawel.bean.quiz.QuizBean;
import pl.polsl.smolarski.pawel.dao.diagramtask.DiagramTaskDao;
import pl.polsl.smolarski.pawel.pojo.diagramtask.DiagramTask;
import org.primefaces.model.diagram.DefaultDiagramModel;
import org.primefaces.model.diagram.DiagramModel;
import org.primefaces.model.diagram.Element;
import org.primefaces.model.diagram.connector.StraightConnector;
import org.primefaces.model.diagram.endpoint.DotEndPoint;
import org.primefaces.model.diagram.endpoint.EndPoint;
import org.primefaces.model.diagram.endpoint.EndPointAnchor;
import org.primefaces.model.diagram.endpoint.RectangleEndPoint;
import org.primefaces.model.diagram.overlay.ArrowOverlay;
import org.primefaces.model.diagram.Connection;
import pl.polsl.smolarski.pawel.bean.BeanTaskable;
import pl.polsl.smolarski.pawel.utils.ModelUtils;
import static pl.polsl.smolarski.pawel.utils.ViewUtils.addMessage;

/**
 * Bean class for Diagram task.
 *
 * @author psmolarski
 * @version 1.0
 */
@ManagedBean
@ViewScoped
public class DiagramTaskBean implements Serializable, BeanTaskable<DiagramTask>
{

    /**
     * Variable of session bean to control game
     */
    @ManagedProperty("#{quizBean}")
    private QuizBean quizBean;

    public QuizBean getQuizBean()
    {
        return quizBean;
    }

    public void setQuizBean(QuizBean quizBean)
    {
        this.quizBean = quizBean;
    }

    /**
     * Diagram Model Variable
     */
    private DefaultDiagramModel model;

    /**
     * Event of connection variable
     */
    private boolean suspendEvent;

    /**
     * Local task variable
     */
    private DiagramTask task = new DiagramTask();

    /**
     * Constant to use for dao
     */
    private static final DiagramTaskDao TASK_DAO = new DiagramTaskDao();

    /**
     * Initialization method
     */
    @PostConstruct
    public void init()
    {
        task = (DiagramTask) quizBean.getPresentTask();

        if (task != null)
        {
            createDiagramModel();
        }
    }

    /**
     * Method for creating diagram model
     */
    private void createDiagramModel()
    {
        model = new DefaultDiagramModel();
        model.setMaxConnections(-1);

        model.getDefaultConnectionOverlays().add(new ArrowOverlay(20, 20, 1, 1));
        StraightConnector connector = new StraightConnector();
        connector.setPaintStyle("{strokeStyle:'#98AFC7', lineWidth:3}");
        connector.setHoverPaintStyle("{strokeStyle:'#5C738B'}");
        model.setDefaultConnector(connector);

        Element case_1 = new Element(task.getCase1(), "10em", "6em");
        EndPoint endPoint_1 = createRectangleEndPoint(EndPointAnchor.BOTTOM);
        case_1.setDraggable(false);
        endPoint_1.setSource(true);
        endPoint_1.setId("1");
        case_1.addEndPoint(endPoint_1);

        Element case_3 = new Element(task.getCase3(), "25em", "6em");
        EndPoint endPoint_3 = createRectangleEndPoint(EndPointAnchor.BOTTOM);
        endPoint_3.setSource(true);
        endPoint_3.setId("3");
        case_3.setDraggable(false);
        case_3.addEndPoint(endPoint_3);

        Element case_5 = new Element(task.getCase5(), "40em", "6em");
        EndPoint endPoint_5 = createRectangleEndPoint(EndPointAnchor.BOTTOM);
        endPoint_5.setSource(true);
        endPoint_5.setId("5");
        case_5.setDraggable(false);
        case_5.addEndPoint(endPoint_5);

        Element case_7 = new Element(task.getCase7(), "55em", "6em");
        EndPoint endPoint_7 = createRectangleEndPoint(EndPointAnchor.BOTTOM);
        endPoint_7.setSource(true);
        endPoint_7.setId("7");
        case_7.setDraggable(false);
        case_7.addEndPoint(endPoint_7);

        Element case_2 = new Element(task.getCase2(), "10em", "24em");
        EndPoint endPoint_2 = createDotEndPoint(EndPointAnchor.AUTO_DEFAULT);
        case_2.setDraggable(false);
        endPoint_2.setTarget(true);
        endPoint_2.setId("2");
        case_2.addEndPoint(endPoint_2);

        Element case_4 = new Element(task.getCase4(), "25em", "24em");
        EndPoint endPoint_4 = createDotEndPoint(EndPointAnchor.AUTO_DEFAULT);
        case_4.setDraggable(false);
        endPoint_4.setTarget(true);
        endPoint_4.setId("4");
        case_4.addEndPoint(endPoint_4);

        Element case_6 = new Element(task.getCase6(), "40em", "24em");
        EndPoint endPoint_6 = createDotEndPoint(EndPointAnchor.AUTO_DEFAULT);
        case_6.setDraggable(false);
        endPoint_6.setTarget(true);
        endPoint_6.setId("6");
        case_6.addEndPoint(endPoint_6);

        Element case_8 = new Element(task.getCase8(), "55em", "24em");
        EndPoint endPoint_8 = createDotEndPoint(EndPointAnchor.AUTO_DEFAULT);
        case_8.setDraggable(false);
        endPoint_8.setTarget(true);
        endPoint_8.setId("8");
        case_8.addEndPoint(endPoint_8);

        model.addElement(case_1);
        model.addElement(case_2);
        model.addElement(case_3);
        model.addElement(case_4);
        model.addElement(case_5);
        model.addElement(case_6);
        model.addElement(case_7);
        model.addElement(case_8);
    }

    public DiagramModel getModel()
    {
        return model;
    }

    /**
     * Method for creating dot end poind
     *
     * @param anchor specification
     * @return EndPoint
     */
    private EndPoint createDotEndPoint(EndPointAnchor anchor)
    {
        DotEndPoint endPoint = new DotEndPoint(anchor);
        endPoint.setScope("network");
        endPoint.setTarget(true);
        endPoint.setStyle("{fillStyle:'#98AFC7'}");
        endPoint.setHoverStyle("{fillStyle:'#5C738B'}");

        return endPoint;
    }

    /**
     * Method for creating rectangle end poind
     *
     * @param anchor specification
     * @return EndPoint
     */
    private EndPoint createRectangleEndPoint(EndPointAnchor anchor)
    {
        RectangleEndPoint endPoint = new RectangleEndPoint(anchor);
        endPoint.setScope("network");
        endPoint.setSource(true);
        endPoint.setStyle("{fillStyle:'#98AFC7'}");
        endPoint.setHoverStyle("{fillStyle:'#5C738B'}");
        return endPoint;
    }

    /**
     * Method which use DAO to save task
     *
     * @param task to save
     */
    @Override
    public void save(DiagramTask task)
    {
        try
        {
            TASK_DAO.addTask(task);
            addMessage("Success!", "Task added correctly.");

        }
        catch (HibernateException e)
        {
            addMessage("Error!", "Please try again.");
            Logger.getLogger(DiagramTaskBean.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    /**
     * Method which use DAO to delete task
     *
     * @param task to delete
     */
    @Override
    public void delete(DiagramTask task)
    {
        try
        {
            TASK_DAO.deleteTask(task.getId());
            addMessage("Success!", "Task deleted correctly.");

        }
        catch (HibernateException e)
        {
            addMessage("Error!", "Please try again.");
            Logger.getLogger(DiagramTaskBean.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    /**
     * To get all tasks
     *
     * @return List of tasks
     */
    public static List<DiagramTask> getAllrecords()
    {
        List<DiagramTask> tasks = new ArrayList();

        try
        {
            tasks = TASK_DAO.retrieveTask();

        }
        catch (HibernateException e)
        {
            addMessage("Error!", "Please try again.");
            Logger.getLogger(DiagramTaskBean.class.getName()).log(Level.SEVERE, null, e);
        }
        return tasks;
    }

    /**
     * To update task
     */
    @Override
    public void update()
    {
        try
        {
            TASK_DAO.updateTask(task);
            addMessage("Success!", "Task updated correctly.");

        }
        catch (HibernateException e)
        {
            addMessage("Error!", "Please try again.");
            Logger.getLogger(DiagramTaskBean.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public DiagramTask getTask()
    {
        return task;
    }

    public void setTask(DiagramTask task)
    {
        this.task = task;
    }

    /**
     * Method to clear local variable task
     */
    @Override
    public void clearTask()
    {
        this.task = new DiagramTask();
    }

    /**
     * Method to validate user answer
     */
    @Override
    public void validate()
    {
        List<Connection> connections = model.getConnections();
        List<String> connectionsRelation = new ArrayList<>();

        for (Connection c : connections)
        {
            StringBuilder sb = new StringBuilder();
            sb.append(c.getSource().getId());
            sb.append("-");
            sb.append(c.getTarget().getId());
            connectionsRelation.add(sb.toString());
        }

        List<String> answerRelations = Arrays.asList(task.getAnswerRelations().split(";"));

        if (connectionsRelation.isEmpty())
        {
            connectionsRelation.add("");
        }

        if (ModelUtils.areEqualLists(connectionsRelation, answerRelations))
        {
            System.out.println("Diagram get points");
            quizBean.setPoints(quizBean.getPoints() + 1);
        }

        quizBean.game();

    }

}
