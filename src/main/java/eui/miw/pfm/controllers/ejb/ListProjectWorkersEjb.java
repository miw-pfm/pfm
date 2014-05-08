/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eui.miw.pfm.controllers.ejb;

import eui.miw.pfm.models.entities.ProjectEntity;
import eui.miw.pfm.models.entities.WorkerEntity;
import java.util.List;

/**
 *
 * @author Roberto Amor
 */
public class ListProjectWorkersEjb {
    public List<WorkerEntity> obtainWorkers(final ProjectEntity project) {
        for(WorkerEntity worker : project.getWorkers()){
            System.out.println("worker: "+worker.getName());
        }
        //return null;
        
        return project.getWorkers();
        //return new ArrayList<WorkerEntity>();
    }
}
