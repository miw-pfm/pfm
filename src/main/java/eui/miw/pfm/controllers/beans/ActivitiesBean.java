/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eui.miw.pfm.controllers.beans;

import eui.miw.pfm.controllers.ejb.ActivitiesEjb;
import eui.miw.pfm.models.entities.ActivityEntity;
import eui.miw.pfm.models.entities.IterationEntity;
import eui.miw.pfm.models.entities.ProjectEntity;
import eui.miw.pfm.models.entities.SubActivityEntity;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedProperty;
import javax.inject.Named;

/**
 *
 * @author Jean Mubaied
 * @author Jose Mª Villar
 */
@RequestScoped
@Named
public class ActivitiesBean extends Bean implements Serializable {
    
    private transient ProjectEntity project;
    private static final Logger LOGGER = Logger.getLogger(ProjectConfBean.class.getName());//NOPMD

    @ManagedProperty(value = "#{iterationBean}")
    private final transient ProjectIterationsBean iterationBean = new ProjectIterationsBean();
    
    public ActivitiesBean() {        
        super();
        this.project = new ProjectEntity();

        try {
            this.project = ((ProjectEntity) sessionMap.get("project"));
        } catch (Exception e) {
            LOGGER.warning("No session exist");
        }        
    }    
    
    public List<SubActivityEntity> getSubActivities() {
        ActivitiesEjb activityEjb;
        activityEjb = new ActivitiesEjb();        
        return activityEjb.obtainAllSubActivities();
    }
    
    public List<SubActivityEntity> getSubActivities(final ActivityEntity activity) {
        ActivitiesEjb activityEjb;
        activityEjb = new ActivitiesEjb();                
        return activityEjb.obtainSubActivities(activity);
    }
    
    public List<ActivityEntity> getActivities() {
        return new ActivitiesEjb().obtainAllActivities();  
    }
        
    public ProjectEntity getProject() {
        return project;
    }      

    public ProjectIterationsBean getIterationBean() {
        return iterationBean;
    }
       
}
