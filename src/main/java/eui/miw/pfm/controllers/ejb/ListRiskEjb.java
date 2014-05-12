/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eui.miw.pfm.controllers.ejb;

import eui.miw.pfm.models.dao.AbstractDAOFactory;
import eui.miw.pfm.models.entities.ProjectEntity;
import eui.miw.pfm.models.entities.RiskEntity;
import java.util.List;

/**
 *
 * @author Fred Peña
 *
 */
public class ListRiskEjb {

    public List<RiskEntity> findRisks(final ProjectEntity project) {
        final List<RiskEntity> listAll = AbstractDAOFactory.getFactory().getRiskDAO().findAll();
        listAll.removeAll(project.getRisk());
        return listAll;
    }

}
