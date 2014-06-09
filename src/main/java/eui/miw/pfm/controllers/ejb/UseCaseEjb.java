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
        if (this.isUnique(usecase, false)) {
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
        if (this.isUnique(usecase, true)) {
            AbstractDAOFactory.getFactory().getUseCaseDAO().create(usecase);
            return true;
        } else {
            return false;
        }

    }

    private boolean isUnique(final UseCaseEntity usecase, final boolean create) {

        final String psql = "SELECT u FROM UseCaseEntity u WHERE u.project = ?1";//NOPMD
        final List<UseCaseEntity> list = AbstractDAOFactory.getFactory().getUseCaseDAO().find(psql, usecase.getProject());

        if (create) {
            for (UseCaseEntity u : list) {
                if (u.getName().equals(usecase.getName())) {
                    return false;
                }
            }
        } else {
            for (UseCaseEntity u : list) {
                if (u.getName().equals(usecase.getName())) {
                    if (u.getId().equals(usecase.getId())) {
                        continue;
                    } else {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    //@author Jose M Villar
    public List<UseCaseEntity> obtainUseCase(final ProjectEntity project) {
        final String psql = "SELECT uc FROM UseCaseEntity uc WHERE uc.project = ?1";//NOPMD
        return AbstractDAOFactory.getFactory().getUseCaseDAO().find(psql, project);
    }
    
    //Manuel Rodriguez
    public int getEnabledUseCases(final ProjectEntity projectEntity) {
        final String psql = "SELECT u FROM UseCaseEntity u WHERE u.project=?1 and u.enabled=1";//NOPMD
        int enabled_usecases = 0;
        for (UseCaseEntity uce : AbstractDAOFactory.getFactory().getUseCaseDAO().find(psql, projectEntity)) {
            ++enabled_usecases;
        }
        return enabled_usecases;
    }

    //@author Cesar y Manuel Álvarez
    public List<UseCaseEntity> obtainUseCaseChecked(final ProjectEntity project) {
        final String psql = "SELECT uc FROM UseCaseEntity uc WHERE uc.project = ?1 AND uc.iteration != null";//NOPMD
        return AbstractDAOFactory.getFactory().getUseCaseDAO().find(psql, project);
    }
}
