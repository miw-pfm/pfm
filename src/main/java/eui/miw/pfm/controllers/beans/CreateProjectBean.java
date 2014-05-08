/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eui.miw.pfm.controllers.beans;

import eui.miw.pfm.controllers.ejb.CalendarProjectEjb;
import eui.miw.pfm.controllers.ejb.CalendarTemplateEjb;
import eui.miw.pfm.controllers.ejb.CreateProjectEjb;
import eui.miw.pfm.models.entities.CalendarEntity;
import eui.miw.pfm.models.entities.CalendarTemplateEntity;
import eui.miw.pfm.models.entities.ProjectEntity;
import eui.miw.pfm.models.entities.UserEntity;
import java.io.Serializable;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author César Martínez
 */
@RequestScoped
@Named
public class CreateProjectBean extends Bean implements Serializable {

    private static final long serialVersionUID = 1L;
    private ProjectEntity projectEntity;
    private UserEntity userEntity;
    private final CreateProjectEjb createProjectEjb;//NOPMD
    private static final Logger LOGGER = Logger.getLogger(CreateProjectBean.class.getName());//NOPMD

    /**
     * Creates a new instance of CreateProjectBean
     */
    public CreateProjectBean() {//NOPMD 
        this.projectEntity = new ProjectEntity();
        this.createProjectEjb = new CreateProjectEjb();

        try {
            this.userEntity = ((UserEntity) sessionMap.get("userlogin"));
        } catch (Exception e) {
            LOGGER.warning("No session exist");
        }
    }

    public ProjectEntity getProjectEntity() {
        return projectEntity;
    }

    public void setProjectEntity(final ProjectEntity projectEntity) {
        this.projectEntity = projectEntity;
    }

    public boolean nameProjectValidator() {
        return this.createProjectEjb.nameProjectValidator(projectEntity, this.userEntity);
    }

    public String createProject() { //NOPMD
        String view = null;//NOPMD

        if (nameProjectValidator()) {//NOPMD
            assert this.projectEntity != null;
            this.projectEntity.setOwner((UserEntity) this.sessionMap.get("userlogin"));
            this.createProjectEjb.createProject(this.projectEntity);
            this.sessionMap.add("project", this.projectEntity);
            load_holidays();
            view = "confProject";
        } else {
            LOGGER.warning("Not a valid name");
            final FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("form", new FacesMessage(FacesMessage.SEVERITY_ERROR, this.projectEntity.getName() + " is not a valid name", ""));
        }
        return view;
    }

    public void load_holidays() {
        CalendarProjectEjb cpe = new CalendarProjectEjb();
        CalendarTemplateEjb cte = new CalendarTemplateEjb();
        for (CalendarTemplateEntity ct : cte.obtainHolidays()) {
            CalendarEntity ce = new CalendarEntity();
            ce.setName(ct.getName());
            ce.setDescription(ct.getName());
            ce.setHoliday(ct.getHoliday());
            ce.setProject(projectEntity);
            cpe.create(ce);
        }
    }
}
