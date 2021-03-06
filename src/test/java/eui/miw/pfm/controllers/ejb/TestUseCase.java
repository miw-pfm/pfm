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
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Roberto Amor
 */
public class TestUseCase {

    private transient UseCaseEntity usecase;
    private transient UseCaseEjb usecaseEjb;
    private transient ProjectDAO projectDAO;
    private transient UseCaseDAO usecaseDAO;
    private transient UserDAO userDAO;
    private transient UserEntity user;
    private transient ProjectEntity project;
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
        userDAO = AbstractDAOFactory.getFactory().getUserDAO();
        projectDAO = AbstractDAOFactory.getFactory().getProjectDAO();
        usecaseDAO = AbstractDAOFactory.getFactory().getUseCaseDAO();

        usecaseEjb = new UseCaseEjb();

        fill_userentity();
        fill_projectentity();
        fill_usecaseentity();

        userDAO.create(user);
        projectDAO.create(project);
    }

    @Test
    public void updateTest() {
        usecaseDAO.create(usecase); // se guarda en la base de datos
        usecase.setDescription("UseCase de ejemplo modificado");
        usecase.setName("UseCase 1 Modificado");
        usecaseEjb.update(usecase);
        UseCaseEntity usecase2 = usecaseDAO.read(usecase.getId()); // se obtiene de la BD        
        assertTrue("Modified", usecase2.getDescription().equals("UseCase de ejemplo modificado") && usecase2.getName().equals("UseCase 1 Modificado"));
    }

    @Test
    public void createUseCase() {
        usecaseDAO.create(usecase);
        usecaseEjb.create(usecase);
        String[] name = {"name"};
        String[] values = {UC_NAME};
        UseCaseEntity recoveredUC = usecaseDAO.read(usecase.getId());
        assertEquals(recoveredUC.getName(), usecase.getName());
        assertEquals(recoveredUC.getDescription(), usecase.getDescription());
    }

    @Test
    public void testDelete() {
        usecaseDAO.create(usecase);        
        assertNotNull("", usecaseDAO.read(usecase.getId()));
        usecaseEjb.delete(usecase);
        assertNull("This project still exists after deleted", usecaseDAO.read(usecase.getId()));
    }

    public void fill_userentity() {
        this.user = new UserEntity();
        this.user.setName(NAME);
        this.user.setPassword(PASSWORD);
        this.user.setUsername(USERNAME);
        this.user.setEmail(EMAIL);
        this.user.setSurname(SURENAME);
        this.user.setSecondSurname(SECOND_SURENAME);
    }

    public void fill_projectentity() {
        project = new ProjectEntity();
        project.setChosenNumIteration(NUMBER_OF_ITERATIONS);
        project.setDescription(PROJECT_DESCRIPTION);
        project.setStartDate(new Date());
        project.setEndDate(new Date());
        project.setName(PROJECT_NAME);
        project.setWeekNumIteration(NUMBER_OF_WEEKS);
        project.setOwner(user);
    }

    public void fill_usecaseentity() {
        usecase = new UseCaseEntity();
        usecase.setName(UC_NAME);
        usecase.setDescription(UC_DESCRIPTION);
        usecase.setProject(project);
    }

    @After
    public void after() {
        usecaseDAO.delete(usecase);
        projectDAO.delete(project);
        userDAO.delete(user);
    }
}
