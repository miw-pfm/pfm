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
 * @author Jose Angel
 */
public class TestConfProjectBean {

    @Test
    public void edit() {

        ProjectDAO projectDAO;
        projectDAO = AbstractDAOFactory.getFactory().getProjectDAO();
        final ProjectEntity project = new ProjectEntity();

        project.setChosenNumIteration(3);        
        project.setStartDate(new Date());
        project.setEndDate(new Date());
        project.setEstimatedNumIteration(3);
        project.setName("Project1");
        project.setWeekNumIteration(3);
        project.setDescription("Test edit");

        projectDAO.create(project);

        ConfProjectBean confBean;
        confBean = new ConfProjectBean();

        confBean.edit(project.getId());

        assertTrue("Project exist",confBean.getProject().equals(project));
        assertFalse("Project dosn't exist",confBean.getProject().equals(new ProjectEntity()));

    }
}
