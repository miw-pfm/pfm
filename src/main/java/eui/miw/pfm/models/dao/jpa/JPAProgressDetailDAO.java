/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eui.miw.pfm.models.dao.jpa;

import eui.miw.pfm.models.dao.interfaces.ProgressDetailDAO;
import eui.miw.pfm.models.entities.DisciplineEntity;
import eui.miw.pfm.models.entities.IterationEntity;
import eui.miw.pfm.models.entities.ProgressDetailEntity;
import eui.miw.pfm.models.entities.UseCaseEntity;
import java.util.List;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;

/**
 *
 * @author Fred Peña
 */
public class JPAProgressDetailDAO extends JPATransactionGenericDAO<ProgressDetailEntity, Integer> implements ProgressDetailDAO {

    public JPAProgressDetailDAO() {
        super(ProgressDetailEntity.class);
    }

    @Override
    public ProgressDetailEntity findProgressDetail(final IterationEntity iteration, final UseCaseEntity useCase, final DisciplineEntity discipline) {
        final Query query = entityManager.createQuery("SELECT dp FROM ProgressDetailEntity dp WHERE dp.discipline = ?1 AND dp.iteration = ?2 AND dp.useCase = ?3");
        query.setParameter(1, discipline);
        query.setParameter(2, iteration);
        query.setParameter(3, useCase);

        final List results = query.getResultList();
        if (results.isEmpty()) {
            return null;
        } else if (results.size() == 1) {
            return (ProgressDetailEntity) results.get(0);
        }
        throw new NonUniqueResultException();
    }

}
