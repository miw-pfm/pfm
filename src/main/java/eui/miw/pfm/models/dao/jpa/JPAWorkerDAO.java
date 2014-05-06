package eui.miw.pfm.models.dao.jpa;

import eui.miw.pfm.models.dao.interfaces.WorkerDAO;
import eui.miw.pfm.models.entities.WorkerEntity;

public class JPAWorkerDAO extends JPATransactionGenericDAO<WorkerEntity, Integer> implements WorkerDAO{

    public JPAWorkerDAO() {
        super(WorkerEntity.class);
    }
}
