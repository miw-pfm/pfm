/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eui.miw.pfm.controllers.beans;

import eui.miw.pfm.controllers.ejb.UseCaseEjb;
import eui.miw.pfm.models.entities.ProjectEntity;
import eui.miw.pfm.models.entities.UseCaseEntity;
import eui.miw.pfm.util.SessionMap;
import java.io.Serializable;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Roberto Amor
 * @author Clemencio Morales
 * @author Manuel Rodríguez
 */
@SessionScoped
@Named
public class UseCaseBean extends Bean implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private UseCaseEntity usecase;
    private ProjectEntity project;
    private SessionMap sessionMap;
    private static final Logger LOG = Logger.getLogger(ConfProjectBean.class.getName());//NOPMD
    
    public UseCaseBean(){
        super();
        project = new ProjectEntity();
        this.sessionMap = new SessionMap();

        try {
            this.project = ((ProjectEntity) this.sessionMap.get("project"));
        } catch (Exception e) {
            LOG.warning("No session exist");
        }
    }
    
    public UseCaseBean(final UseCaseEntity usecase){
        this.usecase = usecase;
    }

    public UseCaseEntity getUsecase() {
        return usecase;
    }

    public void setUsecase(final UseCaseEntity usecase) {
        this.usecase = usecase;
    }
    
    public String update(){
        String result;
        UseCaseEjb ejb = new UseCaseEjb();
        ejb.update(this.usecase);
        result = "list_usecases";
        return result;
    }
    
    public String create(){
        String result;
        UseCaseEjb ejb = new UseCaseEjb();
        ejb.create(this.usecase);
        result = "list_usecases";
        return result;
        
    }
    
    public String delete(){
        String view = "list_useCases";
        UseCaseEjb ejb = new UseCaseEjb();
        ejb.delete(usecase);
        return view;
    }
  
}
