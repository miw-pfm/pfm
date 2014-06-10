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

    public boolean update(final RiskEntity risk) {
        if (this.isUnique(risk, false)) {
            AbstractDAOFactory.getFactory().getRiskDAO().update(risk);
            return true;//NOPMD
        } else {
            return false;
        }

    }

    public boolean create(final RiskEntity risk) {
        if (this.isUnique(risk, true)) {
            AbstractDAOFactory.getFactory().getRiskDAO().create(risk);
            return true;//NOPMD
        } else {
            return false;
        }

    }

    public List<RiskEntity> findRisks(final ProjectEntity project) {
        final String psql = "SELECT r FROM RiskEntity r WHERE r.project = ?1";//NOPMD
        return AbstractDAOFactory.getFactory().getRiskDAO().find(psql, new Object[]{project});
    }

    public void delete(final RiskEntity risk) {
        AbstractDAOFactory.getFactory().getRiskDAO().delete(risk);
    }

    private boolean isUnique(final RiskEntity risk, final boolean create) {

        final String psql = "SELECT r FROM RiskEntity r WHERE r.project = ?1";//NOPMD
        final List<RiskEntity> list = AbstractDAOFactory.getFactory().getRiskDAO().find(psql, new Object[]{risk.getProject()});

        if (create) {
            for (RiskEntity r : list) {
                if (r.getName().equals(risk.getName())) {
                    return false;//NOPMD
                }
            }
        } else {
            for (RiskEntity r : list) {
                if (r.getName().equals(risk.getName()) && !r.getId().equals(risk.getId())) {
                    return false;//NOPMD
                }
            }
        }
        return true;
    }
}
