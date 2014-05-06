/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eui.miw.pfm.controllers.ejb;

import eui.miw.pfm.models.dao.AbstractDAOFactory;
import eui.miw.pfm.models.entities.ProjectEntity;
import eui.miw.pfm.models.entities.UserEntity;
import java.util.logging.Logger;

/**
 *
 * @author César Martínez
 */
public class CreateProjectEjb {

    private static final Logger LOG = Logger.getLogger(CreateProjectEjb.class.getName());//NOPMD

    public void createProject(final ProjectEntity project) {
        AbstractDAOFactory.getFactory().getProjectDAO().create(project);
    }

    public boolean nameProjectValidator(final ProjectEntity projectEntity, final UserEntity userEntity) {
        String psql = "SELECT p FROM ProjectEntity p WHERE p.owner = ?1";
        for (ProjectEntity pe : AbstractDAOFactory.getFactory().getProjectDAO().find(psql, userEntity)) {
            if (pe.getName().equals(projectEntity.getName())) {
                return false;
            }
        }
        return true;
    }
}
