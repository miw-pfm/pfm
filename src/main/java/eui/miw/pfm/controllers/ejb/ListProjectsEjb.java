/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eui.miw.pfm.controllers.ejb;

import eui.miw.pfm.models.dao.AbstractDAOFactory;
import eui.miw.pfm.models.entities.ProjectEntity;
import eui.miw.pfm.models.entities.UserEntity;
import java.util.List;

/**
 *
 * @author Roberto Amor
 */
public class ListProjectsEjb {

    public List<ProjectEntity> obtainProjects(final UserEntity user) {
        String psql = "SELECT p FROM ProjectEntity p WHERE p.owner = ?1";
        return AbstractDAOFactory.getFactory().getProjectDAO().find(psql, user);
    }
}
