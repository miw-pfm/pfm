/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eui.miw.pfm.controllers.beans;

import eui.miw.pfm.controllers.ejb.ProjectEjb;
import eui.miw.pfm.models.entities.ProjectEntity;
import java.io.Serializable;
import java.util.Map;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author William
 */
@RequestScoped
@Named
public class ProjectOpenBean extends Bean implements Serializable {

    private static final long serialVersionUID = 1L;
    private ProjectEntity project;
     private static final Logger LOG = Logger.getLogger(ProjectOpenBean.class.getName());//NOPMD

    public ProjectOpenBean() {
        super();
        this.project = new ProjectEntity();
    }

    public String showOpenProject(ProjectEntity project) {

        assert project != null;
        assert sessionMap != null;
        
        try {
            sessionMap.put("project", project);
        } catch (Exception e) {
            LOG.warning("Problem with the session.");
        }
        return "openedProject";
    }

    public ProjectEntity getProject() {
        return project;
    }

    public void setProject(final ProjectEntity project) {
        this.project = project;
    }
}
