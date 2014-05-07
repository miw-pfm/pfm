/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eui.miw.pfm.util.moks;

import eui.miw.pfm.models.dao.AbstractDAOFactory;
import eui.miw.pfm.models.entities.WorkerEntity;
import java.util.List;

/**
 *
 * @author Jose M Villar
 */
public class ListWorkersEjbMock {
    public List<WorkerEntity> obtainWorker() {
        return AbstractDAOFactory.getFactory().getWorkerDAO().findAll();
    }    
}
