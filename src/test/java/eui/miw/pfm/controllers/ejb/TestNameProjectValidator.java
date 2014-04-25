/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eui.miw.pfm.controllers.ejb;

import eui.miw.pfm.models.dao.AbstractDAOFactory;
import eui.miw.pfm.models.dao.interfaces.ProjectDAO;
import eui.miw.pfm.models.dao.interfaces.UserDAO;
import eui.miw.pfm.models.entities.ProjectEntity;
import eui.miw.pfm.models.entities.UserEntity;
import java.util.Date;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Cesar Martinez
 */
public class TestNameProjectValidator {

    private transient ProjectEntity project;
    private transient ProjectEntity project2;
    private transient UserEntity user1;
    private transient  CreateProjectEjb createProjectEjb;

    @Before
    public void before() {
        project = new ProjectEntity();
        project2 = new ProjectEntity();
        user1 = new UserEntity();
        createProjectEjb = new CreateProjectEjb();

        user1.setName("Pepe");
        user1.setPassword("1234");
        user1.setUsername("pepe23");
        user1.setEmail("pepe@pepe.com");
        user1.setSurename("lopez");
        user1.setSecondSurename("guti");

    }

    @Test
    public void nameValidator1() {
        int numInt;
        numInt = 2;


        final UserDAO userDAO = AbstractDAOFactory.getFactory().getUserDAO();
        userDAO.create(this.user1);

        project.setChosenNumIteration(numInt);
        project.setDescription("Prueba de nameValidator");
        project.setStartDate(new Date());
        project.setEndDate(new Date());
        project.setEstimatedNumIteration(numInt);
        project.setName("Project1");
        project.setWeekNumIteration(numInt);
        project.setOwner(user1);

        createProjectEjb.createProject(project);


      
        assertFalse("Ya existe project y no lo valida correctamente", createProjectEjb.nameProjectValidator(project));

        project2.setChosenNumIteration(numInt);
        project2.setDescription("Prueba de nameValidator");
        project2.setStartDate(new Date());
        project2.setEndDate(new Date());
        project2.setEstimatedNumIteration(numInt);
        project2.setName("Project2");
        project2.setWeekNumIteration(numInt);
        project2.setOwner(user1);

  
        assertTrue("No existe project2 y no lo Valida Correctamente", createProjectEjb.nameProjectValidator(project2));

        createProjectEjb.createProject(project2);
        assertFalse("Ya existe project2 y no lo valida correctamente", createProjectEjb.nameProjectValidator(project2));

        final ProjectEntity project3 = new ProjectEntity();
        project3.setName("Project3");
        assertTrue("No existe project3 y no lo Valida Correctamente", createProjectEjb.nameProjectValidator(project3));

    }

    @After
    public void destroy() {
        final ProjectDAO projectDAO = AbstractDAOFactory.getFactory().getProjectDAO();
        final UserDAO userDAO = AbstractDAOFactory.getFactory().getUserDAO();
        projectDAO.delete(project);
        projectDAO.delete(project2);
        userDAO.delete(user1);
    }

}
