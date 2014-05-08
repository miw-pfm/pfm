/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eui.miw.pfm.controllers.ejb;

import eui.miw.pfm.models.dao.AbstractDAOFactory;
import eui.miw.pfm.models.entities.ProjectEntity;
import eui.miw.pfm.models.entities.UseCaseEntity;
import eui.miw.pfm.models.entities.UserEntity;
import eui.miw.pfm.models.entities.WorkerEntity;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Jose M Villar
 */
public class TestListProjectWorkersEjb {

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
        workerEntity1.setName("Pepe1");  //NOPMD
        workerEntity1.setSurname("Pepe");
        workerEntity1.addProject(project);

        workerEntity2 = new WorkerEntity();
        workerEntity2.setDni("098765432");
        workerEntity2.setEmail("pepe2@pepe.com");
        workerEntity2.setGitUser("pepe");
        workerEntity2.setName("Pepe2");
        workerEntity2.setSurname("Pepe");
        workerEntity2.addProject(project);

        workerEntity3 = new WorkerEntity();
        workerEntity3.setDni("234567890"); //NOPMD
        workerEntity3.setEmail("pepe3@pepe.com");
        workerEntity3.setGitUser("pepe");
        workerEntity3.setName("Pepe3");
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
    public void removeWorker() {
        final ListProjectWorkersEjb listWEjb = new ListProjectWorkersEjb();
        List<WorkerEntity> listPW = new ArrayList<WorkerEntity>();

        listPW.addAll(project.getWorkers());
        System.out.println(listPW.toString());
        
        assertTrue("Recupera w1, w2 y w3 correctamente", listWEjb.obtainWorkers(project).containsAll(listPW));
        listPW.clear();
        
        listWEjb.remove(project, workerEntity1);
        listWEjb.remove(project, workerEntity3);
        
        listPW.addAll(project.getWorkers());
        System.out.println(listPW.toString());
        
        assertTrue("Recupera w2 correctamente", listWEjb.obtainWorkers(project).containsAll(listPW));
        listPW.clear();                
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
