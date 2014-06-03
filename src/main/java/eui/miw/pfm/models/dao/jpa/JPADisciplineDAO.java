/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eui.miw.pfm.models.dao.jpa;

import eui.miw.pfm.models.dao.interfaces.DisciplineDAO;
import eui.miw.pfm.models.entities.DisciplineEntity;
/**
 *
 * @author Manuel Rodriguez
 */
public class JPADisciplineDAO extends JPATransactionGenericDAO<DisciplineEntity, Integer> implements DisciplineDAO {
      public JPADisciplineDAO(){
       super(DisciplineEntity.class);
    } 

         
}
