/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eui.miw.pfm.controllers.beans;

import eui.miw.pfm.controllers.ejb.ListProjectsEjb;
import eui.miw.pfm.util.LazyProjectDataModel;
import eui.miw.pfm.models.dao.AbstractDAOFactory;
import eui.miw.pfm.models.dao.interfaces.UserDAO;
import eui.miw.pfm.models.entities.ProjectEntity;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;

import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;

import org.primefaces.model.LazyDataModel;

@ManagedBean
public class ListProjectsBean {

    private LazyDataModel<ProjectEntity> lazyModel;

    private ProjectEntity selectedProject;

    private List<ProjectEntity> projects;

    public ListProjectsBean() {
        ListProjectsEjb eaE;
        eaE = new ListProjectsEjb();
        UserDAO userDAO = AbstractDAOFactory.getFactory().getUserDAO();
        this.setProjects(eaE.obtainProjects(userDAO.read(1)));
        lazyModel = new LazyProjectDataModel(this.projects);
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
        ProjectEntity project ;
        project = (ProjectEntity) event.getObject();
        OpenProjectBean opJSF;
        opJSF = new OpenProjectBean();
        opJSF.showOpenProject(project);
        FacesContext facesContext ;
        facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext ;
        externalContext = facesContext.getExternalContext();
        try {
            externalContext.redirect(opJSF.showOpenProject(project )  + ".xhtml");
        } catch (IOException ex) {
            Logger.getLogger(ListProjectsBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
