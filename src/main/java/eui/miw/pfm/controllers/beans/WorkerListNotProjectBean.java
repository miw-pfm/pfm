/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eui.miw.pfm.controllers.beans;

import eui.miw.pfm.controllers.ejb.WorkersListEjb;
import eui.miw.pfm.models.dao.AbstractDAOFactory;
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
 *
 * Refactoring : Fred Peña & Jose M Villar
 */
@Named
@SessionScoped
public class WorkerListNotProjectBean extends Bean implements Serializable {

    private static final long serialVersionUID = 1L;
    private transient LazyDataModel<WorkerEntity> lazyModel;
    private static final Logger LOGGER = Logger.getLogger(WorkerListNotProjectBean.class.getName());
    private WorkerEntity selectedWorker;
    private List<WorkerEntity> workers;
    private UserEntity user;
    private ProjectEntity project;

    public WorkerListNotProjectBean() {
        super();
        try {
            this.user = ((UserEntity) sessionMap.get("userlogin"));
            this.project = ((ProjectEntity) sessionMap.get("project"));
        } catch (Exception e) {
            LOGGER.warning("No session exist");
        }
        this.project = AbstractDAOFactory.getFactory().getProjectDAO().read(project.getId());
        this.workers = new WorkersListEjb().obtainWorkersNotProject(this.project);
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

    public LazyDataModel<WorkerEntity> getLazyModel() {
        return lazyModel;
    }

    /**
     *
     * @author Jose M Villar
     */
    public void reload() {
        this.workers = new WorkersListEjb().obtainWorkersNotProject(this.project);
        this.lazyModel = new LazyWorkerDataModel(this.workers);
    }

}
