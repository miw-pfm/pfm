package eui.miw.pfm.controllers.ejb;

import eui.miw.pfm.models.dao.AbstractDAOFactory;
import eui.miw.pfm.models.entities.IterationEntity;
import eui.miw.pfm.models.entities.SubActivityEntity;
import eui.miw.pfm.models.entities.WorkUnitEntity;
import eui.miw.pfm.models.entities.WorkerEntity;
import java.util.List;

/**
 *
 * @author Jose Mª Villar
 * @César Martínez
 */
public class WorkUnitEjb {

    private static final int WORK_UNIT = 4;

    public Integer calculatingWorkUnits(final Integer numHours) {
        return numHours * WORK_UNIT;
    }

    public void create(final WorkUnitEntity workUnitEntity) {
        AbstractDAOFactory.getFactory().getWorkUnitDAO().create(workUnitEntity);
    }

    public void update(final WorkUnitEntity workUnitEntity) {
        AbstractDAOFactory.getFactory().getWorkUnitDAO().update(workUnitEntity);
    }

    public void delete(final WorkUnitEntity workUnitEntity) {
        AbstractDAOFactory.getFactory().getWorkUnitDAO().delete(workUnitEntity);
    }

    public List<WorkUnitEntity> getAvailableWorkUnits(final SubActivityEntity subActivity, final IterationEntity iteration) {
        final String psql = "SELECT wu FROM WorkUnitEntity wu WHERE wu.iteration = ?1 AND wu.subactivity = ?2 AND wu.worker IS NULL";//NOPMD
        return AbstractDAOFactory.getFactory().getWorkUnitDAO().find(psql, iteration, subActivity);
    }

    public List<WorkUnitEntity> getWorkUnitsByIterAndSubActivity(final SubActivityEntity subActivity, final IterationEntity iteration) {
        final String psql = "SELECT wu FROM WorkUnitEntity wu WHERE wu.iteration = ?1 AND wu.subactivity = ?2";//NOPMD
        return AbstractDAOFactory.getFactory().getWorkUnitDAO().find(psql, iteration, subActivity);
    }

    public List<WorkUnitEntity> getWorkUnitsByIter(final IterationEntity iteration) {
        final String psql = "SELECT wu FROM WorkUnitEntity wu WHERE wu.iteration = ?1";//NOPMD
        return AbstractDAOFactory.getFactory().getWorkUnitDAO().find(psql, iteration);
    }
    
    public List<WorkUnitEntity> getTotalWorkUnitsByWorker(final SubActivityEntity subActivity, final IterationEntity iteration, final WorkerEntity worker) {
        final String psql = "SELECT wu FROM WorkUnitEntity wu WHERE wu.iteration = ?1 AND wu.subactivity = ?2 AND wu.worker = ?3";//NOPMD
        return AbstractDAOFactory.getFactory().getWorkUnitDAO().find(psql, new Object[]{iteration,subActivity,worker});
}
    
    public List<WorkUnitEntity> getWorkerWorkUnits(final WorkerEntity worker) {
        final String psql = "SELECT wu FROM WorkUnitEntity wu WHERE wu.worker = ?1";//NOPMD
        return AbstractDAOFactory.getFactory().getWorkUnitDAO().find(psql, new Object[]{worker});
    }
}
