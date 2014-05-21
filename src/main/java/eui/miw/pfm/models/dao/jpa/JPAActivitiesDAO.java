/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eui.miw.pfm.models.dao.jpa;

import eui.miw.pfm.models.entities.ActivitiesEntity;
import eui.miw.pfm.models.dao.interfaces.ActivitiesDAO;
/**
 *
 * @author Jose Mª Villar
 * @author Jean Mubaied
 */
public class JPAActivitiesDAO extends JPATransactionGenericDAO<ActivitiesEntity, Integer> implements ActivitiesDAO {
      public JPAActivitiesDAO(){
       super(ActivitiesEntity.class);
    } 

         
}
