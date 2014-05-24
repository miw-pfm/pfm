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

    public ProjectOpenBean() {
        super();
        this.project = new ProjectEntity();
    }

    public String openProject() { //NOPMD

        Map<String, String> params ;
        params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String projectId ;
        projectId = params.get("projectId");
        this.openProject(Integer.parseInt(projectId));
        return "projectConfig";
    }

    public String showOpenProject(ProjectEntity project) {

        assert project != null;
        assert sessionMap != null;
//        this.sessionMap.put("project", this.project);
        try {
            sessionMap.put("project", project);
        } catch (Exception e) {

        }
        return "openedProject";
    }

    public ProjectEntity openProject(int projectId) { //NOPMD

        project = new ProjectEjb().openProject(projectId);

        //This entity should be filled with the user in session
        /*
         UserEntity sessionUser;
         sessionUser = new UserEntity(1);
         if(project.getOwner().getId() != sessionUser.getId()){
        
         project = null;
        
         }*/
        assert project != null;
        assert sessionMap != null;
//        this.sessionMap.put("project", this.project);
        try {
            sessionMap.put("project", this.project);
        } catch (Exception e) {

        }
        return project;

    }

    //public List<Participant> 
    public String showOpenProject(final int projectId) {
        this.openProject(projectId);
        return "openedProject";
    }

    public ProjectEntity getProject() {
        return project;
    }

    public void setProject(final ProjectEntity project) {
        this.project = project;
    }
}
