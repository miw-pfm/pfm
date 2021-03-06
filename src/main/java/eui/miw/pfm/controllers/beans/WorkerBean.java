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
@Named
@RequestScoped
public class WorkerBean extends Bean implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(WorkerBean.class.getName());

    private transient WorkerEntity worker;

    public WorkerBean() {
        super();
        worker = new WorkerEntity();
    }

    public WorkerEntity getWorkerEntity() {
        return worker;
    }

    public void setWorkerEntity(final WorkerEntity workerEntity) {
        this.worker = workerEntity;
    }

    public String update() {
        LOGGER.info(this.worker.toString());
        String view = ""; //NOPMD
        switch (new WorkerEjb().update(this.worker)) {
            case 1:
                FacesContext.getCurrentInstance().addMessage("form", new FacesMessage(FacesMessage.SEVERITY_ERROR, "The worker DNI already exists", "")); //NOPMD
                break;
            case 2:
                FacesContext.getCurrentInstance().addMessage("form", new FacesMessage(FacesMessage.SEVERITY_ERROR, "The worker email already exists", ""));
                break;
            case 3:
                FacesContext.getCurrentInstance().addMessage("form", new FacesMessage(FacesMessage.SEVERITY_ERROR, "The worker GitHub already exists", ""));
                break;
            default:
                FacesContext.getCurrentInstance().addMessage("form", new FacesMessage(FacesMessage.SEVERITY_INFO, "Worker Updated", ""));
                view = "/workerListAll";

        }
        return view;
    }

    public String create() {
        LOGGER.info(this.worker.toString());
        String view = ""; //NOPMD
        switch (new WorkerEjb().create(this.worker)) {
            case 1:
                FacesContext.getCurrentInstance().addMessage("form", new FacesMessage(FacesMessage.SEVERITY_ERROR, "The worker DNI already exists", "")); //NOPMD
                break;
            case 2:
                FacesContext.getCurrentInstance().addMessage("form", new FacesMessage(FacesMessage.SEVERITY_ERROR, "The worker email already exists", ""));
                break;
            case 3:
                FacesContext.getCurrentInstance().addMessage("form", new FacesMessage(FacesMessage.SEVERITY_ERROR, "The worker GitHub already exists", ""));
                break;
            default:
                FacesContext.getCurrentInstance().addMessage("form", new FacesMessage(FacesMessage.SEVERITY_INFO, "Worker Created", ""));
                view = "/workerListAll";

        }
        return view;        
    }

    public String delete() {

        if (this.worker == null) {
            FacesContext.getCurrentInstance().addMessage("form", new FacesMessage(FacesMessage.SEVERITY_WARN, "No Worker Selected", ""));
        } else {
            LOGGER.info(this.worker.toString());

            new WorkerEjb().delete(this.worker);
            FacesContext.getCurrentInstance().addMessage("form", new FacesMessage(FacesMessage.SEVERITY_INFO, "Worker Deleted", ""));
        }
        return "/workerListAll";
    }

    public String editWorker() {
        if (this.worker == null) {
            FacesContext.getCurrentInstance().addMessage("form", new FacesMessage(FacesMessage.SEVERITY_WARN, "No Worker Selected", ""));
        } else {
            return "/workerEdit"; //NOPMD
        }
        return null;
    }

    public String viewWorker() {
        if (this.worker == null) {
            FacesContext.getCurrentInstance().addMessage("form", new FacesMessage(FacesMessage.SEVERITY_WARN, "No Worker Selected", ""));
        } else {
            super.sessionMap.put("worker", this.worker);
            return "/workerProfile"; //NOPMD
        }
        return null;
    }

    public List<WorkerEntity> getWorkers() {
        return new WorkerEjb().getWorkers();
    }
}
