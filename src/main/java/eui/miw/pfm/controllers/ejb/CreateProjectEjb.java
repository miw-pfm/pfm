/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eui.miw.pfm.controllers.ejb;

import eui.miw.pfm.controllers.beans.CreateProjectBean;
import eui.miw.pfm.models.dao.AbstractDAOFactory;
import eui.miw.pfm.models.dao.interfaces.ProjectDAO;
import eui.miw.pfm.models.entities.ProjectEntity;
import java.util.logging.Logger;

/**
 *
 * @author César Martínez
 */
public class CreateProjectEjb {

    private static final Logger LOG = Logger.getLogger(CreateProjectBean.class.getName());//NOPMD

    public void createProject(final ProjectEntity project) {
        ProjectDAO projectDAO;
        projectDAO = AbstractDAOFactory.getFactory().getProjectDAO();
        projectDAO.create(project);
    }

    public boolean nameProjectValidator(final ProjectEntity projectEntity) {
        final String[] name = {"name"};
        final String[] value = {projectEntity.getName()};
        ProjectDAO projectDAO;
        projectDAO = AbstractDAOFactory.getFactory().getProjectDAO();
        return projectDAO.find(name, value).isEmpty();
    }
}
