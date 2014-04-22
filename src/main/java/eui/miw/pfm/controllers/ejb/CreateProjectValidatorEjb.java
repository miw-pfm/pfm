/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eui.miw.pfm.controllers.ejb;

import eui.miw.pfm.models.dao.AbstractDAOFactory;
import eui.miw.pfm.models.dao.interfaces.ProjectDAO;
import eui.miw.pfm.models.entities.ProjectEntity;
import java.util.List;

/**
 *
 * @author César Martínez
 */
public class CreateProjectValidatorEjb {
    public List<ProjectEntity> nameValidator(){
        
        ProjectDAO projectDAO;
        projectDAO  = AbstractDAOFactory.getFactory().getProjectDAO();
        
        List<ProjectEntity> projects;
        projects = projectDAO.findAll();
        return projects;
    }
}
