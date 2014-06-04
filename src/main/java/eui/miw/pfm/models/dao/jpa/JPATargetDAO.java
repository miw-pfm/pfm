/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eui.miw.pfm.models.dao.jpa;

import eui.miw.pfm.models.dao.interfaces.TargetDAO;
import eui.miw.pfm.models.entities.TargetEntity;

/**
 *
 * @author Jean Mubaied
 */
public class JPATargetDAO extends JPATransactionGenericDAO<TargetEntity, Integer> implements TargetDAO{

    public JPATargetDAO() {
        super(TargetEntity.class);
    }
    
}
