/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eui.miw.pfm.controllers.ejb;

import eui.miw.pfm.models.dao.AbstractDAOFactory;
import eui.miw.pfm.models.entities.UserEntity;

/**
 *
 * @author Fred Peña
 */
public class LoginEjb {

    public UserEntity findUser(final String userName, final String password) {
        return AbstractDAOFactory.getFactory().getUserDAO().findUser(userName, password);
    }

}
