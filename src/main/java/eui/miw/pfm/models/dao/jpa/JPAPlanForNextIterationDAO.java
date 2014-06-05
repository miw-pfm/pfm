/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eui.miw.pfm.models.dao.jpa;

import eui.miw.pfm.models.dao.interfaces.PlanForNextIterationDAO;
import eui.miw.pfm.models.entities.PlanForNextIterationEntity;

/**
 *
 * @author Fred Peña
 */
public class JPAPlanForNextIterationDAO extends JPATransactionGenericDAO<PlanForNextIterationEntity, Integer> implements PlanForNextIterationDAO {

    public JPAPlanForNextIterationDAO() {
        super(PlanForNextIterationEntity.class);
    }

}
