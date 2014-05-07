/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eui.miw.pfm.controllers.ejb;

import eui.miw.pfm.models.dao.AbstractDAOFactory;
import eui.miw.pfm.models.dao.interfaces.ProjectDAO;
import eui.miw.pfm.models.dao.interfaces.UserDAO;
import eui.miw.pfm.models.dao.interfaces.WorkerDAO;
import eui.miw.pfm.models.entities.ProjectEntity;
import eui.miw.pfm.models.entities.UserEntity;
import eui.miw.pfm.models.entities.WorkerEntity;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author aw0591
 */
public class TestCRUDWorker {

    private transient final UserEntity user = new UserEntity();
    private transient final ProjectEntity project = new ProjectEntity();
    private transient final WorkerEntity worker = new WorkerEntity();
    private transient final WorkerEntity worker2 = new WorkerEntity();

    private transient final UserDAO userDAO = AbstractDAOFactory.getFactory().getUserDAO();
    private transient final ProjectDAO projectDAO = AbstractDAOFactory.getFactory().getProjectDAO();
    private transient final WorkerDAO workerDAO = AbstractDAOFactory.getFactory().getWorkerDAO();
        
    @Before
    public void before() {
        this.user.setName("usuario");
        this.userDAO.create(user);

        this.project.setName("Projecto prueba Worker");
        this.project.setOwner(user);
        this.projectDAO.create(project);          
    }
    
    @Test
    public void deleteWorker() {
        this.worker.setName("Trabajador 1");
        this.worker.setDni("47028153F");
        this.worker.addProject(project);              
        this.workerDAO.create(this.worker);
        
        this.project.addWorker(this.worker);
        this.projectDAO.update(project);
        
        assertNotNull("No existe el trabajador antes de borrar", workerDAO.read(worker.getId()));
        
        final WorkerEjb workerEjb = new WorkerEjb();
        workerEjb.delete(this.worker);
        assertNull("Todavia existe el trabajador despues de borrar", workerDAO.read(this.worker.getId())); 
        
        this.worker2.setName("Trabajador 1");
        this.worker2.setDni("47028153F");
        this.worker2.addProject(project);        

        workerEjb.delete(this.worker2); 
        this.workerDAO.create(this.worker2);
        assertNotNull("El trabajador existe despues de borrar", workerDAO.read(this.worker2.getId()));         
    }
}
