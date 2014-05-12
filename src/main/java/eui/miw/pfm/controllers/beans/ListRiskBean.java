/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eui.miw.pfm.controllers.beans;

import eui.miw.pfm.controllers.ejb.ListRiskEjb;
import eui.miw.pfm.models.entities.ProjectEntity;
import eui.miw.pfm.models.entities.RiskEntity;
import eui.miw.pfm.models.entities.UserEntity;
import eui.miw.pfm.util.LazyRiskDataModel;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.primefaces.model.LazyDataModel;

/**
 *
 * @author Fred Peña
 *
 */
@Named
@SessionScoped
public class ListRiskBean extends Bean implements Serializable {

    private static final long serialVersionUID = 1L;
    private transient LazyDataModel<RiskEntity> lazyModel;
    private static final Logger LOGGER = Logger.getLogger(ListRiskBean.class.getName());//NOPMD
    private RiskEntity selectedRisk;
    private transient List<RiskEntity> lRisk;
    private UserEntity user;
    private ProjectEntity project;

    public ListRiskBean() {
        super();
        try {
            this.user = ((UserEntity) sessionMap.get("userlogin"));
            this.project = ((ProjectEntity) sessionMap.get("project"));
        } catch (Exception e) {
            LOGGER.warning("No session exist");
        }

        this.lRisk = new ListRiskEjb().findRisks(this.project);
        this.lazyModel = new LazyRiskDataModel(this.lRisk);
    }

    public RiskEntity getSelectedRisk() {
        return selectedRisk;
    }

    public void setSelectedRisk(final RiskEntity selectedRisk) {
        this.selectedRisk = selectedRisk;
    }

    public List<RiskEntity> getlRisk() {
        return lRisk;
    }

    public void setlRisk(final List<RiskEntity> lRisk) {
        this.lRisk = lRisk;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(final UserEntity user) {
        this.user = user;
    }

    public ProjectEntity getProject() {
        return project;
    }

    public void setProject(final ProjectEntity project) {
        this.project = project;
    }

    public LazyDataModel<RiskEntity> getLazyModel() {
        return lazyModel;
    }

    public void reload() {
        this.lRisk = new ListRiskEjb().findRisks(this.project);
        this.lazyModel = new LazyRiskDataModel(this.lRisk);
    }

}
