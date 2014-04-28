/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eui.miw.pfm.controllers.ejb;

import eui.miw.pfm.models.dao.AbstractDAOFactory;
import eui.miw.pfm.models.dao.interfaces.ProjectDAO;
import eui.miw.pfm.models.dao.interfaces.UseCaseDAO;
import eui.miw.pfm.models.dao.interfaces.UserDAO;
import eui.miw.pfm.models.entities.ProjectEntity;
import eui.miw.pfm.models.entities.UseCaseEntity;
import eui.miw.pfm.models.entities.UserEntity;
import java.util.Date;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Clemencio Morales Lucas
 * @author Manuel Rodríguez Momediano
 */
public class TestCreateUseCase {
    
    private transient ProjectEntity project;
    private transient UseCaseEjb useCaseEjb;
    private transient UseCaseEntity useCaseEntity;
    private transient UserEntity user;
    private transient ProjectDAO projectDAO;
    private transient UserDAO userDAO;
    
    //Input data for UseCase
    private transient final String UC_NAME = "Caso de Uso 1";
    private transient final String UC_DESCRIPTION = "First UC";
    
    //Input data for Project
    private transient final int NUMBER_OF_ITERATIONS = 2;
    private transient final int NUMBER_OF_WEEKS = 3;
    private transient final String PROJECT_DESCRIPTION = "First project";
    private transient final String PROJECT_NAME = "Project 1";
    
    //Input data for User
    private transient final String NAME = "Pepe";
    private transient final String PASSWORD = "1234";
    private transient final String USERNAME = "pepe23";
    private transient final String EMAIL = "pepe@pepe.com";
    private transient final String SURENAME = "lopez";
    private transient final String SECOND_SURENAME = "guti";
    
    @Before
    public void before() {
        project = new ProjectEntity();
        useCaseEjb = new UseCaseEjb();
        userDAO = AbstractDAOFactory.getFactory().getUserDAO();
        projectDAO = AbstractDAOFactory.getFactory().getProjectDAO();
       
        project.setChosenNumIteration(NUMBER_OF_ITERATIONS);
        project.setDescription(PROJECT_DESCRIPTION);
        project.setStartDate(new Date());
        project.setEndDate(new Date());
        project.setEstimatedNumIteration(NUMBER_OF_ITERATIONS);
        project.setName(PROJECT_NAME);
        project.setWeekNumIteration(NUMBER_OF_WEEKS);
        
        user = new UserEntity();
        user.setName(NAME);
        user.setPassword(PASSWORD);
        user.setUsername(USERNAME);
        user.setEmail(EMAIL);
        user.setSurename(SURENAME);
        user.setSecondSurename(SECOND_SURENAME);
        userDAO.create(user);
        
        project.setOwner(user);
        projectDAO.create(project);
        
        useCaseEntity = new UseCaseEntity();
        useCaseEntity.setName(UC_NAME);
        useCaseEntity.setDescription(UC_DESCRIPTION);
        useCaseEntity.setProject(project);
        
        projectDAO.update(project);
    }
    
    @Test
     public void createUseCase() {
        final UserDAO userDAO = AbstractDAOFactory.getFactory().getUserDAO();
        final UseCaseDAO useCaseDAO = AbstractDAOFactory.getFactory().getUseCaseDAO();
        final ProjectDAO projectDAO = AbstractDAOFactory.getFactory().getProjectDAO();
        useCaseDAO.create(this.useCaseEntity);
        useCaseEjb.create(useCaseEntity);
        
        String[] name = {"name"};
        String[] values = {"Caso de Uso 1"};
        
        UseCaseEntity recoveredUC = useCaseDAO.read(1);
        
        assertEquals(recoveredUC.getName(), useCaseEntity.getName());
        assertEquals(recoveredUC.getDescription(), useCaseEntity.getDescription());
     }
    
    @After
    public void after() {
        final ProjectDAO projectDAO = AbstractDAOFactory.getFactory().getProjectDAO();
        final UseCaseDAO useCaseDAO = AbstractDAOFactory.getFactory().getUseCaseDAO();
        final UserDAO userDAO = AbstractDAOFactory.getFactory().getUserDAO();

        useCaseDAO.delete(useCaseEntity);
        projectDAO.delete(project);
        userDAO.delete(user);
        
    }
}
