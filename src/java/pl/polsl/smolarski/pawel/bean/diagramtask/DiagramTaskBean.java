/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.smolarski.pawel.bean.diagramtask;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import pl.polsl.smolarski.pawel.bean.quiz.QuizBean;
import pl.polsl.smolarski.pawel.dao.diagramtask.DiagramTaskDao;
import pl.polsl.smolarski.pawel.pojo.diagramtask.DiagramTask;
import org.primefaces.context.RequestContext;
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
import javax.faces.application.FacesMessage;
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
//        task = (DiagramTask) QuizBean.getPresentTask();

        model = new DefaultDiagramModel();
        model.setMaxConnections(1);

        model.getDefaultConnectionOverlays().add(new ArrowOverlay(20, 20, 1, 1));
        StraightConnector connector = new StraightConnector();
        connector.setPaintStyle("{strokeStyle:'#98AFC7', lineWidth:3}");
        connector.setHoverPaintStyle("{strokeStyle:'#5C738B'}");
        model.setDefaultConnector(connector);
        
        
        Element computerA = new Element("Computer A", "10em", "6em");
        EndPoint endPointCA = createRectangleEndPoint(EndPointAnchor.BOTTOM);
        computerA.setDraggable(false);
        endPointCA.setSource(true);
//        endPointCA.setMaxConnections(1);
        computerA.addEndPoint(endPointCA);
        
        
        Element computerB = new Element("Computer B", "25em", "6em");
        EndPoint endPointCB = createRectangleEndPoint(EndPointAnchor.BOTTOM);
        endPointCB.setSource(true);
        computerB.setDraggable(false);
//        endPointCB.setMaxConnections(1);
        computerB.addEndPoint(endPointCB);

        Element computerC = new Element("Computer C", "40em", "6em");
        EndPoint endPointCC = createRectangleEndPoint(EndPointAnchor.BOTTOM);
        endPointCC.setSource(true);
        computerC.setDraggable(false);
//        endPointCC.setMaxConnections(1);
        computerC.addEndPoint(endPointCC);

        Element computerCE = new Element("Computer CE", "55em", "6em");
        EndPoint endPointCE = createRectangleEndPoint(EndPointAnchor.BOTTOM);
        endPointCE.setSource(true);
        computerCE.setDraggable(false);
//        endPointCE.setMaxConnections(1);
        computerCE.addEndPoint(endPointCE);

        Element serverA = new Element("Computer D", "10em", "24em");
        EndPoint endPointSA = createDotEndPoint(EndPointAnchor.AUTO_DEFAULT);
        serverA.setDraggable(false);
        endPointSA.setTarget(true);
//        endPointSA.setMaxConnections(1);
        serverA.addEndPoint(endPointSA);
        
        Element serverB = new Element("Computer E", "25em", "24em");
        EndPoint endPointSB = createDotEndPoint(EndPointAnchor.AUTO_DEFAULT);
        serverB.setDraggable(false);
        endPointSB.setTarget(true);
//        endPointSB.setMaxConnections(1);
        serverB.addEndPoint(endPointSB);

        Element serverC = new Element("Computer F", "40em", "24em");
        EndPoint endPointSC = createDotEndPoint(EndPointAnchor.AUTO_DEFAULT);
        serverC.setDraggable(false);
        endPointSC.setTarget(true);
//        endPointSC.setMaxConnections(1);
        serverC.addEndPoint(endPointSC);

        Element serverD = new Element("Computer G", "55em", "24em");
        EndPoint endPointSD = createDotEndPoint(EndPointAnchor.AUTO_DEFAULT);
        serverD.setDraggable(false);
        endPointSD.setTarget(true);
//        endPointSD.setMaxConnections(1);
        serverD.addEndPoint(endPointSD);

        model.addElement(computerA);
        model.addElement(computerB);
        model.addElement(computerC);
        model.addElement(computerCE);
        model.addElement(serverC);
        model.addElement(serverD);
        model.addElement(serverA);
        model.addElement(serverB);
    }

    public DiagramModel getModel()
    {
        return model;
    }

    public void onConnect(ConnectEvent event)
    {
        if (!suspendEvent)
        {
            EndPoint source = event.getSourceEndPoint();
            EndPoint target = event.getTargetEndPoint();

//            if(event.getSourceElement().getEndPoints().isEmpty() || event.getSourceEndPoint().)
            
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Connected",
                    "From " + event.getSourceElement().getData() + " To " + event.getTargetElement().getData());

            addMessage("ss", msg.toString());

//            RequestContext.getCurrentInstance().update("form:msgs");
        }
        else
        {
            suspendEvent = false;
        }
    }

    public void onDisconnect(DisconnectEvent event)
    {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Disconnected",
                "From " + event.getSourceElement().getData() + " To " + event.getTargetElement().getData());

        addMessage("ss", msg.toString());

//        RequestContext.getCurrentInstance().update("form:msgs");
    }

    public void onConnectionChange(ConnectionChangeEvent event)
    {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Connection Changed",
                "Original Source:" + event.getOriginalSourceElement().getData()
                + ", New Source: " + event.getNewSourceElement().getData()
                + ", Original Target: " + event.getOriginalTargetElement().getData()
                + ", New Target: " + event.getNewTargetElement().getData());

        addMessage("ss", msg.toString());

//        RequestContext.getCurrentInstance().update("form:msgs");
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

    }

}
