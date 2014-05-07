/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eui.miw.pfm.controllers.ejb;

import eui.miw.pfm.models.dao.AbstractDAOFactory;
import eui.miw.pfm.models.entities.WorkerEntity;
import org.junit.After;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Fred Peña
 */
public class TestCRUDWorker {

    private WorkerEntity workerEntity1;
    private WorkerEntity workerEntity2;
    private WorkerEntity workerEntity3;

    @Before
    public void init() {
        workerEntity1 = new WorkerEntity();
        workerEntity1.setDni("12345678");
        workerEntity1.setEmail("pepe1@pepe.com");
        workerEntity1.setGitUser("pepe");
        workerEntity1.setName("Pepe");
        workerEntity1.setSurname("Pepe");

        workerEntity2 = new WorkerEntity();
        workerEntity2.setDni("098765432");
        workerEntity2.setEmail("pepe2@pepe.com");
        workerEntity2.setGitUser("pepe");
        workerEntity2.setName("Pepe");
        workerEntity2.setSurname("Pepe");

        workerEntity3 = new WorkerEntity();
        workerEntity3.setDni("234567890");
        workerEntity3.setEmail("pepe3@pepe.com");
        workerEntity3.setGitUser("pepe");
        workerEntity3.setName("Pepe");
        workerEntity3.setSurname("Pepe");

        AbstractDAOFactory.getFactory().getWorkerDAO().create(workerEntity1);
        AbstractDAOFactory.getFactory().getWorkerDAO().create(workerEntity2);
        AbstractDAOFactory.getFactory().getWorkerDAO().create(workerEntity3);

    }

    @Test
    public void testUpdate() {
        WorkerEjb workerEjb = new WorkerEjb();

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
        WorkerEjb workerEjb = new WorkerEjb();
        WorkerEntity workerEntity4 = new WorkerEntity();
        WorkerEntity workerEntity5 = new WorkerEntity();
        WorkerEntity workerEntity6 = new WorkerEntity();

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

    @After
    public void finish() {
        AbstractDAOFactory.getFactory().getWorkerDAO().delete(workerEntity1);
        AbstractDAOFactory.getFactory().getWorkerDAO().delete(workerEntity2);
        AbstractDAOFactory.getFactory().getWorkerDAO().delete(workerEntity3);
    }

}
