/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eui.miw.pfm.controllers.beans;

import eui.miw.pfm.controllers.ejb.ListWorkersEjb;
import eui.miw.pfm.models.entities.ProjectEntity;
import eui.miw.pfm.models.entities.UserEntity;
import eui.miw.pfm.models.entities.WorkerEntity;
import eui.miw.pfm.util.LazyWorkerDataModel;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.primefaces.model.LazyDataModel;

/**
 *
 * @author Roberto Amor
 */
@Named
@SessionScoped
public class ListWorkerBean extends Bean implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(ListWorkerBean.class.getName());//NOPMD
    private final LazyDataModel<WorkerEntity> lazyModel;//NOPMD
    private WorkerEntity selectedWorker;
    private List<WorkerEntity> workers;
    private UserEntity user;
    private ProjectEntity project;

    public ListWorkerBean() {
        super();
        try {
            this.user = ((UserEntity) sessionMap.get("UserLogIn"));
            this.project = ((ProjectEntity) sessionMap.get("project"));
        } catch (Exception e) {
            LOGGER.warning("No session exist");
        }
        final ListWorkersEjb workersEjb = new ListWorkersEjb();
        this.workers = workersEjb.obtainWorkers(this.project);
        this.lazyModel = new LazyWorkerDataModel(this.workers);
    }

    public WorkerEntity getSelectedWorker() {
        return selectedWorker;
    }

    public void setSelectedWorker(final WorkerEntity selectedWorker) {
        this.selectedWorker = selectedWorker;
    }

    public List<WorkerEntity> getWorkers() {
        return workers;
    }

    public void setWorkers(final List<WorkerEntity> workers) {
        this.workers = workers;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(final UserEntity user) {
        this.user = user;
    }

    public ProjectEntity getProject() {
        return project;
    }

    public void setProject(final ProjectEntity project) {
        this.project = project;
    }
}
