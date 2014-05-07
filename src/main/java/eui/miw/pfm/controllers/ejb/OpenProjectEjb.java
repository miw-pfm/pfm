/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eui.miw.pfm.controllers.ejb;

import eui.miw.pfm.models.dao.AbstractDAOFactory;
import eui.miw.pfm.models.entities.ProjectEntity;

/**
 *
 * @author William
 */
public class OpenProjectEjb {

    public ProjectEntity openProject(int projectId) {//NOPMD
        return AbstractDAOFactory.getFactory().getProjectDAO().read(projectId);

    }
}
