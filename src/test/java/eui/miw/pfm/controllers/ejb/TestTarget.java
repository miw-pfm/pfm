/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eui.miw.pfm.controllers.ejb;

import eui.miw.pfm.models.dao.AbstractDAOFactory;
import eui.miw.pfm.models.dao.interfaces.DisciplineDAO;
import eui.miw.pfm.models.dao.interfaces.ProjectDAO;
import eui.miw.pfm.models.dao.interfaces.TargetDAO;
import eui.miw.pfm.models.dao.interfaces.UserDAO;
import eui.miw.pfm.models.entities.DisciplineEntity;
import eui.miw.pfm.models.entities.ProjectEntity;
import eui.miw.pfm.models.entities.TargetEntity;
import eui.miw.pfm.models.entities.UserEntity;
import java.util.Date;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Jean Mubaied
 */
public class TestTarget {

    private transient TargetEntity target;
    private transient TargetEjb targetEjb;
    private transient ProjectEntity project;
    private transient DisciplineEntity discipline;
    private transient UserEntity user;
    private transient ProjectDAO projectDAO;
    private transient UserDAO userDAO;
    private transient TargetDAO targetDAO;
    private transient DisciplineDAO disciplineDAO;

    //Input data for target
    private transient final int T_INCEPTION = 15;
    private transient final int T_ELABORATION = 20;
    private transient final int T_CONSTRUCTION = 14;
    private transient final int T_TRANSITION = 15;

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

    //Input data for Discipline
    private transient final String NAME_OF_DICIPLINE = "Discipline";

    @Before
    public void before() {
        userDAO = AbstractDAOFactory.getFactory().getUserDAO();
        projectDAO = AbstractDAOFactory.getFactory().getProjectDAO();
        targetDAO = AbstractDAOFactory.getFactory().getTargetDAO();
        disciplineDAO = AbstractDAOFactory.getFactory().getDisciplineDAO();

        targetEjb = new TargetEjb();

        fill_userentity();
        fill_projectentity();
        fill_disciplineentity();
        fill_targetentity();

        userDAO.create(user);
        projectDAO.create(project);
        disciplineDAO.create(discipline);
        targetDAO.create(target);
    }

    @Test
    public void createUseCase() {
        targetDAO.create(this.target);
        targetEjb.createTarget(this.target);
        TargetEntity recoveredT = targetDAO.read(target.getId());
        assertEquals(recoveredT.getConstruction(), this.target.getConstruction());
        assertEquals(recoveredT.getElaboration(), this.target.getElaboration());
        assertEquals(recoveredT.getInception(), this.target.getInception());
        assertEquals(recoveredT.getTransition(), this.target.getTransition());

    }

    @Test
    public void updateTest() {
        targetDAO.create(this.target); // se guarda en la base de datos
        this.target.setInception(99);
        this.target.setElaboration(99);
        targetEjb.update(this.target);
        TargetEntity targettest = targetDAO.read(this.target.getId()); // se obtiene de la BD        
        assertTrue("Modified", targettest.getInception().equals(99) && targettest.getElaboration().equals(99));
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
        this.project = new ProjectEntity();
        this.project.setChosenNumIteration(NUMBER_OF_ITERATIONS);
        this.project.setDescription(PROJECT_DESCRIPTION);
        this.project.setStartDate(new Date());
        this.project.setEndDate(new Date());
        this.project.setName(PROJECT_NAME);
        this.project.setWeekNumIteration(NUMBER_OF_WEEKS);
        this.project.setOwner(user);
    }

    public void fill_disciplineentity() {
        this.discipline = new DisciplineEntity();
        this.discipline.setName(NAME_OF_DICIPLINE);

    }

    public void fill_targetentity() {
        this.target = new TargetEntity();
        this.target.setInception(T_INCEPTION);
        this.target.setElaboration(T_ELABORATION);
        this.target.setConstruction(T_CONSTRUCTION);
        this.target.setTransition(T_TRANSITION);
        this.target.setProject(this.project);
        this.target.setDiscipline(this.discipline);
    }

    @After
    public void after() {
        targetDAO.delete(this.target);
        disciplineDAO.delete(this.discipline);
        projectDAO.delete(this.project);
        userDAO.delete(this.user);
    }
}
