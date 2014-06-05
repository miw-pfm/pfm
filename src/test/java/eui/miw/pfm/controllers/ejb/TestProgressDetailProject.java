/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eui.miw.pfm.controllers.ejb;

import eui.miw.pfm.models.dao.AbstractDAOFactory;
import eui.miw.pfm.models.entities.DisciplineEntity;
import eui.miw.pfm.models.entities.IterationEntity;
import eui.miw.pfm.models.entities.ProgressDetailEntity;
import eui.miw.pfm.models.entities.ProjectEntity;
import eui.miw.pfm.models.entities.UseCaseEntity;
import eui.miw.pfm.models.entities.UserEntity;
import eui.miw.pfm.util.TypeIteration;
import java.util.Calendar;
import java.util.Date;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Fred Pena
 */
public class TestProgressDetailProject {

    private static ProgressDetailEntity progressDetail1;
    private static ProgressDetailEntity progressDetail2;
    private static ProgressDetailEntity progressDetail3;

    private static UserEntity user;
    private static ProjectEntity project;
    private static DisciplineEntity discipline;
    private static IterationEntity iteration;
    private static UseCaseEntity useCase;

    @BeforeClass
    public static void before() {

        user = new UserEntity();
        user.setName("usuario");
        user.setEmail("pepe@gmail.com");
        user.setPassword("123456789");
        user.setSecondSurname("Pepe");
        user.setSurname("Pepe");
        user.setUsername("pepe");
        AbstractDAOFactory.getFactory().getUserDAO().create(user);

        project = new ProjectEntity();
        project.setName("Projecto prueba Calendar");
        project.setChosenNumIteration(1);
        project.setDescription("Desc");
        project.setEndDate(new Date());
        project.setEstimatedNumIteration(1);
        project.setStartDate(new Date());
        project.setStringEndDate("123456");
        project.setWeekNumIteration(2);
        project.setOwner(user);
        AbstractDAOFactory.getFactory().getProjectDAO().create(project);

        discipline = new DisciplineEntity();
        discipline.setName("Discipline 1");
        AbstractDAOFactory.getFactory().getDisciplineDAO().create(discipline);

        iteration = new IterationEntity();
        iteration.setEndDate(Calendar.getInstance());
        iteration.setIterValue(4);
        iteration.setProject(project);
        iteration.setStartDate(Calendar.getInstance());
        iteration.setTypeIteration(TypeIteration.INCEPTION);
        AbstractDAOFactory.getFactory().getIterationDAO().create(iteration);

        useCase = new UseCaseEntity();
        useCase.setDescription("Description UC1");
        useCase.setName("Use Case 1");
        useCase.setProject(project);
        AbstractDAOFactory.getFactory().getUseCaseDAO().create(useCase);
        
         progressDetail1 = new ProgressDetailEntity();
        progressDetail1.setDiscipline(discipline);
        progressDetail1.setIteration(iteration);
        progressDetail1.setPercent(5);
        progressDetail1.setUseCase(useCase);

        progressDetail2 = new ProgressDetailEntity();
        progressDetail2.setDiscipline(discipline);
        progressDetail2.setIteration(iteration);
        progressDetail2.setPercent(4);
        progressDetail2.setUseCase(useCase);

        progressDetail3 = new ProgressDetailEntity();
        progressDetail3.setDiscipline(discipline);
        progressDetail3.setIteration(iteration);
        progressDetail3.setPercent(3);
        progressDetail3.setUseCase(useCase);

        

    }

    @Test
    public void testEnabledUseCases() {
        ProgressDetailEjb progressDetailEjb=createProgressDetails();       
        assertTrue("ERROR enabled use cases", progressDetailEjb.getEnabledUseCases(project)== 1);
    }
    
    @Test
    public void testCreate() {
        
        createProgressDetails();

        assertTrue("ERROR creating", AbstractDAOFactory.getFactory().getProgressDetailDAO().read(progressDetail1.getId()).equals(progressDetail1));
        assertTrue("ERROR creating", AbstractDAOFactory.getFactory().getProgressDetailDAO().read(progressDetail2.getId()).equals(progressDetail2));
        assertTrue("ERROR creating", AbstractDAOFactory.getFactory().getProgressDetailDAO().read(progressDetail3.getId()).equals(progressDetail3));

    }

    public ProgressDetailEjb createProgressDetails()
    {
        ProgressDetailEjb progressDetailEjb = new ProgressDetailEjb();
        progressDetailEjb.create(progressDetail1);
        progressDetailEjb.create(progressDetail2);
        progressDetailEjb.create(progressDetail3);
        return progressDetailEjb;
        
    }
    
    @Test
    public void testFind() {
        assertNotNull("Not Found Progress Detail : ",new ProgressDetailEjb().getByIterationUseCaseDiscipline(iteration, useCase, discipline));
    }

    @AfterClass
    public static void after() {
        AbstractDAOFactory.getFactory().getProgressDetailDAO().delete(progressDetail1);
        AbstractDAOFactory.getFactory().getProgressDetailDAO().delete(progressDetail2);
        AbstractDAOFactory.getFactory().getProgressDetailDAO().delete(progressDetail3);

        AbstractDAOFactory.getFactory().getProjectDAO().delete(project);
        AbstractDAOFactory.getFactory().getUserDAO().delete(user);
        AbstractDAOFactory.getFactory().getDisciplineDAO().delete(discipline);
        AbstractDAOFactory.getFactory().getUseCaseDAO().delete(useCase);
        AbstractDAOFactory.getFactory().getIterationDAO().delete(iteration);
    }
}
