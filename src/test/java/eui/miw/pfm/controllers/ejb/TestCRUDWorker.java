package eui.miw.pfm.controllers.ejb;

import eui.miw.pfm.models.dao.AbstractDAOFactory;
import eui.miw.pfm.models.entities.ProjectEntity;
import eui.miw.pfm.models.entities.UserEntity;
import eui.miw.pfm.models.entities.WorkerEntity;
import org.junit.After;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Fred Peña
 * @author Jose M Villar
 */
public class TestCRUDWorker {

    private transient WorkerEntity workerEntity1;
    private transient WorkerEntity workerEntity2;
    private transient WorkerEntity workerEntity3;

    private transient UserEntity user;
    private transient ProjectEntity project;
    
    @Before
    public void init() {
        user = new UserEntity();
        user.setName("usuario");
        AbstractDAOFactory.getFactory().getUserDAO().create(user);

        project = new ProjectEntity();
        project.setName("Projecto prueba Worker");
        project.setOwner(user);
        AbstractDAOFactory.getFactory().getProjectDAO().create(project);

        workerEntity1 = new WorkerEntity();
        workerEntity1.setDni("12345678");
        workerEntity1.setEmail("pepe1@pepe.com");
        workerEntity1.setGitUser("pepe");  //NOPMD
        workerEntity1.setName("Pepe");  //NOPMD
        workerEntity1.setSurname("Pepe");
        workerEntity1.addProject(project);

        workerEntity2 = new WorkerEntity();
        workerEntity2.setDni("098765432");
        workerEntity2.setEmail("pepe2@pepe.com");
        workerEntity2.setGitUser("pepe");
        workerEntity2.setName("Pepe");
        workerEntity2.setSurname("Pepe");
        workerEntity2.addProject(project);

        workerEntity3 = new WorkerEntity();
        workerEntity3.setDni("234567890"); //NOPMD
        workerEntity3.setEmail("pepe3@pepe.com");
        workerEntity3.setGitUser("pepe");
        workerEntity3.setName("Pepe");
        workerEntity3.setSurname("Pepe");
        workerEntity3.addProject(project);

        AbstractDAOFactory.getFactory().getWorkerDAO().create(workerEntity1);
        AbstractDAOFactory.getFactory().getWorkerDAO().create(workerEntity2);
        AbstractDAOFactory.getFactory().getWorkerDAO().create(workerEntity3);

        project.addWorker(workerEntity1);
        AbstractDAOFactory.getFactory().getProjectDAO().update(project);

        project.addWorker(workerEntity2);
        AbstractDAOFactory.getFactory().getProjectDAO().update(project);

        project.addWorker(workerEntity3);
        AbstractDAOFactory.getFactory().getProjectDAO().update(project);

    }

    @Test
    public void testUpdate() {
        final WorkerEjb workerEjb = new WorkerEjb();

        workerEntity1.setDni("qwerty");
        workerEntity1.setEmail("pepe1@gmail.com");

        workerEntity2.setDni("234567890");
        workerEntity2.setEmail("pepe3@gmail.com");

        workerEntity3.setDni("234567890");
        workerEntity3.setEmail("pepe3@gmail.com");

        workerEjb.update(workerEntity1);
        workerEjb.update(workerEntity2);
        workerEjb.update(workerEntity3);

        assertTrue("ERROR updating", AbstractDAOFactory.getFactory().getWorkerDAO().read(workerEntity1.getId()).equals(workerEntity1));
        assertTrue("ERROR updating", AbstractDAOFactory.getFactory().getWorkerDAO().read(workerEntity2.getId()).equals(workerEntity2));
        assertTrue("ERROR updating", AbstractDAOFactory.getFactory().getWorkerDAO().read(workerEntity3.getId()).equals(workerEntity3));

    }

    @Test
    public void testCreate() {
        final WorkerEjb workerEjb = new WorkerEjb();
        final WorkerEntity workerEntity4 = new WorkerEntity();
        final WorkerEntity workerEntity5 = new WorkerEntity();
        final WorkerEntity workerEntity6 = new WorkerEntity();

        workerEntity4.setDni("12345678");
        workerEntity4.setEmail("pepecreate@pepe.com");
        workerEntity4.setGitUser("pepe");
        workerEntity4.setName("Pepe");
        workerEntity4.setSurname("Pepe");

        workerEntity5.setDni("098765432");
        workerEntity5.setEmail("pepecreate@pepe.com");
        workerEntity5.setGitUser("pepe");
        workerEntity5.setName("Pepe");
        workerEntity5.setSurname("Pepe");

        workerEntity6.setDni("234567890");
        workerEntity6.setEmail("pepecreate@pepe.com");
        workerEntity6.setGitUser("pepe");
        workerEntity6.setName("Pepe");
        workerEntity6.setSurname("Pepe");

        workerEjb.create(workerEntity4);
        workerEjb.create(workerEntity5);
        workerEjb.create(workerEntity6);

        assertTrue("ERROR creating", AbstractDAOFactory.getFactory().getWorkerDAO().read(workerEntity4.getId()).equals(workerEntity4));
        assertTrue("ERROR creating", AbstractDAOFactory.getFactory().getWorkerDAO().read(workerEntity5.getId()).equals(workerEntity5));
        assertTrue("ERROR creating", AbstractDAOFactory.getFactory().getWorkerDAO().read(workerEntity6.getId()).equals(workerEntity6));

        AbstractDAOFactory.getFactory().getWorkerDAO().delete(workerEntity4);
        AbstractDAOFactory.getFactory().getWorkerDAO().delete(workerEntity5);
        AbstractDAOFactory.getFactory().getWorkerDAO().delete(workerEntity6);
    }

    @Test
    public void testDelete() {

        assertNotNull("No existe el trabajador antes de borrar", AbstractDAOFactory.getFactory().getWorkerDAO().read(workerEntity1.getId()));

        final WorkerEjb workerEjb = new WorkerEjb();
        workerEjb.delete(workerEntity1);
        assertNull("Todavia existe el trabajador despues de borrar", AbstractDAOFactory.getFactory().getWorkerDAO().read(workerEntity1.getId()));
    }

    @After
    public void finish() {
        AbstractDAOFactory.getFactory().getWorkerDAO().delete(workerEntity1);
        AbstractDAOFactory.getFactory().getWorkerDAO().delete(workerEntity2);
        AbstractDAOFactory.getFactory().getWorkerDAO().delete(workerEntity3);
        
        AbstractDAOFactory.getFactory().getProjectDAO().delete(project);
        AbstractDAOFactory.getFactory().getUserDAO().delete(user);
    }

}