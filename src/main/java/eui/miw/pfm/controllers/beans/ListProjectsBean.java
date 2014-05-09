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
    private final LazyDataModel<ProjectEntity> lazyModel;//NOPMD
    private ProjectEntity selectedProject;
    private List<ProjectEntity> projects;
    private UserEntity userEntity;

    public ListProjectsBean() {
        super();
        try {
            this.userEntity = ((UserEntity) sessionMap.get("userlogin"));
        } catch (Exception e) {
            LOGGER.warning("No session exist");
        }
        final ListProjectsEjb projectsEjb = new ListProjectsEjb();//NOPMD
        this.projects = new ListProjectsEjb().obtainProjects(this.userEntity);
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

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(final UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public void setSelectedProject(final ProjectEntity selectedProject) {
        this.selectedProject = selectedProject;
    }

    public void onRowSelect(SelectEvent event) {//NOPMD
        //FacesMessage msg = new FacesMessage("Project Selected", (ProjectEntity) event.getObject());        
        final ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        try {
            externalContext.redirect(new OpenProjectBean().showOpenProject((ProjectEntity) event.getObject()) + ".xhtml");
        } catch (IOException ex) {
            Logger.getLogger(ListProjectsBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
