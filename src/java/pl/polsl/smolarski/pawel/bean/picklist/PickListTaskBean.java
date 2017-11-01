/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.smolarski.pawel.bean.picklist;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.event.SelectEvent;
import pl.polsl.smolarski.pawel.bean.quiz.QuizBean;
import pl.polsl.smolarski.pawel.dao.picklist.PickListDao;
import pl.polsl.smolarski.pawel.pojo.picklisttask.PickListTask;
import org.primefaces.event.TransferEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.DualListModel;
import static pl.polsl.smolarski.pawel.utils.SessionUtils.addMessage;

/**
 *
 * @author psmolarski
 */
@ManagedBean
@ViewScoped
public class PickListTaskBean implements Serializable
{

    private PickListTask task = new PickListTask();
    private static final PickListDao taskDao = new PickListDao();

    private DualListModel<String> tasks;

    @PostConstruct
    public void init()
    {
//        task = (PickListTask) QuizBean.getPresentTask();

        List<String> tasksSource = new ArrayList<>();
        List<String> tasksTarget = new ArrayList<>();

        tasksSource.add("San Francisco");
        tasksSource.add("London");
        tasksSource.add("Paris");
        tasksSource.add("Istanbul");
        tasksSource.add("Berlin");
        tasksSource.add("Barcelona");
        tasksSource.add("Rome");

        tasks = new DualListModel<String>(tasksSource, tasksTarget);
    }

    public void onTransfer(TransferEvent event)
    {
//        StringBuilder builder = new StringBuilder();
//        for (Object item : event.getItems())
//        {
//            builder.append(((Theme) item).getName()).append("<br />");
//        }
//
//        FacesMessage msg = new FacesMessage();
//        msg.setSeverity(FacesMessage.SEVERITY_INFO);
//        msg.setSummary("Items Transferred");
//        msg.setDetail(builder.toString());
//
//        FacesContext.getCurrentInstance().addMessage(null, msg);
        addMessage("ss", "lel");

    }

    public void onSelect(SelectEvent event)
    {
//        FacesContext context = FacesContext.getCurrentInstance();
//        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Item Selected", event.getObject().toString()));
        addMessage("ss", "lel");
    }

    public void onUnselect(UnselectEvent event)
    {
//        FacesContext context = FacesContext.getCurrentInstance();
//        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Item Unselected", event.getObject().toString()));
        addMessage("ss", "lel");
    }

    public void onReorder()
    {
//        FacesContext context = FacesContext.getCurrentInstance();
//        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "List Reordered", null));
        addMessage("ss", "lel");
    }

    public DualListModel<String> getTasks()
    {
        return tasks;
    }

    public void setTasks(DualListModel<String> tasks)
    {
        this.tasks = tasks;
    }

    /**
     * Method which use DAO to save task
     */
    public void save(PickListTask task)
    {
        taskDao.addTask(task);
    }

    /**
     * Method which use DAO to delete task
     */
    public void delete(PickListTask task)
    {
        taskDao.deleteTask(task.getId());
    }

    public static List<PickListTask> getallrecords()
    {
        List<PickListTask> tasks = taskDao.retrieveTask();
        return tasks;
    }

    public void update()
    {
        taskDao.updateTask(task);
    }

    public PickListTask getTask()
    {
        return task;
    }

    public void setTask(PickListTask task)
    {
        this.task = task;
    }

    public void clearTask()
    {
        this.task = new PickListTask();
    }

    public void validate()
    {
        //todo
    }
}
