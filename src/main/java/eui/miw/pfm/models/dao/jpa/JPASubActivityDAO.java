/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eui.miw.pfm.models.dao.jpa;

import eui.miw.pfm.models.dao.interfaces.SubActivityDAO;
import eui.miw.pfm.models.entities.SubActivityEntity;

/**
 *
 * @author Jean Mubaied
 */
public class JPASubActivityDAO extends JPATransactionGenericDAO<SubActivityEntity, Integer> implements SubActivityDAO{
      public JPASubActivityDAO(){
       super(SubActivityEntity.class);
    } 

         
}
