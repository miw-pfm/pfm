/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eui.miw.pfm.controllers.beans;

import eui.miw.pfm.controllers.ejb.WorkerEjb;
import eui.miw.pfm.models.entities.WorkerEntity;

/**
 *
 * @author aw0591
 */
public class WorkerBean {

    private transient final WorkerEntity worker;
    public WorkerBean() {
        worker = new WorkerEntity();        
    }
       
    public String deleteWorker(){
        final WorkerEjb wejb = new WorkerEjb();
        wejb.delete(worker);
        return null;
    }
}
