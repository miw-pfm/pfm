/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eui.miw.pfm.models.dao.interfaces;

import eui.miw.pfm.models.dao.TransactionGenericDAO;
import eui.miw.pfm.models.entities.DisciplineEntity;
import eui.miw.pfm.models.entities.IterationEntity;
import eui.miw.pfm.models.entities.ProgressDetailEntity;
import eui.miw.pfm.models.entities.UseCaseEntity;

/**
 *
 * @author Fred Peña
 */
public interface ProgressDetailDAO extends TransactionGenericDAO<ProgressDetailEntity, Integer> {

    public ProgressDetailEntity findProgressDetail(final IterationEntity iteration, final UseCaseEntity useCase, final DisciplineEntity discipline);
}
