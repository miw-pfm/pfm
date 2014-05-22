/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eui.miw.pfm.controllers.beans;

import eui.miw.pfm.controllers.ejb.ProjectEjb;
import eui.miw.pfm.models.entities.ProjectEntity;
import eui.miw.pfm.util.moks.Participant;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
    private List<Participant> participants;

    public ProjectOpenBean() {
        super();
        this.project = new ProjectEntity();
        this.participants = new ArrayList<>();
        this.participants.add(new Participant(1, "participante 1"));
        this.participants.add(new Participant(2, "participante 2"));
        this.participants.add(new Participant(3, "participante 3"));
        this.participants.add(new Participant(4, "participante 4"));
        // this.openProject(2);
    }

    public String openProject() { //NOPMD

        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String projectId = params.get("projectId");
        openProject(Integer.parseInt(projectId));
        // return openProject(Integer.parseInt(projectId));
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
    public String showOpenProject(int projectId) {

        this.openProject(projectId);

        this.participants.add(new Participant(1, "participante 1"));
        this.participants.add(new Participant(2, "participante 2"));
        this.participants.add(new Participant(3, "participante 3"));
        this.participants.add(new Participant(4, "participante 4"));

        return "openedProject";
    }

    public ProjectEntity getProject() {
        return project;
    }

    public void setProject(ProjectEntity project) {
        this.project = project;
    }

    public List<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Participant> participants) {
        this.participants = participants;
    }

}
