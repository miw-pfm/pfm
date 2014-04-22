/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eui.miw.pfm.controllers.beans;

import eui.miw.pfm.models.dao.AbstractDAOFactory;
import eui.miw.pfm.models.dao.interfaces.ProjectDAO;
import eui.miw.pfm.models.entities.ProjectEntity;
import java.util.Date;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author bk0823
 */
public class TestCreateProjectValidatorBean {

    @Test
    public void nameValidator1() {
        ProjectDAO projectDAO;
        projectDAO = AbstractDAOFactory.getFactory().getProjectDAO();
        final ProjectEntity project = new ProjectEntity();
        int numInt; 
        numInt= 2;
        project.setChosenNumIteration(numInt);
        project.setDescription("Prueba de nameValidator");
        project.setStartDate(new Date());
        project.setEndDate(new Date());
        project.setEstimatedNumIteration(numInt);
        project.setName("Project1");
        project.setWeekNumIteration(numInt);

        projectDAO.create(project);

        CreateProjectValidatorBean cPVB;
        cPVB = new CreateProjectValidatorBean();
        assertTrue("Ya existe y no lo valida correctamente", cPVB.nameValidator("Project1"));
        assertFalse("No existe y no lo Valida Correctamente", cPVB.nameValidator("Project2"));
    }

    @Test
    public void nameValidator2() {
        final ProjectDAO projectDAO = AbstractDAOFactory.getFactory().getProjectDAO();
        ProjectEntity project;
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

        projectDAO.create(project);
        CreateProjectValidatorBean cPVB;
        cPVB = new CreateProjectValidatorBean();

        assertTrue("No Valida Correctamente", cPVB.nameValidator("Project1"));
        assertTrue("No Valida Correctamente", cPVB.nameValidator("Project2"));
        assertFalse("No Valida Correctamente", cPVB.nameValidator("Project3"));
    }
}
