/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eui.miw.pfm.controllers.beans;

import eui.miw.pfm.controllers.ejb.ProjectEjb;
import eui.miw.pfm.models.entities.ProjectEntity;
import java.io.Serializable;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author Roberto Amor
 */
@Named
@RequestScoped
public class ResourcesPlanBean extends Bean implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private transient ProjectEntity project;
    private static final Logger LOGGER = Logger.getLogger(ProjectConfBean.class.getName());//NOPMD
    private int workers;
    private final ProjectEjb projectEjb;//NOPMD

    public ResourcesPlanBean() {
        projectEjb = new ProjectEjb();
        try {
            this.project = ((ProjectEntity) sessionMap.get("project"));
        } catch (Exception e) {
            LOGGER.warning("No session exist");
        }
        this.workers = this.project.getWorkersInPlan();
    }

    public int getWorkers() {
        return workers;
    }

    public void setWorkers(int workers) {
        this.workers = workers;
    }
    
    public void saveWorkers(){
        if(validateWorkers()){
            System.out.println(this.workers);
            this.project.setWorkersInPlan(this.workers);
            projectEjb.update(this.project);
            FacesContext.getCurrentInstance().addMessage("form", new FacesMessage(FacesMessage.SEVERITY_INFO, "Workers Saved", ""));
        }
    }
    
    public boolean validateWorkers(){
        if(this.workers>=0)
            return true;
        else
            return false;
    }
}
