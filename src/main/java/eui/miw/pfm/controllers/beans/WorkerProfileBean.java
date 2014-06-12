/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eui.miw.pfm.controllers.beans;

import eui.miw.pfm.models.entities.IterationEntity;
import eui.miw.pfm.models.entities.ProjectEntity;
import eui.miw.pfm.models.entities.SubActivityEntity;
import eui.miw.pfm.models.entities.WorkerEntity;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedProperty;
import javax.inject.Named;

/**
 *
 * @author Fred Peña
 * @author Clemencio Morales
 * @author Pepe Bustamante
 *
 * Refactorizado por @author Jose Mª Villar Bogalo
 */
@RequestScoped
@Named
public class WorkerProfileBean extends Bean implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(WorkerProfileBean.class.getName());//NOPMD

    private transient ProjectEntity project;
    private transient WorkerEntity worker;

    @ManagedProperty(value = "#{workUnitBean}")
    private final transient WorkUnitBean workUnitBean = new WorkUnitBean();

    public WorkerProfileBean() {
        super();
        try {
            this.project = ((ProjectEntity) sessionMap.get("project"));
            this.worker = ((WorkerEntity) sessionMap.get("worker"));
        } catch (Exception e) {
            LOGGER.info("No session exist");
        }
        this.workUnitBean.setWorker(worker);
    }

    public WorkUnitBean getWorkUnitBean() {
        return workUnitBean;
    }

    public ProjectEntity getProject() {
        return project;
    }

    public void setProject(final ProjectEntity project) {
        this.project = project;
    }

    public WorkerEntity getWorker() {
        return worker;
    }

    public void setWorker(final WorkerEntity worker) {
        this.worker = worker;
    }

    public List<SubActivityEntity> getAllWorkUnit(final IterationEntity iteration) {
        this.workUnitBean.setIteration(iteration);
        return this.workUnitBean.getWorkerSubActivities();
    }
}