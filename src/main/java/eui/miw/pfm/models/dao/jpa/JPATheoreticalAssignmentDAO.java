/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eui.miw.pfm.models.dao.jpa;

import eui.miw.pfm.models.dao.interfaces.TheoreticalAssignmentDAO;
import eui.miw.pfm.models.entities.TheoreticalAssignmentEntity;

/**
 *
 * @author Fred Peña
 */
public class JPATheoreticalAssignmentDAO extends JPATransactionGenericDAO<TheoreticalAssignmentEntity, Integer> implements TheoreticalAssignmentDAO {

    public JPATheoreticalAssignmentDAO() {
        super(TheoreticalAssignmentEntity.class);
    }

}
