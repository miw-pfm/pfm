/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eui.miw.pfm.controllers.beans;

import eui.miw.pfm.models.dao.AbstractDAOFactory;
import eui.miw.pfm.models.dao.interfaces.ProjectDAO;
import eui.miw.pfm.models.dao.interfaces.UserDAO;
import eui.miw.pfm.models.entities.ProjectEntity;
import eui.miw.pfm.models.entities.UserEntity;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Cesar Martinez
 */
public class TestCreateProjectValidatorBean {

    private ProjectEntity project;
    private ProjectEntity project2;
    private UserEntity user1;

    @Before
    public void before() {
        project = new ProjectEntity();
        project2 = new ProjectEntity();
        user1 = new UserEntity();

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

        ProjectDAO projectDAO = AbstractDAOFactory.getFactory().getProjectDAO();
        UserDAO userDAO = AbstractDAOFactory.getFactory().getUserDAO();
        userDAO.create(this.user1);

        project.setChosenNumIteration(numInt);
        project.setDescription("Prueba de nameValidator");
        project.setStartDate(new Date());
        project.setEndDate(new Date());
        project.setEstimatedNumIteration(numInt);
        project.setName("Project1");
        project.setWeekNumIteration(numInt);
        project.setOwner(user1);

        projectDAO.create(project);
        projectDAO = AbstractDAOFactory.getFactory().getProjectDAO();

        CreateProjectValidatorBean cPVB;
        cPVB = new CreateProjectValidatorBean();
        cPVB.setProjectEntity(project);

        assertTrue("Ya existe project y no lo valida correctamente", cPVB.nameValidator());

        project2.setChosenNumIteration(numInt);
        project2.setDescription("Prueba de nameValidator");
        project2.setStartDate(new Date());
        project2.setEndDate(new Date());
        project2.setEstimatedNumIteration(numInt);
        project2.setName("Project2");
        project2.setWeekNumIteration(numInt);
        project2.setOwner(user1);

        cPVB.setProjectEntity(project2);
        assertFalse("No existe project2 y no lo Valida Correctamente", cPVB.nameValidator());

        projectDAO.create(project2);
        assertTrue("Ya existe project2 y no lo valida correctamente", cPVB.nameValidator());

        ProjectEntity project3 = new ProjectEntity();
        project3.setName("Project3");
        cPVB.setProjectEntity(project3);
        assertFalse("No existe project3 y no lo Valida Correctamente", cPVB.nameValidator());

    }

    @After
    public void destroy() {
        ProjectDAO projectDAO = AbstractDAOFactory.getFactory().getProjectDAO();
        UserDAO userDAO = AbstractDAOFactory.getFactory().getUserDAO();
        projectDAO.delete(project);
        projectDAO.delete(project2);
        userDAO.delete(user1);
    }
}
