/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eui.miw.pfm.controllers.beans;

import eui.miw.pfm.controllers.ejb.WorkerProfileEjb;
import eui.miw.pfm.models.dao.AbstractDAOFactory;
import eui.miw.pfm.models.entities.ProjectEntity;
import eui.miw.pfm.models.entities.WorkerEntity;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author Fred Peña
 * @author Clemencio Morales
 * @author Pepe Bustamante
 */
@RequestScoped
@Named
public class WorkerProfileBean extends Bean implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(WorkerProfileBean.class.getName());

    private List<ProjectEntity> projects;
    private WorkerEntity workerEntity;

    public WorkerProfileBean() {
        super();
        this.workerEntity = AbstractDAOFactory.getFactory().getWorkerDAO().read(1);
    }

    public List<ProjectEntity> getProjects() {
        return projects;
    }

    public void setProjects(final List<ProjectEntity> projects) {
        this.projects = projects;
    }

    public WorkerEntity getWorkerEntity() {
        return workerEntity;
    }

    public void setWorkerEntity(final WorkerEntity workerEntity) {
        this.workerEntity = workerEntity;
    }

    public String projects() {
        if (workerEntity != null) {
            WorkerProfileEjb workerProfileEjb = new WorkerProfileEjb();
            projects = workerProfileEjb.findProjects(workerEntity);
        } else {
            FacesContext.getCurrentInstance().addMessage("form", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Worker not selected", ""));
        }
        return null;

    }
}
