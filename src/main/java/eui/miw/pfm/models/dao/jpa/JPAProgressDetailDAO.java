/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eui.miw.pfm.models.dao.jpa;

import eui.miw.pfm.models.dao.interfaces.ProgressDetailDAO;
import eui.miw.pfm.models.entities.ProgressDetailEntity;

/**
 *
 * @author Fred Peña
 */
public class JPAProgressDetailDAO extends JPATransactionGenericDAO<ProgressDetailEntity, Integer> implements ProgressDetailDAO {

    public JPAProgressDetailDAO() {
        super(ProgressDetailEntity.class);
    }

}
