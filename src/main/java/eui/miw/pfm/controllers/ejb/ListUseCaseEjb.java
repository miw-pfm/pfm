/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eui.miw.pfm.controllers.ejb;

import eui.miw.pfm.models.dao.AbstractDAOFactory;
import eui.miw.pfm.models.dao.interfaces.UseCaseDAO;
import eui.miw.pfm.models.entities.ProjectEntity;
import eui.miw.pfm.models.entities.UseCaseEntity;
import java.util.List;
import java.util.Set;

/**
 *
 * @author aw0591
 */
public class ListUseCaseEjb {

    public List<UseCaseEntity> obtainUseCase(ProjectEntity project) {        
        UseCaseDAO usecaseDAO = AbstractDAOFactory.getFactory().getUseCaseDAO();        
        String psql = "SELECT uc FROM UseCaseEntity uc WHERE uc.project = ?1";
        return usecaseDAO.find(psql, project);        
    }
    
}
