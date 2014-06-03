/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eui.miw.pfm.controllers.ejb;

import eui.miw.pfm.models.dao.AbstractDAOFactory;
import eui.miw.pfm.util.moks.entities.ProgressDetailEntityMock;

/**
 *
 * @author Fred Peña
 */
public class ProgressDetailEjb {

    public void create(final ProgressDetailEntityMock progressDetail) {
        AbstractDAOFactory.getFactory().getProgressDetailDAO().create(progressDetail);
    }

}
