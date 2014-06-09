/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eui.miw.pfm.controllers.ejb;

import eui.miw.pfm.models.dao.AbstractDAOFactory;
import eui.miw.pfm.models.entities.DisciplineEntity;
import eui.miw.pfm.models.entities.IterationEntity;
import eui.miw.pfm.models.entities.ProgressDetailEntity;
import eui.miw.pfm.models.entities.ProjectEntity;
import eui.miw.pfm.models.entities.UseCaseEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Fred Peña
 * @author William
 * @author Manuel Rodríguez
 */
public class ProgressDetailEjb {

    public void create(final ProgressDetailEntity progressDetail) {
        AbstractDAOFactory.getFactory().getProgressDetailDAO().create(progressDetail);
    }

    public List<ProgressDetailEntity> getByIterationUseCaseDiscipline(final IterationEntity iteration, final UseCaseEntity useCase, final DisciplineEntity discipline) {
        final String psql = "SELECT pd FROM ProgressDetailEntity pd  WHERE pd.iteration = ?1 AND pd.useCase = ?2 AND pd.discipline = ?3 ";//NOPMD
        return AbstractDAOFactory.getFactory().getProgressDetailDAO().find(psql, new Object[]{iteration, useCase, discipline});
    }

    public void update(final ProgressDetailEntity progressDetail) {
        AbstractDAOFactory.getFactory().getProgressDetailDAO().update(progressDetail);
    }

    public ProgressDetailEntity findProgressDetail(final IterationEntity iteration, final UseCaseEntity useCase, final DisciplineEntity discipline) {
        return AbstractDAOFactory.getFactory().getProgressDetailDAO().findProgressDetail(iteration, useCase, discipline);
    }

    public List<ProgressDetailEntity> getDetails() {
        return AbstractDAOFactory.getFactory().getProgressDetailDAO().findAll();
    }

    public int getEnabledUseCases(final ProjectEntity projectEntity) {
        final String psql = "SELECT u FROM UseCaseEntity u WHERE u.project=?1 and u.isEnabled=1";//NOPMD
        int enabled_usecases = 0;
        for (UseCaseEntity uce : AbstractDAOFactory.getFactory().getUseCaseDAO().find(psql, projectEntity)) {
            ++enabled_usecases;
        }
        return enabled_usecases;
    }

    public List<ProgressDetailEntity> getProgressDetailEntitiesByProject(final ProjectEntity project) {
        final String psql = "SELECT pd FROM ProgressDetailEntity pd, ProjectEntity p, IterationEntity i WHERE pd.iteration = i and i.project = p and p = ?1";//NOPMD
        return AbstractDAOFactory.getFactory().getProgressDetailDAO().find(psql, new Object[]{project});
    }
}
