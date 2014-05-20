/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eui.miw.pfm.controllers.ejb;

import eui.miw.pfm.models.dao.AbstractDAOFactory;
import eui.miw.pfm.models.entities.IterationEntity;
import java.util.List;

/**
 *
 * @author Manuel Alvarez
 */
public class IterationEjb {

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
}