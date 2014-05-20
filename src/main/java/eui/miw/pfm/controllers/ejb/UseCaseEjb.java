/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eui.miw.pfm.controllers.ejb;

import eui.miw.pfm.models.dao.AbstractDAOFactory;
import eui.miw.pfm.models.entities.ProjectEntity;
import eui.miw.pfm.models.entities.UseCaseEntity;
import java.util.List;

/**
 *
 * @author Roberto Amor
 * @author Clemencio Morales Lucas
 * @author Manuel Rodríguez Momediano
 */
public class UseCaseEjb {

    public boolean update(final UseCaseEntity usecase) {
        if (this.isUnique(usecase)) {
            AbstractDAOFactory.getFactory().getUseCaseDAO().update(usecase);
            return true;
        } else {
            return false;
        }
    }

    public void delete(final UseCaseEntity usecase) {
        AbstractDAOFactory.getFactory().getUseCaseDAO().delete(usecase);
    }

    public boolean create(final UseCaseEntity usecase) {
        if (this.isUnique(usecase)) {
            AbstractDAOFactory.getFactory().getUseCaseDAO().create(usecase);
            return true;
        } else {
            return false;
        }

    }

    private boolean isUnique(final UseCaseEntity usecase) {

        final String psql = "SELECT u FROM UseCaseEntity u WHERE u.project = ?1";//NOPMD
        List<UseCaseEntity> list = AbstractDAOFactory.getFactory().getUseCaseDAO().find(psql, usecase.getProject());
        for (UseCaseEntity u : list) {
            if (u.equals(usecase)) {
                return false;
            }
        }
        return true;
    }

    //@author Jose M Villar
    public List<UseCaseEntity> obtainUseCase(final ProjectEntity project) {
        final String psql = "SELECT uc FROM UseCaseEntity uc WHERE uc.project = ?1";//NOPMD
        return AbstractDAOFactory.getFactory().getUseCaseDAO().find(psql, project);
    }
}
