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
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Cesar Martinez
 */
public class TestCreateProjectValidatorBean {

    private UserEntity user;
    private ProjectEntity project;        
    
 
    @Before
    public void before() {      
        this.user = new UserEntity();
        this.project = new ProjectEntity();
        
        this.user.setName("Pepe");
        this.user.setPassword("1234");
        this.user.setUsername("pepe23");
        this.user.setEmail("pepe@pepe.com");
        this.user.setSurename("lopez");
        this.user.setSecondSurename("guti"); 
        
        UserDAO userDAO;
        userDAO = AbstractDAOFactory.getFactory().getUserDAO();
        userDAO.create(this.user);        
    }   
    
    @Test
    public void nameValidator1() {
        ProjectDAO projectDAO;
        projectDAO = AbstractDAOFactory.getFactory().getProjectDAO();
        int numInt; 
        numInt= 2;
        project.setChosenNumIteration(numInt);
        project.setDescription("Prueba de nameValidator");
        project.setStartDate(new Date());
        project.setEndDate(new Date());
        project.setEstimatedNumIteration(numInt);
        project.setName("Project1");
        project.setWeekNumIteration(numInt);
        project.setOwner(user);
        
        projectDAO.create(project);

        CreateProjectValidatorBean cPVB;
        cPVB = new CreateProjectValidatorBean();
//        assertTrue("Ya existe y no lo valida correctamente", cPVB.nameValidator("Project1"));
//        assertFalse("No existe y no lo Valida Correctamente", cPVB.nameValidator("Project2"));
    }

    @Test
    public void nameValidator2() {
        final ProjectDAO projectDAO = AbstractDAOFactory.getFactory().getProjectDAO();
        int numInt;
        numInt = 2;
        project = new ProjectEntity();
        project.setChosenNumIteration(numInt);
        project.setDescription("Prueba de nameValidator");
        project.setStartDate(new Date());
        project.setEndDate(new Date());
        project.setEstimatedNumIteration(numInt);
        project.setName("Project2");
        project.setWeekNumIteration(numInt);
        project.setOwner(user);
        

        projectDAO.create(project);
        CreateProjectValidatorBean cPVB;
        cPVB = new CreateProjectValidatorBean();

//        assertTrue("Ya existe y no lo valida correctamente", cPVB.nameValidator("Project1"));
//        assertTrue("Ya existe y no lo valida correctamente", cPVB.nameValidator("Project2"));
//        assertFalse("No existe y no lo Valida Correctamente", cPVB.nameValidator("Project3"));
    }
}
