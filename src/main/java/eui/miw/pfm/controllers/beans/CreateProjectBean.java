/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eui.miw.pfm.controllers.beans;

import eui.miw.pfm.controllers.ejb.CreateProjectEjb;
import eui.miw.pfm.models.dao.AbstractDAOFactory;
import eui.miw.pfm.models.entities.ProjectEntity;
import eui.miw.pfm.util.SessionMap;
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
    private final SessionMap sessionMap;//NOPMD
    private final CreateProjectEjb createProjectEjb;//NOPMD
    private static final Logger LOG = Logger.getLogger(CreateProjectBean.class.getName());//NOPMD

    /**
     * Creates a new instance of CreateProjectBean
     */
    public CreateProjectBean() {//NOPMD 
        this.projectEntity = new ProjectEntity();
        this.sessionMap = new SessionMap();
        this.createProjectEjb = new CreateProjectEjb();
    }

    public ProjectEntity getProjectEntity() {
        return projectEntity;
    }

    public void setProjectEntity(final ProjectEntity projectEntity) {
        this.projectEntity = projectEntity;
    }

    public boolean nameProjectValidator() {
        return this.createProjectEjb.nameProjectValidator(projectEntity, AbstractDAOFactory.getFactory().getUserDAO().read(1));
    }

    public String createProject() { //NOPMD
        String view = null;//NOPMD

        if (nameProjectValidator()) {//NOPMD
            assert this.projectEntity != null;
            this.projectEntity.setOwner(AbstractDAOFactory.getFactory().getUserDAO().read(1));
            this.createProjectEjb.createProject(this.projectEntity);
            this.sessionMap.add("project", this.projectEntity);
            view = "confProject";
        } else {
            LOG.warning("Not a valid name");
            final FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("form", new FacesMessage("WARNING!!!", this.projectEntity.getName() + " is not a valid name"));
        }
        return view;
    }
}
