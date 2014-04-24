/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eui.miw.pfm.controllers.ejb;

import eui.miw.pfm.models.dao.AbstractDAOFactory;
import eui.miw.pfm.models.dao.interfaces.ProjectDAO;
import eui.miw.pfm.models.entities.ProjectEntity;

/**
 *
 * @author César Martínez
 */
public class CreateProjectEjb {

    public void createProject(final ProjectEntity project) {
        ProjectDAO projectDAO;
        projectDAO = AbstractDAOFactory.getFactory().getProjectDAO();
        projectDAO.create(project);
    }
}
