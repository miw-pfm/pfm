/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eui.miw.pfm.controllers.ejb;

import eui.miw.pfm.models.dao.AbstractDAOFactory;
import eui.miw.pfm.models.dao.interfaces.UseCaseDAO;
import eui.miw.pfm.models.entities.UseCaseEntity;
import org.apache.log4j.Logger;



/**
 *
 * @author Manuel Rodríguez Momediano
 * @author Clemencio Morales Lucas
 */
public class CreateUseCaseEjb {
    private static final Logger LOG = Logger.getLogger(CreateUseCaseEjb.class.getName());//NOPMD
    
    public void createUseCase (final UseCaseEntity useCaseEntity){
        UseCaseDAO useCaseDAO;
        useCaseDAO = AbstractDAOFactory.getFactory().getUseCaseDAO();
        useCaseDAO.create(useCaseEntity);
    }
}