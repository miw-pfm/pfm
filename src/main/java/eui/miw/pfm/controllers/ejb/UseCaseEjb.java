/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eui.miw.pfm.controllers.ejb;

import eui.miw.pfm.models.dao.AbstractDAOFactory;
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

    public void update(final UseCaseEntity usecase) {
        AbstractDAOFactory.getFactory().getUseCaseDAO().update(usecase);
    }

    public void delete(final UseCaseEntity usecase) {
        AbstractDAOFactory.getFactory().getUseCaseDAO().delete(usecase);
    }

    public void create(final UseCaseEntity useCaseEntity) {
        AbstractDAOFactory.getFactory().getUseCaseDAO().create(useCaseEntity);
    }
}
