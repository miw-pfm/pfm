/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eui.miw.pfm.controllers.ejb;

import eui.miw.pfm.models.dao.AbstractDAOFactory;
import eui.miw.pfm.models.dao.interfaces.UseCaseDAO;
import eui.miw.pfm.models.entities.UseCaseEntity;

/**
 *
 * @author Roberto Amor
 * @author Clemencio Morales Lucas
 * @author Manuel Rodríguez Momediano
 */
public class UseCaseEjb {
    
    public UseCaseEjb() {
        super();
    }
    
    public void update(UseCaseEntity usecase){
        UseCaseDAO usecaseDAO;
        usecaseDAO = AbstractDAOFactory.getFactory().getUseCaseDAO();
    }
    
    public void create (final UseCaseEntity useCaseEntity){
        UseCaseDAO useCaseDAO;
        useCaseDAO = AbstractDAOFactory.getFactory().getUseCaseDAO();
        useCaseDAO.create(useCaseEntity);
    }
}
