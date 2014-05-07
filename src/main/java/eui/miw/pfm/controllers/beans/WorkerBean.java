/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eui.miw.pfm.controllers.beans;

import eui.miw.pfm.controllers.ejb.WorkerEjb;
import eui.miw.pfm.models.entities.WorkerEntity;
import java.io.Serializable;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author Fred Peña
 */
@RequestScoped
@Named
public class WorkerBean extends Bean implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(WorkerBean.class.getName());

    WorkerEntity workerEntity;

    public WorkerBean() {
        super();
        workerEntity = new WorkerEntity();
    }

    public WorkerEntity getWorkerEntity() {
        return workerEntity;
    }

    public void setWorkerEntity(WorkerEntity workerEntity) {
        this.workerEntity = workerEntity;
    }

    public String update() {
        assert this.workerEntity != null;
        LOGGER.info(this.workerEntity.toString());
        WorkerEjb workerEjb = new WorkerEjb();
        workerEjb.update(this.workerEntity);
        return null;
    }

}
