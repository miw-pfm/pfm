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
import eui.miw.pfm.models.entities.UseCaseEntity;

/**
 *
 * @author Fred Peña
 */
public class ProgressDetailEjb {

    public void create(final ProgressDetailEntity progressDetail) {
        AbstractDAOFactory.getFactory().getProgressDetailDAO().create(progressDetail);
    }

    public void update(final ProgressDetailEntity progressDetail) {
        AbstractDAOFactory.getFactory().getProgressDetailDAO().update(progressDetail);
    }

    public ProgressDetailEntity findProgressDetail(final IterationEntity iteration, final UseCaseEntity useCase, final DisciplineEntity discipline) {
        return AbstractDAOFactory.getFactory().getProgressDetailDAO().findProgressDetail(iteration, useCase, discipline);
    }

}
