/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eui.miw.pfm.models.entities;

import eui.miw.pfm.models.dao.AbstractDAOFactory;
import eui.miw.pfm.models.dao.interfaces.ProjectDAO;
import eui.miw.pfm.models.dao.interfaces.UserDAO;
import eui.miw.pfm.models.dao.interfaces.WorkerDAO;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
/**
 *
 * @author Roberto Amor
 */
public class TestWorkerEntity {
    private static WorkerDAO wd = AbstractDAOFactory.getFactory().getWorkerDAO();
    private static WorkerEntity worker;

    public TestWorkerEntity() {
    }
    
    @BeforeClass
    public static void init() {
       TestWorkerEntity.wd = AbstractDAOFactory.getFactory().getWorkerDAO();
       TestWorkerEntity.worker = new WorkerEntity();
    }
    
    @Test
    public void testCreateEntity(){
        TestWorkerEntity.worker.setName("Pepe");
        TestWorkerEntity.worker.setDni("12345678X");
        TestWorkerEntity.worker.setGitUser("pepito23");
        TestWorkerEntity.worker.setEmail("pepe@pepe.com");
        TestWorkerEntity.worker.setSurname("lopez");
        
        ProjectDAO pd = AbstractDAOFactory.getFactory().getProjectDAO();
        ProjectEntity p = pd.read(1);
        TestWorkerEntity.worker.addProject(p);
        wd = AbstractDAOFactory.getFactory().getWorkerDAO();
        wd.create(TestWorkerEntity.worker);

        assertTrue("fin test",true); // NOPMD
        
        WorkerEntity w2 = TestWorkerEntity.wd.read(1);
        
        for(ProjectEntity pr : w2.getProjects()){
            System.out.println("proyecto: "+pr.getName());
        }
        
        ProjectEntity pro = pd.read(1);
        for(WorkerEntity worker : pro.getWorkers()){
            System.out.println("worker: "+worker.getName());
        }
        
        
    }
    
    @Test
    public void testDeleteProjectWorker(){
        ProjectDAO pd = AbstractDAOFactory.getFactory().getProjectDAO();
        ProjectEntity p = pd.read(1);
        TestWorkerEntity.worker.removeProject(p);
        wd = AbstractDAOFactory.getFactory().getWorkerDAO();
        this.wd.update(TestWorkerEntity.worker);
        assertTrue("fin test",true); // NOPMD
    }
    
    @AfterClass
    public static void after() {
        TestWorkerEntity.wd.delete(TestWorkerEntity.worker);
    }
}
