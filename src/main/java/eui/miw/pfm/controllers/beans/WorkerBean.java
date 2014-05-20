package eui.miw.pfm.controllers.beans;

import eui.miw.pfm.controllers.ejb.WorkerEjb;
import eui.miw.pfm.models.entities.WorkerEntity;
import eui.miw.pfm.util.ExceptionCatch;
import eui.miw.pfm.util.LazyWorkerDataModel;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.model.LazyDataModel;

/**
 *
 * @author Fred Peña
 * @author Jose M Villar
 * @author Jose Angel
 */

@javax.faces.view.ViewScoped
@Named

public class WorkerBean extends Bean implements Serializable {

    private static final long serialVersionUID = 1L;
    private transient LazyDataModel<WorkerEntity> lazyModel;
    private static final Logger LOGGER = Logger.getLogger(WorkerBean.class.getName());

    private WorkerEntity workerEntity;
    private WorkerEntity selected;

    public WorkerBean() {
        super();
        workerEntity = new WorkerEntity();
        this.lazyModel = new LazyWorkerDataModel(this.getWorkers());
    }
    
    public WorkerEntity getWorkerEntity() {
        return workerEntity;
    }

    public WorkerEntity getSelected() {
        return selected;
    }

    public void setSelected(WorkerEntity selected) {
        this.selected = selected;
    }
    

    public LazyDataModel<WorkerEntity> getLazyModel() {
        return lazyModel;
    }

    public void setWorkerEntity(final WorkerEntity workerEntity) {
        this.workerEntity = workerEntity;
    }

    public String update() {
        assert this.workerEntity != null;
        final WorkerEjb workerEjb = new WorkerEjb();

        LOGGER.info("Update: " + this.workerEntity.toString());

        workerEjb.update(this.workerEntity);

        if (ExceptionCatch.getInstance().isException()) {
            FacesContext.getCurrentInstance().addMessage("form", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error Update Worker", ""));
            ExceptionCatch.getInstance().setException(false);
        } else {
            FacesContext.getCurrentInstance().addMessage("form", new FacesMessage(FacesMessage.SEVERITY_INFO, "Worker Update", ""));
        }

        return "workersList";
    }

    public String create() {
        assert this.workerEntity != null;
        LOGGER.info(this.workerEntity.toString());
        final WorkerEjb workerEjb = new WorkerEjb();
        workerEjb.create(this.workerEntity);

        if (ExceptionCatch.getInstance().isException()) {
            FacesContext.getCurrentInstance().addMessage("form", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error Create Worker", ""));
            ExceptionCatch.getInstance().setException(false);
        } else {
            FacesContext.getCurrentInstance().addMessage("form", new FacesMessage(FacesMessage.SEVERITY_INFO, "Worker Created", ""));
        }

        return "workersList";
    }

//    public String delete(final WorkerEntity worker) {
//        assert this.workerEntity != null;
//        LOGGER.info(this.workerEntity.toString());
//        final WorkerEjb workerEjb = new WorkerEjb();
//        workerEjb.delete(worker);
//        return "workersList";
//    }
    
    public String delete() {
        LOGGER.info(this.selected.toString());
        final WorkerEjb workerEjb = new WorkerEjb();
        workerEjb.delete(selected);
        return "workersList";
    }

//    public String editWorker(final WorkerEntity worker) {
//        assert this.workerEntity != null;
//        this.workerEntity = worker;
//
//        LOGGER.info("Edit: " + this.workerEntity.toString());
//
//        return "editWorker";
//    }
    
    public String editWorker() {
        this.workerEntity = selected;

        LOGGER.info("Edit: " + this.workerEntity.toString());

        return "editWorker";
    }

    public List<WorkerEntity> getWorkers() {
        final WorkerEjb ejb = new WorkerEjb();
        return ejb.getWorkers();
    }
}
