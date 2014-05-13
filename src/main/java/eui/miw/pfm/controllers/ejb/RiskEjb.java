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
 */
public class RiskEjb {

    public void update(final RiskEntity riskEntity) {
        AbstractDAOFactory.getFactory().getRiskDAO().update(riskEntity);

    }

    public void create(final RiskEntity riskEntity) {        
        AbstractDAOFactory.getFactory().getRiskDAO().create(riskEntity);
    }

    public List<RiskEntity> findRisks(final ProjectEntity project) {
        return project.getRisk();
    }

    public void delete(final RiskEntity risk) {             
        AbstractDAOFactory.getFactory().getRiskDAO().delete(risk);
    }
}
