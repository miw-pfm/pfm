/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eui.miw.pfm.controllers.beans;

import eui.miw.pfm.controllers.ejb.ActivitiesEjb;
import eui.miw.pfm.models.dao.AbstractDAOFactory;
import eui.miw.pfm.models.entities.ActivityEntity;
import eui.miw.pfm.models.entities.ProjectEntity;
import eui.miw.pfm.models.entities.SubActivityEntity;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.event.CellEditEvent;

/**
 *
 * @author Jean Mubaied
 * @author Jose Mª Villar
 */
@ViewScoped
@Named
public class ActivitiesBean extends Bean implements Serializable {

    private static final long serialVersionUID = 1L;

    private ProjectEntity project;
    private transient ActivityEntity activity;
    private transient List<SubActivityEntity> subActivities;
    private static final Logger LOGGER = Logger.getLogger(ProjectConfBean.class.getName());//NOPMD
    private String prueba;
    

    @ManagedProperty(value = "#{iterationBean}")
    private final transient IterationBean iterationBean = new IterationBean();

    public ActivitiesBean() {
        super();
        try {
            this.project = ((ProjectEntity) sessionMap.get("project"));
        } catch (Exception e) {
            LOGGER.warning("No session exist");
        }

        this.project = AbstractDAOFactory.getFactory().getProjectDAO().read(project.getId());
        this.activity = AbstractDAOFactory.getFactory().getActivityDAO().read(1);
        
        ActivitiesEjb activityEjb;
        activityEjb = new ActivitiesEjb();
        this.subActivities = activityEjb.obtainSubActivities(activity);        
    }

    
    public ActivityEntity getActivity() {
        return activity;
    }

    public void setActivity(final ActivityEntity activity) {
        this.activity = activity;
    }

    public List<SubActivityEntity> getSubActivities() {
        return subActivities;
    }

    public void setSubActivities(final List<SubActivityEntity> subActivities) {
        this.subActivities = subActivities;
    }

    public ProjectEntity getProject() {
        return project;
    }

    public void setProject(final ProjectEntity project) {
        this.project = project;
    }

    public IterationBean getIterationBean() {
        return iterationBean;
    }

    public List<SubActivityEntity> getSubActivitiesByActivity(final ActivityEntity activity) {
        ActivitiesEjb activityEjb;
        activityEjb = new ActivitiesEjb();
        return activityEjb.obtainSubActivities(activity);
    }

    public List<ActivityEntity> getActivities() {
        return new ActivitiesEjb().obtainAllActivities();
    }
    
    public void onCellEdit(final CellEditEvent event) {
//        final Object oldValue = event.getOldValue();
//        final Object newValue = event.getNewValue();
        
        System.out.println("---------------------------");
        
//        if(newValue != null && !newValue.equals(oldValue)) {
//            final FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
//            FacesContext.getCurrentInstance().addMessage(null, msg);
//        }
    }    
}
