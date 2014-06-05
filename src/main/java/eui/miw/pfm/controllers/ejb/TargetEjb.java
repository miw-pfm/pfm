/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eui.miw.pfm.controllers.ejb;

import eui.miw.pfm.models.dao.AbstractDAOFactory;
import eui.miw.pfm.models.entities.DisciplineEntity;
import eui.miw.pfm.models.entities.ProjectEntity;
import eui.miw.pfm.models.entities.TargetEntity;
import java.util.List;

/**
 *
 * @author Jean Mubaied
 */
public class TargetEjb {
    
    public void createTarget(final TargetEntity target) {
        AbstractDAOFactory.getFactory().getTargetDAO().create(target);
    }
    
    public List<TargetEntity> obtainProjectTarget(final ProjectEntity project) {
        final String psql = "SELECT t FROM TargetEntity t WHERE t.project = ?1";//NOPMD
        return AbstractDAOFactory.getFactory().getTargetDAO().find(psql, project);
    }
    
    public List<TargetEntity> obtainTarget(final ProjectEntity project, final DisciplineEntity discipline) {
        final String psql = "SELECT t FROM TargetEntity t WHERE t.project = ?1 AND t.discipline = ?2";//NOPMD
        return AbstractDAOFactory.getFactory().getTargetDAO().find(psql, project);
    }
    
    public void update(final TargetEntity target) {
        assert target != null;
        AbstractDAOFactory.getFactory().getTargetDAO().update(target);
    }
    
    public void delete(final TargetEntity target) {
        AbstractDAOFactory.getFactory().getTargetDAO().delete(target);
    }
    
}
