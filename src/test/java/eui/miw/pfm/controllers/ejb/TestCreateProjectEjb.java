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
public class TestCreateProjectEjb {

    private ProjectEntity project;
    private ProjectDAO projectDAO;
    private UserDAO userDAO;
    private UserEntity user;


    @Before
    public void before() {
        project = new ProjectEntity();

        user = new UserEntity();
        user.setName("Pepe");
        user.setPassword("1234");
        user.setUsername("pepe23");
        user.setEmail("pepe@pepe.com");
        user.setSurename("lopez");
        user.setSecondSurename("guti");
    }

    @Test
    public void createProject() {
        projectDAO = AbstractDAOFactory.getFactory().getProjectDAO();        
        userDAO = AbstractDAOFactory.getFactory().getUserDAO();
        userDAO.create(this.user);
        
        int numInt;
        numInt = 2;
        
        project.setChosenNumIteration(numInt);
        project.setDescription("Prueba de nameValidator");
        project.setStartDate(new Date());
        project.setEndDate(new Date());
        project.setEstimatedNumIteration(numInt);
        project.setName("TestProject1111");
        project.setWeekNumIteration(numInt);
        project.setOwner(user);
        
        projectDAO.create(project);

        String[] name = {"name"};
        String[] values = {"TestProject"};
        assertNotNull(projectDAO.find(name, values));

    }

    @After
    public void after() {
        projectDAO.delete(project);
        userDAO.delete(user);        
    }
}
