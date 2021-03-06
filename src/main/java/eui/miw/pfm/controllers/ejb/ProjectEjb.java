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
 * @author César Martínez
 * @author Roberto Amor
 * @author Jose Angel
 * @author William
 */
public class ProjectEjb {

    public void createProject(final ProjectEntity project) {
        AbstractDAOFactory.getFactory().getProjectDAO().create(project);
    }

    public boolean nameProjectValidator(final ProjectEntity project, final UserEntity user) {
        final String psql = "SELECT p FROM ProjectEntity p WHERE p.owner = ?1";//NOPMD
        final Object[] objects = new Object[]{user};
        for (ProjectEntity pe : AbstractDAOFactory.getFactory().getProjectDAO().find(psql, objects)) {
            if (pe.getName().equals(project.getName())) {
                return false;//NOPMD
            }
        }
        return true;
    }

    public List<ProjectEntity> obtainProjects(final UserEntity user) {
        final String psql = "SELECT p FROM ProjectEntity p WHERE p.owner = ?1";//NOPMD
        return AbstractDAOFactory.getFactory().getProjectDAO().find(psql, new Object[]{user});
    }

    public void update(final ProjectEntity project) {
        assert project != null;
        AbstractDAOFactory.getFactory().getProjectDAO().update(project);
    }

    public ProjectEntity openProject(final int code) {
        return AbstractDAOFactory.getFactory().getProjectDAO().read(code);
    }
}
