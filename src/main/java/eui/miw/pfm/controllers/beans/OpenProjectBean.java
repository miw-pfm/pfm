/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eui.miw.pfm.controllers.beans;

import eui.miw.pfm.controllers.ejb.OpenProjectEjb;
import eui.miw.pfm.models.entities.ProjectEntity;
import eui.miw.pfm.models.entities.UserEntity;
import eui.miw.pfm.util.SessionMap;
import eui.miw.pfm.util.moks.Participant;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author William
 */
@ManagedBean
@RequestScoped
public class OpenProjectBean  extends Bean implements Serializable{
    
    private static final long serialVersionUID = 1L;
    private ProjectEntity project;
    private List<Participant> participants ;
    private final SessionMap sessionMap;//NOPMD
    
    public OpenProjectBean() {
        super();
        this.project = new ProjectEntity();
        this.participants = new ArrayList<Participant>();
        this.participants.add(new Participant(1,"participante 1"));
        this.participants.add(new Participant(2,"participante 2"));
        this.participants.add(new Participant(3,"participante 3"));
        this.participants.add(new Participant(4,"participante 4"));
        this.sessionMap = new SessionMap();
       // this.openProject(2);
    }
    
     public String openProject(){ //NOPMD
        
        Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
	String projectId = params.get("projectId");
        openProject(Integer.parseInt(projectId));
       // return openProject(Integer.parseInt(projectId));
        return "confProject";
    }
     
     public String showOpenProject(ProjectEntity project) {
        
        assert project != null;
        assert this.sessionMap != null;
//        this.sessionMap.add("project", this.project);
        try{
            this.sessionMap.add("project", project);
        }catch(Exception e){
            
        }
        return "openProject"; 
     }
    
     public ProjectEntity openProject(int projectId){ //NOPMD
        
        OpenProjectEjb opEJB ; 
        opEJB = new OpenProjectEjb();
        project = opEJB.openProject(projectId);
        
        //This entity should be filled with the user in session
        /*
        UserEntity sessionUser;
        sessionUser = new UserEntity(1);
        if(project.getOwner().getId() != sessionUser.getId()){
        
            project = null;
        
        }*/
        
        assert project != null;
        assert this.sessionMap != null;
//        this.sessionMap.add("project", this.project);
        try{
            this.sessionMap.add("project", this.project);
        }catch(Exception e){
            
        }
        return project;
       
    }
    
    //public List<Participant> 
    
    public String showOpenProject(int projectId){
        
        this.openProject(projectId);
        
        this.participants.add(new Participant(1,"participante 1"));
        this.participants.add(new Participant(2,"participante 2"));
        this.participants.add(new Participant(3,"participante 3"));
        this.participants.add(new Participant(4,"participante 4"));
        
        return "openProject";
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
