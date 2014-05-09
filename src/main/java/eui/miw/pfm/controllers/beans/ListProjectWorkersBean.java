/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eui.miw.pfm.controllers.beans;

import eui.miw.pfm.controllers.ejb.ListProjectWorkersEjb;
import eui.miw.pfm.models.entities.ProjectEntity;
import eui.miw.pfm.models.entities.UserEntity;
import eui.miw.pfm.models.entities.WorkerEntity;
import eui.miw.pfm.util.LazyWorkerDataModel;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedProperty;
import javax.inject.Named;
import org.primefaces.model.LazyDataModel;

/**
 *
 * @author Roberto Amor
 * @author Jose Mª Villar
 */
@Named
@SessionScoped
public class ListProjectWorkersBean extends Bean implements Serializable {

    private static final long serialVersionUID = 1L;
    private transient final LazyDataModel<WorkerEntity> lazyModel;
    private WorkerEntity selectedWorker;
    private List<WorkerEntity> workers;
    private UserEntity user;
    private ProjectEntity project;
    private static final Logger LOGGER = Logger.getLogger(ListProjectWorkersBean.class.getName());

    @ManagedProperty(value = "#{listWorkerBean}")
    private transient ListWorkerBean listWorkerBean = new ListWorkerBean();

    public ListProjectWorkersBean() {
        super();
        try {
            this.user = ((UserEntity) sessionMap.get("UserLogIn"));
            this.project = ((ProjectEntity) sessionMap.get("project"));
        } catch (Exception e) {
        }
        final ListProjectWorkersEjb eaE = new ListProjectWorkersEjb();
        this.workers = eaE.obtainWorkers(this.project);
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

    public String remove(final WorkerEntity worker) {
        assert worker != null;
        assert this.project != null;

        LOGGER.info(worker.toString());
        LOGGER.info(this.project.toString());

        final ListProjectWorkersEjb eaE = new ListProjectWorkersEjb();
        eaE.remove(project, worker);

        this.listWorkerBean.reload();

        return "list_worker";
    }

    public String add(final WorkerEntity worker) {
        assert worker != null;
        assert this.project != null;

        LOGGER.info(worker.toString());
        LOGGER.info(this.project.toString());

        final ListProjectWorkersEjb eaE = new ListProjectWorkersEjb();
        eaE.add(project, worker);

        this.listWorkerBean.reload();
        
        return "list_worker";
    }

    public ListWorkerBean getListWorkerBean() {
        return listWorkerBean;
    }
}
