/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eui.miw.pfm.controllers.ejb;

import eui.miw.pfm.controllers.beans.IterationBean;
import eui.miw.pfm.models.dao.AbstractDAOFactory;
import eui.miw.pfm.models.entities.DisciplineEntity;
import eui.miw.pfm.models.entities.IterationEntity;
import eui.miw.pfm.models.entities.ProgressDetailEntity;
import eui.miw.pfm.models.entities.ProjectEntity;
import eui.miw.pfm.models.entities.UseCaseEntity;
import java.util.List;

/**
 *
 * @author Fred Peña
 * @author William
 * @author Manuel Rodríguez
 * @author Roberto Amor
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

    public List<ProgressDetailEntity> getProgressDetailEntitiesByProject(final ProjectEntity project) {
        final String psql = "SELECT pd FROM ProgressDetailEntity pd, ProjectEntity p, IterationEntity i WHERE pd.iteration = i and i.project = p and p = ?1";//NOPMD
        return AbstractDAOFactory.getFactory().getProgressDetailDAO().find(psql, new Object[]{project});
    }

    //@author Jose Mª Villar Bógalo
    public Integer getSumTotalProgressDetail(final ProjectEntity project, final IterationEntity iteration, final DisciplineEntity discipline) {
        final String psql = "SELECT pd FROM ProgressDetailEntity pd  WHERE pd.iteration = ?1 AND pd.discipline = ?2 ";//NOPMD        
        final List<ProgressDetailEntity> progressDetail = AbstractDAOFactory.getFactory().getProgressDetailDAO().find(psql, new Object[]{iteration, discipline});
        Integer sumTotal = 0;

        for (ProgressDetailEntity progressDetailEntity : progressDetail) {
            sumTotal += progressDetailEntity.getPercent();
        }
        return Math.round(sumTotal / new UseCaseEjb().getEnabledUseCases(project));
    }
    
    // @author Jose Mª Villar  
    public Integer obtainPercentsOfIdentification(final IterationEntity iteration, final ProjectEntity project) {
        final List<UseCaseEntity> useCaseEntitys = new UseCaseEjb().obtainUseCaseChecked(project);
        final List<IterationEntity> preIterations = new IterationBean().listPreIterations(iteration); //NOPMD

        float numProgressIdent = 0; //NOPMD
        for (UseCaseEntity useCaseEntity : useCaseEntitys) {
            if (preIterations.contains(useCaseEntity.getIteration())) {
                numProgressIdent++; //NOPMD
            }
        }

        return (int) ((numProgressIdent / new UseCaseEjb().getEnabledUseCases(project)) * 100);
    }
}
