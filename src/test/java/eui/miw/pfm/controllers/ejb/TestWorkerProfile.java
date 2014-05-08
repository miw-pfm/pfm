/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eui.miw.pfm.controllers.ejb;

import eui.miw.pfm.models.dao.AbstractDAOFactory;
import eui.miw.pfm.models.entities.ProjectEntity;
import eui.miw.pfm.models.entities.UserEntity;
import eui.miw.pfm.models.entities.WorkerEntity;
import eui.miw.pfm.util.moks.profile.TasksEntityMock;
import java.util.Date;
import java.util.List;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Fred Peña
 * @author Clemencio Morales
 * @author Pepe Bustamante
 */
public class TestWorkerProfile {

    private transient ProjectEntity projectEntity1;
    private transient ProjectEntity projectEntity2;
    private transient WorkerEntity workerEntity1;
    private transient WorkerEntity workerEntity2;
    private transient UserEntity userEntity;
    private transient TasksEntityMock tasksEntityMock1;
    private transient TasksEntityMock tasksEntityMock2;
    private transient TasksEntityMock tasksEntityMock3;

    @Before
    public void init() {
        userEntity = new UserEntity();
        userEntity.setEmail("pepe@gmail.com");
        userEntity.setName("Pepe");
        userEntity.setPassword("123456789");
        userEntity.setSecondSurname("Pepe");
        userEntity.setSurname("Pepe");
        userEntity.setUsername("pepe");
        AbstractDAOFactory.getFactory().getUserDAO().create(userEntity);

        workerEntity1 = new WorkerEntity();
        workerEntity1.setDni("12345678");
        workerEntity1.setEmail("pepe@gmail.com");
        workerEntity1.setGitUser("pepe");
        workerEntity1.setName("Pepe");
        workerEntity1.setSurname("Pepe");
        AbstractDAOFactory.getFactory().getWorkerDAO().create(workerEntity1);

        workerEntity2 = new WorkerEntity();
        workerEntity2.setDni("23456789");
        workerEntity2.setEmail("pepe1@gmail.com");
        workerEntity2.setGitUser("pepe1");
        workerEntity2.setName("Pepe1");
        workerEntity2.setSurname("Pepe1");
        AbstractDAOFactory.getFactory().getWorkerDAO().create(workerEntity2);

        projectEntity1 = new ProjectEntity();
        projectEntity1.setChosenNumIteration(1);
        projectEntity1.setDescription("Des 1");
        projectEntity1.setEndDate(new Date());
        projectEntity1.setEstimatedNumIteration(2);
        projectEntity1.setName("Project 1");
        projectEntity1.setOwner(userEntity);
        projectEntity1.setStartDate(new Date());
        projectEntity1.setStringEndDate("123");
        projectEntity1.setWeekNumIteration(3);
        projectEntity1.addWorker(workerEntity1);
        projectEntity1.addWorker(workerEntity2);
        AbstractDAOFactory.getFactory().getProjectDAO().create(projectEntity1);

        projectEntity2 = new ProjectEntity();
        projectEntity2.setChosenNumIteration(1);
        projectEntity2.setDescription("Des 2");
        projectEntity2.setEndDate(new Date());
        projectEntity2.setEstimatedNumIteration(2);
        projectEntity2.setName("Project 2");
        projectEntity2.setOwner(userEntity);
        projectEntity2.setStartDate(new Date());
        projectEntity2.setStringEndDate("123");
        projectEntity2.setWeekNumIteration(3);
        projectEntity2.addWorker(workerEntity1);
        AbstractDAOFactory.getFactory().getProjectDAO().create(projectEntity2);

        tasksEntityMock1 = new TasksEntityMock();
        tasksEntityMock1.setName("Task 1");
        tasksEntityMock1.setProject(projectEntity1);
        tasksEntityMock1.setTime(2);
        tasksEntityMock1.setWorker(workerEntity1);
        AbstractDAOFactory.getFactory().getTaskDAO().create(tasksEntityMock1);

        tasksEntityMock2 = new TasksEntityMock();
        tasksEntityMock2.setName("Task 2");
        tasksEntityMock2.setProject(projectEntity1);
        tasksEntityMock2.setTime(2);
        tasksEntityMock2.setWorker(workerEntity2);
        AbstractDAOFactory.getFactory().getTaskDAO().create(tasksEntityMock2);

        tasksEntityMock3 = new TasksEntityMock();
        tasksEntityMock3.setName("Task 1");
        tasksEntityMock3.setProject(projectEntity2);
        tasksEntityMock3.setTime(2);
        tasksEntityMock3.setWorker(workerEntity1);
        AbstractDAOFactory.getFactory().getTaskDAO().create(tasksEntityMock3);

    }

    @Test
    public void testWorker1() {
        WorkerProfileEjb workerProfileEjb = new WorkerProfileEjb();
        List<ProjectEntity> projectEntities = workerProfileEjb.findProjects(AbstractDAOFactory.getFactory().getWorkerDAO().read(workerEntity1.getId()));

        for (ProjectEntity pe : projectEntities) {
            Assert.assertTrue("No exist", workerEntity1.getProjects().contains(projectEntity1));
            Assert.assertTrue("No exist", workerEntity1.getProjects().contains(projectEntity2));
            for (TasksEntityMock te : pe.getTaskMock()) {
                Assert.assertTrue("No exist", workerEntity1.getTaskMock().contains(tasksEntityMock1));
                Assert.assertTrue("No exist", workerEntity1.getTaskMock().contains(tasksEntityMock3));
                Assert.assertFalse("Exist", workerEntity1.getTaskMock().contains(tasksEntityMock2));
            }
        }
    }

    @Test
    public void testWorker2() {
        WorkerProfileEjb workerProfileEjb = new WorkerProfileEjb();
        List<ProjectEntity> projectEntities = workerProfileEjb.findProjects(AbstractDAOFactory.getFactory().getWorkerDAO().read(workerEntity2.getId()));

        for (ProjectEntity pe : projectEntities) {
            Assert.assertTrue("No exist", workerEntity2.getProjects().contains(projectEntity1));
            Assert.assertFalse("Exist", workerEntity2.getProjects().contains(projectEntity2));
            for (TasksEntityMock te : pe.getTaskMock()) {
                Assert.assertFalse("Exist", workerEntity2.getTaskMock().contains(tasksEntityMock1));
                Assert.assertFalse("Exist", workerEntity2.getTaskMock().contains(tasksEntityMock3));
                Assert.assertTrue("No exist", workerEntity2.getTaskMock().contains(tasksEntityMock2));
            }
        }
    }

    @After
    public void finish() {
        AbstractDAOFactory.getFactory().getTaskDAO().delete(tasksEntityMock1);
        AbstractDAOFactory.getFactory().getTaskDAO().delete(tasksEntityMock2);
        AbstractDAOFactory.getFactory().getTaskDAO().delete(tasksEntityMock3);
        AbstractDAOFactory.getFactory().getProjectDAO().delete(projectEntity1);
        AbstractDAOFactory.getFactory().getProjectDAO().delete(projectEntity2);
        AbstractDAOFactory.getFactory().getWorkerDAO().delete(workerEntity1);
        AbstractDAOFactory.getFactory().getWorkerDAO().delete(workerEntity2);
        AbstractDAOFactory.getFactory().getUserDAO().delete(userEntity);
    }
}
