package eui.miw.pfm.controllers.beans;

import eui.miw.pfm.controllers.ejb.WorkerEjb;
import eui.miw.pfm.models.entities.WorkerEntity;
import java.io.Serializable;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author Fred Peña
 * @author Jose M Villar
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
        LOGGER.info(this.workerEntity.toString());
        final WorkerEjb workerEjb = new WorkerEjb();
        workerEjb.update(this.workerEntity);
        return null;
    }

    public String create() {
        assert this.workerEntity != null;
        LOGGER.info(this.workerEntity.toString());
        final WorkerEjb workerEjb = new WorkerEjb();
        workerEjb.create(this.workerEntity);
        FacesContext.getCurrentInstance().addMessage("form", new FacesMessage(FacesMessage.SEVERITY_INFO, "Worker Created", ""));
        return null;
    }

    public String delete() {
        assert this.workerEntity != null;
        LOGGER.info(this.workerEntity.toString());
        final WorkerEjb workerEjb = new WorkerEjb();
        workerEjb.delete(this.workerEntity);
        return null;
    }
}
