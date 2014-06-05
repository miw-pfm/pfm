/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eui.miw.pfm.controllers.beans;

import eui.miw.pfm.controllers.ejb.TargetEjb;
import eui.miw.pfm.models.entities.DisciplineEntity;
import eui.miw.pfm.models.entities.ProjectEntity;
import eui.miw.pfm.models.entities.TargetEntity;
import java.io.Serializable;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Jean Mubaied
 */
@ViewScoped
@Named
public class TargetBean extends Bean implements Serializable {

    private static final long serialVersionUID = 1L;
    private TargetEntity target;
    private transient ProjectEntity project;
    private DisciplineEntity discipline;
    private static final Logger LOGGER = Logger.getLogger(ProjectConfBean.class.getName());//NOPMD

    public TargetBean() {
        super();
        this.target = new TargetEntity();
        this.discipline = new DisciplineEntity();
        try {
            this.project = ((ProjectEntity) sessionMap.get("project"));
        } catch (Exception e) {
            LOGGER.warning("No session exist");
        }
    }

    public TargetEntity getTarget() {
        return target;
    }

    public void setTarget(final TargetEntity target) {
        this.target = target;
    }

    public String create() {
        if (this.project == null) {
            FacesContext.getCurrentInstance().addMessage("form", new FacesMessage(FacesMessage.SEVERITY_WARN, "No project selected", ""));
            return null;//NOPMD
        }

        LOGGER.info(this.target.toString());

        this.target.setProject(this.project);
        new TargetEjb().createTarget(target);
        return null;
    }
    
    public String delete() {
        if (null ==  this.target) {
            FacesContext.getCurrentInstance().addMessage("form", new FacesMessage(FacesMessage.SEVERITY_WARN, "No target Selected", ""));
        } else {
            this.target.setProject(project);
            
            LOGGER.info(this.target.toString());
            
            new TargetEjb().delete(target);
            FacesContext.getCurrentInstance().addMessage("form", new FacesMessage(FacesMessage.SEVERITY_INFO, "Target Deleted", ""));
        }
        return null;
    }

    public String update() {
        this.target.setProject(project);
        LOGGER.info(this.target.toString());
        new TargetEjb().update(target);
        return null;
    }

}
