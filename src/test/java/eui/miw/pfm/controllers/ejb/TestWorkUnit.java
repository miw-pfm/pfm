package eui.miw.pfm.controllers.ejb;

import eui.miw.pfm.models.dao.AbstractDAOFactory;
import eui.miw.pfm.models.entities.IterationEntity;
import eui.miw.pfm.models.entities.ProjectEntity;
import eui.miw.pfm.models.entities.SubActivityEntity;
import eui.miw.pfm.models.entities.UserEntity;
import eui.miw.pfm.models.entities.WorkUnitEntity;
import eui.miw.pfm.models.entities.WorkerEntity;
import eui.miw.pfm.util.TypeIteration;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Jose Mª Villar
 */
public class TestWorkUnit {

    private UserEntity user;
    private ProjectEntity project;
    private SubActivityEntity subActivity;
    private IterationEntity iteration;
    private WorkerEntity worker;

    private final WorkUnitEjb workUnitEjb = new WorkUnitEjb();
    private final List<WorkUnitEntity> listWorkUnit = new ArrayList<>();

    @Before
    public void before() {
        this.user = new UserEntity();
        this.project = new ProjectEntity();

        this.user.setName("usuario");
        AbstractDAOFactory.getFactory().getUserDAO().create(user);

        this.project.setName("Projecto prueba CU");
        this.project.setOwner(user);
        AbstractDAOFactory.getFactory().getProjectDAO().create(project);

        this.iteration = new IterationEntity();
        this.iteration.setTypeIteration(TypeIteration.INCEPTION);
        this.iteration.setIterValue(1);
        this.iteration.setProject(project);
        AbstractDAOFactory.getFactory().getIterationDAO().create(iteration);

        worker = new WorkerEntity(Integer.SIZE, "Pepe", "", "12345678Y", "");
        AbstractDAOFactory.getFactory().getWorkerDAO().create(worker);

        this.subActivity = AbstractDAOFactory.getFactory().getSubActivityDAO().read(1);

        for (int i = 0; i < 2; i++) {
            listWorkUnit.add(new WorkUnitEntity(iteration, subActivity));
            workUnitEjb.create(listWorkUnit.get(i));
        }

        listWorkUnit.get(1).setWorker(worker);
        AbstractDAOFactory.getFactory().getWorkUnitDAO().update(listWorkUnit.get(1));
    }

    @Test
    public void testCreate() {
        for (int i = 0; i < 2; i++) {
            assertTrue("ERROR creating", AbstractDAOFactory.getFactory().getWorkUnitDAO().read(listWorkUnit.get(i).getId()).equals(listWorkUnit.get(i)));
        }
    }

    @Test
    public void testUpdate() {
        listWorkUnit.get(0).setSubactivity(AbstractDAOFactory.getFactory().getSubActivityDAO().read(2));
        workUnitEjb.update(listWorkUnit.get(0));
        assertTrue("ERROR creating", AbstractDAOFactory.getFactory().getSubActivityDAO().read(2).equals(listWorkUnit.get(0).getSubactivity()));
    }

    @Test
    public void testDelete() {
        workUnitEjb.delete(listWorkUnit.get(0));
        assertNull("ERROR deleting", AbstractDAOFactory.getFactory().getWorkUnitDAO().read(listWorkUnit.get(0).getId()));
    }

    @Test
    public void getAvailableWorkUnits() {
        assertTrue("ERROR availableUnits", workUnitEjb.getAvailableWorkUnits(subActivity, iteration).size() == 1);
    }

    @Test
    public void getWorkUnitsByIterAndSubActivity() {
        assertTrue("ERROR workUnitsByIterAndSubActivity", workUnitEjb.getWorkUnitsByIterAndSubActivity(subActivity, iteration).size() == 2);
    }

    @Test
    public void getWorkUnitsByIter() {
        assertTrue("ERROR workUnitsByIterAndSubActivity", workUnitEjb.getWorkUnitsByIter(iteration).size() == 2);
    }

    @Test
    public void getTotalWorkUnitsByWorker() {
        assertTrue("ERROR totalWorkUnitsByWorker", workUnitEjb.getTotalWorkUnitsByWorker(subActivity, iteration, worker).size() == 1);
    }

    @Test
    public void getWorkerWorkUnits() {
        assertTrue("ERROR workerWorkUnits", workUnitEjb.getWorkerWorkUnits(worker).size() == 1);
    }

    @Test
    public void storeHoursBySubActivityIteration() {
        final int numHours = 1;
        final int numWorkUnits = numHours * 4;

        for (int wu = 0; wu < this.workUnitEjb.calculatingWorkUnits(numHours); wu++) {
            this.listWorkUnit.add(new WorkUnitEntity(iteration, subActivity));
            this.workUnitEjb.create(listWorkUnit.get(wu));
        }

        System.out.println("numWorkUnits: " + this.workUnitEjb.getWorkUnitsByIterAndSubActivity(subActivity, iteration).size());
        assertSame("numero de unidades alamcenadas por subactividad e iteracion", this.workUnitEjb.getWorkUnitsByIterAndSubActivity(subActivity, iteration).size(), numWorkUnits);
    }

    @After
    public void after() {
        for (WorkUnitEntity workUnitEntity : listWorkUnit) {
            this.workUnitEjb.delete(workUnitEntity);
        }

        AbstractDAOFactory.getFactory().getIterationDAO().delete(iteration);
        AbstractDAOFactory.getFactory().getProjectDAO().delete(project);
        AbstractDAOFactory.getFactory().getUserDAO().delete(user);
        AbstractDAOFactory.getFactory().getWorkerDAO().delete(worker);

    }

}
