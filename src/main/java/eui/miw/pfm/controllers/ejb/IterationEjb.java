/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eui.miw.pfm.controllers.ejb;

import eui.miw.pfm.models.dao.AbstractDAOFactory;
import eui.miw.pfm.models.entities.IterationEntity;
import eui.miw.pfm.models.entities.ProjectEntity;
import eui.miw.pfm.util.TypeIteration;
import java.util.List;

/**
 *
 * @author Manuel Alvarez
 */
public class IterationEjb {

    public IterationEntity obtainIteration(final Integer code) {
        return AbstractDAOFactory.getFactory().getIterationDAO().read(code);
    }

    public void create(final IterationEntity iteration) {
        AbstractDAOFactory.getFactory().getIterationDAO().create(iteration);
    }

    public void delete(final IterationEntity iteration) {
        AbstractDAOFactory.getFactory().getIterationDAO().delete(iteration);
    }

    public void update(final IterationEntity iteration) {
        AbstractDAOFactory.getFactory().getIterationDAO().update(iteration);
    }

    public List<IterationEntity> getIterations(final ProjectEntity project) {
        final String psql = "SELECT i FROM IterationEntity i WHERE i.project = ?1";//NOPMD
        return AbstractDAOFactory.getFactory().getIterationDAO().find(psql, new Object[]{project});
    }

    public List<IterationEntity> getIterationsOfOnePhase(final TypeIteration type, final ProjectEntity project) {
        final String psql = "SELECT i FROM IterationEntity i WHERE i.typeIteration = ?1 AND i.project = ?2";//NOPMD
        return AbstractDAOFactory.getFactory().getIterationDAO().find(psql, new Object[]{type, project});
    }

    public List<IterationEntity> getAllIterations(final ProjectEntity project) {
        final String psql = "SELECT i FROM IterationEntity i WHERE i.project = ?1";//NOPMD
        return AbstractDAOFactory.getFactory().getIterationDAO().find(psql, new Object[]{project});
    }
}
