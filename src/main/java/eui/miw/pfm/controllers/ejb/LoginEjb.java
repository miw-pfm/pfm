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
 * @author bk0804
 */
public class LoginEjb {

    public UserEntity findUser(String userName, String password) {
        return AbstractDAOFactory.getFactory().getUserDAO().findUser(userName, password);
    }

}
