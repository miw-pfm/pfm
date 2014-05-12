package eui.miw.pfm.controllers.beans;

import eui.miw.pfm.controllers.ejb.WorkerEjb;
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
 * @author Jose M Villar
 * @author Jose Angel
 */
@RequestScoped
@Named
public class WorkerBean extends Bean implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(WorkerBean.class.getName());

    private WorkerEntity workerEntity;

    public WorkerBean() {
        super();
        workerEntity = new WorkerEntity();
    }

    public WorkerEntity getWorkerEntity() {
        return workerEntity;
    }

    public void setWorkerEntity(final WorkerEntity workerEntity) {
        this.workerEntity = workerEntity;
    }

    public String update() {
        assert this.workerEntity != null;
        final WorkerEjb workerEjb = new WorkerEjb();

        LOGGER.info("Update: "+this.workerEntity.toString());
        
        workerEjb.update(this.workerEntity);
        return "workersList";
    }

    public String create() {
        assert this.workerEntity != null;
        LOGGER.info(this.workerEntity.toString());
        final WorkerEjb workerEjb = new WorkerEjb();
        workerEjb.create(this.workerEntity);
        FacesContext.getCurrentInstance().addMessage("form", new FacesMessage(FacesMessage.SEVERITY_INFO, "Worker Created", ""));
        return "workersList";
    }

    public String delete(final WorkerEntity worker) {
        assert this.workerEntity != null;
        LOGGER.info(this.workerEntity.toString());
        final WorkerEjb workerEjb = new WorkerEjb();
        workerEjb.delete(worker);
        return "workersList";
    }
    
    public String editWorker(final WorkerEntity worker) {
        this.workerEntity = worker;
        LOGGER.info("Edit: "+this.workerEntity.toString());
        
        return "editWorker";
    }  
    
    public List<WorkerEntity> getWorkers() { 
        final WorkerEjb ejb = new WorkerEjb();
        return ejb.getWorkers();
    }
}
