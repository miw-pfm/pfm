/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eui.miw.pfm.models.dao.jpa;

import eui.miw.pfm.models.dao.interfaces.IterationDAO;
import eui.miw.pfm.models.entities.IterationEntity;

/**
 *
 * @author Jose Angel
 */
public class JPAIterationDAO extends JPATransactionGenericDAO<IterationEntity, Integer> implements IterationDAO{

    public JPAIterationDAO() {
        super(IterationEntity.class);
    }
    
}
