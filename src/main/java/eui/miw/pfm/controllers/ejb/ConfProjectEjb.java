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
 * @author Jose Angel
 */
public class ConfProjectEjb {
    
    public ProjectEntity getProject(final int projId) //NOPMD
    {
        ProjectDAO ProjectDAO;        
        ProjectDAO = AbstractDAOFactory.getFactory().getProjectDAO();    	            
        
        ProjectEntity project;
        project = ProjectDAO.read(projId);        
        
        assert project != null;
        
        return project;
    }

    public void update(final ProjectEntity project) {
        ProjectDAO ProjectDAO;
        ProjectDAO = AbstractDAOFactory.getFactory().getProjectDAO();    	
        
        ProjectDAO.update(project); 
    }
}
