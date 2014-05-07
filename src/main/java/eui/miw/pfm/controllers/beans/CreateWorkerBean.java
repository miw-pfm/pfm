package eui.miw.pfm.controllers.beans;

import eui.miw.pfm.controllers.ejb.CreateWorkerEjb;
import eui.miw.pfm.models.entities.WorkerEntity;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
//import org.apache.log4j.Logger;

/**
 *
 * @author Clemencio Morales Lucas
 */

@RequestScoped
@Named
public class CreateWorkerBean extends Bean implements Serializable{
    private static final long serialVersionUID = 1L;
    private WorkerEntity workerEntity;
    private CreateWorkerEjb createWorkerEjb;
//    private static final Logger LOGGER = Logger.getLogger(CreateWorkerBean.class.getName());//NOPMD
    
    public CreateWorkerBean() {//NOPMD 
        this.workerEntity = new WorkerEntity();
        this.createWorkerEjb = new CreateWorkerEjb();
    }

    public WorkerEntity getWorkerEntity() {
        return workerEntity;
    }

    public void setWorkerEntity(final WorkerEntity workerEntity) {
        this.workerEntity = workerEntity;
    }

    public CreateWorkerEjb getCreateWorkerEjb() {
        return createWorkerEjb;
    }

    public void setCreateWorkerEjb(final CreateWorkerEjb createWorkerEjb) {
        this.createWorkerEjb = createWorkerEjb;
    }
    
    public String createWorker() {
        assert this.workerEntity != null;
        this.createWorkerEjb.createWorker(this.workerEntity);
        return null;
    }
}