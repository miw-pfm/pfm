package eui.miw.pfm.controllers.ejb;

import eui.miw.pfm.models.dao.AbstractDAOFactory;
import eui.miw.pfm.models.entities.WorkerEntity;
//import java.util.logging.Logger;

/**
 *
 * @author Clemencio Morales
 */
public class CreateWorkerEjb {
    
//    private static final Logger LOG = Logger.getLogger(CreateWorkerEjb.class.getName());//NOPMD

    public void createWorker(final WorkerEntity worker){
        AbstractDAOFactory.getFactory().getWorkerDAO().create(worker);
    }
}
