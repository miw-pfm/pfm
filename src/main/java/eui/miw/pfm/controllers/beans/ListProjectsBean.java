/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eui.miw.pfm.controllers.beans;

import eui.miw.pfm.controllers.ejb.ListProjectsEjb;
import eui.miw.pfm.util.LazyProjectDataModel;
import eui.miw.pfm.models.entities.ProjectEntity;
import eui.miw.pfm.models.entities.UserEntity;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.faces.context.ExternalContext;

import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;

import org.primefaces.model.LazyDataModel;

@Named
@RequestScoped
public class ListProjectsBean extends Bean implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(ListProjectsBean.class.getName());//NOPMD
    private final LazyDataModel<ProjectEntity> lazyModel;
    private ProjectEntity selectedProject;
    private List<ProjectEntity> projects;
    private UserEntity userEntity;

    public ListProjectsBean() {        
        try {
            this.userEntity = ((UserEntity) sessionMap.get("UserLogIn"));
        } catch (Exception e) {
            LOGGER.warning("No session exist");
        }
        ListProjectsEjb eaE = new ListProjectsEjb();
        this.projects = eaE.obtainProjects(this.userEntity);
        this.lazyModel = new LazyProjectDataModel(this.projects);

    }

    public List<ProjectEntity> getProjects() {
        return projects;
    }

    public void setProjects(final List<ProjectEntity> projects) {
        this.projects = projects;
    }

    public LazyDataModel<ProjectEntity> getLazyModel() {
        return lazyModel;
    }

    public ProjectEntity getSelectedProject() {
        return selectedProject;
    }

    public void setSelectedProject(ProjectEntity selectedProject) {
        this.selectedProject = selectedProject;
    }

    public void onRowSelect(SelectEvent event) {//NOPMD
        //FacesMessage msg = new FacesMessage("Project Selected", (ProjectEntity) event.getObject());
        ProjectEntity project;
        project = (ProjectEntity) event.getObject();
        OpenProjectBean opJSF;
        opJSF = new OpenProjectBean();
        opJSF.showOpenProject(project);
        FacesContext facesContext;
        facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext;
        externalContext = facesContext.getExternalContext();
        try {
            externalContext.redirect(opJSF.showOpenProject(project) + ".xhtml");
        } catch (IOException ex) {
            Logger.getLogger(ListProjectsBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
