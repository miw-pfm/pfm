/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eui.miw.pfm.controllers.beans;

import eui.miw.pfm.controllers.ejb.ListUseCaseEjb;
import eui.miw.pfm.controllers.ejb.UseCaseEjb;
import eui.miw.pfm.models.entities.ProjectEntity;
import eui.miw.pfm.models.entities.UseCaseEntity;
import eui.miw.pfm.util.SessionMap;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author Roberto Amor
 * @author Clemencio Morales
 * @author Manuel Rodríguez
 */

@Named
@RequestScoped
public class UseCaseBean extends Bean implements Serializable {

    private static final long serialVersionUID = 1L;

    private UseCaseEntity usecase = new UseCaseEntity();
    private ProjectEntity project;
    private SessionMap sessionMap;
    private static final Logger LOG = Logger.getLogger(ConfProjectBean.class.getName());//NOPMD

    public UseCaseBean() {
        super();
        project = new ProjectEntity();
        usecase = new UseCaseEntity();
        this.sessionMap = new SessionMap();

        try {
            this.project = ((ProjectEntity) this.sessionMap.get("project"));
        } catch (Exception e) {
            LOG.warning("No session exist");
        }
    }

    public UseCaseBean(final UseCaseEntity usecase) {
        this.usecase = usecase;
    }

    public UseCaseEntity getUsecase() {
        return usecase;
    }

    public void setUsecase(final UseCaseEntity usecase) {
        this.usecase = usecase;
        LOG.info(this.usecase.toString());
    }

    public String update() {
        UseCaseEjb ejb = new UseCaseEjb();
        this.usecase.setProject(project);
        LOG.info(this.usecase.toString());

        ejb.update(this.usecase);                  
        return "useCasesList";
    }

    public String create() {
        this.usecase.setProject(this.project);
        UseCaseEjb ejb = new UseCaseEjb();
        ejb.create(this.usecase);
        final FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage("form", new FacesMessage("Use Case Created","Use Case Created"));
        return null;
    }

    public String delete(final UseCaseEntity useCaseEntity) {
        UseCaseEjb ejb = new UseCaseEjb();
        this.usecase.setProject(project);
         this.usecase = useCaseEntity;
        LOG.info(this.usecase.toString());       
        ejb.delete(usecase);
        final FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage("form_edit", new FacesMessage("Use Case Deleted","Use Case Deleted"));        
        return null;
    }

    public List<UseCaseEntity> getUseCases() {
        ListUseCaseEjb listejb = new ListUseCaseEjb();
        return listejb.obtainUseCase(this.project);
    }

    public String editUseCase(final UseCaseEntity useCaseEntity) {
        this.usecase = useCaseEntity;
        return "useCasesedit";
    }
}
