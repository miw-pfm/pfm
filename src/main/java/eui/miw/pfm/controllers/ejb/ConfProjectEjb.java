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
        
    public void update(final ProjectEntity project)
    {
        ProjectDAO ProjectDAO;        
        ProjectDAO = AbstractDAOFactory.getFactory().getProjectDAO();    	            
        
        assert project != null;        
        ProjectDAO.update(project);
    }
}
