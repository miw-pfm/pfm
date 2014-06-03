/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eui.miw.pfm.controllers.ejb;

import eui.miw.pfm.models.dao.AbstractDAOFactory;
import eui.miw.pfm.models.entities.DisciplineEntity;
import java.util.List;

/**
 *
 * @author Fred Peña
 */
public class DisciplineEjb {

    public List<DisciplineEntity> findAll() {
        return AbstractDAOFactory.getFactory().getDisciplineDAO().findAll();
    }
}
