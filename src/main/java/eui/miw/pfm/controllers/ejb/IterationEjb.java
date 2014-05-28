/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eui.miw.pfm.controllers.ejb;

import eui.miw.pfm.models.dao.AbstractDAOFactory;
import eui.miw.pfm.models.entities.IterationEntity;
import eui.miw.pfm.util.TypeIteration;
import java.util.List;

/**
 *
 * @author Manuel Alvarez
 */
public class IterationEjb {
    
    public IterationEntity obtainIteration(final Integer iterationID){
        return AbstractDAOFactory.getFactory().getIterationDAO().read(iterationID);
    }
    
    public void create(final IterationEntity iterationEntity) {
        AbstractDAOFactory.getFactory().getIterationDAO().create(iterationEntity);
    }

    public void delete(final IterationEntity iterationEntity) {
        AbstractDAOFactory.getFactory().getIterationDAO().delete(iterationEntity);
    }

    public void update(final IterationEntity iterationEntity) {
        AbstractDAOFactory.getFactory().getIterationDAO().update(iterationEntity);

    }

    public List<IterationEntity> getIterations() {
        return AbstractDAOFactory.getFactory().getIterationDAO().findAll();
    }

    public List<IterationEntity> getIterationsOfOnePhase(final TypeIteration type) {
        final String psql = "SELECT i FROM IterationEntity i WHERE i.typeIteration = ?1";//NOPMD
        return AbstractDAOFactory.getFactory().getIterationDAO().find(psql, type);
    }
}
