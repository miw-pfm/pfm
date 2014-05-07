/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eui.miw.pfm.controllers.beans;

import eui.miw.pfm.controllers.ejb.ListProjectWorkersEjb;
import eui.miw.pfm.controllers.ejb.ListProjectsEjb;
import eui.miw.pfm.models.entities.ProjectEntity;
import eui.miw.pfm.models.entities.UserEntity;
import eui.miw.pfm.models.entities.WorkerEntity;
import eui.miw.pfm.util.LazyProjectDataModel;
import eui.miw.pfm.util.LazyWorkerDataModel;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import org.primefaces.model.LazyDataModel;

/**
 *
 * @author Roberto Amor
 */
@Named
@RequestScoped
public class ListProjectWorkersBean extends Bean implements Serializable {
    private static final long serialVersionUID = 1L;
    private final LazyDataModel<WorkerEntity> lazyModel;
    private WorkerEntity selectedWorker;
    private List<WorkerEntity> workers;
    private UserEntity user;
    private ProjectEntity project;
    
    public ListProjectWorkersBean() {        
        try {
            this.user = ((UserEntity) sessionMap.get("UserLogIn"));
            this.project = ((ProjectEntity) sessionMap.get("project"));
        } catch (Exception e) {
        }
        ListProjectWorkersEjb eaE = new ListProjectWorkersEjb();
        this.workers = eaE.obtainWorkers(this.project);
        this.lazyModel = new LazyWorkerDataModel(this.workers);

    }
}
