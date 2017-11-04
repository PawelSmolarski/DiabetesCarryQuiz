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
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import pl.polsl.smolarski.pawel.bean.quiz.QuizBean;
import pl.polsl.smolarski.pawel.dao.diagramtask.DiagramTaskDao;
import pl.polsl.smolarski.pawel.pojo.diagramtask.DiagramTask;
import org.primefaces.event.diagram.ConnectEvent;
import org.primefaces.event.diagram.ConnectionChangeEvent;
import org.primefaces.event.diagram.DisconnectEvent;
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
import pl.polsl.smolarski.pawel.utils.SessionUtils;
import static pl.polsl.smolarski.pawel.utils.SessionUtils.addMessage;

/**
 *
 * @author psmolarski
 */
@ManagedBean
@ViewScoped
public class DiagramTaskBean implements Serializable
{

    private DefaultDiagramModel model;

    private boolean suspendEvent;

    private DiagramTask task = new DiagramTask();
    private static final DiagramTaskDao taskDao = new DiagramTaskDao();

    @PostConstruct
    public void init()
    {
        //TODO sczytanie do listy
        task = (DiagramTask) QuizBean.getPresentTask();

        if (task != null)
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
    }

    public DiagramModel getModel()
    {
        return model;
    }

    public void onConnect(ConnectEvent event)
    {
        if (!suspendEvent)
        {
            addMessage("ss", "on connect");

        }
        else
        {
            suspendEvent = false;
        }
    }

    public void onDisconnect(DisconnectEvent event)
    {

        addMessage("ss", "on disconnect");

    }

    public void onConnectionChange(ConnectionChangeEvent event)
    {

        addMessage("ss", "on connection change");

        suspendEvent = true;
    }

    private EndPoint createDotEndPoint(EndPointAnchor anchor)
    {
        DotEndPoint endPoint = new DotEndPoint(anchor);
        endPoint.setScope("network");
        endPoint.setTarget(true);
        endPoint.setStyle("{fillStyle:'#98AFC7'}");
        endPoint.setHoverStyle("{fillStyle:'#5C738B'}");

        return endPoint;
    }

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
     */
    public void save(DiagramTask task)
    {
        taskDao.addTask(task);
    }

    /**
     * Method which use DAO to delete task
     */
    public void delete(DiagramTask task)
    {
        taskDao.deleteTask(task.getId());
    }

    public static List<DiagramTask> getallrecords()
    {
        List<DiagramTask> tasks = taskDao.retrieveTask();
        return tasks;
    }

    public void update()
    {
        taskDao.updateTask(task);
    }

    public DiagramTask getTask()
    {
        return task;
    }

    public void setTask(DiagramTask task)
    {
        this.task = task;
    }

    public void clearTask()
    {
        this.task = new DiagramTask();
    }

    public void validate()
    {
        List<Connection> connections = model.getConnections();
        List<String> connectionsRelation = new ArrayList<>();
        if (connections.isEmpty())
        {
            addMessage("Error!", "Make at least one connection");
        }
        else
        {
            System.out.println("on validate diagram:");
            for (Connection c : connections)
            {
                StringBuilder sb = new StringBuilder();
                sb.append(c.getSource().getId());
                sb.append("-");
                sb.append(c.getTarget().getId());
                connectionsRelation.add(sb.toString());
            }

            List<String> answerRelations = Arrays.asList(task.getAnswerRelations().split(";"));

            if (SessionUtils.areEqualLists(connectionsRelation, answerRelations))
            {
                System.out.println("Diagram get points");
                QuizBean.setPoints(QuizBean.getPoints() + 1);

            }

//            boolean isValid = true;
//            for (String s : connectionsRelation)
//            {
//                if (!task.getAnswerRelations().contains(s))
//                {
//                    System.out.println("some mistake of player");
//                    isValid = false;
//                    break;
//                }
//            }
//            if (isValid == true)
//            {
//                System.out.println("player get points diaaaaagram");
//                QuizBean.setPoints(QuizBean.getPoints() + 1);
//            }
            QuizBean.game();
        }

    }

}
